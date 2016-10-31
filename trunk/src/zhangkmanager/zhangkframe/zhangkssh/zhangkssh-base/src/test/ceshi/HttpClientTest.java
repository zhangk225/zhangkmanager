import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import com.zhangk.baseframe.common.util.http.SSLUtils;

public class HttpClientTest {
	/** * @param args * @throws InterruptedException * @throws IOException */
	public static void main(String[] args) throws InterruptedException,
			IOException {
		URL url = null;
		HttpsURLConnection httpCon = null;
		try {
			SSLUtils.trustAllHttpsCertificates();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HostnameVerifier hv = SSLUtils.getHostnameVerifier();
		HttpsURLConnection.setDefaultHostnameVerifier(hv);
		url = new URL("https://192.168.10.3:8443/api/login");
		httpCon = (HttpsURLConnection) url.openConnection();
		httpCon.setRequestProperty("Content-Type",
                "application/json");
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("POST");
		HttpsURLConnection.setFollowRedirects(true);
		httpCon.setConnectTimeout(5000);
		String message = "{\"id\":\"admin\",\"password\":\"sysadmin\"}"; // JSON工具类，转换对象为JSON格式//
		OutputStream output = httpCon.getOutputStream();
		output.write(message.getBytes("UTF-8"));
		output.flush();
		output.close();
		System.out.println(httpCon.getContentType());
		InputStream in = httpCon.getInputStream();
		byte[] readByte = new byte[1024]; // 读取返回的内容
		int readCount = in.read(readByte, 0, 1024);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while (readCount != -1) {
			baos.write(readByte, 0, readCount);
			readCount = in.read(readByte, 0, 1024);
		}
		String responseBody = new String(baos.toByteArray(), "UTF-8");
		System.out.println(responseBody);
		baos.close();
		if (httpCon != null)
			httpCon.disconnect();
	}

}