package com.loopdiner.share;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.loopdiner.comman.LdConstants;
import com.loopdiner.comman.QueryCond;
import com.loopdiner.comments.CommentDao;
import com.loopdiner.comments.LikeFollowDao;
import com.project.dao.UserDao;
import com.project.domain.User;

@Service(value="shareService")
public class ShareService {
	
	@Resource
	private ShareDao shareDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private CommentDao commentDao;
	
	@Resource
	private LikeFollowDao likeFollowDao;

	public ShareService() {
		// TODO Auto-generated constructor stub
	}
	
	public Share saveShare(Share share){
		return shareDao.saveShare(share);
	}
	
	public Share updateShare(Share share){
		return shareDao.updateShare(share);
	}
	
	public List<ShareResult> getShareList(QueryCond queryCond){
		List<ShareResult> shareList = shareDao.getShareList(queryCond);
		for(ShareResult shareResult:shareList){
			ShareImg oneShareImg = shareDao.getOneImg(shareResult.getId());
			shareResult.setImg(oneShareImg.getImg());
			shareResult.setTopics(shareDao.getTopics(shareResult.getId()));
		}
		return shareList;
	}
	
	public ShareDetail getShareDetail(Integer shareId) {
		Share share = shareDao.getShare(shareId);
		ShareDetail shareDetail = new ShareDetail();
		BeanUtils.copyProperties(share, shareDetail);
		
		User user = userDao.queryOne("User", shareDetail.getUserid());
		shareDetail.setUsername(user.getName());
		shareDetail.setUserimg(user.getImg());
		
		
		Integer likeAmount = likeFollowDao.getLikeAmount(
				LdConstants.COMMENT_TYPE_SHARE,shareId);
		shareDetail.setLikeAmount(likeAmount);
		Integer followAmount = likeFollowDao.getFollowAmount(
				LdConstants.COMMENT_TYPE_COOKBOOK,shareId);
		shareDetail.setFollowAmount(followAmount);
		Integer commentAmount = commentDao.getCommentAmount(
				LdConstants.COMMENT_TYPE_COOKBOOK,shareId);
		shareDetail.setCommentAmount(commentAmount);
		
		shareDetail.setReplyAmount(new Integer(0));
		
		return shareDetail;
	}

}
