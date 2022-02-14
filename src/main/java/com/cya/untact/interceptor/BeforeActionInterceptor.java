package com.cya.untact.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Component("beforeActionInterceptor") // 컴포넌트 이름 설정
public class BeforeActionInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}
