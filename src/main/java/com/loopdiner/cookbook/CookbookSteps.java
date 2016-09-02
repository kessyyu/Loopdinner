package com.loopdiner.cookbook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cookbook_steps")
public class CookbookSteps {
	
	@Id
	@GeneratedValue
	@Column(nullable=false)
	private Integer id;
	
	@Column
	private String img;
	
	@Column
	private String content;
	
	@Column
	private Integer cookbookid;
	
	@Column
	private Integer queueId;

	public CookbookSteps() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCookbookid() {
		return cookbookid;
	}

	public void setCookbookid(Integer cookbookid) {
		this.cookbookid = cookbookid;
	}

	public Integer getQueueId() {
		return queueId;
	}

	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}
	
	

}
