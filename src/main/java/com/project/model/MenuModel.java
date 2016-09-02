package com.project.model;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.loopdiner.restaurant.MenuTable;

public interface MenuModel extends BaseModel<MenuTable>{
	//public List<MenuTable> listMenuForRest(int id);
	
	public void deleteByRestaurantId(Integer restuanrantId);
}
