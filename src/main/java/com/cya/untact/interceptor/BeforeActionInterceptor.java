package com.cya.untact.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cya.untact.dto.Member;
import com.cya.untact.dto.Rq;
import com.cya.untact.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BeforeActionInterceptor implements HandlerInterceptor {
	
	@Autowired
	private MemberService memberService; 
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		HttpSession session = req.getSession();
		
		Member loginedMember = null;
		int loginedMemberId = 0;
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int)session.getAttribute("loginedMemberId");
		}
		if(loginedMemberId != 0) {
			loginedMember = memberService.getMemberById(loginedMemberId);
		}
		
		req.setAttribute("rq", new Rq(loginedMember));

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}
