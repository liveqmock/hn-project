package hn.travel.persist.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;

public class MemCachedUtil {
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(MemCachedUtil.class);
	// 默认缓存3分钟
	private static final int REF_SECONDS = 5 * 60;

	private static ICacheManager<IMemcachedCache> manager;

	private static Map<String, IMemcachedCache> cacheArray;

	private static final String defalutCacheName = "mclient1";

	static {
		cacheArray = new HashMap<String, IMemcachedCache>();
		manager = CacheUtil.getCacheManager(IMemcachedCache.class,
				MemcachedCacheManager.class.getName());
		// manager.setConfigFile("memcached.xml");
		manager.start();
		cacheArray.put(defalutCacheName, manager.getCache(defalutCacheName));
	}

	private static String getCacheName(String type, Object key) {
		StringBuffer cacheName = new StringBuffer(type);
		if (key != null) {
			cacheName.append("_").append(key);
		}
		return cacheName.toString();
	}

	public static void set(String type, Object key, Object value) {
		set(type, key, value, REF_SECONDS);
	}

	public static void putNoTimeInCache(String type, Object key, Object value) {
		if (value != null) {
			set(type, key, value, -1);
		}
	}

	public static void set(String type, Object key, Object value, int seconds) {
		if (value != null) {
			String cacheName = getCacheName(type, key);
			try {
				if (seconds < 1) {
					cacheArray.get(defalutCacheName).put(cacheName, value);
				} else {
					cacheArray.get(defalutCacheName).put(cacheName, value,
							seconds);
				}
			} catch (Exception e) {
				logger.info("cache " + defalutCacheName
						+ " socket error。");
			}
		}
	}

	public static void delete(String type, Object key) {
		cacheArray.get(defalutCacheName).remove(getCacheName(type, key));
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> clazz, String type, Object key) {
		return (T) cacheArray.get(defalutCacheName)
				.get(getCacheName(type, key));
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getList(Class<T> clazz, String type, Object key) {
		return (List<T>) cacheArray.get(defalutCacheName).get(
				getCacheName(type, key));
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> clazz, String type, Object key,
			int localTTL) {
		try {
			return (T) cacheArray.get(defalutCacheName).get(
					getCacheName(type, key), localTTL);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getList(Class<T> clazz, String type, Object key,
			int localTTL) {
		try {
			return (List<T>) cacheArray.get(defalutCacheName).get(
					getCacheName(type, key), localTTL);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <V> Map<String, V> getMap(Class<V> clazz, String type,
			Object key, int localTTL) {
		try {
			return (Map<String, V>) cacheArray.get(defalutCacheName).get(
					getCacheName(type, key), localTTL);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <V> Map<String, V> getMap(Class<V> clazz, String type,
			Object key) {
		try {
			return (Map<String, V>) cacheArray.get(defalutCacheName).get(
					getCacheName(type, key));
		} catch (Exception e) {
			return null;
		}
	}

	public static Set<String> getKeyList() {
		return cacheArray.get(defalutCacheName).keySet();
	}

	public static void clear() {
		cacheArray.get(defalutCacheName).clear();
	}

	public static void close() {
		manager.stop();
	}

	public static void main(String argv[]) {
		MemCachedUtil.set("test", "name", "zchen01", 30);
		System.out.println(MemCachedUtil.get(Object.class, "test", "name"));
		MemCachedUtil.close();
	}
}