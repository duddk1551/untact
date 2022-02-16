package com.cya.untact.controller;

import javax.servlet.http.HttpServlet;
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
	public String showJoin(HttpServletRequest req) {
		return "usr/member/join";
	}
	
	@RequestMapping("/usr/member/login")
	public String showLogin(HttpServletRequest req) {
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/findLoginId")
	public String showFindLoginId(HttpServletRequest req) {
		return "usr/member/findLoginId";
	}
	
	@RequestMapping("/usr/member/doLogin")
	public String login(HttpServletRequest req, String loginId, String loginPw, String redirectUri) {
		
		if(Util.isEmpty(redirectUri)) {
			redirectUri = "/";
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			return Util.msgAndBack(req, loginId + "(은)는 존재하지 않는 아이디 입니다.");
		}
		
		if(member.getLoginPw().equals(loginPw) == false) {
			return Util.msgAndBack(req, "비밀번호가 일치하지 않습니다.");
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("loginedMemberId", member.getId());
		
		
		String msg = "환영합니다.";
		return Util.msgAndReplace(req, msg, redirectUri);
	}
	
	@RequestMapping("/usr/member/doFindLoginId")
	public String doFindLoginId(HttpServletRequest req, String name, String email, String redirectUri) {
		
		if(Util.isEmpty(redirectUri)) {
			redirectUri = "/";
		}
		
		Member member = memberService.getMemberByNameAndEmail(name, email);
		
		if(member == null) {
			return Util.msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
		}
		
		return Util.msgAndBack(req, String.format("회원님읜 아이디는 `%s` 입니다.", member.getLoginId()));

	}
	
	@RequestMapping("/usr/member/doLogout")
	public String logout(HttpServletRequest req, HttpSession session) {
		
		session.removeAttribute("loginedMemberId");
		
		String msg = "로그아웃 되었습니다.";
		return Util.msgAndReplace(req, msg, "/");
	}
	
	@RequestMapping("/usr/member/addMember")
	public String addMember(HttpServletRequest req, String loginId, String loginPw, String name, String nickname, String email, String cellphoneNo) {
		Member existMember = memberService.getMemberByLoginId(loginId);
		
		if(existMember != null) {
			return Util.msgAndBack(req, loginId + "(은)는 이미 사용중인 로그인 아이디 입니다.");
		}
		
		ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, email, cellphoneNo);
		
		if(joinRd.isFail()) {
			return Util.msgAndBack(req, joinRd.getMsg());
		}	
			
		return Util.msgAndReplace(req, joinRd.getMsg(), "/");
	}
}
