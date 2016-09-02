package com.loopdiner.cookbook;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loopdiner.comman.BackResult;
import com.loopdiner.comman.QueryCond;
import com.loopdiner.restaurant.RestQueryCondition;
import com.loopdiner.restaurant.RestaurantDetail;

@Controller
@RequestMapping("/Cookbook")
public class CookbookController {
	
	@Resource
	private CookbookService cookbookService;

	public CookbookController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/saveCookbook", method = RequestMethod.POST)
    @ResponseBody
	public BackResult saveCookbook(@RequestBody Cookbook cookbook){
		BackResult backResult = new BackResult();
		backResult.setStatus("success");
		try {
			cookbookService.saveCookbook(cookbook);
		} catch (Exception e) {
			backResult.setStatus("fail");
			e.printStackTrace();
		}
		return backResult;
	}
	
	@RequestMapping(value = "/updateCookbook", method = RequestMethod.POST)
    @ResponseBody
	public BackResult updateCookbook(@RequestBody Cookbook cookbook){
		BackResult backResult = new BackResult();
		backResult.setStatus("success");
		try {
			cookbookService.updateCookbook(cookbook);
		} catch (Exception e) {
			backResult.setStatus("fail");
			e.printStackTrace();
		}
		return backResult;
	}
	
	@RequestMapping(value = "/getCookbookMsg", method = RequestMethod.POST)
    @ResponseBody
	public CookbookResultList getCookbookMsg(@RequestBody QueryCond queryCond){
		CookbookResultList cookbookResultList = new CookbookResultList();
		cookbookResultList.setCookbooks(cookbookService.getCookbookMsg(queryCond));
		return cookbookResultList;
	}
	

	@RequestMapping(value = "/getCookbookDetailMsg", method = RequestMethod.POST)
    @ResponseBody
	public CookbookDetail getCookbookDetailMsg(@RequestBody CookQueryCond cookQueryCond){
		CookbookDetail cookbookDetail = cookbookService.getCookbookDetailMsg(cookQueryCond.getCookbookid());
		return cookbookDetail;
	}

}
