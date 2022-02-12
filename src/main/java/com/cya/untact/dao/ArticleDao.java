package com.cya.untact.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cya.untact.dto.Article;
import com.cya.untact.dto.Board;

@Mapper
public interface ArticleDao {
	
	
	void addArticle(@Param("boardId") int boardId,@Param("memberId") int memberId, @Param("title") String title, @Param("content") String content);
	
	Article getArticle(@Param("id") int id);
	
	boolean deleteArticle(@Param("id") int id);
	
	int getLastInsertId();

	void modifyArticle(@Param("id") int id,@Param("title") String title,@Param("content") String content);

	Board getBoard(@Param("id") int id);

	int getArticleTotalCount(@Param("boardId") int boardId, @Param("searchKeyword") String searchKeyword, @Param("searchKeywordType") String searchKeywordType);

	List<Article> getForPrintArticles(@Param("boardId") int boardId, @Param("searchKeyword") String searchKeyword, @Param("searchKeywordType") String searchKeywordType, @Param("limitFrom") int limitFrom, @Param("limitTake") int limitTake);

}
