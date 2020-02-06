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
public class UdpClient {

    private String mServerIp = "192.168.31.239";
    private InetAddress mServerAddress;
    private int mServerPort = 7777;
    private DatagramSocket mSocket;
    private Scanner mScanner;

    public UdpClient() {
        try {
            mServerAddress = InetAddress.getByName(mServerIp);
            mSocket = new DatagramSocket();
            mScanner = new Scanner(System.in);
            mScanner.useDelimiter("\n");
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                String clientMsg = mScanner.next();
                byte[] clientMsgBytes = clientMsg.getBytes();
                DatagramPacket clientPacket = new DatagramPacket(clientMsgBytes, clientMsgBytes.length,
                        mServerAddress, mServerPort);
                mSocket.send(clientPacket);

                byte[] buf = new byte[1024];
                DatagramPacket serverMsgPacket = new DatagramPacket(buf, buf.length);
                mSocket.receive(serverMsgPacket);

                InetAddress address = serverMsgPacket.getAddress();
                int port = serverMsgPacket.getPort();
                String serverMsg = new String(serverMsgPacket.getData(), 0, serverMsgPacket.getLength());

                System.out.println("start: received address is " + address + "  port is " + port + "  serverMsg is " + serverMsg);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new UdpClient().start();
    }
}
