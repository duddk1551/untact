<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle"
	value="<span><i class='fas fa-home'></i></span> <span>MEMBER MODIFY</span>" />
<%@ include file="../common/head.jspf"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<script>
let MemberModify__submitFormDone = false;
function MemberModify__submitForm(form) {
	if(MemberModify__submitFormDone) {
		return;
	}
	
	form.inputLoginPw.value = form.inputLoginPw.value.trim();
	if(form.inputLoginPw.value.length > 0) {
		
		form.loginPwComfirm.value = form.loginPwComfirm.value.trim();
		if(form.loginPwComfirm.value.length == 0) {
			alert('비밀번호확인을 입력해주세요.');
			form.loginPwComfirm.focus();
			
			return false;
		}
		
		if(form.inputLoginPw.value != form.loginPwComfirm.value) {
			alert('비밀번호가 일치하지 않습니다.');
			form.loginPwComfirm.focus();
			
			return false;
		}
		
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
	
	if(form.inputLoginPw.value.length > 0) {
		form.loginPw.value = sha256(form.inputLoginPw.value);
		form.inputLoginPw.value = '';
		form.loginPwComfirm.value = '';
	}
	
	form.submit();
	MemberModify__submitFormDone = true;
}
</script>

<div class="section section-member-modify px-2">
	<div class="container mx-auto">
		<form method="POST" action="doModify" onsubmit="MemberModify__submitForm(this); return false;">
			<input type="hidden" name="loginPw" />
			<div class="form-control">
				<label class="label"> 아이디 </label>
				<div class="plain-text">
					${rq.loginedMember.loginId}
				</div>
			</div>
			<div class="form-control">
				<label class="label"> 비밀번호 </label>
				<input class="input input-bordered w-full" type="password" name="inputLoginPw"
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
					maxlength="30" placeholder="이름을 입력해주세요." value="${rq.loginedMember.name}"/>
			</div>
			<div class="form-control">
				<label class="label"> 닉네임 </label>
				<input class="input input-bordered w-full" type="text" name="nickname"
					maxlength="30" placeholder="닉네임을 입력해주세요."  value="${rq.loginedMember.nickname}"/>
			</div>
			<div class="form-control">
				<label class="label"> 이메일 </label>
				<input class="input input-bordered w-full" type="email" name="email"
					maxlength="50" placeholder="이메일을 입력해주세요." value="${rq.loginedMember.email}"/>
			</div>
			<div class="form-control">
				<label class="label"> 전화번호 </label>
				<input class="input input-bordered w-full" type="tel" name="cellphoneNo"
					maxlength="30" placeholder="전화번호를 입력해주세요." value="${rq.loginedMember.cellphoneNo}"/>
			</div>
			
			<div class="mt-4 btn-wrap gap-1">
				<button type="submit" class="btn btn-primary btn-sm mb-1">
					<span>
						<i class="fas fa-user-plus"></i>
					</span>
					&nbsp;
					<span>수정</span>
				</button>
				
				<a href="../member/mypage" class="btn btn-link btn-sm mb-1">
					<span>
						<i class="fas fa-house-user"></i>
					</span>
					&nbsp;
					<span>마이페이지</span>
				</a>

				<a href="/" class="btn btn-link btn-sm mb-1">
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