package com.zhangkssh.baseframe.common.util.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

public class Encrypt {

	public String encrypt(String strSrc, String encName) {
		// parameter strSrc is a string will be encrypted,
		// parameter encName is the algorithm name will be used.
		// encName dafault to "MD5"
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = strSrc.getBytes();
		try {
			if (StringUtils.isEmpty(encName)) {
				encName = "MD5";
			}
			md = MessageDigest.getInstance(encName);
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Invalid algorithm.");
			return null;
		}
		return strDes;
	}

	private String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	public static void main(String[] args) {
		Encrypt encrypt = new Encrypt();
		String strSrc = "password";
		System.out.println("Source String:" + strSrc);
		System.out.println("Encrypted String:");
		System.out.println("Use Def:" + encrypt.encrypt(strSrc, null));
		System.out.println("Use MD5:" + encrypt.encrypt(strSrc, ""));
		System.out.println("Use SHA:" + encrypt.encrypt(strSrc, "SHA-1"));
		System.out.println("Use SHA-256:" + encrypt.encrypt(strSrc, "SHA-256"));
	}
}
