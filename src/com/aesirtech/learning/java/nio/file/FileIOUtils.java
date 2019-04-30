package com.aesirtech.learning.java.nio.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ProjectName: Java IO
 * @Description:
 * @Author: Aesir
 * @Date: 2019/4/30 22:38
 */
public class FileIOUtils {
    public void write(String filePathName, byte[] bytes) throws IOException {
        File file = new File(filePathName);
        FileOutputStream outputStream = new FileOutputStream(file);
        FileChannel fileChannel = outputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        /*
         * The pointer must be reset to the initial position,
         * otherwise channel.write() is empty,
         * because channel.write() starts at the pointer position.
         */
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        /*
         * Channel will be closed automatically when Stream is closed,
         * because Channel is obtained from the Stream.
         */
        outputStream.close();
    }

    public byte[] read(String filePathName) throws IOException {
        File file = new File(filePathName);
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel fileChannel = inputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        fileChannel.read(byteBuffer);
        inputStream.close();
        return byteBuffer.array();
    }

    public void copy(String sourceFilePathName, String targetFilePathName) throws IOException {
        File sourceFile = new File(sourceFilePathName);
        File targetFile = new File(targetFilePathName);
        FileInputStream inputStream = new FileInputStream(sourceFile);
        FileOutputStream outputStream = new FileOutputStream(targetFile);
        FileChannel sourceChannel = inputStream.getChannel();
        FileChannel targetChannel = outputStream.getChannel();
        // More efficient for large files.
        targetChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        sourceChannel.close();
        targetChannel.close();
    }
}
