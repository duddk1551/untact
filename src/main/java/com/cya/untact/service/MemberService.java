package com.cya.untact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cya.untact.dao.MemberDao;
import com.cya.untact.dto.Member;
import com.cya.untact.dto.ResultData;
import com.cya.untact.util.Util;

@Service
public class MemberService {
	
	@Value("${custom.siteMainUri}")
    private String siteMainUri;
    @Value("${custom.siteName}")
    private String siteName;
	
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MailService mailService;
	@Autowired
	private AttrService attrService;

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

	public Member getMemberByNameAndEmail(String name, String email) {
		return memberDao.getMemberByNameAndEmail(name, email);
	}

	public ResultData sendTempLoginPwByEmail(Member actor) {
		String title = "[" + siteName + "] 임시 패스워드 발송";
        String tempPassword = Util.getTempPassword(6);
        String body = "<h1>임시 패스워드 : " + tempPassword + "</h1>";
        body += "<a href=\"" + siteMainUri + "/usr/member/login\" target=\"_blank\">로그인 하러가기</a>";

        ResultData sendResultData = mailService.send(actor.getEmail(), title, body);

        if (sendResultData.isFail()) {
            return sendResultData;
        }
        
        tempPassword = Util.sha256(tempPassword);
        
        setTempPassword(actor, tempPassword);

        return new ResultData("S-1", "계정의 이메일주소로 임시 패스워드가 발송되었습니다.");
	}
	
	private void setTempPassword(Member actor, String tempPassword) {
        //attrService.setValue("member", actor.getId(), "extra", "useTempPassword", "1", null);
		
        memberDao.modify(actor.getId(), tempPassword, null, null, null, null);
    }

	public ResultData modifyMember(int id, String loginPw, String name, String nickname, String email, String cellphoneNo) {
		memberDao.modify(id, loginPw, name, nickname, email, cellphoneNo);
		
		return new ResultData("S-1", "회원정보가 수정되었습니다.", "id", id);
	}

	public ResultData checkValidModifyPrivateAuthCode(int actorId, String passwordAuthCode) {
		if (attrService.getValue("member__" + actorId + "__extra__modifyPrivateAuthCode").equals(passwordAuthCode)) {
            return new ResultData("S-1", "유효한 키 입니다.");
        }

        return new ResultData("F-1", "유효하지 않은 키 입니다.");
	}

}
