package com.cya.untact.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cya.untact.dto.Article;
import com.cya.untact.dto.ResultData;
import com.cya.untact.util.Util;

@Component
public class ArticleDao {
	
	private List<Article> articles;
	private int articleLastId;
	
	public ArticleDao() {
		//멤버변수 초기화
		articles = new ArrayList<>();
		articleLastId = 0;
		//testdata 2개 생성
		articles.add(
				new Article(++articleLastId, "2020-12-12 12:12:12", "2020-12-12 12:12:12", "제목1 입니다.", "내용1 입니다."));
		articles.add(
				new Article(++articleLastId, "2020-12-12 12:12:12", "2020-12-12 12:12:12", "제목2 입니다.", "내용2 입니다."));
	}

	public int addArticle(String title, String content) {
		int id = ++articleLastId;
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();
		
		articles.add(new Article(id, regDate, updateDate, title, content));
		
		return id;
	}

	public Article getArticle(int id) {
		for(Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	
	public List<Article> getArticles(String searchKeywordType, String searchKeyword) {
		if (searchKeyword == null) {
			return articles;
		}

		List<Article> filtered = new ArrayList<>();

		for (Article article : articles) {
			boolean contains = false;

			if (searchKeywordType.equals("title")) {
				contains = article.getTitle().contains(searchKeyword);
			} else if (searchKeywordType.equals("body")) {
				contains = article.getContent().contains(searchKeyword);
			} else {
				contains = article.getTitle().contains(searchKeyword);

				if (contains == false) {
					contains = article.getContent().contains(searchKeyword);
				}
			}

			if (contains) {
				filtered.add(article);
			}
		}

		return filtered;
	}

	public boolean deleteArticle(int id) {
		Article article = getArticle(id);
		if(article == null) {
			return false;
		}
		articles.remove(article);
		return true;
	}

	public void modifyArticle(int id, String title, String content) {
		Article article = getArticle(id);
		
		article.setUpdateDate(Util.getNowDateStr());
		article.setTitle(title);
		article.setContent(content);
		
	}

}
