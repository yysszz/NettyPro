package com.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class NIOFileChannel04 {
    public static void main(String[] args) throws  Exception{
        File file1 = new File("d:\\1.jpg");
        FileInputStream fileInputStream = new FileInputStream(file1);

        File file2 = new File("d:\\2.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(file2);

        FileChannel fileInputStreamChannel = fileInputStream.getChannel();
        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();

        fileOutputStreamChannel.transferFrom(fileInputStreamChannel,0,fileInputStreamChannel.size());

    }
}
