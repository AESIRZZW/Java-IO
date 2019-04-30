package com.aesirtech.learning.java.nio.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @ProjectName: Java IO
 * @Description:
 * @Author: Aesir
 * @Date: 2019/5/1 0:25
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 80);
        if (!socketChannel.connect(address)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("While connecting to the server, " +
                        "we can continue to do other things without being blocked.");
            }
        }
        Scanner scanner = new Scanner(System.in);
        String request = scanner.nextLine();
        ByteBuffer byteBuffer = ByteBuffer.wrap(request.getBytes());
        socketChannel.write(byteBuffer);
        socketChannel.close();
    }
}
