package hn.travel.cms.web.member;

import hn.travel.cms.generic.web.GenericController;
import hn.travel.persist.entity.member.Favorites;
import hn.travel.persist.service.member.FavoritesService;
import hn.travel.persist.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/mem/favorites")
public class FavoritesController extends GenericController {
	@Autowired
	private FavoritesService favoritesService;

	@RequestMapping()
	public String list(Long memId, Model model) {
		model.addAttribute("memId", memId);
		List<Favorites> favorites = favoritesService.findByMemId(memId);
		model.addAttribute("favorites", favorites);
		return "member/favor_list";
	}

	

	/*
	 * 表单提交日期绑定
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = DateUtil.SHORT_FORMAT;
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
}
