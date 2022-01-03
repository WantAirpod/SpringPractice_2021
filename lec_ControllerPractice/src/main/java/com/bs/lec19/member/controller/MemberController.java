package com.bs.lec19.member.controller;

import com.bs.lec19.member.Member;
import com.bs.lec19.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService service;
	//ModelAttribute 사용 (또 다른기능) : 메서드 명을 변경된다.
	@ModelAttribute("serverTime")
	public String getServerTime(Locale locale) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		return dateFormat.format(date);
	}
	
	@RequestMapping(value = "/memJoin", method = RequestMethod.POST)
	public String memJoin(Member member) {
		service.memberRegister(member);
		return "memJoinOk";
	}
	
	@RequestMapping(value = "/memLogin", method = RequestMethod.POST)
	public String memLogin(Member member) {
		service.memberSearch(member);
		return "memLoginOk";
	}
	//ModelAttribute 사용
	@RequestMapping(value = "/memRemove", method = RequestMethod.POST)
	public String memRemove(@ModelAttribute("mem") Member member) {
		
		service.memberRemove(member);

		return "memRemoveOk";
	}
	
	/*
	@RequestMapping(value = "/memModify", method = RequestMethod.POST)
	public String memModify(Model model, Member member) {
		
		Member[] members = service.memberModify(member);
		
		model.addAttribute("memBef", members[0]);
		model.addAttribute("memAft", members[1]);
		
		return "memModifyOk";
	}
	*/

	/**
	 * ModelANdView	 객체 데이터와 뷰이름을 같이 전달하는 객체
	 * @param member
	 * @return
	 */
	@RequestMapping(value = "/memModify", method = RequestMethod.POST)
	public ModelAndView memModify(Member member) {
		
		Member[] members = service.memberModify(member);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memBef", members[0]);
		mav.addObject("memAft", members[1]);
		
		mav.setViewName("memModifyOk");
		
		return mav;
	}
	
}
