package com.loopdiner.share;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="share")
public class Share {
	
	@Id
	@GeneratedValue
	@Column(nullable=false)
	private Integer id;
	

	@Column
	private Integer userid;
	
	@Column
	private String date;
	
	@Column
	private String sharecontent;
	
	@Transient
	private List<ShareImg> shareImgs;
	
	@Transient
	private List<ShareTopic> shareTopics;

	public Share() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSharecontent() {
		return sharecontent;
	}

	public void setSharecontent(String sharecontent) {
		this.sharecontent = sharecontent;
	}

	public List<ShareImg> getShareImgs() {
		return shareImgs;
	}

	public void setShareImgs(List<ShareImg> shareImgs) {
		this.shareImgs = shareImgs;
	}

	public List<ShareTopic> getShareTopics() {
		return shareTopics;
	}

	public void setShareTopics(List<ShareTopic> shareTopics) {
		this.shareTopics = shareTopics;
	}
	
	
}
