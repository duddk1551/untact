package com.cya.untact.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.cya.untact.dto.Article;
import com.cya.untact.dto.Board;
import com.cya.untact.dto.ResultData;
import com.cya.untact.util.Util;

@Mapper
public interface ArticleDao {
	
	
	void addArticle(@Param("boardId") int boardId,@Param("memberId") int memberId, @Param("title") String title, @Param("content") String content);
	
	Article getArticle(@Param("id") int id);
	
	//public List<Article> getArticles(String searchKeywordType, String searchKeyword);
	
	boolean deleteArticle(@Param("id") int id);
	
	int getLastInsertId();

	void modifyArticle(@Param("id") int id,@Param("title") String title,@Param("content") String content);

	Board getBoard(@Param("id") int id);

	int getArticleTotalCount(@Param("boardId") int boardId, @Param("searchKeyword") String searchKeyword);

	List<Article> getForPrintArticles(@Param("boardId") int boardId, @Param("searchKeyword") String searchKeyword, @Param("limitFrom") int limitFrom, @Param("limitTake") int limitTake);

}
