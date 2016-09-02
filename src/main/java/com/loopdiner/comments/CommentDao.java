package com.loopdiner.comments;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.impl.BaseDaoImpl;

@Repository(value="commentDao")
@Transactional
public class CommentDao extends BaseDaoImpl<Comment>{

	public CommentDao() {
	}
	
	public Comment4Rest getOneCommentByRestId(String commentType,Integer commentId, String dateOrder){
		Comment4Rest comment4Rest = null;
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id,a.name,b.img,a.content from comment a,user b ")
				.append("where a.userid=b.id and a.commentType=:commentType and commentId=:commentId ")
				.append("order by a.date " + dateOrder);
		Query query = session.createSQLQuery(sql.toString())
				.setParameter("commentType", commentType)
				.setParameter("commentId", commentId)
				.setFirstResult(0).setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.list();
		if(result!=null && result.size()>0){
			comment4Rest = new Comment4Rest();
			Object[] obj = result.get(0);
			comment4Rest.setId((Integer)obj[0]);
			comment4Rest.setName((String)obj[1]);
			comment4Rest.setImg((String)obj[2]);
			comment4Rest.setContent((String)obj[3]);
		};
		return comment4Rest;
	}
	
	public Integer getCommentAmount(String commentType,Integer commentId){
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql = new StringBuffer();
		sql.append("select count(id) from comment ")
				.append("where commentType=:commentType and commentId=:commentId ");
		Query query = session.createSQLQuery(sql.toString())
				.setParameter("commentType", commentType)
				.setParameter("commentId", commentId);
		
		@SuppressWarnings("unchecked")
		List<Object> result = query.list();
		BigInteger count = (BigInteger)(result.get(0));
		return count.intValue();
	}
	
	public List<CommentResult> getCommentMsg(CommentQueryCond commentQueryCond){
		Integer page = 0;
		if(commentQueryCond.getPageindex()>0){
			page = commentQueryCond.getPageindex() - 1;
		}
		int startIndex = page*commentQueryCond.getPageCount();
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id,a.content,b.name,b.img,a.userid from comment a,user b ")
				.append("where a.userid=b.id and a.commentType=:commentType and commentId=:commentId ");
		Query query = session.createSQLQuery(sql.toString())
				.setParameter("commentType", commentQueryCond.getCommentType())
				.setParameter("commentId", commentQueryCond.getCommentId())
				.setFirstResult(startIndex).setMaxResults(commentQueryCond.getPageCount());
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.list();
		List<CommentResult> results = new ArrayList<CommentResult>();
		for(Object[] obj : result) {
			CommentResult rr = new CommentResult();
			rr.setId((Integer)obj[0]);
			rr.setContent((String)obj[1]);
			rr.setUsername((String)obj[2]);
			rr.setUserimg((String)obj[3]);
			rr.setUserid((Integer)obj[4]);
			results.add(rr);
		}
		return results;
	}
	
	/*
	private List<Comment4Rest> getTwoCommentsByCookId(String commentType,Integer commentId) {
		List<Comment4Rest> comments = new ArrayList<Comment4Rest>();
		Comment4Rest comment4Rest1 = this.getOneCommentByRestId(
				commentType,commentId, "asc");
		Comment4Rest comment4Rest2 = null;
		if(comment4Rest1 != null){
			comments.add(comment4Rest1);
			comment4Rest2 = this.getOneCommentByRestId(
					commentType,commentId, "desc");
			if(comment4Rest2!=null && (!comment4Rest1.getId().equals(comment4Rest2.getId()))){
				comments.add(comment4Rest2);
			}
		}
		return comments;
	}
	*/

}
