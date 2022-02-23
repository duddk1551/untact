package com.cya.untact.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cya.untact.dto.Member;
import com.cya.untact.dto.ResultData;
import com.cya.untact.dto.Rq;
import com.cya.untact.service.MemberService;
import com.cya.untact.util.Util;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MpaUsrMemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/member/mypage")
	public String showMypage(HttpServletRequest req) {
		return "usr/member/mypage";
	}
	
	@RequestMapping("/usr/member/join")
	public String showJoin(HttpServletRequest req) {
		return "usr/member/join";
	}
	
	@RequestMapping("/usr/member/login")
	public String showLogin(HttpServletRequest req) {
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/modify")
	public String showModify(HttpServletRequest req, String passwordAuthCode) {
		
		Member loginedMember = ((Rq)req.getAttribute("rq")).getLoginedMember();
		ResultData checkValidPasswordAuthCodeRd = memberService.checkValidPasswordAuthCode(loginedMember.getId(), passwordAuthCode);
		
		if(checkValidPasswordAuthCodeRd.isFail()) {
			return Util.msgAndBack(req, checkValidPasswordAuthCodeRd.getMsg());
		}
		
		return "usr/member/modify";
	}
	
	@RequestMapping("/usr/member/checkPassword")
	public String showCheckPassword(HttpServletRequest req) {
		return "usr/member/checkPassword";
	}
	
	@RequestMapping("/usr/member/findLoginId")
	public String showFindLoginId(HttpServletRequest req) {
		return "usr/member/findLoginId";
	}
	
	@RequestMapping("/usr/member/findLoginPw")
	public String showFindLoginPw(HttpServletRequest req) {
		return "usr/member/findLoginPw";
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
	
	@RequestMapping("/usr/member/doFindLoginPw")
	public String doFindLoginPw(HttpServletRequest req, String loginId, String name, String email, String redirectUri) {
		
		if(Util.isEmpty(redirectUri)) {
			redirectUri = "/";
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			return Util.msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
		}
		
		if(member.getName().equals(name) == false) {
			return Util.msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
		}
		
		if(member.getEmail().equals(email) == false) {
			return Util.msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
		}
		
		ResultData sendTempLoginPwByEmailRd = memberService.sendTempLoginPwByEmail(member);
		
		return Util.msgAndReplace(req, sendTempLoginPwByEmailRd.getMsg(), redirectUri);

	}
	
	@RequestMapping("/usr/member/doCheckPassword")
	public String doCheckPassword(HttpServletRequest req, String loginPw, String redirectUri) {
		Member loginedMember = ((Rq)req.getAttribute("rq")).getLoginedMember();
		
		if(loginedMember.getLoginPw().equals(loginPw) == false) {
			return Util.msgAndBack(req, "비밀번호가 일치하지 않습니다.");
		}
		
		String authCode = memberService.getpasswordAuthCode(loginedMember.getId());
		
		redirectUri = Util.getNewUri(redirectUri, "passwordAuthCode", authCode);
		
		return Util.msgAndReplace(req, "", redirectUri);

	}
	
	@RequestMapping("/usr/member/doLogin")
	public String login(HttpServletRequest req, HttpSession session, String loginId, String loginPw, String redirectUri) {
		
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
		
		session.setAttribute("loginedMemberId", member.getId());
		
		String msg = "환영합니다.";
		
		boolean needToChangePassword = memberService.needToChangePassword(member.getId());
		if(needToChangePassword) {
			msg = "현재 비밀번호를 사용한지 "+ memberService.getNeedToChangePasswordFreeDays() +" 일이 지났습니다. 비밀번호를 변경해주세요.";
			redirectUri = "/usr/member/mypage";
		}
		
		boolean IsUsingTempPassword = memberService.usingTempPassword(member.getId());
		if(IsUsingTempPassword) {
			msg = "임시 비밀번호를 변경해주세요.";
			redirectUri = "/usr/member/mypage";
		}
		return Util.msgAndReplace(req, msg, redirectUri);
	}
	
	@RequestMapping("/usr/member/doLogout")
	public String logout(HttpServletRequest req, HttpSession session) {
		
		session.removeAttribute("loginedMemberId");
		
		String msg = "로그아웃 되었습니다.";
		return Util.msgAndReplace(req, msg, "/");
	}
	
	@RequestMapping("/usr/member/doModify")
	public String modifyMember(HttpServletRequest req, String loginPw, String name, String nickname, String email, String cellphoneNo, String passwordAuthCode) {
		
		Member loginedMember = ((Rq)req.getAttribute("rq")).getLoginedMember();
		ResultData checkValidPasswordAuthCodeRd = memberService.checkValidPasswordAuthCode(loginedMember.getId(), passwordAuthCode);
		
		if(checkValidPasswordAuthCodeRd.isFail()) {
			return Util.msgAndBack(req, checkValidPasswordAuthCodeRd.getMsg());
		}
		
		int id = ((Rq)req.getAttribute("rq")).getLoginedMemberId();

		if(loginPw != null && loginPw.trim().length() == 0) {
			loginPw = null;
		}

		ResultData modifyRd = memberService.modifyMember(id, loginPw, name, nickname, email, cellphoneNo);
		
		if(modifyRd.isFail()) {
			return Util.msgAndBack(req, modifyRd.getMsg());
		}	
			
		return Util.msgAndReplace(req, modifyRd.getMsg(), "/");
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
