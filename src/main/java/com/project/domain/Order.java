package com.project.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;




@Entity
@Table(name="order_menu")
public class Order {
	
	@Id
	@GeneratedValue
	@Column(name="id" ,nullable=false)
	private int id;
	
	@Column(name="date")
	private String date;
	
	@Column(name="price")
	private String price;
	
	@Column(name="items")
	private String items;
	
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id")
	//@JsonBackReference
	@JsonIgnore  
	//@JsonManagedReference
	private User user_order;
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
	//@JsonBackReference
	//@JsonIgnore 
	//@JsonManagedReference
	@JsonIgnore  
	private Restaurant rest_order;
	
	@OneToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER,mappedBy="ordertocomm")
//	@JsonManagedReference
	//@JsonBackReference
	//@JsonIgnore
	private Set<OrderComm>ordercomms;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public User getUser_order() {
		return user_order;
	}

	public void setUser_order(User user_order) {
		this.user_order = user_order;
	}

	public Restaurant getRest_order() {
		return rest_order;
	}

	public void setRest_order(Restaurant rest_order) {
		this.rest_order = rest_order;
	}

	public Set<OrderComm> getOrdercomms() {
		return ordercomms;
	}

	public void setOrdercomms(Set<OrderComm> ordercomms) {
		this.ordercomms = ordercomms;
	}
	
}
