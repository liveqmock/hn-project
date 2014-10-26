package hn.travel.cms.generic.web;

import hn.travel.persist.utils.DateUtil;
import hn.travel.persist.utils.JsonUtil;
import hn.travel.persist.utils.PropertiesUtil;
import hn.travel.persist.utils.UuidUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;

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
		if (itemlist != null && itemlist.length >= 1) {
			for (Long id : itemlist) {
				if (id != null) {
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
		return (!org.springframework.util.StringUtils.hasText(value)) ? defaultValue
				: value;
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

	// 用@ResponseBody在IE下会出现文件下载
	protected void writeUploadJson(HttpServletResponse resp, Object re) {
		resp.setHeader("Content-Type", "text/html; charset=UTF-8");
		try {
			resp.getWriter().write(JsonUtil.toJsonStr(re));
		} catch (IOException e) {
		}
	}

	/**
	 * 上传图片
	 * 
	 * @param re
	 * @param imgUriFile
	 * @param request
	 * @return
	 */
	protected String uploadImg(Map<String, Object> re,
			MultipartFile imgUriFile, HttpServletRequest request) {
		if (imgUriFile == null || imgUriFile.isEmpty()) {
			re.put("error", "不能上传空的图片");
			return null;
		}

		String fileExt = FileUpUtil.getExt(imgUriFile.getOriginalFilename());
		if (!FileUpUtil.isImg(fileExt)) {
			re.put("error", "只允许上传图片类型（" + FileUpUtil.IMG_EXT_STR + "）");
			return null;
		}

		String realPath = PropertiesUtil.getProp("scenic.imgPath");
		String saveUrl = realPath + UuidUtil.getInst().getUuid() + "."
				+ fileExt;
		realPath = request.getServletContext().getRealPath(saveUrl);
		try {
			imgUriFile.transferTo(new File(realPath));
			return saveUrl;
		} catch (IllegalStateException | IOException e) {
			re.put("error", "图片上传错误");
			return null;
		}
	}
}
