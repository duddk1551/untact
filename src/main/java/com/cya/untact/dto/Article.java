package com.cya.untact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private int boardId;
    private int memberId;
    private String title;
	private String content;
    private boolean blindStatus;
    private String blindDate;
    private boolean delStatus;
    private String delDate;
    private int hitCount;
    private int repliesCount;
    private int likeCount;
    private int dislikeCount;
    
    private String extra__writerName;
    
    public String getContentForPrint() {
    	
    	String contnetForPrint = content.replaceAll("\r\n", "<br>");
    	contnetForPrint = contnetForPrint.replaceAll("\r", "<br>");
    	contnetForPrint = contnetForPrint.replaceAll("\n", "<br>");
    	
    	return contnetForPrint;
    }
	
}
