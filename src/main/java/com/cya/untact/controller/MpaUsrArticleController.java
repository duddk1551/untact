package com.cya.untact.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cya.untact.dto.Article;
import com.cya.untact.dto.ResultData;
import com.cya.untact.util.Util;

@Controller
public class MpaUsrArticleController {
	
	private List<Article> articles;
	private int articleLastId;
	
	public MpaUsrArticleController() {
		articles = new ArrayList<>();
		articleLastId = 0;
		makeTestData();
	}
	
	@RequestMapping("/mpaUsr/article/addArticle")
	@ResponseBody
	public ResultData addArticle(String title, String content) {
		int id = writeArticle(title, content);
		Article article = getArticleById(id);
		
		return new ResultData("S-1", id + "번 글이 작성되었습니다.", "article", article);
	}

	@RequestMapping("/mpaUsr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {
		Article article = getArticleById(id);
		
		if(article == null) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
		}
		
		return new ResultData("S-1", article.getId() + "번 글입니다.", "article", article);
	}
	

	@RequestMapping("/mpaUsr/article/deleteArticle")
	@ResponseBody
	public ResultData deleteArticle(int id) {
		boolean deleted = deleteArticleById(id);
		
		if(deleted == false) {
			return new ResultData("F-1", id + "번 글이 존재하지 않습니다.", "id", id);
		}
		
		return new ResultData("F-1", id + "번 글이 삭제되었습니다.", "id", id);
	}
	
	@RequestMapping("/mpaUsr/article/modifyArticle")
	@ResponseBody
	public ResultData modifyArticle(int id, String title, String content) {
		boolean modified = modifyArticleById(id, title, content);
		
		if(modified == false) {
			return new ResultData("F-1", id + "번 글이 존재하지 않습니다.", "id", id);
		}
		
		return new ResultData("F-1", id + "번 글이 수정되었습니다.", "article", getArticleById(id));
	}
	

	//내부메서드
	private void makeTestData() {
		for(int i = 0; i < 5; i++) {
			writeArticle("제목1", "내용");
		}
	}
	private int writeArticle(String title, String content) {
		int id = articleLastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();
		
		Article article = new Article(id, regDate, updateDate, title, content);
		articles.add(article);
		
		articleLastId = id;
		
		return id;
	}

	private Article getArticleById(int id) {
		for(Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	
	private boolean deleteArticleById(int id) {
		Article article = getArticleById(id);
		
		if(article == null) {
			return false;
		}
		articles.remove(article);
		return true;
	}
	
	private boolean modifyArticleById(int id, String title, String content) {
		Article article = getArticleById(id);
		
		if(article == null) {
			return false;
		}
		article.setUpdateDate(Util.getNowDateStr());
		article.setTitle(title);
		article.setContent(content);
		return true;
	}
	
}
