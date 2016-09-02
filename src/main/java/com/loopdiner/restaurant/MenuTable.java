package com.loopdiner.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="menutable")
public class MenuTable {
	
	@Id
	@GeneratedValue
	@Column(name="id" ,nullable=false)
	private int id;
	
	@Column(name="name")
	private String name;

	@Column(name="menu_desc")
	private String menu_desc;
	
	@Column(name="menu_price")
	private String menu_price;
	
	@Column(name="ordernumber")
	private int ordernumber;
	
	@Column(name="img_menu")
	private String img_menu;
	
	@Column(name="meterial")
	private String material;
	
	@Column(name="stature")
	private String stature;
	
	@Column
	private Integer restaurant_id;
	
	@Column
	private Integer queueId;
	
	/*
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
	@JsonIgnore  
	private Restaurant rest_menu;
	*/
	

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

	public String getMenu_desc() {
		return menu_desc;
	}

	public void setMenu_desc(String menu_desc) {
		this.menu_desc = menu_desc;
	}

	public String getMenu_price() {
		return menu_price;
	}

	public void setMenu_price(String menu_price) {
		this.menu_price = menu_price;
	}

	/*
	public Restaurant getRest_menu() {
		return rest_menu;
	}

	public void setRest_menu(Restaurant rest_menu) {
		this.rest_menu = rest_menu;
	}
	*/

	public int getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getImg_menu() {
		return img_menu;
	}

	public void setImg_menu(String img_menu) {
		this.img_menu = img_menu;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getStature() {
		return stature;
	}

	public void setStature(String stature) {
		this.stature = stature;
	}

	public Integer getQueueId() {
		return queueId;
	}

	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}

	public Integer getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Integer restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	
	
}
