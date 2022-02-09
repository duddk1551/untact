package com.cya.untact.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cya.untact.dto.Article;
import com.cya.untact.dto.ResultData;
import com.cya.untact.service.ArticleService;
import com.cya.untact.util.Util;

@Controller
public class MpaUsrArticleController {
	
	
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article getArticle(Integer id) {
			
		Article article = articleService.getArticle(id);
		return article;
		
	}
	
//	@RequestMapping("/usr/article/list")
//	@ResponseBody
//	public List<Article> showList(String searchKeywordType, String searchKeyword) {
//			
//		if(searchKeywordType != null) {
//			searchKeywordType = searchKeywordType.trim();
//		}
//		if(searchKeywordType == null || searchKeywordType.length() == 0) {
//			searchKeywordType = "titleAndBody";
//		}
//		if(searchKeyword != null) {
//			searchKeyword = searchKeyword.trim();
//		}
//		if(searchKeyword == null || searchKeyword.length() == 0) {
//			searchKeyword = null;
//		}
//		
//		return articleService.getArticles(searchKeywordType, searchKeyword);
//	}
	
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
	
	@RequestMapping("/usr/article/deleteArticle")
	@ResponseBody
	public ResultData deleteArticle(Integer id) {
		
		if(id == null) {
			return new ResultData("F-1", "번호를 입력해주세요");
		}
		
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.");
		} 
			
		
		return articleService.deleteArticle(id);
		
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
