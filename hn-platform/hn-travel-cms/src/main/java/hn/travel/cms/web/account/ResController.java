/**   
 * Copyright (c) 2014, 启创数据 All rights reserved
 */
package hn.travel.cms.web.account;

import hn.travel.persist.generic.bo.Resource;
import hn.travel.persist.generic.bo.TravelConstants;
import hn.travel.persist.service.account.ResourceService;
import hn.travel.persist.service.account.ShiroDbRealm.ShiroUser;
import hn.travel.persist.utils.DateUtil;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 * @Description 
 * @author chenbing
 * @date 2014-8-17
 * @version V1.0.0
 */
@Controller
@RequestMapping(value = "/res")
public class ResController {

	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String top(Model model) {
		model.addAttribute("version", TravelConstants.VERSION);
		//asd
		model.addAttribute("loginTime", DateUtil.DEAFULT_FORMAT.format(new Date()));
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		model.addAttribute("user", user);
		List<Resource> list = resourceService.getTopMenus();
		model.addAttribute("menus", list);
		return "account/top";
	}
	
	@RequestMapping(value = "list/{pid}", method = RequestMethod.GET)
	public String list(@PathVariable("pid") Long pid, Model model) {
		List<Resource> list = resourceService.getByParentId(pid);
		model.addAttribute("menus", list);
		return "account/menu";
	}
}
