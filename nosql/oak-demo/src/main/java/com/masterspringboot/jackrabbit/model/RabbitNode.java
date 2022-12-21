package com.masterspringboot.jackrabbit.model;

public class RabbitNode {
    String parentId;
    String fileName;
    String fileId;
    String mimeType;

    public RabbitNode(String parentId, String fileName, String mimeType, String fileId) {
        this.parentId = parentId;
        this.fileName = fileName;
        this.mimeType = mimeType;
        this.fileId  = fileId;
    }

    public RabbitNode() {
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
