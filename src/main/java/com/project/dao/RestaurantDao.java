package com.project.dao;

import java.util.List;

import com.loopdiner.restaurant.FollowRestResult;
import com.loopdiner.restaurant.RestQueryCondition;
import com.loopdiner.restaurant.RestuarantResult;
import com.project.domain.Restaurant;

public interface RestaurantDao extends BaseDao<Restaurant>{
	public List<Restaurant> queryPopularRest(String recomm);
	
	public List<RestuarantResult> getRecommRestMsg(RestQueryCondition rstQueryCondition);
	
	public List<FollowRestResult> getUserFollowRestMsg(RestQueryCondition rstQueryCondition);
	
	/**
	 * update part of properties
	 * @param restaurant
	 */
	public void updateRestuarant(Restaurant restaurant);

}
