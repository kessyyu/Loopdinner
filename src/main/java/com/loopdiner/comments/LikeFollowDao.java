package com.loopdiner.comments;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.impl.BaseDaoImpl;

@Repository(value="likeFollowDao")
@Transactional
public class LikeFollowDao extends BaseDaoImpl<LikeFollow>{

	public LikeFollowDao() {
	}
	
	public Integer getLikeAmount(String contentType,Integer contentId){
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql = new StringBuffer();
		sql.append("select count(id) from like_follow ")
				.append("where type='like' and contentType=:contentType and contentId=:contentId ");
		Query query = session.createSQLQuery(sql.toString())
				.setParameter("contentType", contentType)
				.setParameter("contentId", contentId);
		
		@SuppressWarnings("unchecked")
		List<Object> result = query.list();
		BigInteger count = (BigInteger)(result.get(0));
		return count.intValue();
	}
	
	public Integer getFollowAmount(String contentType,Integer contentId){
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql = new StringBuffer();
		sql.append("select count(id) from like_follow ")
				.append("where type='follow' and contentType=:contentType and contentId=:contentId ");
		Query query = session.createSQLQuery(sql.toString())
				.setParameter("contentType", contentType)
				.setParameter("contentId", contentId);
		
		@SuppressWarnings("unchecked")
		List<Object> result = query.list();
		BigInteger count = (BigInteger)(result.get(0));
		return count.intValue();
	}
	
	public LikeFollow saveLikeFollow(LikeFollow likeFollow){
		LikeFollow addedLikeFollow = this.add(likeFollow);
		return addedLikeFollow;
	}
	
}
