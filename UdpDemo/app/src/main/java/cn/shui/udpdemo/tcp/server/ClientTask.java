package cn.shui.udpdemo.tcp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by shui on 2020-01-14
 */
public class ClientTask extends Thread implements MsgPool.MsgComingListener {

    private Socket mSocket;
    private InputStream mIs;
    private OutputStream mOs;

    public ClientTask(Socket mSocket) {
        this.mSocket = mSocket;
        try {
            mIs = mSocket.getInputStream();
            mOs = mSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(mIs));
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println("read =" + line);
                // 转发消息至其他Socket
                MsgPool.getInstance().sendMsg(mSocket.getPort() + " : " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMsgComing(String msg) {
        try {
            mOs.write(msg.getBytes());
            mOs.write("\n".getBytes());
            mOs.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
