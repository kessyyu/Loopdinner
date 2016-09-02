package com.project.model;

import com.loopdiner.restaurant.StoryRest;

public interface StroyRestModel extends BaseModel<StoryRest>{
	public void deleteByRestaurantId(Integer restuanrantId);
}
