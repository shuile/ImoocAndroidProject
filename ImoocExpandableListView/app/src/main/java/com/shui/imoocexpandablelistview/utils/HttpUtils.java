package com.shui.imoocexpandablelistview.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {
    public static String doGet(String urlStr) {
        HttpURLConnection con = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL(urlStr);
            con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(5 * 1000);
            con.setConnectTimeout(5 * 1000);
            con.setRequestMethod("GET");
            //success
            if (con.getResponseCode() == 200) {
                is = con.getInputStream();
                baos = new ByteArrayOutputStream();
                int len = -1;
                byte[] buf = new byte[512];
                while ((len = is.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                baos.flush();
                return baos.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (con != null) {
                con.disconnect();
            }
        }
        return null;
    }
}
