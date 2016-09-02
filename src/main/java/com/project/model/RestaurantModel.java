package com.project.model;

import java.util.List;

import com.loopdiner.comments.Comment4Rest;
import com.loopdiner.restaurant.FollowRestResult;
import com.loopdiner.restaurant.RestQueryCondition;
import com.loopdiner.restaurant.RestaurantDetail;
import com.loopdiner.restaurant.RestuarantResult;
import com.project.domain.Restaurant;

public interface RestaurantModel extends BaseModel<Restaurant>{
	public List<Restaurant> queryPopularRest(String recomm);
	
	public List<RestuarantResult> getRecommRestMsg(RestQueryCondition rstQueryCondition);
	
	public List<FollowRestResult> getUserFollowRestMsg(RestQueryCondition rstQueryCondition);
	
	/**
	 * update part of properties
	 * @param restaurant
	 */
	public void updateRestuarant(Restaurant restaurant);
	
	/**
	 * get detail of a restaurant
	 * @param restaurantId 
	 * @return
	 */
	public RestaurantDetail getRestDetailMsg(Integer restaurantId);
	
	public List<Comment4Rest> getTwoCommentsByRestId(Integer restaurantId);
}
