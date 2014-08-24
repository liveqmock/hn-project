package hn.travel.persist.utils;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import com.google.common.collect.Maps;

/***
 * 运营商枚举
 * @author Administrator
 *
 */
public enum OperatorsEnum {

	CM("1", "移动"),
	CU("2", "联通"),
	CT("3", "电信版");
	
	private static Map<String, OperatorsEnum> valueMap = Maps.newHashMap();
	
	public String value;
	public String displayName;
	
	static {
		for (OperatorsEnum time : OperatorsEnum.values()) {
			valueMap.put(time.value, time);
		}
	}
	
	OperatorsEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}
	
	public static OperatorsEnum parse(String value) {
		return valueMap.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	public Date getDate() {
		try {
			return DateUtil.SHORT_FORMAT.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
