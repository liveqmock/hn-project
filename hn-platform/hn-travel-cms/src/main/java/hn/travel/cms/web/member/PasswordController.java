package hn.travel.cms.web.member;

import hn.travel.persist.entity.member.Member;
import hn.travel.persist.service.member.MemberService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/mem/pwd")
public class PasswordController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping()
	public String findPwd(Member member, Model model) {
		model.addAttribute("member", member);
		List<Member> members = memberService.getAllMember();
		model.addAttribute("members", members);
		return "member/pwd_find";
	}
	
	@RequestMapping(value="/mobile/{id}", method=RequestMethod.GET)
	public String findPwdByMobile(@PathVariable("id") Long id, Model model) {
		List<Member> members = memberService.getAllMember();
		model.addAttribute("members", members);
		return "member/pwd_find";
	}
	
	@RequestMapping(value="/email/{id}", method=RequestMethod.GET)
	public String findPwdByEmail(@PathVariable("id") Long id, Model model) {
		List<Member> members = memberService.getAllMember();
		model.addAttribute("members", members);
		return "member/pwd_find";
	}
}
