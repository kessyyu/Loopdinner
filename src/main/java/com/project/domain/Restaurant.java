package com.project.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.loopdiner.restaurant.MenuTable;
import com.loopdiner.restaurant.StoryRest;



@Entity
@Table(name="restaurant")
public class Restaurant {
	
	@Id
	@GeneratedValue
	@Column(name="id" ,nullable=false)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="location")
	private String location;
	
	@Column(name="intro")
	private String intro;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="img")
	private String img;
	
	@Column(name="situation")
	private String situation;
	
	@Column(name="date")
	private String date;
	
	@Column(name="startNum")
	private String startNum;
	
	@Column(name="endNum")
	private String endNum;
	
	@Column(name="price")
	private String price;
	
	@Column(name="email")
	private String email;
	
	@Column(name="recomm")
	private String recomm;
	
	@Column(name="stature")
	private String stature;
	
	@Column(name="like_rest")
	private String like_rest;
	
	@Column(name="follow_rest")
	private String follow_rest;
	
	@Column(name="managerid")
	private String managerid;
	
	@Column(nullable=false)
	private int userid;
	

	@OneToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER,mappedBy="rest_order")
	//@JsonManagedReference
	//@JsonBackReference
	  //@JsonIgnore
	private Set<Order> orders;
	
	//@OneToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER,mappedBy="rest_menu")
	//@JsonManagedReference
	//@JsonBackReference
	  //@JsonIgnore
	//private Set<MenuTable> menus;
	
	@Transient
	private List<MenuTable> menus;
	
	@OneToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER,mappedBy="rest_manager")
	//@JsonManagedReference
	//@JsonBackReference
	  //@JsonIgnore
	private Set<ManagerRest> managersrest;
	
	/*
	@OneToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER,mappedBy="rest_story")
	//@JsonManagedReference
	//@JsonBackReference
	 //@JsonIgnore
	private Set<StoryRest> storys;
	*/
	@Transient
	private List<StoryRest> storys;
	
	
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

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	/*
	public Set<MenuTable> getMenus() {
		return menus;
	}

	public void setMenus(Set<MenuTable> menus) {
		this.menus = menus;
	}
	*/

	public Set<ManagerRest> getManagersrest() {
		return managersrest;
	}

	public void setManagersrest(Set<ManagerRest> managersrest) {
		this.managersrest = managersrest;
	}

	/*
	public Set<StoryRest> getStorys() {
		return storys;
	}

	public void setStorys(Set<StoryRest> storys) {
		this.storys = storys;
	}
	*/

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

	public List<MenuTable> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuTable> menus) {
		this.menus = menus;
	}

	public List<StoryRest> getStorys() {
		return storys;
	}

	public void setStorys(List<StoryRest> storys) {
		this.storys = storys;
	}
	
	

}
