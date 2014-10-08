package hn.travel.persist.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 脚本工具
 * 
 * @author Spider
 */
public class ShellUtils {
	private static Logger logger = LoggerFactory.getLogger(ShellUtils.class);

	/**
	 * 直接运行脚本
	 * 
	 * @param sh
	 *            shell脚本
	 * @return 状�态0表示正常
	 */
	public static int exec(String sh) {
		int exitVal = -1;
		try {
			Runtime rt = Runtime.getRuntime();
			Process pr = rt.exec(sh);
			exitVal = pr.waitFor();
		} catch (Exception e) {
			logger.error("exec error:", e);
		}
		return exitVal;
	}

	/**
	 * 返回脚本信息
	 * 
	 * @param sh
	 *            shell脚本
	 * @param isDebug
	 *            是否打印脚本错误信息
	 * @return
	 */
	public static ExecResult execReturnResult(String sh, boolean isDebug) {
		int exitVal = 0;
		StringBuilder readLine = new StringBuilder("");
		String s = null;
		InputStream is = null;
		BufferedReader br = null;
		try {
			Runtime rt = Runtime.getRuntime();
			Process pr = rt.exec(sh);
			if (isDebug) {
				is = pr.getErrorStream();
			} else {
				is = pr.getInputStream();
			}
			br = new BufferedReader(new InputStreamReader(is));
			s = br.readLine();
			// 首行不用加换行符
			if (s != null && !s.trim().equals(""))
				readLine.append(s);
			// 第二行开始加换行
			while (!StringUtils.hasText(s = br.readLine()))
				readLine.append(System.getProperty("line.separator") + s);
			exitVal = pr.waitFor();
		} catch (Exception e) {
			logger.error("execReturnResult error:", e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(br);
		}
		return new ExecResult(exitVal, readLine.toString());
	}
}