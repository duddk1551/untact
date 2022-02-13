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
	
	private String msgAndBack(Model model, String msg) {
		model.addAttribute("msg", msg);
		model.addAttribute("historyBack", true);
		return "common/redirect";
	}
	
	private String msgAndReplace(Model model, String msg, String replaceUrl) {
		model.addAttribute("msg", msg);
		model.addAttribute("replaceUrl", replaceUrl);
		return "common/redirect";
	}

	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article getArticle(Integer id) {
			
		Article article = articleService.getArticle(id);
		return article;
		
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(Model model, String searchKeyword, String searchKeywordType, @RequestParam(defaultValue = "1") int boardId, @RequestParam(defaultValue = "1") int page) {
		Board board = articleService.getBoard(boardId);
		
		if(Util.isEmpty(searchKeywordType)) {
			searchKeywordType = "titleAndContent";
		}
		
		if(board == null) { 
			return msgAndBack(model, boardId + "번 게시판이 존재하지 않습니다.");
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
	
	@RequestMapping("/usr/article/addArticle")
	@ResponseBody
	public ResultData addArticle(String title, String content) {
		
		if(Util.isEmpty(title)) {
			return new ResultData("F-1", "제목을 입력해주세요");
		}
		
		if(Util.isEmpty(content)) {
			return new ResultData("F-2", "내용을 입력해주세요");
		}
		return articleService.addArticle(title, content);
	}
	
	@RequestMapping("/usr/article/write")
	public String writeArticle(Model model, @RequestParam(defaultValue = "1") int boardId) {
		
		Board board = articleService.getBoard(boardId);
		
		if(board == null) { 
			return msgAndBack(model, boardId + "번 게시판이 존재하지 않습니다.");
		}
		
		model.addAttribute("board", board);
		
		return "usr/article/write";
		
	}
	
	@RequestMapping("/usr/article/deleteArticle")
	public String deleteArticle(Model model, Integer id) {
		
		if(id == null) {
			return msgAndBack(model, "번호를 입력해주세요");
		}
		
		ResultData rd = articleService.deleteArticle(id);
		
		if(rd.isFail()) {
			return msgAndBack(model, rd.getMsg());
		}	
		
		String redirectUrl = "../article/list?boardId=" + rd.getBody().get("id");		
		return msgAndReplace(model, rd.getMsg(), redirectUrl);
		
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
