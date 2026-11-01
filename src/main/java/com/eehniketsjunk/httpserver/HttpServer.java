package com.eehniketsjunk.httpserver;

import com.eehniketsjunk.httpserver.config.Configuration;
import com.eehniketsjunk.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static void main(String[] args){

        System.out.println("Starting server...");

        ConfigurationManager.getInstance().LoadConfigurationFile("src/main/resources/http.json");

        Configuration conf = ConfigurationManager.getInstance().getCurrentConfig();

        System.out.println(conf.getPort());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket= serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            //todo read

            String html = "<html><head><title>My Java HTTP server</title></head><body><h1>This page was served using my java HTTP server.</h1></body></html>";

            String CRLF = "\n\r";

            String response = "HTTP/1.1 200 OK" + "CRLF"//Status Line: HTTP_Version Response_Code Response_Message
                                + "Content-Length: " + html.getBytes().length + CRLF //Header
                                + CRLF
                                + html
                                + CRLF + CRLF;
            outputStream.write(response.getBytes());
            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
