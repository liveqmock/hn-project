/**   
 * Copyright (c) 2014, 启创数据 All rights reserved
 */
package hn.travel.portal.web;

import hn.travel.persist.entity.Scenic;
import hn.travel.persist.entity.ScenicTicket;
import hn.travel.persist.service.scenic.ScenicService;
import hn.travel.persist.service.scenic.ScenicTicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @Description 
 * @author chenbing
 * @date 2014年10月19日
 * @version V1.0.0
 */
@Controller
@RequestMapping(value = "/scenic")
public class ScenicController {
	@Autowired
	private ScenicService scenicService;
	@Autowired
	private ScenicTicketService scService;
	
	@RequestMapping(value = {"/", "index.htm"})
	public String index() {
		
		return "scenic/index/index";
	}

	@RequestMapping(value = "detail/{id}.htm")
	public String detail(@PathVariable("id") Long id, Model model) {
		Scenic scenic = scenicService.get(id);
		if(scenic != null) {
			PageRequest p = new PageRequest(0, 20);
			Page<ScenicTicket> page = scService.page(id, p);
			if(page != null && page.hasContent()) {
				model.addAttribute("scList", page.getContent());
			}
			model.addAttribute("scenic", scenic);
		}
		return "scenic/detail/index";
	}
}
