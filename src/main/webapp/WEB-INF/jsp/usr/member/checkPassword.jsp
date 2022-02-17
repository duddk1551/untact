<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle"
	value="<span><i class='fas fa-sign-in-alt'></i></span> <span>CHECK PASSWORD</span>" />
<%@ include file="../common/head.jspf"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<script>
let MemberCheckPassword__submitFormDone = false;
function MemberCheckPassword__submitForm(form) {
	if(MemberCheckPassword__submitFormDone) {
		return;
	}
	
	form.inputLoginPw.value = form.inputLoginPw.value.trim();
	if(form.inputLoginPw.value.length == 0) {
		alert('비밀번호를 입력해주세요.');
		form.inputLoginPw.focus();
		
		return false;
	}
	
	form.loginPw.value = sha256(form.inputLoginPw.value);
	form.inputLoginPw.value = '';
	
	form.submit();
	MemberCheckPassword__submitFormDone = true;
}
</script>

<div class="section section-login px-2">
	<div class="container mx-auto">
		<form method="POST" action="doCheckPassword" onsubmit="MemberCheckPassword__submitForm(this); return false;">
			<input type="hidden" name="redirectUri" value="${param.afterUri}" />
			<input type="hidden" name="loginPw" />
			<div class="form-control">
				<label class="label"> 비밀번호 </label>
				<input class="input input-bordered w-full" type="password" name="inputLoginPw"
					maxlength="100" placeholder="비밀번호를 입력해주세요." />
			</div>
			
			
			<div class="mt-4 btn-wrap gap-1">
				<button type="submit" class="btn btn-primary btn-sm mb-1">
					<span>
						<i class='fas fa-sign-in-alt'></i>
					</span>
					&nbsp;
					<span>비밀번호 확인</span>
				</button>

				<a href="/" class="btn btn-sm mb-1">
					<span>
						<i class="fas fa-house-user"></i>
					</span>
					&nbsp;
					<span>홈</span>
				</a>
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/foot.jspf"%>