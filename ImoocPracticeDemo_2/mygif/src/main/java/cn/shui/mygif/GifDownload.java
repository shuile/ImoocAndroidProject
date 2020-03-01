package cn.shui.mygif;

import java.io.File;

/**
 * Created by shui on 2020-02-17
 */
public class GifDownload extends Thread {

    private String mUrl;

    public GifDownload(String url) {
        mUrl = url;
    }

    @Override
    public void run() {
        File file = new File("data/data/cn.shui.mygif/cache/", "test.gif");

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }
    }
}
