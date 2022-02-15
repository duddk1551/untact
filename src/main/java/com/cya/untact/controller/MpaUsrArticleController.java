package com.cya.untact.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cya.untact.dto.Article;
import com.cya.untact.dto.Board;
import com.cya.untact.dto.ResultData;
import com.cya.untact.service.ArticleService;
import com.cya.untact.util.Util;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MpaUsrArticleController {
	
	
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Integer id) {
			
		Article article = articleService.getArticleForPrint(id);
		
		if(article == null) { 
			return Util.msgAndBack(req, id + "번 게시물이 존재하지 않습니다.");
		}
		
		Board board = articleService.getBoard(article.getBoardId());
		
		req.setAttribute("article", article);
		req.setAttribute("board", board);
		
		return "usr/article/detail";
		
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(HttpServletRequest req, String searchKeyword, String searchKeywordType, @RequestParam(defaultValue = "1") int boardId, @RequestParam(defaultValue = "1") int page) {
		Board board = articleService.getBoard(boardId);
		
		if(Util.isEmpty(searchKeywordType)) {
			searchKeywordType = "titleAndContent";
		}
		
		if(board == null) { 
			return Util.msgAndBack(req, boardId + "번 게시판이 존재하지 않습니다.");
		}
		
		req.setAttribute("board", board);
		
		int totalItemsCount = articleService.getArticleTotalCount(boardId, searchKeyword, searchKeywordType);
		
		req.setAttribute("totalItemsCount", totalItemsCount);
		
		int itemsCountInAPage = 20;
		int totalPage = (int) Math.ceil(totalItemsCount / (double)itemsCountInAPage);
		
		req.setAttribute("page", page);
		req.setAttribute("totalPage", totalPage);
		
		List<Article> articles = articleService.getForPrintArticles(boardId, searchKeyword, searchKeywordType, itemsCountInAPage, page);
		
		req.setAttribute("articles", articles);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/write")
	public String showArticle(HttpServletRequest req, @RequestParam(defaultValue = "1") int boardId) {
		
		Board board = articleService.getBoard(boardId);
		
		if(board == null) { 
			return Util.msgAndBack(req, boardId + "번 게시판이 존재하지 않습니다.");
		}
		
		req.setAttribute("board", board);
		
		return "usr/article/write";
		
	}
	
	@RequestMapping("/usr/article/addArticle")
	public String addArticle(HttpServletRequest req, int boardId, String title, String content) {
		
		if(Util.isEmpty(title)) {
			return Util.msgAndBack(req, "제목을 입력해주세요");
		}
		
		if(Util.isEmpty(content)) {
			return Util.msgAndBack(req, "내용을 입력해주세요");
		}
		int memberId = 3;//임시
		ResultData writeArticleRd = articleService.addArticle(boardId, memberId, title, content);
		
		if(writeArticleRd.isFail()) {
			return Util.msgAndBack(req, writeArticleRd.getMsg());
		}
		
		String replaceUrl = "detail?id=" + writeArticleRd.getBody().get("id");
		return Util.msgAndReplace(req, writeArticleRd.getMsg(), replaceUrl);
	}
	
	@RequestMapping("/usr/article/deleteArticle")
	public String deleteArticle(HttpServletRequest req, Integer id) {
		
		if(id == null) {
			return Util.msgAndBack(req, "번호를 입력해주세요");
		}
		
		ResultData rd = articleService.deleteArticle(id);
		
		if(rd.isFail()) {
			return Util.msgAndBack(req, rd.getMsg());
		}	
		
		String redirectUrl = "../article/list?boardId=" + rd.getBody().get("id");		
		return Util.msgAndReplace(req, rd.getMsg(), redirectUrl);
		
	}

	@RequestMapping("/usr/article/modifyArticle")
	@ResponseBody
	public ResultData modifyArticle(Integer id, String title, String content) {
		
		if(id == null) {
			return new ResultData("F-1", "번호를 입력해주세요");
		}
		if(Util.isEmpty(title)) {
			return new ResultData("F-2", "제목을 입력해주세요");
		}
		if(Util.isEmpty(content)) {
			return new ResultData("F-3", "내용을 입력해주세요");
		}
		
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return new ResultData("F-4", "존재하지 않는 게시물입니다.");
		}
		
		return articleService.modifyArticle(id, title, content);

	}
	
	
}
