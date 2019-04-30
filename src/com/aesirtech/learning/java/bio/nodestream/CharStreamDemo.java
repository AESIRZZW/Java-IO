package com.aesirtech.learning.java.bio.nodestream;

import java.io.*;

/**
 * @ProjectName: Java IO
 * @Description: A Simple Demonstration of Char Stream Reading & Writing
 * @Author: Aesir
 * @Date: 2019/4/15 22:07
 */
public class CharStreamDemo {
    public static void main(String[] args) {
        File sourceFile = new File("resource/The Road Not Taken.txt");
        File targetFile = new File("resource/The Road Not Taken (BIO).txt");
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(sourceFile);
            fileWriter = new FileWriter(targetFile);

            char[] cbuf = new char[10];
            // Record the number of chars read into cbuf each time.
            int len;
            while ((len = fileReader.read(cbuf)) != -1) {
                fileWriter.write(cbuf, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /*
             * Write separately to ensure that each stream is closed
             * even if the other stream has an exception.
             */
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
