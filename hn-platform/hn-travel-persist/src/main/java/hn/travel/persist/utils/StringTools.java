package hn.travel.persist.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class StringTools {

	private static Logger logger = LoggerFactory.getLogger(StringTools.class);

	private static final String PASSWORD_CRYPT_KEY = "wofswcDma@w0fs";

	private final static String DES = "DES";

	public static String ISO2UTF(String str) {
		String encode = getEncoding(str);
		if (encode.equals("ISO-8859-1")) {
			if (str != null) {
				try {
					return new String(str.getBytes("ISO-8859-1"), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					return null;
				}
			}
			return null;
		} else {
			return str;
		}
	}

	/**
	 * 判断字符串的编码
	 * 
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}

	/**
	 * 密码加密
	 */
	public final static String encrypt(String password) {
		try {
			return byte2hex(encrypt(password.getBytes(),
					PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	public static String makeRandom(int digit)
			throws java.lang.IllegalArgumentException {
		if (digit <= 0)
			throw new IllegalArgumentException();
		String code = "";
		int r = 0;
		Random random = new Random();
		for (int i = 1; i <= digit; i++) {
			r = random.nextInt(10);
			code += r;
		}
		return code;
	}

	public static OperatorsEnum checkOperators(String phoneNum) {
		if (!StringUtils.hasText(phoneNum)) {
			return null;
		}
		String cmRegex = "(86|\\+86)?(134|135|136|137|138|139|150|151|152|155|157|158|159|188|187|182)\\d{8}";
		String cuRegex = "(86|\\+86)?(130|131|132|155|156|185|186)\\d{8}";
		String ctRegex = "(86|\\+86)?(((180|189|133|153)\\d{8})|(\\d{11}))";
		if (phoneNum.matches(cmRegex)) {
			return OperatorsEnum.CM;
		} else if (phoneNum.matches(cuRegex)) {
			return OperatorsEnum.CU;
		} else if (phoneNum.matches(ctRegex)) {
			return OperatorsEnum.CT;
		} 
		return null;
	}

	/**
	 * 数据加密
	 * 
	 * @param data
	 * @param key
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String data, String key) {
		if (data != null)
			try {
				return byte2hex(encrypt(data.getBytes(), key.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	/**
	 * 二行制转字符串
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	/**
	 * 加密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);
	}

	/**
	 * 密码解密
	 */
	public final static String decrypt(String data) {
		try {
			return new String(decrypt(hex2byte(data.getBytes()),
					PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 数据解密
	 * 
	 * @param data
	 * @param key
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public final static String decrypt(String data, String key) {
		if (data != null)
			try {
				return new String(decrypt(hex2byte(data.getBytes()),
						key.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	public final static String encryptMD5(String password) {
		try {
			return getMD5(password.getBytes()).toUpperCase();
		} catch (Exception e) {
		}
		return null;
	}

	public final static String encryptMD5Cms(String password) {
		try {
			return getMD5(password.getBytes());
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 对UUID生成的唯一码MD5加密
	 * 
	 * @return
	 */
	public final static String encryptUUID() {
		return getMD5(UUID.randomUUID().toString().getBytes());
	}

	/**
	 * MD5的算法在RFC1321 中定义 在RFC 1321中，给出了Test suite用来检验你的实现是否正确：<br>
	 * MD5 ("") = d41d8cd98f00b204e9800998ecf8427e<br>
	 * MD5 ("a") = 0cc175b9c0f1b6a831c399e269772661<br>
	 * MD5 ("abc") = 900150983cd24fb0d6963f7d28e17f72<br>
	 * MD5 ("message digest") = f96b697d7cb7938d525a2f31aaf161d0<br>
	 * MD5 ("abcdefghijklmnopqrstuvwxyz") = c3fcd3d76192e4007dfb496cca67e13b<br>
	 */
	public static String getMD5(byte[] source) {
		String s = null;
		// 用来将字节转换成 16 进制表示的字符
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte[] tmp = md.digest(); // MD5 的计算结果是一个 128 位的长整数，用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，所以表示成 16
			// 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节，转换成 16
				// 进制字符的转换，取第 i 个字节
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换，>>>
				// 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符串
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	/***
	 * 如果字符串为null，则返回“”；用于前端展现
	 * 
	 * @param str
	 * @return
	 */
	public static String getNullStr(String str) {
		if (str == null)
			return "";
		return str;
	}

	public static String getNullStr2(String str) {
		if (str == null || "null".equals(str))
			return "";
		return str;
	}

	public static String joinWithSeparator(String[] elements) {
		if (elements != null && elements.length >= 1) {
			StringBuilder b = new StringBuilder();
			for (String s : elements) {
				b.append(s).append("/");
			}
			b.deleteCharAt(b.length() - 1);
			return b.toString();
		}
		return null;
	}

	public static String getSavedFileNameViaDate(String fileName) {
		return getSavedFileNameViaDate(fileName, "");
	}

	/***
	 * 组装保存的文件路径
	 * 
	 * @param folderName
	 *            文件夹名
	 * @param fileName
	 *            文件名 用于取后缀
	 * @param size
	 *            文件尺寸
	 * @return
	 */
	public static String getSavedFileNameViaDate(String fileName, String size) {
		Date d = new Date();
		StringBuilder b = new StringBuilder(DateUtil.FILENAME_FORMAT.format(d));
		b.append("_").append(Thread.currentThread().getId())
				.append(new Random().nextInt(1000));
		if (StringUtils.hasText(size)) {
			b.append("_").append(size);
		}
		b.append(".").append(getFileExt(fileName));
		return b.toString();
	}

	public static String getSavedFileNameViaDate(String folderName,
			String fileName, String size) {
		Date d = new Date();
		StringBuilder b = new StringBuilder(DateUtil.FILENAME_FORMAT.format(d));
		b.append("_").append(new Random().nextInt(1000));
		if (StringUtils.hasText(size)) {
			b.append("_").append(size);
		}
		b.append(".").append(getFileExt(fileName));
		return joinWithSeparator(new String[] { folderName, b.toString() });
	}

	/***
	 * 根据后缀名组装文件保存路径
	 * 
	 * @param folderName
	 *            文件夹名
	 * @param ext
	 *            文件后缀名
	 * @param size
	 *            文件尺寸
	 * @return
	 */
	public static String getSavedFileNameViaExt(String ext, String size) {
		Date d = new Date();
		StringBuilder b = new StringBuilder(DateUtil.FILENAME_FORMAT.format(d));
		b.append("_").append(new Random().nextInt(1000));
		if (StringUtils.hasText(size)) {
			b.append("_").append(size);
		}
		b.append(".").append(ext);
		return b.toString();
	}

	public static String getSavedFileNameViaExt(String ext) {
		return getSavedFileNameViaExt(ext, "");
	}

	public static String getSavedFolderViaDate() {
		Date d = new Date();
		return DateUtil.FOLDER_FORMAT.format(d);
	}

	public static String getFileExt(String fileName) {
		if (StringUtils.hasText(fileName)) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		return "";
	}

	public static String getPathViaSize(String paths, boolean withDefault) {
		return getPathViaSize(paths, null, withDefault);
	}

	/***
	 * 根据图片尺寸获取图片路径
	 * 
	 * @param paths
	 *            eg:img\tennis\20120708\
	 *            20120708141542_228.jpg;img\tennis\20120708\20120708141544_520_772x420.jpg;img\tennis\20120708\20120708141550_400_135x105.jpg;img\tennis\20120708\20120708141550_272_90x60.jpg;img\tennis\20120708\20120708141550_
	 *            4 9 7 _ 1 9 5 x 1 3 0 . j p g
	 * @param size
	 *            eg:772x420
	 * @return
	 */
	public static String getPathViaSize(String paths, String size,
			boolean withDefault) {
		if (StringUtils.hasText(paths)) {
			String[] arrs = paths.split(";");
			if (arrs.length >= 1) {
				String noSizeImg = "";
				for (String s : arrs) {
					String[] ps = s.split("_");
					if (ps.length >= 1) {
						if (ps.length == 2) {
							noSizeImg = s;
							continue;
						}
						if (StringUtils.hasText(size)) {
							if (ps.length == 3) {
								if (ps[2].startsWith(size)) {
									return s;
								}
							}
						}
					}
				}
				/*
				 * if (StringUtils.hasText(size)) { try { return
				 * StringTools.processImg(noSizeImg,
				 * PropertiesUtil.getProp("common.rootPath"),
				 * WofsConstants.PROJECT_VIDEO,
				 * VideoSourceEnum.YOUKU.getValue(), new String[] { size }); }
				 * catch (Exception e) { logger.error("处理图片出现异常"); } }
				 */
				return withDefault ? noSizeImg : "";
			}
		}
		return "";
	}

	public static String removePathViaSize(String paths, List<String> sizes) {
		StringBuilder builder = new StringBuilder();
		if (StringUtils.hasText(paths)) {
			String[] arrs = paths.split(";");
			if (arrs.length >= 1) {
				if (!sizes.isEmpty()) {
					for (String str : arrs) {
						String extens = StringUtils.getFilenameExtension(str);
						String s = StringUtils.stripFilenameExtension(str);
						String[] ps = s.split("_");
						if (ps.length >= 1) {
							if (ps.length == 2) {
								builder.append(s).append(".").append(extens)
										.append(";");
								continue;
							}
							if (ps.length == 3) {
								if (!sizes.contains(ps[2])) {
									builder.append(s).append(".")
											.append(extens).append(";");
									continue;
								} else {
									FileUtil.delFileViaPath(PropertiesUtil
											.getProp("common.rootPath"), str);
								}
							}
						}
					}
				} else {
					for (String str : arrs) {
						String[] ps = str.split("_");
						if (ps.length == 3) {
							builder.append(str).append(";");
							continue;
						}
						if (ps.length == 2) {
							FileUtil.delFileViaPath(
									PropertiesUtil.getProp("common.rootPath"),
									str);
						}
					}
				}
			}
		}
		if (builder.length() >= 1) {
			builder.deleteCharAt(builder.length() - 1);
		}
		return builder.toString();
	}

	/***
	 * 获取资源文件的请求url
	 * 
	 * @param paths
	 *            保存在库中的相对路径串，以；分隔
	 * @param size
	 *            图片尺寸
	 * @return
	 */
	public static String getRescUrl(String paths, String size,
			boolean withDefault) {
		String path = getPathViaSize(paths, size, withDefault);
		if (StringUtils.hasText(path)) {
			return convertSlash(StringTools.joinWithSeparator(new String[] {
					PropertiesUtil.getProp("common.imgDomain"), path }));
		}
		return "";
	}

	/***
	 * 获取资源文件的请求url
	 * 
	 * @param path
	 *            图片的相对路径
	 * @return
	 */
	public static String getRescUrl(String path, boolean withDefault) {
		return getRescUrl(path, "", withDefault);
	}

	public static String getRescUrl(String path) {
		if (StringUtils.hasText(path)) {
			String[] pathArr = path.split(";");
			if (pathArr.length >= 1) {
				return convertSlash(StringTools
						.joinWithSeparator(new String[] {
								PropertiesUtil.getProp("common.imgDomain"),
								pathArr[0] }));
			}
		}
		return "";
	}

	/***
	 * 
	 * @param path
	 *            :以;分隔的图片路径
	 * @return: 取path中第一个路径的请求URL
	 */
	public static String getImgLocalUrlXX(String path) {
		if (StringUtils.hasText(path)) {
			String[] pathArr = path.split(";");
			if (pathArr.length >= 1) {
				return convertSlash(StringTools
						.joinWithSeparator(new String[] {
								PropertiesUtil.getProp("common.rootPath"),
								pathArr[0] }));
			}
		}
		return "";
	}

	/**
	 * 获取图片存储的本地路径
	 * 
	 * @param path
	 *            :图片的相对路径
	 * @return
	 */
	public static String getFileLocalPath(String path) {
		if (StringUtils.hasText(path)) {
			if (path.endsWith(";")) {
				path = path.replace(";", "");
			}
			// return convertSlash(StringTools.joinWithSeparator(new String[]
			// {PropertiesUtil.getProp("common.rootPath"), path}));
			return StringUtils.cleanPath(StringTools
					.joinWithSeparator(new String[] {
							PropertiesUtil.getProp("common.rootPath"), path }));
		}
		return null;
	}

	public static <T> Set<T> split2SetFilterEmpty(String str, String regex,
			Class<T> clazz) {
		Set<T> result = Sets.newHashSet();
		if (StringUtils.hasText(str)) {
			String[] arr = str.split(regex);
			if (arr != null && arr.length >= 1) {
				for (String s : arr) {
					if (StringUtils.hasText(s)) {
						try {
							result.add(clazz.getConstructor(String.class)
									.newInstance(s));
						} catch (IllegalArgumentException e) {
							logger.error("类型转换出现异常: ", e);
						} catch (SecurityException e) {
							logger.error("类型转换出现异常: ", e);
						} catch (InstantiationException e) {
							logger.error("类型转换出现异常: ", e);
						} catch (IllegalAccessException e) {
							logger.error("类型转换出现异常: ", e);
						} catch (InvocationTargetException e) {
							logger.error("类型转换出现异常: ", e);
						} catch (NoSuchMethodException e) {
							logger.error("类型转换出现异常: ", e);
						}
					}
				}
			}
		}
		return result;
	}

	public static <T> List<T> arr2ListFilterEmpty(String[] arr, Class<T> clazz) {
		List<T> result = Lists.newLinkedList();
		if (arr != null && arr.length >= 1) {
			for (String s : arr) {
				if (StringUtils.hasText(s)) {
					try {
						result.add(clazz.getConstructor(String.class)
								.newInstance(s));
					} catch (IllegalArgumentException e) {
						logger.error("类型转换出现异常: ", e);
					} catch (SecurityException e) {
						logger.error("类型转换出现异常: ", e);
					} catch (InstantiationException e) {
						logger.error("类型转换出现异常: ", e);
					} catch (IllegalAccessException e) {
						logger.error("类型转换出现异常: ", e);
					} catch (InvocationTargetException e) {
						logger.error("类型转换出现异常: ", e);
					} catch (NoSuchMethodException e) {
						logger.error("类型转换出现异常: ", e);
					}
				}
			}
		}
		return result;
	}

	public static <T> String list2StrWithRegex(Collection<T> list, String regex) {
		StringBuilder builder = new StringBuilder();
		if (list != null && !list.isEmpty()) {
			for (T t : list) {
				builder.append(regex).append(t.toString());
			}
			builder.deleteCharAt(0);
		}
		return builder.toString();
	}

	/***
	 * 将字符串中的反斜杠转换为斜杠，eg:aa\bbb -> aa/bb
	 * 
	 * @param str
	 * @return
	 */
	public static String convertSlash(String str) {
		if (StringUtils.hasText(str)) {
			return str.replaceAll("\\\\", "/");
		}
		return null;
	}

	/***
	 * 格式化字符串
	 * 
	 * @param pattern
	 *            Hi {}
	 * @param objs
	 *            there
	 * @return Hi there
	 */
	public static String format(String pattern, Object... objs) {
		if (StringUtils.hasText(pattern)) {
			if (objs != null && objs.length >= 1) {
				FormattingTuple tuple = MessageFormatter.arrayFormat(pattern,
						objs);
				if (tuple != null)
					return tuple.getMessage();
			}
		}
		return null;
	}

	/*
	 * public static String processImgXX(String imgLink, String classify,
	 * String[] sizeArr, boolean withDefault) { return processImg(imgLink,
	 * PropertiesUtil.getProp("common.rootPath"), WofsConstants.PROJECT_VIDEO,
	 * classify, sizeArr, withDefault); }
	 */


	/***
	 * 封装搜索关键词
	 * 
	 * @param keywords
	 * @return
	 */
	public static String wrapSearchKeywords(String keywords) {
		if (StringUtils.hasText(keywords)) {
			if (keywords.startsWith("<em>")) {
				keywords = keywords.substring(4, keywords.length());
			}
			if (keywords.endsWith("</em>")) {
				keywords = keywords.substring(0, keywords.length() - 5);
			}
			if (keywords.equals("+") || keywords.equals("/")
					|| keywords.equals("\\") || keywords.equals("*")) {
				return "";
			}
			char[] t1 = null;
			t1 = keywords.toCharArray();
			int t0 = t1.length;
			boolean isHan = false;
			for (int i = 0; i < t0; i++) {
				if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					isHan = true;
					break;
				}
			}
			if (isHan) {
				return StringUtils.trimWhitespace(keywords);
			} else {
				String kw = StringUtils.trimWhitespace(keywords);
				if (kw.length() > 1)
					kw = kw + "*";
				return kw;
			}
		}
		return "";
	}

	public static String filterTags(String str, String... tags) {
		if (StringUtils.hasText(str)) {
			for (String t : tags)
				str = str.replaceAll(t, "");
		}
		return str;
	}

	/**
	 * 获取文件保存路径 如： img/video/movie/20121122/201211221232_223
	 * 返回:img/video/movie/20121122 img/video/movie/20121122/201211221232_223.jpg
	 * 返回:img/video/movie/20121122
	 * 
	 * @param fileNameWithPath
	 *            ：带有路径的文件名
	 * @return 获取文件保存路径
	 */
	public static String getFilePath(String fileNameWithPath) {
		String fn = StringUtils.getFilename(fileNameWithPath);
		if (StringUtils.hasText(fn)) {
			return fileNameWithPath.substring(0,
					fileNameWithPath.length() - fn.length() - 1);
		}
		return fileNameWithPath;
	}

	public static void main(String[] args) {

		String ss = "huanglj123";
		System.out.println(StringTools.encryptMD5Cms(ss));

		// System.out.println(getRescUrl(ss));
		// String k = "<em>asdasd</em>";
		// System.out.println(filterTags(k, "<em>", "</em>"));
		/*
		 * String str = "img\\video\\tv\\20121015\\20121015163825_52.jpg";
		 * System.out.println(convertSlash(str));
		 */
		/*
		 * String f = "img/video/movie/20121014/20121014193249_366_730x380.jpg";
		 * System.out.println(StringUtils.stripFilenameExtension(f));
		 */
		// String path =
		// "img/video/movie/20121014/20121014193249_366_730x380.jpg;img/video/movie/20121014/20121014193104_936_730x380.jpg;img/video/movie/20121014/20121014192653_802_730x380.jpg;img/video/movie/20121014/20121014192653_151_64x36.jpg;img/video/movie/20121014/20121014192611_282_730x380.jpg;img/video/movie/20121014/20121014192611_181_64x36.jpg";
		// System.out.println(removePathViaSize(path,
		// Lists.newArrayList("730x380")));
		/*
		 * String p = "\\www\\img/img/video/aa";
		 * System.out.println(StringUtils.cleanPath(p));
		 */
		/*
		 * List<Boolean> list = split2ListFilterEmpty("11,22,33", ",",
		 * Boolean.class); for (Boolean i : list) { System.out.println(i); }
		 */
		// String s =
		// "img/tennis\\20120708\\20120708141542_228.jpg;img/tennis/20120708/20120708141544_520_772x420.jpg;img/tennis/20120708/20120708141550_400_135x105.jpg;img/tennis/20120708/20120708141550_272_90x60.jpg;img/tennis/20120708/20120708141550_497_195x130.jpg";
		// String sub = convertSlash(s);
		// String sub = getPathViaSize(s, "90x60");
		// System.out.println("http://www.wofs.com.cn/".startsWith("http://"));

	}
}
