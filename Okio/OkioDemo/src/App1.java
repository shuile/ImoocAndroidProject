import okio.Buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class App1 {
    public static void main(String[] args) throws IOException {
        Buffer buffer = new Buffer();
//        System.out.println(buffer);
//
//        buffer.writeUtf8("abc");
//        System.out.println(buffer);
//
//        while (!buffer.exhausted()) {
//            System.out.println(buffer.readUtf8(1));
//        }

//        for (int i = 0; i < 10; i++) {
//            buffer.writeInt(i);
//        }
//        while (!buffer.exhausted()) {
//            System.out.println(buffer.readInt());
//        }

        buffer.readFrom(new FileInputStream("in.png"));
        System.out.println(buffer);
        buffer.writeTo(new FileOutputStream("out.png"));
        System.out.println(buffer);
    }
}
