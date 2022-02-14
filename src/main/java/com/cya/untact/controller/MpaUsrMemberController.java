package com.cya.untact.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cya.untact.dto.Member;
import com.cya.untact.dto.ResultData;
import com.cya.untact.service.MemberService;
import com.cya.untact.util.Util;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MpaUsrMemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/member/join")
	public String showJoin(Model model) {
		return "usr/member/join";
	}
	
	@RequestMapping("/usr/member/login")
	public String showLogin(Model model) {
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	public String doLogin(Model model, HttpServletRequest req, String loginId, String loginPw, String redirectUrl) {
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			return Util.msgAndBack(model, loginId + "(은)는 존재하지 않는 아이디 입니다.");
		}
		
		if(member.getLoginPw().equals(loginPw) == false) {
			return Util.msgAndBack(model, "비밀번호가 일치하지 않습니다.");
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("loginedMemberId", member.getId());
		
		
		String msg = "환영합니다.";
		return Util.msgAndReplace(model, msg, redirectUrl);
	}
	
	@RequestMapping("/usr/member/addMember")
	public String addMember(Model model, String loginId, String loginPw, String name, String nickname, String email, String cellphoneNo) {
		Member existMember = memberService.getMemberByLoginId(loginId);
		
		if(existMember != null) {
			return Util.msgAndBack(model, loginId + "(은)는 이미 사용중인 로그인 아이디 입니다.");
		}
		
		ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, email, cellphoneNo);
		
		if(joinRd.isFail()) {
			return Util.msgAndBack(model, joinRd.getMsg());
		}	
			
		return Util.msgAndReplace(model, joinRd.getMsg(), "/");
	}
}
