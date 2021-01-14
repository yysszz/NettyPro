package com.guigu.nio;

import java.nio.IntBuffer;

public class BaiscBuffer {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5*2);
        for (int i = 0; i < intBuffer.capacity()/2; i++) {
            intBuffer.put(i*2);
        }
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
