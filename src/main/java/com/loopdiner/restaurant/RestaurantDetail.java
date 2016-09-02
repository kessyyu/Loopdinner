package com.loopdiner.restaurant;

import java.util.List;

import com.loopdiner.comments.Comment4Rest;

public class RestaurantDetail{
	
	private int id;
	
	private String name;
	
	private String location;
	
	private String intro;
	
	private String phone;
	
	private String img;
	
	private String situation;
	
	private String date;
	
	private String startNum;
	
	private String endNum;
	
	private String price;
	
	private String email;
	
	private String recomm;
	
	private String stature;
	
	private String like_rest;
	
	private String follow_rest;
	
	private String managerid;
	
	private int userid;
	
	private String username;
	private String userimg;
	
	private Integer likeAmount;
	private Integer followAmount;
	private Integer commentAmount;
	
	private List<MenuGroup> menus;
	private List<StoryRest> storys;
	
	private List<Comment4Rest> comments;

	public RestaurantDetail() {
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

	

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getUserimg() {
		return userimg;
	}



	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}



	public List<Comment4Rest> getComments() {
		return comments;
	}

	public void setComments(List<Comment4Rest> comments) {
		this.comments = comments;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getIntro() {
		return intro;
	}



	public void setIntro(String intro) {
		this.intro = intro;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getImg() {
		return img;
	}



	public void setImg(String img) {
		this.img = img;
	}



	public String getSituation() {
		return situation;
	}



	public void setSituation(String situation) {
		this.situation = situation;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getStartNum() {
		return startNum;
	}



	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}



	public String getEndNum() {
		return endNum;
	}



	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}



	public String getPrice() {
		return price;
	}



	public void setPrice(String price) {
		this.price = price;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getRecomm() {
		return recomm;
	}



	public void setRecomm(String recomm) {
		this.recomm = recomm;
	}



	public String getStature() {
		return stature;
	}



	public void setStature(String stature) {
		this.stature = stature;
	}



	public String getLike_rest() {
		return like_rest;
	}



	public void setLike_rest(String like_rest) {
		this.like_rest = like_rest;
	}



	public String getFollow_rest() {
		return follow_rest;
	}



	public void setFollow_rest(String follow_rest) {
		this.follow_rest = follow_rest;
	}



	public String getManagerid() {
		return managerid;
	}



	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}



	public int getUserid() {
		return userid;
	}



	public void setUserid(int userid) {
		this.userid = userid;
	}



	public List<MenuGroup> getMenus() {
		return menus;
	}



	public void setMenus(List<MenuGroup> menus) {
		this.menus = menus;
	}



	public List<StoryRest> getStorys() {
		return storys;
	}



	public void setStorys(List<StoryRest> storys) {
		this.storys = storys;
	}
	
	
	
}
