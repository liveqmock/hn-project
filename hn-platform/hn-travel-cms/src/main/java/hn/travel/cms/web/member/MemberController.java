package hn.travel.cms.web.member;

import hn.travel.cms.generic.web.GenericController;
import hn.travel.persist.entity.member.Member;
import hn.travel.persist.service.member.MemberService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/mem")
public class MemberController extends GenericController {
	@Autowired
	private MemberService memberService;

	@RequestMapping()
	public String list(Member member, Model model) {
		model.addAttribute("member", member);
		List<Member> members = memberService.getMembersByCriteria(member);
		model.addAttribute("members", members);
		return "member/mem_list";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateJsp(@PathVariable("id") Long id, Model model) {
		model.addAttribute("member", memberService.getMember(id));
		return "member/mem_update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateMember(@RequestParam(value = "id") Long id,
			@RequestParam(value = "userName") String userName,
			@RequestParam(value = "mobile") Long mobile,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "addr") String addr) {
		Member member = new Member();
		member.setId(id);
		member.setMobile(mobile);
		member.setEmail(email);
		member.setUserName(userName);
		member.setAddr(addr);
		member.setState(1);
		member.setCreateTime(new Date());
		memberService.updateMember(member);
		return "redirect:/mem";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addJsp() {
		return "member/mem_add";
	}

	@RequestMapping(value = "/addMember", method = RequestMethod.POST)
	public String addMember(@RequestParam(value = "userName") String userName,
			@RequestParam(value = "mobile") Long mobile,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "addr") String addr,
			@RequestParam(value = "password") String password) {
		Member member = new Member();
		member.setMobile(mobile);
		member.setEmail(email);
		member.setUserName(userName);
		member.setPassword(password);
		member.setAddr(addr);
		member.setState(1);
		member.setCreateTime(new Date());
		memberService.registerMember(member);
		return "redirect:/mem";
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
