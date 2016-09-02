package com.loopdiner.share;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loopdiner.comman.BackResult;
import com.loopdiner.comman.QueryCond;

@Controller
@RequestMapping("/Share")
public class ShareController {
	
	@Resource
	private ShareService shareService;

	public ShareController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/saveShare", method = RequestMethod.POST)
    @ResponseBody
	public BackResult saveShare(@RequestBody Share share){
		BackResult backResult = new BackResult();
		backResult.setStatus("success");
		try {
			shareService.saveShare(share);
		} catch (Exception e) {
			backResult.setStatus("fail");
			e.printStackTrace();
		}
		return backResult;
	}
	
	@RequestMapping(value = "/updateShare", method = RequestMethod.POST)
    @ResponseBody
	public BackResult updateShare(@RequestBody Share share){
		BackResult backResult = new BackResult();
		backResult.setStatus("success");
		try {
			shareService.updateShare(share);
		} catch (Exception e) {
			backResult.setStatus("fail");
			e.printStackTrace();
		}
		return backResult;
	}
	
	@RequestMapping(value = "/getShareList", method = RequestMethod.POST)
    @ResponseBody
	public ShareResultList getShareList(@RequestBody QueryCond queryCond){
		ShareResultList shareResultList = new ShareResultList();
		shareResultList.setShares(shareService.getShareList(queryCond));
		return shareResultList;
	}
	
	@RequestMapping(value = "/getShareDetailMsg", method = RequestMethod.POST)
    @ResponseBody
	public ShareDetail getShareDetailMsg(@RequestBody ShareQueryCond shareQueryCond){
		ShareDetail shareDetail = shareService.getShareDetail(shareQueryCond.getShareid());
		return shareDetail;
	}

}
