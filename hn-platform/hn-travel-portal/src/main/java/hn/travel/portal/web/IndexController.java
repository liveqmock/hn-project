/**   
 * Copyright (c) 2014, 启创数据 All rights reserved
 */
package hn.travel.portal.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 * @Description 
 * @author chenbing
 * @date 2014年10月6日
 * @version V1.0.0
 */
@Controller
public class IndexController {

	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String index(Model m){
		m.addAttribute("name", "chenbing");
		System.out.println("==================");
		return "index/index";
	}
}
