package com.cya.untact.controller;

import java.util.List;

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
	public String showDetail(Model model, Integer id) {
			
		Article article = articleService.getArticleForPrint(id);
		
		if(article == null) { 
			return Util.msgAndBack(model, id + "번 게시물이 존재하지 않습니다.");
		}
		
		Board board = articleService.getBoard(article.getBoardId());
		
		model.addAttribute("article", article);
		model.addAttribute("board", board);
		
		return "usr/article/detail";
		
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(Model model, String searchKeyword, String searchKeywordType, @RequestParam(defaultValue = "1") int boardId, @RequestParam(defaultValue = "1") int page) {
		Board board = articleService.getBoard(boardId);
		
		if(Util.isEmpty(searchKeywordType)) {
			searchKeywordType = "titleAndContent";
		}
		
		if(board == null) { 
			return Util.msgAndBack(model, boardId + "번 게시판이 존재하지 않습니다.");
		}
		
		model.addAttribute("board", board);
		
		int totalItemsCount = articleService.getArticleTotalCount(boardId, searchKeyword, searchKeywordType);
		
		model.addAttribute("totalItemsCount", totalItemsCount);
		
		int itemsCountInAPage = 20;
		int totalPage = (int) Math.ceil(totalItemsCount / (double)itemsCountInAPage);
		
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		
		List<Article> articles = articleService.getForPrintArticles(boardId, searchKeyword, searchKeywordType, itemsCountInAPage, page);
		
		model.addAttribute("articles", articles);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/write")
	public String showArticle(Model model, @RequestParam(defaultValue = "1") int boardId) {
		
		Board board = articleService.getBoard(boardId);
		
		if(board == null) { 
			return Util.msgAndBack(model, boardId + "번 게시판이 존재하지 않습니다.");
		}
		
		model.addAttribute("board", board);
		
		return "usr/article/write";
		
	}
	
	@RequestMapping("/usr/article/addArticle")
	public String addArticle(Model model, int boardId, String title, String content) {
		
		if(Util.isEmpty(title)) {
			return Util.msgAndBack(model, "제목을 입력해주세요");
		}
		
		if(Util.isEmpty(content)) {
			return Util.msgAndBack(model, "내용을 입력해주세요");
		}
		int memberId = 3;//임시
		ResultData writeArticleRd = articleService.addArticle(boardId, memberId, title, content);
		
		if(writeArticleRd.isFail()) {
			return Util.msgAndBack(model, writeArticleRd.getMsg());
		}
		
		String replaceUrl = "detail?id=" + writeArticleRd.getBody().get("id");
		return Util.msgAndReplace(model, writeArticleRd.getMsg(), replaceUrl);
	}
	
	@RequestMapping("/usr/article/deleteArticle")
	public String deleteArticle(Model model, Integer id) {
		
		if(id == null) {
			return Util.msgAndBack(model, "번호를 입력해주세요");
		}
		
		ResultData rd = articleService.deleteArticle(id);
		
		if(rd.isFail()) {
			return Util.msgAndBack(model, rd.getMsg());
		}	
		
		String redirectUrl = "../article/list?boardId=" + rd.getBody().get("id");		
		return Util.msgAndReplace(model, rd.getMsg(), redirectUrl);
		
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
