package cn.shui.recordvoicedemo;

import java.io.File;

/**
 * Created by shui on 2020/3/21
 */
public class Msg {
    // 内容、设计到语音、发送/接收的标志
    private String txt;
    private File file;
    private int flag; // 1:本人，-1:对方

    public Msg(String txt, File file, int flag) {
        this.txt = txt;
        this.file = file;
        this.flag = flag;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "txt='" + txt + '\'' +
                ", file=" + file.getAbsolutePath() +
                ", flag=" + flag +
                '}';
    }
}
