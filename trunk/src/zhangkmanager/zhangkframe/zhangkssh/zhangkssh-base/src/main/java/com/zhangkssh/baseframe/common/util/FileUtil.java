package com.zhangkssh.baseframe.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FileUtil {

	public static void getFileRow(String fileName,
			GetFileRowMethod getFileRowMethod) {
		InputStreamReader inputReader = null;
		BufferedReader bufferReader = null;
		try {
			InputStream inputStream = new FileInputStream(fileName);
			inputReader = new InputStreamReader(inputStream);
			bufferReader = new BufferedReader(inputReader);
			// 读取一行
			String line = null;
			while ((line = bufferReader.readLine()) != null) {
				getFileRowMethod.deal(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	/**
	 * 创建文件夹
	 * 
	 * @param path
	 */
	public static void mkdir(String path) {
		StringTokenizer st = new StringTokenizer(path, "/");
		String path1 = st.nextToken() + "/";
		String path2 = path1;
		while (st.hasMoreTokens()) {
			path1 = st.nextToken() + "/";
			path2 += path1;
			File inbox = new File(path2);
			if (!inbox.exists())
				inbox.mkdir();
		}
	}
	
	public interface GetFileRowMethod {
		public void deal(String line);
	}
}
