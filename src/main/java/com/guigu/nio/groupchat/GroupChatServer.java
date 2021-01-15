package src.main.java.com.guigu.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

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


                         }
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

    public static void main(String[] args) {

    }
}
