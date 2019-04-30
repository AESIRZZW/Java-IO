package com.aesirtech.learning.java.bio.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: Java IO
 * @Description:
 * @Author: Aesir
 * @Date: 2019/4/29 23:24
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        /**
         * Create ServerSocket object instance.
         */
        ServerSocket serverSocket = new ServerSocket(80);
        Socket socket = new Socket();
        byte[] bytes = new byte[1024];
        InputStream inputStream = null;
        OutputStream outputStream = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String clientIp = "";
        while (!"bye".equals(new String(bytes))) {
            // Monitor client connection (blocking).
            socket = serverSocket.accept();
            // Get InputStream from the connection to receive request (blocking).
            inputStream = socket.getInputStream();
            inputStream.read(bytes);
            clientIp = socket.getInetAddress().getHostAddress();
            /*
             * Byte [] does not override Object's toString method,
             * then it only returns the class signature,
             * so bytes.toString() and new String(bytes) have different results,
             * which latter is correct.
             */
            System.out.println("[" + clientIp + " " + simpleDateFormat.format(new Date()) + "]: " + new String(bytes).trim());
            // Get OutputStream from the connection to send response.
            outputStream = socket.getOutputStream();
            outputStream.write(("Hello " + clientIp + "!\n \n" + simpleDateFormat.format(new Date())).getBytes());
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
