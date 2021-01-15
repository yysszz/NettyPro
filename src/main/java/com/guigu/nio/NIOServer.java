package src.main.java.com.guigu.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        Selector selector = Selector.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        serverSocketChannel.configureBlocking(false);
        //连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            //等待连接事件发生 等待时间 返回有事件发生个数
            if (selector.select(3000) == 0) {
                System.out.println("服务器等待了3秒，无连接");
                continue;
            }

            //返回不为0
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //根据事件不同分别处理 ON_ACCEPT  新客户端连接
                if(key.isAcceptable()){
                    //该客户端生成一个SocketChannel
                    //accept 方法本身阻塞 isAcceptable表示已经有连接 连接事件堆积 accept()不再阻塞
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到selector,关注事件为OP_READ,同时给channel关联一个buffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println(selector.keys());
                }
                //发生读事件
                if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    //上边连接事件 绑定的 byteBuffer
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    socketChannel.read(byteBuffer);
                    System.out.println("from 客户端：" + new String(byteBuffer.array()));
                }
                iterator.remove();
            }

        }
    }
}
