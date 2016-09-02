package com.loopdiner.restaurant;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.loopdiner.comman.LdConstants;
import com.loopdiner.comments.Comment4Rest;
import com.loopdiner.comments.CommentDao;
import com.loopdiner.comments.LikeFollowDao;
import com.project.dao.UserDao;
import com.project.domain.Restaurant;
import com.project.domain.User;
import com.project.model.impl.BaseModelImpl;

@Service(value="restService")
public class RestaurantService extends BaseModelImpl<Restaurant>{
	
	@Resource(name="restDao")
	private RestDao restDao;
	
	@Resource(name="commentDao")
	private CommentDao commentDao;
	
	
	@Resource(name="likeFollowDao")
	private LikeFollowDao likeFollowDao;
	
	@Resource
	private UserDao userDao;

	
	public List<Restaurant> queryPopularRest(String recomm) {
		return restDao.queryPopularRest(recomm);
	}

	public List<RestuarantResult> getRecommRestMsg(RestQueryCondition rstQueryCondition) {
		return restDao.getRecommRestMsg(rstQueryCondition);
	}

	
	public void updateRestuarant(Restaurant restaurant) {
		restDao.updateRestuarant(restaurant);		
	}

	
	public List<FollowRestResult> getUserFollowRestMsg(RestQueryCondition rstQueryCondition) {
		return restDao.getUserFollowRestMsg(rstQueryCondition);
	}

	
	public RestaurantDetail getRestDetailMsg(Integer restaurantId) {
		//Restaurant restaurant = restDao.queryOne("Restaurant", restaurantId);
		Restaurant restaurant = restDao.getRestaurantById(restaurantId);
		RestaurantDetail restaurantDetail = new RestaurantDetail();
		BeanUtils.copyProperties(restaurant, restaurantDetail);
		
		restaurantDetail.setMenus(null);
		/*
		List<MenuGroup> menuGroupList = new ArrayList<MenuGroup>();
		MenuGroup preMenuGroup = null;
		for(MenuTable menuTable : restaurant.getMenus()){
			if(preMenuGroup==null){
				preMenuGroup = new MenuGroup();
				preMenuGroup.setMenu_content(new ArrayList<MenuTable>());
				preMenuGroup.setMenu_desc(menuTable.getMenu_desc());
			}
			if (menuTable.getMenu_desc().equals(preMenuGroup.getMenu_desc())) {
				preMenuGroup.getMenu_content().add(menuTable);
			} else {
				preMenuGroup = new MenuGroup();
				preMenuGroup.setMenu_content(new ArrayList<MenuTable>());
				preMenuGroup.setMenu_desc(menuTable.getMenu_desc());
				preMenuGroup.getMenu_content().add(menuTable);
				menuGroupList.add(preMenuGroup);
			}
		}
		restaurantDetail.setMenus(menuGroupList);
		*/
		
		Map<String, List<MenuTable>> map = new LinkedHashMap<String, List<MenuTable>>();
		for(MenuTable menuTable : restaurant.getMenus()){
			if(!map.containsKey(menuTable.getMenu_desc())){
				List<MenuTable> menuList = new ArrayList<MenuTable>();
				map.put(menuTable.getMenu_desc(), menuList);
			}
			map.get(menuTable.getMenu_desc()).add(menuTable);
		}
		List<MenuGroup> menuGroupList = new ArrayList<MenuGroup>();
		for(String key: map.keySet()){
			MenuGroup menuGroup = new MenuGroup();
			menuGroup.setMenu_desc(key);
			menuGroup.setMenu_content(map.get(key));
			menuGroupList.add(menuGroup);
		}
		restaurantDetail.setMenus(menuGroupList);
		
		User user = userDao.queryOne("User", restaurantDetail.getUserid());
		restaurantDetail.setUsername(user.getName());
		restaurantDetail.setUserimg(user.getImg());
		
		List<Comment4Rest> comments = this.getTwoCommentsByRestId(restaurantId);
		restaurantDetail.setComments(comments);
		Integer likeAmount = likeFollowDao.getLikeAmount(
				LdConstants.COMMENT_TYPE_REATAURANT,restaurantId);
		restaurantDetail.setLikeAmount(likeAmount);
		Integer followAmount = likeFollowDao.getFollowAmount(
				LdConstants.COMMENT_TYPE_REATAURANT,restaurantId);
		restaurantDetail.setFollowAmount(followAmount);
		Integer commentAmount = commentDao.getCommentAmount(
				LdConstants.COMMENT_TYPE_REATAURANT,restaurantId);
		restaurantDetail.setCommentAmount(commentAmount);
		return restaurantDetail;
	}

	
	public List<Comment4Rest> getTwoCommentsByRestId(Integer restaurantId) {
		List<Comment4Rest> comments = new ArrayList<Comment4Rest>();
		Comment4Rest comment4Rest1 = commentDao.getOneCommentByRestId(
				LdConstants.COMMENT_TYPE_REATAURANT,restaurantId, "asc");
		Comment4Rest comment4Rest2 = null;
		if(comment4Rest1 != null){
			comments.add(comment4Rest1);
			comment4Rest2 = commentDao.getOneCommentByRestId(
					LdConstants.COMMENT_TYPE_REATAURANT,restaurantId, "desc");
			if(comment4Rest2!=null && (!comment4Rest1.getId().equals(comment4Rest2.getId()))){
				comments.add(comment4Rest2);
			}
		}
		return comments;
	}
	
}
