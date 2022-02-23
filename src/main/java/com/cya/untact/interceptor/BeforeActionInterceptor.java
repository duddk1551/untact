package com.cya.untact.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cya.untact.dto.Member;
import com.cya.untact.dto.Rq;
import com.cya.untact.service.MemberService;
import com.cya.untact.util.Util;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BeforeActionInterceptor implements HandlerInterceptor {
	
	@Autowired
	private MemberService memberService; 
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		
		Map<String,String> paramMap = Util.getParamMap(req);
		
		HttpSession session = req.getSession();
		
		Member loginedMember = null;
		int loginedMemberId = 0;
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int)session.getAttribute("loginedMemberId");
		}
		if(loginedMemberId != 0) {
			loginedMember = memberService.getMemberById(loginedMemberId);
		}

		String currentUri = req.getRequestURI();
		String queryString = req.getQueryString();

		if (queryString != null && queryString.length() > 0) {
			currentUri += "?" + queryString;
		}
		
		boolean needToChangePassword = false;
		
		if(loginedMember != null) {
			needToChangePassword = memberService.needToChangePassword(loginedMember.getId());
		}
		
		req.setAttribute("rq", new Rq(loginedMember, currentUri, paramMap, needToChangePassword));

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}
