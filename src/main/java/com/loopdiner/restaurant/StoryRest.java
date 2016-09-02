package com.loopdiner.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="story_rest")
public class StoryRest {
	@Id
	@GeneratedValue
	@Column(name="id" ,nullable=false)
	private int id;
	
	@Column(name="story_content")
	private String story_content;
	
	@Column(name="img")
	private String img;
	
	/*
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
	//@JsonBackReference
	@JsonIgnore 
	//@JsonManagedReference
	private Restaurant rest_story;
	*/
	
	@Column
	private Integer restaurant_id;
	
	@Column
	private Integer queueId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStory_content() {
		return story_content;
	}

	public void setStory_content(String story_content) {
		this.story_content = story_content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Integer restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public Integer getQueueId() {
		return queueId;
	}

	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}
	
	

	/*
	public Restaurant getRest_story() {
		return rest_story;
	}

	public void setRest_story(Restaurant rest_story) {
		this.rest_story = rest_story;
	}
	
	*/
	

}
