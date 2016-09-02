package com.project.dao;

import com.loopdiner.restaurant.MenuTable;

public interface MenuDao extends BaseDao<MenuTable>{
	//public List<MenuTable>listMenuForRest(int id);
	
	public void deleteByRestaurantId(Integer restuanrantId);
}
