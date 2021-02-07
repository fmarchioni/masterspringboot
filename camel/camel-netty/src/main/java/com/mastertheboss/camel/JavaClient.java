package com.mastertheboss.camel;

import java.io.*;
import java.net.*;

public class JavaClient {

    public static void main(String argv[]) throws Exception {

        Socket clientSocket = new Socket("localhost", 8001);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        outToServer.writeBytes("Hello Camel\n");

        clientSocket.close();
    }
}