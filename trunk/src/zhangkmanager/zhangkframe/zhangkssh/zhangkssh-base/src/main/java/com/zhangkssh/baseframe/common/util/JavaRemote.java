package com.zhangkssh.baseframe.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * java远程调用linux脚本(ganymed-ssh2-build210.jar)
 * 
 * @author zhangk
 * 
 */
class JavaRemote {

	private static final Logger s_logger = Logger.getLogger(JavaRemote.class
			.getName());

	/**
	 * 远程执行linux命令
	 * @param ip
	 * @param user
	 * @param password
	 * @param command
	 * @return
	 * @throws IOException
	 */
	public String remotesh(String ip, String user, String password,
			String command) throws IOException {
		StringBuffer result = new StringBuffer("");
		Connection conn = null;
		Session sess = null;
		try {
			/* Create a connection instance */
			conn = new Connection(ip);
			/* Now connect */
			conn.connect();
			/* Authenticate */
			boolean isAuthenticated = conn.authenticateWithPassword(user,
					password);
			if (isAuthenticated == false)
				throw new IOException("Authentication failed. username:" + user
						+ "  password:" + password);
			/* Create a session */
			sess = conn.openSession();
			// sess.execCommand("uname -a && date && uptime && who");
			sess.execCommand(command);
			InputStream stdout = new StreamGobbler(sess.getStdout());

			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(
					new InputStreamReader(stdout));
			while (true) {
				String readline = br.readLine();
				if (readline == null) {
					break;
				} else {
					result.append(readline);
				}
			}
			/* Show exit status, if available (otherwise "null") */

			/* Close this session */
			sess.close();
			/* Close the connection */
			conn.close();
			return result.toString();
		} catch (IOException e) {
			s_logger.error(
					"remotesh error (IOException) JavaRemote.remotesh( " + ip
							+ "," + user + ", " + password + " ," + command
							+ ") ", e);
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (sess != null) {
				sess.close();
			}
		}

	}
}
