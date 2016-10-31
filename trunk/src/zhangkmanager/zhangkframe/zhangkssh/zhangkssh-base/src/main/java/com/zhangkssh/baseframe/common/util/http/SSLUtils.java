package com.zhangkssh.baseframe.common.util.http;

import org.apache.log4j.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class SSLUtils {
	private static final Logger s_logger = Logger.getLogger(SSLUtils.class);
	public static void trustAllHttpsCertificates() throws Exception {
		javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
		javax.net.ssl.TrustManager tm = new MiTM();
		trustAllCerts[0] = tm;
		javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
				.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
				.getSocketFactory());
	}

	public static HostnameVerifier getHostnameVerifier() {
		return new HostnameVerifier() {
			public boolean verify(String urlHostName, SSLSession session) {
				s_logger.info("Warning: URL Host: " + urlHostName
						+ " vs. " + session.getPeerHost());
				return true;
			}
		};
	}

}
