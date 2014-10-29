/**
 * 
 */
package hn.travel.persist.utils;

/**
 * @author XFZP
 * @date 2014年10月26日
 */
public class UuidUtil {
	private static UuidUtil inst;

	private int uuidIndex = 0;

	private UuidUtil() {
	}

	public static synchronized UuidUtil getInst() {
		if (inst != null)
			return inst;
		return inst = new UuidUtil();
	}

	public String getUuid() {
		long t = System.currentTimeMillis();
		return String.format("%d%04d", t, next());
	}

	private synchronized long next() {
		if (uuidIndex > 9999)
			uuidIndex = 0;
		return uuidIndex++;
	}
}
