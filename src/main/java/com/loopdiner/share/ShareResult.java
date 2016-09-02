package com.loopdiner.share;

import java.util.List;

public class ShareResult {

	public ShareResult() {
		// TODO Auto-generated constructor stub
	}
	
	private String sharecontent;
	private String img;
	private String userimg;
	private Integer userid;
	private String username;
	private Integer id;
	
	private List<ShareTopic> topics;
	
	
	public String getSharecontent() {
		return sharecontent;
	}
	public void setSharecontent(String sharecontent) {
		this.sharecontent = sharecontent;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUserimg() {
		return userimg;
	}
	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<ShareTopic> getTopics() {
		return topics;
	}
	public void setTopics(List<ShareTopic> topics) {
		this.topics = topics;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
