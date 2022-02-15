<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle"
	value="<span><i class='fas fa-sign-in-alt'></i></span> <span>MEMBER LOGIN</span>" />
<%@ include file="../common/head.jspf"%>

<script>
let MemberLogin__submitFormDone = false;
function MemberLogin__submitForm(form) {
	if(MemberLogin__submitFormDone) {
		return;
	}
	
	form.loginId.value = form.loginId.value.trim();
	if(form.loginId.value.length == 0) {
		alert('아이디를 입력해주세요.');
		form.loginId.focus();
		
		return false;
	}
	form.loginPw.value = form.loginPw.value.trim();
	if(form.loginPw.value.length == 0) {
		alert('비밀번호를 입력해주세요.');
		form.loginPw.focus();
		
		return false;
	}
	
	form.submit();
	MemberLogin__submitFormDone = true;
}
</script>

<div class="section section-article-list px-2">
	<div class="container mx-auto">
		<form method="POST" action="doLogin" onsubmit="MemberLogin__submitForm(this); return false;">
			<input type="hidden" name="redirectUri" value="${param.afterLoginUri}" />
			<div class="form-control">
				<label class="label"> 아이디 </label>
				<input class="input input-bordered w-full" type="text" name="loginId"
					maxlength="30" placeholder="아이디를 입력해주세요." />
			</div>
			<div class="form-control">
				<label class="label"> 비밀번호 </label>
				<input class="input input-bordered w-full" type="password" name="loginPw"
					maxlength="30" placeholder="비밀번호를 입력해주세요." />
			</div>
			
			
			<div class="mt-4 btn-wrap gap-1">
				<button type="submit" class="btn btn-primary btn-sm mb-1">
					<span>
						<i class='fas fa-sign-in-alt'></i>
					</span>
					&nbsp;
					<span>로그인</span>
				</button>

				<button href="#" class="btn btn-sm mb-1">
					<span>
						<i class="fas fa-house-user"></i>
					</span>
					&nbsp;
					<span>홈</span>
				</button>
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/foot.jspf"%>