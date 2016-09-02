package com.loopdiner.restaurant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loopdiner.comman.BackResult;
import com.loopdiner.comman.LdConstants;
import com.loopdiner.comman.LdUtils;
import com.loopdiner.comments.LikeFollow;
import com.loopdiner.comments.LikeFollowService;
import com.project.domain.Restaurant;
import com.project.model.MenuModel;
import com.project.model.StroyRestModel;

@Controller
@RequestMapping("/Restaurant")
public class PrivRestController {
	
	@Resource(name = "restService")
	private RestaurantService restService;
	
	@Resource(name = "storyService")
	private StroyRestModel storyService;
	
	@Resource(name = "menuService")
	private MenuModel menuService;
	
	@Resource
	private LikeFollowService likeFollowService;

	/**
	 * 
	 */
	public PrivRestController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/saveRestMsg", method = RequestMethod.POST)
    @ResponseBody
	public BackResult saveRestMsg(@RequestBody Restaurant restaurant)
	{
		System.out.println("aaaaaaaaaa...............");
		BackResult backResult = new BackResult();
		backResult.setStatus("success");
		try {
			//LdUtils ldUtils = new LdUtils();
			String picUrl = LdUtils.getPicUrl(restaurant.getImg());
			restaurant.setImg(picUrl);
			Restaurant addedRestaurant = restService.add(restaurant);
			
			Iterator<StoryRest> itr = restaurant.getStorys().iterator();
			List<StoryRest> storyList = new ArrayList<StoryRest>();
			while (itr.hasNext()) {
				storyList.add(itr.next());
			}
			for (int k = 0; k < storyList.size(); k++) {
				StoryRest storyRest = storyList.get(k);
				//storyRest.setRest_story(addedRestaurant);
				storyRest.setImg(LdUtils.getPicUrl(storyRest.getImg()));
				storyRest.setRestaurant_id(addedRestaurant.getId());
				storyService.add(storyRest);
			}
			
			Iterator<MenuTable> menus = restaurant.getMenus().iterator();
			List<MenuTable> menuList = new ArrayList<MenuTable>();
			while (menus.hasNext()) {
				menuList.add(menus.next());
			}
			for (int l = 0; l < menuList.size(); l++) {
				MenuTable menuTable = menuList.get(l);
				//menuTable.setRest_menu(addedRestaurant);
				menuTable.setRestaurant_id(addedRestaurant.getId());
				menuService.add(menuTable);
			}
			
			LikeFollow likeFollow = new LikeFollow();
			likeFollow.setContentId(addedRestaurant.getId());
			likeFollow.setContentType(LdConstants.COMMENT_TYPE_REATAURANT);
			likeFollow.setType(LdConstants.LIKE_TYPE_FOLLOW);
			likeFollow.setUserid(addedRestaurant.getUserid());
			likeFollowService.saveLikeFollow(likeFollow);
			
		} catch (Exception e) {
			backResult.setStatus("fail");
			e.printStackTrace();
		}
		return backResult;
	}
	
	@RequestMapping(value = "/updateRestMsg", method = RequestMethod.POST)
    @ResponseBody
	public BackResult updateRestMsg(@RequestBody Restaurant restaurant)
	{
		System.out.println("updateRestMsg...............");
		BackResult backResult = new BackResult();
		backResult.setStatus("success");
		try {
			restaurant.setImg(LdUtils.getPicUrl(restaurant.getImg()));
			restService.updateRestuarant(restaurant);
			
			storyService.deleteByRestaurantId(restaurant.getId());			
			Iterator<StoryRest> itr = restaurant.getStorys().iterator();
			List<StoryRest> storyList = new ArrayList<StoryRest>();
			while (itr.hasNext()) {
				storyList.add(itr.next());
			}
			for (int k = 0; k < storyList.size(); k++) {
				StoryRest storyRest = storyList.get(k);
				//storyRest.setRest_story(restaurant);
				storyRest.setImg(LdUtils.getPicUrl(storyRest.getImg()));
				storyRest.setRestaurant_id(restaurant.getId());
				storyService.add(storyRest);
			}
			
			menuService.deleteByRestaurantId(restaurant.getId());
			Iterator<MenuTable> menus = restaurant.getMenus().iterator();
			List<MenuTable> menuList = new ArrayList<MenuTable>();
			while (menus.hasNext()) {
				menuList.add(menus.next());
			}
			for (int l = 0; l < menuList.size(); l++) {
				MenuTable menuTable = menuList.get(l);
				//menuTable.setRest_menu(restaurant);
				menuTable.setRestaurant_id(restaurant.getId());
				menuService.add(menuTable);
			}
			
		} catch (Exception e) {
			backResult.setStatus("fail");
			e.printStackTrace();
		}
		return backResult;
	}
	
	@RequestMapping(value = "/getRecommRestMsg", method = RequestMethod.POST)
    @ResponseBody
	public RestuarantResultList getRecommRestMsg(@RequestBody RestQueryCondition rstQueryCondition){
		rstQueryCondition.setRecomm("true");
		List<RestuarantResult> restRecommList = restService.getRecommRestMsg(rstQueryCondition);
		RestuarantResultList restuarantResultList = new RestuarantResultList();
		restuarantResultList.setRests(restRecommList);
		return restuarantResultList;
	}
	
	@RequestMapping(value = "/getUserFollowRestMsg", method = RequestMethod.POST)
    @ResponseBody
	public RestuarantResultList getUserFollowRestMsg(@RequestBody RestQueryCondition rstQueryCondition){
		List<FollowRestResult> restRecommList = restService.getUserFollowRestMsg(rstQueryCondition);
		RestuarantResultList restuarantResultList = new RestuarantResultList();
		restuarantResultList.setRests(restRecommList);
		return restuarantResultList;
	}
	
	@RequestMapping(value = "/getRestDetailMsg", method = RequestMethod.POST)
    @ResponseBody
	public RestaurantDetail getRestDetailMsg(@RequestBody RestQueryCondition rstQueryCondition){
		RestaurantDetail restaurantDetail = restService.getRestDetailMsg(rstQueryCondition.getRestaurantid());
		return restaurantDetail;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
