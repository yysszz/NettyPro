package com.guigu.bio;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {
    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            ServerSocket serverSocket = new ServerSocket(8088);
            System.out.println("服务器启动");
            while (true){
                final Socket socket = serverSocket.accept();
                System.out.println("客户端连接");
                threadPool.execute(new Runnable() {
                    public void run() {
                        System.out.println("新建线程" + Thread.currentThread().getName());
                        handle(socket);
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void handle(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            int i = 0;
            while (len != -1){
                len = inputStream.read(bytes);
                System.out.println(Thread.currentThread().getName() + "第" + i + "次read");
                i++;
                System.out.println(new String(bytes,0,len));
            }
        }catch (Exception e){
            try {
                socket.close();
            }catch (Exception e1){
                e.printStackTrace();
            }
        }
    }
}
