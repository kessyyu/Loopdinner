package com.loopdiner.restaurant;

public class RestQueryCondition {
	
	private String recomm;
	
	private Integer pageindex;
	private Integer pageCount;
	
	private Integer userid;
	
	private Integer restaurantid;

	public RestQueryCondition() {
		// TODO Auto-generated constructor stub
	}
	
	

	public String getRecomm() {
		return recomm;
	}



	public void setRecomm(String recomm) {
		this.recomm = recomm;
	}



	public Integer getPageindex() {
		return pageindex;
	}

	public void setPageindex(Integer pageindex) {
		this.pageindex = pageindex;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}



	public Integer getUserid() {
		return userid;
	}



	public void setUserid(Integer userid) {
		this.userid = userid;
	}



	public Integer getRestaurantid() {
		return restaurantid;
	}



	public void setRestaurantid(Integer restaurantid) {
		this.restaurantid = restaurantid;
	}
	
	
	
}
