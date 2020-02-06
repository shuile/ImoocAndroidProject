package cn.shui.udpdemo.tcp.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by shui on 2020-01-19
 */
public class TcpClient {

    private Scanner mScanner;

    public TcpClient() {
        mScanner = new Scanner(System.in);
        mScanner.useDelimiter("\n");
    }

    public void start() {
        try {
            Socket socket = new Socket("192.168.31.239", 9090);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            final BufferedReader br = new BufferedReader(new InputStreamReader(is));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

            new Thread() {
                @Override
                public void run() {
                    try {
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            while (true) {
                String msg = mScanner.next();
                bw.write(msg);
                bw.newLine();
                bw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TcpClient().start();
    }
}
