package com.cya.untact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cya.untact.interceptor.BeforeActionInterceptor;
import com.cya.untact.interceptor.NeedToLoginInterceptor;
import com.cya.untact.interceptor.NeedToLogoutInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	// beforeActionInterceptor 인터셉터 불러오기
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;
	
	@Autowired
	NeedToLoginInterceptor needToLoginInterceptor;
	
	@Autowired
	NeedToLogoutInterceptor needToLogoutInterceptor;
	
	// 이 함수는 인터셉터를 적용하는 역할을 합니다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// beforeActionInterceptor 인터셉터가 모든 액션 실행전에 실행되도록 처리
		registry.addInterceptor(beforeActionInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/resource/**")
				.excludePathPatterns("/error");
		
		registry.addInterceptor(needToLoginInterceptor)
				.addPathPatterns("/usr/article/write")
				.addPathPatterns("/usr/article/addArticle")
				.addPathPatterns("/usr/article/deleteArticle")
				.addPathPatterns("/usr/article/modifyArticle")
				.addPathPatterns("/usr/member/modify")
				.addPathPatterns("/usr/member/doModify")
				.addPathPatterns("/usr/member/checkPassword")
				.addPathPatterns("/usr/member/doCheckPassword")
				.addPathPatterns("/usr/member/mypage");
		
		registry.addInterceptor(needToLogoutInterceptor)
				.addPathPatterns("/usr/member/findLoginId")
				.addPathPatterns("/usr/member/doFindLoginId")
				.addPathPatterns("/usr/member/findLoginPw")
				.addPathPatterns("/usr/member/doFindLoginPw")
				.addPathPatterns("/usr/member/login")
				.addPathPatterns("/usr/member/doLogin")
				.addPathPatterns("/usr/member/join")
				.addPathPatterns("/usr/member/addMember")
				.addPathPatterns("/usr/member/findLoginId")
				.addPathPatterns("/usr/member/doFindLoginId")
				.addPathPatterns("/usr/member/findLoginPw")
				.addPathPatterns("/usr/member/doFindLoginPw");
	}
}