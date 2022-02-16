package com.cya.untact.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cya.untact.dto.Member;
import com.cya.untact.dto.ResultData;

@Mapper
public interface MemberDao {

	Member getMemberByLoginId(@Param("loginId") String loginId);
	
	Member getMember(@Param("id") int id);

	void join(@Param("loginId") String loginId, @Param("loginPw") String loginPw, @Param("name") String name, @Param("nickname") String nickname
			, @Param("email") String email, @Param("cellphoneNo") String cellphoneNo);
	
	int getLastInsertId();

	Member getMemberByNameAndEmail(@Param("name") String name, @Param("email") String email);

}
