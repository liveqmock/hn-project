package hn.travel.persist.utils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springside.modules.mapper.JsonMapper;

import com.google.common.collect.Lists;

public class JsonUtil {

	private static JsonMapper mapper = JsonMapper.nonDefaultMapper();

	public static String toJsonStr(Object obj) {
		return mapper.toJson(obj);
	}

	public static <T> T fromJson(String jsonStr, Class<T> clazz) {
		return mapper.fromJson(jsonStr, clazz);
	}
	
	public static <T> List<T> fromColJson(String jsonStr, Class<T> clazz) {
		List<T> result = Lists.newLinkedList();
		@SuppressWarnings("unchecked")
		List<LinkedHashMap<String, Object>> list = mapper.fromJson(jsonStr, List.class);
		if(list != null && !list.isEmpty()) {
			for(LinkedHashMap<String, Object> map : list) {
				result.add(map2Bean(map, clazz));
			}
		}
		return result;
	}

	public static <T> T map2Bean(HashMap<String, Object> map, Class<T> cls) {
		T obj = null;
		try {
			obj = cls.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 取出bean里的所有方法
		Method[] methods = cls.getMethods();
		for (int i = 0; i < methods.length; i++) {
			// 取方法名
			String method = methods[i].getName();
			// 取出方法的类型
			Class<?>[] cc = methods[i].getParameterTypes();
			if (cc.length != 1)
				continue;
			// 如果方法名没有以set开头的则退出本次for
			if (method.indexOf("set") < 0)
				continue;
			// 类型
			String type = cc[0].getSimpleName();
			try {
				// 转成小写
				// Object value = method.substring(3).toLowerCase();
				Object value = method.substring(3, 4).toLowerCase()
						+ method.substring(4);
				// 如果map里有该key
				if (map.containsKey(value) && map.get(value) != null) {
					// 调用其底层方法
					setValue(type, map.get(value), i, methods, obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	/***
	 * 调用底层方法设置值
	 */
	private static <T> void setValue(String type, Object value, int i,
			Method[] method, T bean) {
		if (value != null && !value.equals("")) {
			try {
				if (type.equals("String")) {
					// 第一个参数:从中调用基础方法的对象 第二个参数:用于方法调用的参数
					method[i].invoke(bean, new Object[] { value });
				} else if (type.equals("int") || type.equals("Integer")) {
					method[i].invoke(bean, new Object[] { new Integer(""
							+ value) });
				} else if (type.equals("long") || type.equals("Long")) {
					method[i].invoke(bean,
							new Object[] { new Long("" + value) });
				} else if (type.equals("boolean") || type.equals("Boolean")) {
					method[i].invoke(bean,
							new Object[] { Boolean.valueOf("" + value) });
				} else if (type.equals("BigDecimal")) {
					method[i].invoke(bean, new Object[] { new BigDecimal(""
							+ value) });
				} else if (type.equals("Date")) {
					Date date = null;
					if (value.getClass().getName().equals("java.util.Date")) {
						date = (Date) value;
					} else {
						String dateStr = (String) value;
						if((dateStr).indexOf(":") > 0) {
							date = DateUtil.DEAFULT_FORMAT.parse(dateStr);
						} else {
							date = DateUtil.SHORT_FORMAT.parse(dateStr);
						}
					}
					if (date != null) {
						method[i].invoke(bean, new Object[] { date });
					}
				} else if (type.equals("byte[]")) {
					method[i].invoke(bean,
							new Object[] { new String(value + "").getBytes() });
				}
			} catch (Exception e) {
				System.out
						.println("将linkHashMap 或 HashTable 里的值填充到javabean时出错,请检查!");
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		/*List<MultiBO> list = Lists.newLinkedList();
		list.add(new MultiBO(1L, "youku", "哈哈", "阿萨德","",  "www", "xxx", ""));
		list.add(new MultiBO(2L, "", "", "", "", "", "", ""));
		list.add(new MultiBO(3L, "", "", "", "", "", "", ""));
		list.add(new MultiBO(4L, "", "", "", "", "", "", ""));
		String json = toJsonStr(list);
		List<MultiBO> bs = fromColJson(json, MultiBO.class);
		for(MultiBO bo : bs) {
			System.out.println(bo);
		}*/
		
		String json = "{\"UniFsBroadband\":[{\"USER_ID\":\"5701088210856\",\"USER_NAME\":\"罗建洲\",\"SERIAL_NUMBER\":\"ss1042674@fs.unicom\",\"USER_PHONE\":\"13318347886/18928579080\",\"PRODUCT_CODE\":\"2M\",\"PRODUCT_REMARK\":\"三水2M\",\"PRODUCT_CODE_P\":\"5\",\"PRODUCT_REMARK_P\":\"10M预付1200元包年\",\"FEE\":\"1200\",\"CONTINUE_DATE\":\"2012-2-27 0:00:00\",\"OPEN_DATE\":\"2006-6-5 0:00:00\",\"SETUP_ADDR\":\"三水三水文锋西路2号101,三水三水文锋西路2号101\"},{\"USER_ID\":\"5701088210856\",\"USER_NAME\":\"罗建洲\",\"SERIAL_NUMBER\":\"ss1042674@fs.unicom\",\"USER_PHONE\":\"13318347886/18928579080\",\"PRODUCT_CODE\":\"2M\",\"PRODUCT_REMARK\":\"三水2M\",\"PRODUCT_CODE_P\":\"9\",\"PRODUCT_REMARK_P\":\"6M预付1380元两年\",\"FEE\":\"1380\",\"CONTINUE_DATE\":\"2012-2-27 0:00:00\",\"OPEN_DATE\":\"2006-6-5 0:00:00\",\"SETUP_ADDR\":\"三水三水文锋西路2号101,三水三水文锋西路2号101\"}]}";
		HashMap map = JsonUtil.fromJson(json, HashMap.class);
		System.out.println(map);
		List<HashMap> list = (List<HashMap>)map.get("UniFsBroadband");
		if(list != null && !list.isEmpty()) {
			for(HashMap m : list) {
				System.out.println(m);
			}
		}
		/*String str = "<embed height=\"380\" width=\"440\" name=\"v_31JW7L\" id=\"video_player_other\" allowscriptaccess=\"always\" pluginspage=\"http://get.adobe.com/cn/flashplayer/\" flashvars=\"url_key=\" allowfullscreen=\"true\" quality=\"hight\" src=\"http://player.pps.tv/player/sid/31JW7L/v.swf\" type=\"application/x-shockwave-flash\" wmode=\"Opaque\">";
		String str1 = "height=\"380\" width=\"440\" name=\"v_31JW7L\" id=\"video_player_other\" allowscriptaccess=\"always\" pluginspage=\"http://get.adobe.com/cn/flashplayer/\" flashvars=\"url_key=\" allowfullscreen=\"true\" quality=\"hight\" src=\"http://player.pps.tv/player/sid/31JW7L/v.swf\" type=\"application/x-shockwave-flash\" wmode=\"Opaque\"";
		String[] arr = str1.split(" ");
		if(arr != null && arr.length >= 1) {
			for(String s : arr) {
				String[] ss = s.split("=");
				if(ss != null && ss.length >= 1) {
					String key = ss[0];
					if("src".equals(key)) {
						System.out.println(ss[1]);
					}
				}
			}
		}*/
	}
	
}
