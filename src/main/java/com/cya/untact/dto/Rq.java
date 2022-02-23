package com.cya.untact.dto;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cya.untact.util.Util;

import lombok.Getter;

public class Rq {
	
	private String currentUrl;
	private String currentUri;
	private Member loginedMember;
	Map<String,String> paramMap;
	@Getter
	private boolean needToChangePassword;
	
	public Rq(Member loginedMember, String currentUri, Map<String,String> paramMap, boolean needToChangePassword) {
		this.loginedMember = loginedMember;
		this.currentUrl = currentUri.split("\\?")[0];
		this.currentUri = currentUri;
		this.paramMap = paramMap;
		this.needToChangePassword = needToChangePassword;
	}
	
	public boolean isLogined() {
		return loginedMember != null;
	}
	
	public boolean isNotLogined() {
		return isLogined() == false;
	}
	
	public int getLoginedMemberId() {
		if(isNotLogined()) return 0;
		
		return loginedMember.getId();
	}
	public Member getLoginedMember() {
		return loginedMember;
	}

	public String getEncodedCurrentUri() {
		return Util.getUriEncoded(getCurrentUri());
	}

	private String getCurrentUri() {
		return currentUri;
		
	}
	
	public String getLoginPageUri() {
		String afterLoginUri;
		
		if(isLoginPage()) {
			afterLoginUri = Util.getUriEncoded(paramMap.get("afterLoginUri"));
		} else {
			afterLoginUri = getEncodedCurrentUri();
		}
		return "../member/login?afterLoginUri=" + afterLoginUri;
	}

	private boolean isLoginPage() {
		return currentUrl.equals("/usr/member/login");
	}

}
