package com.loopdiner.comments;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service(value="likeFollowService")
public class LikeFollowService {
	
	@Resource
	private LikeFollowDao likeFollowDao;

	public LikeFollowService() {
		// TODO Auto-generated constructor stub
	}
	
	public LikeFollow saveLikeFollow(LikeFollow likeFollow){
		return likeFollowDao.saveLikeFollow(likeFollow);
	}

}
