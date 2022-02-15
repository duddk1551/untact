<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle"
	value="<span><i class='fas fa-home'></i></span> <span>${board.name} ARTICLE LIST</span>" />
<%@ include file="../common/head.jspf"%>
<div class="section section-article-list">
	<div class="container mx-auto">
		<div class="total-items">
			<span>TOTAL ITEMS :</span>
			<span>${totalItemsCount}</span>
		</div>
		<div class="total-pages">
			<span>TOTAL PAGES :</span>
			<span>${totalPage}</span>
		</div>
		<div class="page">
			<span>CURRENT PAGE :</span>
			<span>${page}</span>
		</div>
		<hr />
		<div class="plain-link-wrap gap-3 mt-4">
			<a href="write?boardId=${board.id}" class="plain-link">
				<span>
					<i class="fas fa-edit"></i>
				</span>
				<span>글 작성</span>
			</a>
		</div>
		<hr />

		<div class="search-form-box mt-2 px-2">
			<form action="" class="grid gap-2">
				<input type="hidden" name="boardId" value="${board.id}" />
				<div class="form-control">
					<select class="select select-bordered" name="searchKeywordType">
						<option disabled="disabled" selected="selected">검색타입을
							선택해주세요.</option>
						<option value="titleAndContent">제목+내용</option>
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select>
				</div>
				<div class="form-control">
					<input value="${param.searchKeyword}" class="input input-bordered"
						name="searchKeyword" placeholder="검색어를 입력해주세요." maxlength="10" />
				</div>
				<div class="form-control">
					<input type="submit" class="btn btn-secondary btn-xs mb-1"
						value="검색" />
				</div>
			</form>
		</div>

		<div class="articles mt-2">
			<c:if test="${articles == null || articles.size() == 0}">
				검색결과가 존재하지 않습니다.
			</c:if>
			<c:forEach items="${articles}" var="article">
				<div>
					ID : ${article.id}
					<br>
					REG DATE : ${article.regDate}
					<br>
					UPDATE DATE : ${article.updateDate}
					<br>
					TITLE : ${article.title}
					<br>
				</div>
				<hr />
			</c:forEach>
		</div>
		<div class="pages mt-4 mb-4 text-center">
			<c:set var="pageMenuArmSize" value="4" />
			<c:set var="startPage"
				value="${page - pageMenuArmSize >= 1 ? page - pageMenuArmSize : 1}" />
			<c:set var="endPage"
				value="${page + pageMenuArmSize <= totalPage ? page + pageMenuArmSize : 1}" />

			<c:set var="urlBase" value="?boardId=${board.id}" />
			<c:set var="urlBase"
				value="${urlBase}&searchKeywordType=${param.searchKeywordType}" />
			<c:set var="urlBase"
				value="${urlBase}&searchKeyword=${param.searchKeyword}" />

			<c:set var="aClassStr"
				value="px-2 inline-block border border-gray-200 rounded text-lg hover:bg-gray-200" />

			<c:if test="${startPage > 1}">
				<a class="${aClassStr}" href="${urlBase}&page=1">◀◀</a>
				<a class="${aClassStr}" href="${urlBase}&page=${startPage-1}">◀</a>
			</c:if>

			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a class="${aClassStr} ${page == i ? 'text-red-500' : ''}"
					href="${urlBase}&page=${i}">${i}</a>
			</c:forEach>

			<c:if test="${endPage < totalPage}">
				<a class="${aClassStr}" href="${urlBase}&page=${endPage + 1}">▶</a>
				<a class="${aClassStr}" href="${urlBase}&page=${totalPage}">▶▶</a>
			</c:if>
		</div>
	</div>
</div>
<%@ include file="../common/foot.jspf"%>