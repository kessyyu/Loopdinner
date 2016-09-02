package com.loopdiner.restaurant;

import java.util.List;

public class MenuGroup {
	
	private String menu_desc;
	private List<MenuTable> menu_content;

	public MenuGroup() {
		// TODO Auto-generated constructor stub
	}

	public String getMenu_desc() {
		return menu_desc;
	}

	public void setMenu_desc(String menu_desc) {
		this.menu_desc = menu_desc;
	}

	public List<MenuTable> getMenu_content() {
		return menu_content;
	}

	public void setMenu_content(List<MenuTable> menu_content) {
		this.menu_content = menu_content;
	}

}
