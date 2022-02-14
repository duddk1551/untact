package com.cya.untact.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cya.untact.dto.Article;
import com.cya.untact.dto.Board;
import com.cya.untact.dto.Member;
import com.cya.untact.dto.ResultData;
import com.cya.untact.service.ArticleService;
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
