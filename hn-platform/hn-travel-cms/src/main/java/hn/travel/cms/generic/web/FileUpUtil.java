/**
 * 
 */
package hn.travel.cms.generic.web;

import java.util.Arrays;
import java.util.List;

/**
 * @author XFZP
 * @date 2014年10月26日
 */
public abstract class FileUpUtil {
	public static final String IMG_EXT_STR = "gif,jpg,jpeg,png,bmp";

	public static final List<String> IMG_EXT = Arrays
			.<String> asList(IMG_EXT_STR.split(","));

	/**
	 * 返回是否是图片
	 * 
	 * @param ext
	 * @return
	 */
	public static boolean isImg(String ext) {
		return IMG_EXT.contains(ext.toLowerCase());
	}

	/**
	 * 返回文件扩展名
	 * 
	 * @param fileName
	 */
	public static String getExt(String fileName) {
		int p = fileName.lastIndexOf(".");
		if (p < 0)
			return "";

		return fileName.substring(p + 1);
	}
}
