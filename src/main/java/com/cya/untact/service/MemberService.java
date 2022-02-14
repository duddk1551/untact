package com.cya.untact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cya.untact.dao.MemberDao;
import com.cya.untact.dto.Member;
import com.cya.untact.dto.ResultData;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public ResultData join(String loginId, String loginPw, String name, String nickname, String email,
			String cellphoneNo) {
		
		memberDao.join(loginId, loginPw, name, nickname, email, cellphoneNo);
		
		int id = memberDao.getLastInsertId();
		
		return new ResultData("S-1", "회원가입이 완료되었습니다.", "id", id);
	}


	public Member getMemberById(int id) {
		return memberDao.getMember(id);
	}

}
