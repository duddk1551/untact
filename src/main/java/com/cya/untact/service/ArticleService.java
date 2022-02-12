package com.cya.untact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cya.untact.dao.ArticleDao;
import com.cya.untact.dto.Article;
import com.cya.untact.dto.Board;
import com.cya.untact.dto.ResultData;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleDao articleDao;

	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}
	
	public ResultData addArticle(String title, String content) {
		int boardId = 3;
		int memberId = 3;
		articleDao.addArticle(boardId, memberId, title, content);
		int id = articleDao.getLastInsertId();
		
		return new ResultData("S-1", "게시물이 작성되었습니다.", "id", id);
	}
	
	public ResultData deleteArticle(int id) {
		boolean rs = articleDao.deleteArticle(id);
		
		if(rs == false) {
			return new ResultData("F-1", "존재하지 않는 게시물입니다.", "id", id);
		}
		return new ResultData("S-1", "게시물이 삭제되었습니다.", "id", id);
	}
	
	public ResultData modifyArticle(int id, String title, String content) {

		articleDao.modifyArticle(id, title, content);
		return new ResultData("S-1", "게시물이 수정되었습니다.", "id", id);
	}

	public Board getBoard(int id) {
		return articleDao.getBoard(id);
	}

	public int getArticleTotalCount(int boardId, String searchKeyword, String searchKeywordType) {
		
		if(searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}
		
		return articleDao.getArticleTotalCount(boardId, searchKeyword, searchKeywordType);
	}

	public List<Article> getForPrintArticles(int boardId, String searchKeyword, String searchKeywordType, int itemsCountInAPage, int page) {
		
		if(searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}
		
		int limitFrom = (page - 1) * itemsCountInAPage;
		int limitTake = itemsCountInAPage;
		
		return articleDao.getForPrintArticles(boardId, searchKeyword, searchKeywordType, limitFrom, limitTake);
	}
	
	
	
}
