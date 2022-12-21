package com.masterspringboot.jackrabbit.service;

import com.masterspringboot.jackrabbit.model.FileResponse;
import com.masterspringboot.jackrabbit.model.RabbitNode;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.jcr.*;
import javax.jcr.version.Version;
import javax.jcr.version.VersionHistory;
import javax.jcr.version.VersionIterator;
import javax.jcr.version.VersionManager;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JackRabbitService {
    Logger logger = LoggerFactory.getLogger(JackRabbitService.class);

    public Node createNode(Session session, RabbitNode input, MultipartFile uploadFile) {
        Node node = null;
        File file = new File(uploadFile.getOriginalFilename());

        try {
            Node parentNode = session.getNodeByIdentifier(input.getParentId());
                if (parentNode != null && parentNode.hasNode(file.getName())) {
                    logger.error(file.getName() + " node already exists!");
                    return editNode(session, input, uploadFile);
                } else {
                    try {
                        node = parentNode.addNode(file.getName(), "nt:file");
                        node.addMixin("mix:versionable");
                        node.addMixin("mix:referenceable");

                        Node content = node.addNode("jcr:content", "nt:resource");

                        InputStream inputStream = uploadFile.getInputStream();
                        Binary binary = session.getValueFactory().createBinary(inputStream);

                        content.setProperty("jcr:data", binary);
                        content.setProperty("jcr:mimeType", input.getMimeType());

                        Date now = new Date();
                        now.toInstant().toString();
                        content.setProperty("jcr:lastModified", now.toInstant().toString());

                        inputStream.close();
                        session.save();

                        VersionManager vm = session.getWorkspace().getVersionManager();
                        vm.checkin(node.getPath());

                        logger.error("File saved!");
                    } catch (Exception e) {
                        logger.error("Exception caught!");
                        e.printStackTrace();
                    }
            }
        } catch (Exception e) {
            logger.error("Exception caught!");
            e.printStackTrace();
        }
        return node;
    }

    public boolean deleteNode(Session session, RabbitNode input) {
        try {
            Node node = session.getNodeByIdentifier(input.getFileId());
            if (node != null) {
                node.remove();
                session.save();
                return true;
            }
        } catch (Exception e) {
            logger.error("Exception caught!");
            e.printStackTrace();
        }

        return false;
    }

    public List<String> getVersionHistory(Session session, RabbitNode input) {
        List<String> versions = new ArrayList<>();
        try {
            VersionManager vm = session.getWorkspace().getVersionManager();

            Node node = session.getNodeByIdentifier(input.getFileId());
            String filePath = node.getPath();
            if (session.itemExists(filePath)) {
                VersionHistory versionHistory = vm.getVersionHistory(filePath);
                Version currentVersion = vm.getBaseVersion(filePath);
                logger.error("Current version: " + currentVersion.getName());

                VersionIterator versionIterator = versionHistory.getAllVersions();
                while (versionIterator.hasNext()) {
                    versions.add(((Version) versionIterator.next()).getName());
                }
            }
        } catch (Exception e) {
            logger.error("Exception caught!");
            e.printStackTrace();
        }
        return versions;
    }

    public Node editNode(Session session, RabbitNode input, MultipartFile uploadFile) {
        File file = new File(uploadFile.getOriginalFilename());
        Node returnNode = null;

        try {
            Node parentNode = session.getNodeByIdentifier(input.getParentId());
            if (parentNode != null && parentNode.hasNode(file.getName())) {
                VersionManager vm = session.getWorkspace().getVersionManager();

                Node fileNode = parentNode.getNode(file.getName());
                vm.checkout(fileNode.getPath());

                Node content = fileNode.getNode("jcr:content");

                InputStream is = uploadFile.getInputStream();
                Binary binary = session.getValueFactory().createBinary(is);
                content.setProperty("jcr:data", binary);

                session.save();
                is.close();

                vm.checkin(fileNode.getPath());
                returnNode = fileNode;
            }
        } catch(Exception e) {
                logger.error("Exception caught");
                e.printStackTrace();
        }

        return returnNode;
    }

    public Node createFolderNode(Session session, RabbitNode input) {
        Node node = null;
        Node parentNode = null;

        try {
            parentNode = session.getNodeByIdentifier(input.getParentId());
            if (session.nodeExists(parentNode.getPath())) {
                if (!parentNode.hasNode(input.getFileName())) {
                    node = parentNode.addNode(input.getFileName(), "nt:folder");
                    node.addMixin("mix:referenceable");
                    session.save();
                    System.out.println("Folder created: "+input.getFileName());
                }
            } else {
                logger.error("Node already exists!");
            }
        } catch (Exception e) {
            logger.error("Exception caught!");
            e.printStackTrace();
        }

        return node;
    }

    public FileResponse getNode(Session session, String versionId, RabbitNode input) {
        FileResponse response = new FileResponse();

        try {
            Node file = session.getNodeByIdentifier(input.getFileId());
            if (file != null) {
                VersionManager vm = session.getWorkspace().getVersionManager();
                VersionHistory history = vm.getVersionHistory(file.getPath());
                for (VersionIterator it = history.getAllVersions(); it.hasNext(); ) {
                    Version version = (Version) it.next();
                    if (versionId.equals(version.getName())) {
                        file = version.getFrozenNode();
                        break;
                    }
                }

                logger.error("Node retrieved: " + file.getPath());

                Node fileContent = file.getNode("jcr:content");
                Binary bin = fileContent.getProperty("jcr:data").getBinary();
                InputStream stream = bin.getStream();
                byte[] bytes = IOUtils.toByteArray(stream);
                bin.dispose();
                stream.close();

                response.setBytes(bytes);
              //  response.setContentType(fileContent.getProperty("jcr:mimeType").getString());
                response.setContentType(input.getMimeType());
                return response;

            } else {
                logger.error("Node does not exist!");
            }

        } catch (Exception e) {
            logger.error("Exception caught!");
            e.printStackTrace();
        }

        return response;
    }
}
