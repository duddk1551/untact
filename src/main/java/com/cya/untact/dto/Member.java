package com.cya.untact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
	
}
