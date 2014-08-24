package hn.travel.cms.generic.bo;

import hn.travel.persist.utils.DateUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import com.google.common.collect.Maps;

public enum DefauleTimeEnum {

	START_TIME("1970-01-01", "默认起始时间"),
	END_TIME("2050-12-30", "默认结束时间");
	
	private static Map<String, DefauleTimeEnum> valueMap = Maps.newHashMap();
	
	public String value;
	public String displayName;
	
	static {
		for (DefauleTimeEnum time : DefauleTimeEnum.values()) {
			valueMap.put(time.value, time);
		}
	}
	
	DefauleTimeEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}
	
	public static DefauleTimeEnum parse(String value) {
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
