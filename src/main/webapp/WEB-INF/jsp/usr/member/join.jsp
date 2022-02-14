<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle"
	value="<span><i class='fas fa-home'></i></span> <span>MEMBER JOIN</span>" />
<%@ include file="../common/head.jspf"%>

<script>
let MemberJoin__submitFormDone = false;
function MemberJoin__submitForm(form) {
	if(MemberJoin__submitFormDone) {
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
	form.loginPwComfirm.value = form.loginPwComfirm.value.trim();
	if(form.loginPwComfirm.value.length == 0) {
		alert('비밀번호확인을 입력해주세요.');
		form.loginPwComfirm.focus();
		
		return false;
	}
	if(form.loginPw.value != form.loginPwComfirm.value) {
		alert('비밀번호가 일치하지 않습니다.');
		form.loginPwComfirm.focus();
		
		return false;
	}
	
	form.name.value = form.name.value.trim();
	if(form.name.value.length == 0) {
		alert('이름을 입력해주세요.');
		form.name.focus();
		
		return false;
	}
	form.nickname.value = form.nickname.value.trim();
	if(form.nickname.value.length == 0) {
		alert('닉네임을 입력해주세요.');
		form.nickname.focus();
		
		return false;
	}
	form.email.value = form.email.value.trim();
	if(form.email.value.length == 0) {
		alert('이메일을 입력해주세요.');
		form.email.focus();
		
		return false;
	}
	form.cellphoneNo.value = form.cellphoneNo.value.trim();
	if(form.cellphoneNo.value.length == 0) {
		alert('휴대폰번호를 입력해주세요.');
		form.cellphoneNo.focus();
		
		return false;
	}
	
	form.submit();
	MemberJoin__submitFormDone = true;
}
</script>

<div class="section section-article-list px-2">
	<div class="container mx-auto">
		<form method="POST" action="addMember" onsubmit="MemberJoin__submitForm(this); return false;">
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
			<div class="form-control">
				<label class="label"> 비밀번호확인 </label>
				<input class="input input-bordered w-full" type="password" name="loginPwComfirm"
					maxlength="30" placeholder="비밀번호확인을 입력해주세요." />
			</div>
			<div class="form-control">
				<label class="label"> 이름 </label>
				<input class="input input-bordered w-full" type="text" name="name"
					maxlength="30" placeholder="이름을 입력해주세요." />
			</div>
			<div class="form-control">
				<label class="label"> 닉네임 </label>
				<input class="input input-bordered w-full" type="text" name="nickname"
					maxlength="30" placeholder="닉네임을 입력해주세요." />
			</div>
			<div class="form-control">
				<label class="label"> 이메일 </label>
				<input class="input input-bordered w-full" type="email" name="email"
					maxlength="50" placeholder="이메일을 입력해주세요." />
			</div>
			<div class="form-control">
				<label class="label"> 전화번호 </label>
				<input class="input input-bordered w-full" type="tel" name="cellphoneNo"
					maxlength="30" placeholder="전화번호를 입력해주세요." />
			</div>
			
			<div class="mt-4 btn-wrap gap-1">
				<button type="submit" class="btn btn-primary btn-sm mb-1">
					<span>
						<i class="fas fa-user-plus"></i>
					</span>
					&nbsp;
					<span>가입</span>
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