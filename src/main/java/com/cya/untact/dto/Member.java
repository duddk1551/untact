package com.cya.untact.dto;

import com.cya.untact.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {
	private int id;
	private String regDate;
	private String updateDate;
	private String loginId;
	private String loginPw;
    private String name;
	private String nickname;
    private String email;
    private String cellphoneNo;
    private boolean delStatus;
    private String delDate;
    
    public String getAuthLevelName() {
    	return "일반회원";
    }

	public String toJsonStr() {
		return Util.toJsonStr(this);
	}
	
}
