package cn.shui.udpdemo.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by shui on 2020-01-11
 */
public class UdpServer {

    private InetAddress mInetAddress;
    private int mPort = 7777;
    private DatagramSocket mSocket;

    private Scanner mScanner;

    public UdpServer() {
        try {
            mInetAddress = InetAddress.getLocalHost();
            mSocket = new DatagramSocket(mPort, mInetAddress);

            mScanner = new Scanner(System.in);
            mScanner.useDelimiter("\n");
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                byte[] buf = new byte[1024];
                DatagramPacket receivedPacket = new DatagramPacket(buf, buf.length);
                mSocket.receive(receivedPacket);

                InetAddress address = receivedPacket.getAddress();
                int port = receivedPacket.getPort();
                String clientMsg = new String(receivedPacket.getData(), 0, receivedPacket.getLength());

                System.out.println("start: received address is " + address + "  port is " + port
                        + "  clientMsg is " + clientMsg);

                String returnedMsg = mScanner.next();
                System.out.println("start: send returnedMsg is " + returnedMsg);
                byte[] returnedMsgBytes = returnedMsg.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(returnedMsgBytes,
                        returnedMsgBytes.length, receivedPacket.getSocketAddress());
                mSocket.send(sendPacket);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new UdpServer().start();
    }
}
