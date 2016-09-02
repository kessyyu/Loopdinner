package com.loopdiner.cookbook;

import java.util.List;

import com.loopdiner.comments.Comment4Rest;

public class CookbookDetail extends Cookbook {
	
	private String username;
	
	private Integer likeAmount;
	private Integer followAmount;
	private Integer commentAmount;
	private Integer replyAmount;
	
	private List<Comment4Rest> comments;

	public CookbookDetail() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Integer getLikeAmount() {
		return likeAmount;
	}



	public void setLikeAmount(Integer likeAmount) {
		this.likeAmount = likeAmount;
	}

	

	public Integer getFollowAmount() {
		return followAmount;
	}

	public void setFollowAmount(Integer followAmount) {
		this.followAmount = followAmount;
	}
	
	

	public Integer getCommentAmount() {
		return commentAmount;
	}



	public void setCommentAmount(Integer commentAmount) {
		this.commentAmount = commentAmount;
	}



	public List<Comment4Rest> getComments() {
		return comments;
	}

	public void setComments(List<Comment4Rest> comments) {
		this.comments = comments;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public Integer getReplyAmount() {
		return replyAmount;
	}



	public void setReplyAmount(Integer replyAmount) {
		this.replyAmount = replyAmount;
	}
	
	
	
}
