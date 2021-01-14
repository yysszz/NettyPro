package com.guigu.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ScattingAndGathringTest {
    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8888);
        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(6);


        SocketChannel socketChannel = serverSocketChannel.accept();

        int messageLength = 11;
        while (true) {
            int byteRead = 0;

            while (byteRead < messageLength){
                long l = socketChannel.read(byteBuffers);
                byteRead += l;
                Arrays.asList(byteBuffers).forEach(buffer -> {
                    System.out.println("position = " + buffer.position() + ",limit = " + buffer.limit());
                });
            }

            Arrays.asList(byteBuffers).forEach(byteBuffer -> {
                byteBuffer.flip();
            });
            int writeByte = 0;
            while (writeByte < messageLength){
                long l = socketChannel.write(byteBuffers);
                writeByte += l;
                System.out.println();
            }
            Arrays.asList(byteBuffers).forEach(byteBuffer -> {
                byteBuffer.clear();
            });
            System.out.println("byteRead = " + byteRead + " byteWrite = " + writeByte);


        }

    }
}
