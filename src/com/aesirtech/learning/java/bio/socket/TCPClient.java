package com.aesirtech.learning.java.bio.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ProjectName: Java IO
 * @Description:
 * @Author: Aesir
 * @Date: 2019/4/29 23:45
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        while (true) {
            Socket socket = new Socket("127.0.0.1", 80);
            OutputStream outputStream = socket.getOutputStream();
            System.out.println("Please enter your request:");
            Scanner scanner = new Scanner(System.in);
            String request = scanner.nextLine();
            outputStream.write(request.getBytes());
            outputStream.flush();
            // Get InputStream from the connection to receive response (blocking).
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            inputStream.read(bytes);
            System.out.println(new String(bytes).trim());
            /*
             * Do not close IO stream prematurely, otherwise it will cause Socket to close.
             */
            inputStream.close();
            outputStream.close();
            socket.close();
        }
    }
}
