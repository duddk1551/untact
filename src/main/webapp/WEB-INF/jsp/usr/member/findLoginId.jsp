<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle"
	value="<span><i class='fas fa-sign-in-alt'></i></span> <span>FIND LOGIN ID</span>" />
<%@ include file="../common/head.jspf"%>

<script>
	let MemberFindLoginId__submitFormDone = false;
	function MemberFindLoginId__submitFormDone(form) {
		if (MemberFindLoginId__submitFormDone) {
			return;
		}

		form.name.value = form.name.value.trim();
		if (form.name.value.length == 0) {
			alert('이름을 입력해주세요.');
			form.name.focus();

			return false;
		}
		form.email.value = form.email.value.trim();
		if (form.email.value.length == 0) {
			alert('이메일을 입력해주세요.');
			form.email.focus();

			return false;
		}

		form.submit();
		MemberFindLoginId__submitFormDone = true;
	}
</script>

<div class="section section-findLoginId px-2">
	<div class="container mx-auto">
		<form method="POST" action="doFindLoginId"
			onsubmit="MemberLogin__submitForm(this); return false;">
			<input type="hidden" name="redirectUri"
				value="${param.afterLoginUri}" />
			<div class="form-control">
				<label class="label"> 이름 </label>
				<input autofocus class="input input-bordered w-full" type="text"
					name="name" maxlength="30" placeholder="이름을 입력해주세요." />
			</div>
			<div class="form-control">
				<label class="label"> 이메일 </label>
				<input class="input input-bordered w-full" type="email"
					name="email" maxlength="100" placeholder="이메일을 입력해주세요." />
			</div>


			<div class="mt-4 btn-wrap gap-1">
				<button type="submit" class="btn btn-primary btn-sm mb-1">
					<span>
						<i class='fas fa-sign-in-alt'></i>
					</span>
					&nbsp;
					<span>아이디 찾기</span>
				</button>
				
				<a href="../member/findLoginPw" type="submit"
					class="btn btn-link btn-sm mb-1">
					<span>
						<i class='fas fa-sign-in-alt'></i>
					</span>
					&nbsp;
					<span>비밀번호찾기</span>
				</a>

				<a href="../member/login" type="submit"
					class="btn btn-link btn-sm mb-1">
					<span>
						<i class='fas fa-sign-in-alt'></i>
					</span>
					&nbsp;
					<span>로그인</span>
				</a>

				<a href="../member/join" type="submit"
					class="btn btn-link btn-sm mb-1">
					<span>
						<i class='fas fa-sign-in-alt'></i>
					</span>
					&nbsp;
					<span>회원가입</span>
				</a>

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