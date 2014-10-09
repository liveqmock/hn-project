package hn.travel.persist.utils;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesUtil {

	private static Logger logger = LoggerFactory
			.getLogger(PropertiesUtil.class);

	private static final String SYS_PROP_PATH = "application.properties";

	private static Properties props;

	static {
		try {
			props = PropertiesLoaderUtils.loadAllProperties(SYS_PROP_PATH);
		} catch (IOException e) {
			logger.error("没找到资源文件{},抛出异常{}", SYS_PROP_PATH, e);
		}
	}

	private PropertiesUtil() {
	}

	public static String getProp(String key) {
		return getProp(key, "");
	}

	public static String getProp(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}
}
