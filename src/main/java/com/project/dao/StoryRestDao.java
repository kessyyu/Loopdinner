package com.project.dao;

import com.loopdiner.restaurant.StoryRest;

public interface StoryRestDao extends BaseDao<StoryRest>{
	
	public void deleteByRestaurantId(Integer restuanrantId);

}
