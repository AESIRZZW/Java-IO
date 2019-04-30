package com.aesirtech.learning.java.nio.file;

import java.io.IOException;

/**
 * @ProjectName: Java IO
 * @Description:
 * @Author: Aesir
 * @Date: 2019/4/30 23:15
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FileIOUtils fileIOUtils = new FileIOUtils();
        byte[] textBytes = fileIOUtils.read("resource/The Road Not Taken.txt");
        fileIOUtils.write("resource/The Road Not Taken (NIO).txt", textBytes);
        byte[] imgBytes = fileIOUtils.read("resource/Los Santos.jpg");
        fileIOUtils.write("resource/Los Santos (NIO).jpg", imgBytes);
        fileIOUtils.copy("resource/Los Santos.jpg", "resource/Los Santos (NIO).bmp");
    }
}
