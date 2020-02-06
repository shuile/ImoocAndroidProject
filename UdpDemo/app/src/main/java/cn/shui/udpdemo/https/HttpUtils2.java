package cn.shui.udpdemo.https;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by shui on 2020-01-29
 */
public class HttpUtils2 {

    public interface HttpListener {
        void onSuccess(String content);

        void onFail(Exception e);
    }

    private static Handler mUIHandler = new Handler(Looper.getMainLooper());

    public static void doGet(final Context context, final String urlStr, final HttpListener listener) {
        new Thread() {
            @Override
            public void run() {
                InputStream is = null;
                try {
                    URL url = new URL(urlStr);
                    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

                    X509Certificate serverCert = getCert(context);

                    String keyStoreType = KeyStore.getDefaultType();
                    KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                    keyStore.load(null);
                    keyStore.setCertificateEntry("srca.cer", serverCert);

                    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    trustManagerFactory.init(keyStore);
                    TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

                    SSLContext sslContext = SSLContext.getInstance("TLS");

                    sslContext.init(null, trustManagers, new SecureRandom());
                    conn.setSSLSocketFactory(sslContext.getSocketFactory());

                    conn.setHostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            HostnameVerifier defaultHostnameVerifier =
                                    HttpsURLConnection.getDefaultHostnameVerifier();
                            return defaultHostnameVerifier.verify("kyfw.12306.cn", session);
                        }
                    });

                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(15000);
                    conn.setConnectTimeout(15000);
                    conn.connect();

//                    is = conn.getInputStream();
//                    byte[] buf = new byte[2048];
//                    int len = -1;
//                    final StringBuilder content = new StringBuilder();
//                    while ((len = is.read(buf)) != -1) {
//                        content.append(new String(buf, 0, len));
//                    }


                    // 解决读取中文乱码问题
                    is = conn.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    char[] buff = new char[2048];
                    int leng = -1;
                    final StringBuilder sb = new StringBuilder();
                    while ((leng = isr.read(buff)) != -1) {
                        sb.append(new String(buff, 0, leng));
                    }


                    mUIHandler.post(new Runnable() {
                        @Override
                        public void run() {
//                            listener.onSuccess(content.toString());
                            listener.onSuccess(sb.toString());
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onFail(e);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                } catch (KeyStoreException e) {
                    e.printStackTrace();
                } catch (CertificateException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {
                        // ignore
                    }
                }
            }
        }.start();
    }

    private static X509Certificate getCert(Context context) {
        try {
            // 当前12306已抛弃自签名证书
            InputStream is = context.getAssets().open("srca.cer");
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            return (X509Certificate) certificateFactory.generateCertificate(is);
        } catch (IOException | CertificateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
