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
import com.cya.untact.dto.ResultData;
import com.cya.untact.service.ArticleService;
import com.cya.untact.util.Util;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MpaUsrMemberController {
	
	
	@RequestMapping("/usr/member/join")
	public String showJoin(Model model) {
		
		return "usr/member/join";
		
	}
	
	@RequestMapping("/usr/member/addMember")
	@ResponseBody
	public Map addMember(String loginId, String loginPw, String name, String nickname, String email, String cellphoneNo) {
		return Map.of("loginId", loginId, "loginPw", loginPw, "name", name, "nickname", nickname, "email", email, "cellphoneNo", cellphoneNo);
	}
}
