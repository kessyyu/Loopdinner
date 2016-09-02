package com.loopdiner.cookbook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cookbook_material")
public class CookbookMaterial {
	
	@Id
	@GeneratedValue
	@Column(nullable=false)
	private Integer id;
	
	@Column
	private String type;
	
	@Column
	private String weight;
	
	@Column
	private Integer cookbookid;
	
	@Column
	private Integer queueId;

	public CookbookMaterial() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
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
