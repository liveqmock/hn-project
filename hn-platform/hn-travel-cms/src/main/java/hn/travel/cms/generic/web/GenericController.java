package hn.travel.cms.generic.web;

import hn.travel.persist.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.google.common.collect.Lists;

public class GenericController {
	
	/*
	 * 表单提交日期绑定
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = DateUtil.DEAFULT_FORMAT;
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	public static Long[] filterEmpty(Long[] itemlist) {
		List<Long> items = Lists.newLinkedList();
		if(itemlist != null && itemlist.length >= 1) {
			for(Long id : itemlist) {
				if(id != null) {
					items.add(id);
				}
			}
		}
		return items.toArray(new Long[items.size()]);
	}
	
	
	/**
	 * 获取浏览器提交的整形参数
	 */
	public static int getParam(HttpServletRequest req, String param,
			int defaultValue) {
		String value = req.getParameter(param);
		int retVal = defaultValue;
		if (org.springframework.util.StringUtils.hasText(value)) {
			int idx = value.indexOf('#');
			if (idx != -1)
				value = value.substring(0, idx);
			try {
				retVal = Integer.parseInt(value);
			} catch (Exception e) {
				retVal = defaultValue;
			}
		}
		return retVal;
	}

	/**
	 * 获取浏览器提交的字符串参数
	 */
	public static String getParam(HttpServletRequest req, String param,
			String defaultValue) {
		String value = req.getParameter(param);
		return (!org.springframework.util.StringUtils.hasText(value)) ? defaultValue : value;
	}
	
	@SuppressWarnings("unchecked")
	public String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	
	public String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}
}
