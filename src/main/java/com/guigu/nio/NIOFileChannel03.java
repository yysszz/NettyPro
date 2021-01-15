package src.main.java.com.guigu.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel03 {
    public static void main(String[] args) throws Exception{
        File file = new File("1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel1 = fileInputStream.getChannel();

        File file1 = new File("2.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        FileChannel fileChannel2 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) {
            byteBuffer.clear();
            int read = fileChannel1.read(byteBuffer);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            fileChannel2.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
