package cn.shui.udpdemo.https;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * Created by shui on 2020-01-29
 */
public class MyX509TrustManager implements X509TrustManager {

    private X509Certificate mServerCert;

    public MyX509TrustManager(X509Certificate mServerCert) {
        this.mServerCert = mServerCert;
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        for (X509Certificate certificate : chain) {
            // 证书是否过期以及合法性的校验
            certificate.checkValidity();
            try {
                certificate.verify(mServerCert.getPublicKey());
            } catch (InvalidKeyException | SignatureException | NoSuchProviderException | NoSuchAlgorithmException e) {
                throw new CertificateException(e);
            }
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
