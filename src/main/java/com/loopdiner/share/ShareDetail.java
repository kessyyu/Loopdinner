package com.loopdiner.share;

public class ShareDetail extends Share {
	
	private String userimg;
	private String username;
	
	private Integer likeAmount;
	private Integer followAmount;
	private Integer commentAmount;
	private Integer replyAmount;

	public ShareDetail() {
		// TODO Auto-generated constructor stub
	}

	public String getUserimg() {
		return userimg;
	}

	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Integer getReplyAmount() {
		return replyAmount;
	}

	public void setReplyAmount(Integer replyAmount) {
		this.replyAmount = replyAmount;
	}
	
}
