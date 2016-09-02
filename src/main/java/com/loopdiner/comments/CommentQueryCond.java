package com.loopdiner.comments;

import com.loopdiner.comman.QueryCond;

public class CommentQueryCond extends QueryCond{
	
	private String commentType;
	private Integer commentId;

	public CommentQueryCond() {
		// TODO Auto-generated constructor stub
	}

	public String getCommentType() {
		return commentType;
	}



	public void setCommentType(String recomm) {
		this.commentType = recomm;
	}

	public Integer getCommentId() {
		return commentId;
	}



	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
		
}
