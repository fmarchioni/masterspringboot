package com.mastertheboss.camel;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class EnrichBean {

    public Document enrich(Document doc) {

        try {
            System.out.println("Before =============");
            printDocument(doc, System.out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Modify SOAP Request
      //  NodeList nodes = doc.getElementsByTagName("givenName");

      //  doc.renameNode(nodes.item(0), null, "firstName");

        try {
            System.out.println("After =============");
            printDocument(doc, System.out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return doc;
    }

    public static void printDocument(Document doc, OutputStream out) throws IOException, TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        transformer.transform(new DOMSource(doc),
                new StreamResult(new OutputStreamWriter(out, "UTF-8")));
    }

}