package com.loopdiner.cookbook;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="cookbook")
public class Cookbook {
	
	@Id
	@GeneratedValue
	@Column(nullable=false)
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String img;
	
	@Column
	private String intro;
	
	@Column
	private Integer userid;
	
	@Column
	private String date;
	
	@Transient
	private List<CookbookMaterial> materials;
	
	@Transient
	private List<CookbookSteps> steps;
	
	public Cookbook() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public List<CookbookMaterial> getMaterials() {
		return materials;
	}

	public void setMaterials(List<CookbookMaterial> materials) {
		this.materials = materials;
	}

	public List<CookbookSteps> getSteps() {
		return steps;
	}

	public void setSteps(List<CookbookSteps> steps) {
		this.steps = steps;
	}
		
}


