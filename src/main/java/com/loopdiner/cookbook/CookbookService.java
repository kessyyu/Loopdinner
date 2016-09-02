package com.loopdiner.cookbook;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.loopdiner.comman.LdConstants;
import com.loopdiner.comman.QueryCond;
import com.loopdiner.comments.Comment4Rest;
import com.loopdiner.comments.CommentDao;
import com.loopdiner.comments.LikeFollowDao;
import com.project.dao.UserDao;
import com.project.domain.User;

@Service(value="cookbookService")
public class CookbookService {
	
	@Resource
	private CookbookDao cookbookDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private CommentDao commentDao;
	
	@Resource(name="likeFollowDao")
	private LikeFollowDao likeFollowDao;

	public CookbookService() {
		// TODO Auto-generated constructor stub
	}
	
	public Cookbook saveCookbook(Cookbook cookbook){
		return cookbookDao.saveCookbook(cookbook);
	}
	
	public Cookbook updateCookbook(Cookbook cookbook){
		return cookbookDao.updateCookbook(cookbook);
	}
	
	public List<CookbookResult> getCookbookMsg(QueryCond queryCond){
		return cookbookDao.getCookbookMsg(queryCond);
	}
	
	public CookbookDetail getCookbookDetailMsg(Integer cookbookId) {
		Cookbook cookbook = cookbookDao.getCookbook(cookbookId);
		CookbookDetail cookbookDetail = new CookbookDetail();
		BeanUtils.copyProperties(cookbook, cookbookDetail);
		
		User user = userDao.queryOne("User", cookbookDetail.getUserid());
		cookbookDetail.setUsername(user.getName());
		

		List<Comment4Rest> comments = this.getTwoCommentsByCookId(cookbookId);
		cookbookDetail.setComments(comments);
		
		Integer likeAmount = likeFollowDao.getLikeAmount(
				LdConstants.COMMENT_TYPE_COOKBOOK,cookbookId);
		cookbookDetail.setLikeAmount(likeAmount);
		Integer followAmount = likeFollowDao.getFollowAmount(
				LdConstants.COMMENT_TYPE_COOKBOOK,cookbookId);
		cookbookDetail.setFollowAmount(followAmount);
		Integer commentAmount = commentDao.getCommentAmount(
				LdConstants.COMMENT_TYPE_COOKBOOK,cookbookId);
		cookbookDetail.setCommentAmount(commentAmount);
		
		cookbookDetail.setReplyAmount(new Integer(0));
		
		return cookbookDetail;
	}
	
	
	private List<Comment4Rest> getTwoCommentsByCookId(Integer cookId) {
		List<Comment4Rest> comments = new ArrayList<Comment4Rest>();
		Comment4Rest comment4Rest1 = commentDao.getOneCommentByRestId(
				LdConstants.COMMENT_TYPE_COOKBOOK,cookId, "asc");
		Comment4Rest comment4Rest2 = null;
		if(comment4Rest1 != null){
			comments.add(comment4Rest1);
			comment4Rest2 = commentDao.getOneCommentByRestId(
					LdConstants.COMMENT_TYPE_COOKBOOK,cookId, "desc");
			if(comment4Rest2!=null && (!comment4Rest1.getId().equals(comment4Rest2.getId()))){
				comments.add(comment4Rest2);
			}
		}
		return comments;
	}

}
