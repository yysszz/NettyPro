package com.nio.groupchat;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel listenChannel;
    private final static int PORT = 6667;

    public GroupChatServer() {

        try {
            selector = Selector.open();

            listenChannel = ServerSocketChannel.open();

            listenChannel.bind(new InetSocketAddress(PORT));

            listenChannel.configureBlocking(false);

            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        }catch (IOException e){

        }
    }

    //监听
    public void listen(){
         try {
             while (true) {
                 int count = selector.select(2000);

                 if (count > 0) {

                     Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                     while (iterator.hasNext()){
                         SelectionKey key = iterator.next();

                         if (key.isAcceptable()) {
                             SocketChannel sc = listenChannel.accept();
                             sc.configureBlocking(false);
                             sc.register(selector,SelectionKey.OP_READ);
                             System.out.println(sc.getRemoteAddress() + " 上线 ");
                         }
                         if (key.isReadable()) {
                            //发生读取事件
                             SelectableChannel channel = key.channel();
                             //专门写读取方法
                         }
                         //把当前的key删除 防止重复处理
                         iterator.remove();
                     }
                 }else {
                     System.out.println("等待中");
                 }

             }
         }catch (Exception e){
                e.printStackTrace();
         }finally {

         }
    }

    private void readData(SelectionKey selectionKey){
        SocketChannel channel = null;
        try{
            channel = (SocketChannel) selectionKey.channel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int count = channel.read(buffer);

            if (count > 0) {
                String msg = new String(buffer.array());

                System.out.println("from 客户端:" + msg);

                //向其他客户端转发(除了自己)

            }
        }catch (Exception e){

        }
    }

    private void sendInfoToOtherClient(String msg,SocketChannel self){
        System.out.println("服务器转发消息中。。。。");

        //遍历所有注册到selector上的SocketChannel 排除self

        for (SelectionKey key : selector.keys()) {

            Channel targetChannel = key.channel();

            if(targetChannel instanceof SocketChannel && targetChannel != self){

                //转型
                SocketChannel dest = (SocketChannel) targetChannel;
                //将msg 存储到buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
            }
        }
    }

    public static void main(String[] args) {
        byte[] bytes = new byte[1024];
        String a = "aaaa";
        byte s = 1;
    }
}
