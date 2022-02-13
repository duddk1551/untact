<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle"
	value="<span><i class='fas fa-home'></i></span> <span>${board.name} ARTICLE WRITE</span>" />
<%@ include file="../common/head.jspf"%>

<script>
let ArticleWrite__submitFormDone = false;
function ArticleWrite__submitForm(form) {
	if(ArticleWrite__submitFormDone) {
		return;
	}
	
	form.title.value = form.title.value.trim();
	
	if(form.title.value.length == 0) {
		alert('제목을 입력해주세요.');
		form.title.focus();
		
		return false;
	}
	
	if(form.content.value.length == 0) {
		alert('내용을 입력해주세요.');
		form.content.focus();
		
		return false;
	}
	
	form.submit();
	ArticleWrite__submitFormDone = true;
}
</script>

<div class="section section-article-list">
	<div class="container mx-auto">
		<form method="POST" action="addArticle" onsubmit="ArticleWrite__submitForm(this); return false;">
			<input type="hidden" name="boardId" value="${board.id}"/>
			<div class="form-control">
				<label class="label"> 제목 </label>
				<input class="input input-bordered w-full" type="text" name="title"
					maxlength="100" placeholder="제목을 입력해주세요." />
			</div>
			<div class="form-control">
				<label class="label"> 내용 </label>
				<textarea class="textarea textarea-bordered w-full h-24"
					name="content" maxlength="2000" placeholder="내용을 입력해주세요."></textarea>
			</div>
			<div class="mt-4 btn-wrap gap-1">
				<button type="submit" class="btn btn-primary btn-sm mb-1">
					<span>
						<i class="fas fa-save"></i>
					</span>
					&nbsp;
					<span>작성</span>
				</button>

				<button onclick="if ( !confirm('삭제하시겠습니까?') ) return false;" href="#"
					class="btn btn-error btn-sm mb-1">
					<span>
						<i class="fas fa-trash"></i>
					</span>
					&nbsp;
					<span>삭제</span>
				</button>
				<button href="#" class="btn btn-sm mb-1" title="자세히 보기">
					<span>
						<i class="fas fa-list"></i>
					</span>
					&nbsp;
					<span>리스트</span>
				</button>
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/foot.jspf"%>