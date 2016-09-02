package com.project.model.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.loopdiner.restaurant.StoryRest;
import com.project.dao.StoryRestDao;
import com.project.model.StroyRestModel;
@Service(value="storyService")
public class StoryRestModelImpl extends BaseModelImpl<StoryRest> implements StroyRestModel{
	@Resource(name="storyDao")
	private StoryRestDao storyDao;
	
	public void deleteByRestaurantId(Integer restuanrantId) {
		storyDao.deleteByRestaurantId(restuanrantId);
	}
}
