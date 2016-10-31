package com.zhangkssh.baseframe.common.util.http;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA. User: Derui Date: 13-12-9 Time: 下午6:00 To change
 * this template use File | Settings | File Templates.
 */
public class HttpUtil {
	private static final Logger s_logger = Logger.getLogger(HttpUtil.class);

	public static final String POST = "POST";

	public static final String GET = "GET";

	public static final String PUT = "PUT";

	public static final String DELETE = "DELETE";

	/**
	 * 通过SSL发送https请求，并获得返回
	 * 
	 * @param urlStr
	 *            - url字符串
	 * @param type
	 *            - 编码类型
	 * @param requestMethod
	 *            - http请求类型]
	 * @param json
	 *            - json信息
	 * @param cookie
	 *            - cookie字符串
	 * @return HttpResponse，包含response
	 *         code，内容和cookie信息，注意如果未能得到信息，code将返回-1，内容和cookie将返回空字符串
	 */
	public HttpResponse sendSSLWithJson(String urlStr, String type,
			String requestMethod, String json, String cookie) {
		// https连接
		HttpsURLConnection httpsConn = getHttpsConnection(urlStr,
				requestMethod, json, cookie);
		if (httpsConn == null) {
			return new HttpResponse(-1, "", "");
		}
		String contents = getContentsFromHttpsConn(httpsConn, type);
		String cookies = getCookiesFromHttpsConn(httpsConn);
		int responseCode = getResponseCodeFromHttpsConn(httpsConn);
		if (httpsConn != null) {
			httpsConn.disconnect();
		}
		return new HttpResponse(responseCode, contents, cookies);
	}

	private String getContentsFromHttpsConn(
			HttpsURLConnection httpsConn, String type) {
		if (httpsConn == null) {
			return "";
		}
		InputStream in = null;
		try {
			in = httpsConn.getInputStream();
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
			byte[] buff = new byte[100]; // buff用于存放循环读取的临时数据
			int rc;
			while ((rc = in.read(buff, 0, 100)) > 0) {
				swapStream.write(buff, 0, rc);
			}
			byte[] hbyte = swapStream.toByteArray(); // in_b为转换之后的结果

			if (httpsConn.getContentType() != null
					&& httpsConn.getContentType().split("=").length == 2) {
				type = httpsConn.getContentType().split("=")[1];
			}
			return new String(hbyte, type);
		} catch (FileNotFoundException e) {
			s_logger.error("Error getting response contents.404 code", e);
		} catch (IOException e) {
			s_logger.error("Error getting response contents.401", e);
		} catch (Exception e) {
			s_logger.error("Error getting response contents.", e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				httpsConn.disconnect();
			} catch (Exception ex) {
			}
		}
		return "";
	}

	private String getCookiesFromHttpsConn(HttpsURLConnection httpsConn) {
		String cookies = "";

		if (httpsConn != null) {
			String key;
			for (int i = 1; (key = httpsConn.getHeaderFieldKey(i)) != null; i++) {
				if (key.equalsIgnoreCase("Set-Cookie")) {
					cookies = httpsConn.getHeaderField(key);
				}
			}
		}

		return cookies;
	}

	private int getResponseCodeFromHttpsConn(HttpsURLConnection httpsConn) {
		int responseCode = -1;
		try {
			responseCode = httpsConn.getResponseCode();
		} catch (IOException e) {
			s_logger.error("Error getting response code.", e);
		} finally {
			if (httpsConn != null) {
				httpsConn.disconnect();
			}
		}
		return responseCode;
	}

	private HttpsURLConnection getHttpsConnection(String urlstr,
			String requestMethod, String json, String cookie) {
		/**//** 网络的url地址 */
		URL url = null;

		/**//** https连接 */
		HttpsURLConnection httpsConn = null;

		try {
			url = new URL(urlstr);
			SSLUtils.trustAllHttpsCertificates();
			HostnameVerifier hv = SSLUtils.getHostnameVerifier();
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
			httpsConn = (HttpsURLConnection) url.openConnection();
			HttpsURLConnection.setFollowRedirects(true);
			httpsConn.setConnectTimeout(60000);
			httpsConn.setRequestMethod(requestMethod);
			httpsConn.setRequestProperty("Content-Type", "application/json");
			if (StringUtils.isNotEmpty(cookie)) {
				httpsConn.setRequestProperty("Cookie", cookie);
			}
			if (StringUtils.isNotEmpty(json)) {
				httpsConn.setDoOutput(true);
				OutputStream output = httpsConn.getOutputStream();
				output.write(json.getBytes("UTF8"));
				output.flush();
				output.close();
			}

			return httpsConn;
		} catch (MalformedURLException e) {
			s_logger.error("Error getting https connection.", e);
		} catch (IOException e) {
			s_logger.error("Error getting https connection.", e);
		} catch (Exception e) {
			s_logger.error("Error getting https connection.", e);
		}
		return null;
	}
	
	/**
     * 将指定的网页通过特定的编码提取其html代码
     *
     * @param urlStr 网页地址 比如: http://www.163.com
     * @param type 网页中使用的编码
     * @return String 形式的html代码如出现异常则为空
     */
   public String getContent(String urlstr, String type) {

        /**
         * 网络的url地址
         */
        URL url = null;

        /**
         * http连接
         */
        HttpURLConnection httpConn = null;

        /**
         * 输入流
         */
        InputStream in = null;
        try {
            url = new URL(urlstr);
            httpConn = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(true);
            httpConn.setConnectTimeout(5000);
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Content-Type", "application/json");
            in = httpConn.getInputStream();
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100]; //buff用于存放循环读取的临时数据
            int rc = 0;
            while ((rc = in.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            byte[] hbyte = swapStream.toByteArray(); //in_b为转换之后的结果
            if (httpConn.getContentType() != null && httpConn.getContentType().split("=").length == 2) {
                type = httpConn.getContentType().split("=")[1];
            }
            return new String(hbyte, type).replaceAll("\r", "").replaceAll("\n", "");
        } catch (MalformedURLException e) {
            s_logger.error("MalformedURLException异常,发送http连接失败!可能是路径中包含中文乱码引起的");
        } catch (IOException e) {
            s_logger.error("连接不可用," + urlstr);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (httpConn != null) {
                    httpConn.disconnect();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return "";
            }
        }
        return "";
    }
}
