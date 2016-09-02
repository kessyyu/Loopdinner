package com.loopdiner.share;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.loopdiner.comman.LdUtils;
import com.loopdiner.comman.QueryCond;
import com.project.dao.impl.BaseDaoImpl;

@Repository(value="shareDao")
@Transactional
public class ShareDao  extends BaseDaoImpl<Share>{

	public ShareDao() {
		// TODO Auto-generated constructor stub
	}
	
	public Share saveShare(Share share){
		share.setDate(LdUtils.getDateStr());
		Share addedShare = this.add(share);
		share.setId(addedShare.getId());
		
		if(share.getShareImgs()!=null){
			for(ShareImg shareImg:share.getShareImgs()){ 
				shareImg.setShareid(share.getId());
				shareImg.setImg(LdUtils.getPicUrl(shareImg.getImg()));
				shareImg.setDate(LdUtils.getDateStr());
				ShareImg addedShareImg = (ShareImg) this.sessionFactory
						.getCurrentSession().merge(shareImg);
				shareImg.setId(addedShareImg.getId());
			}			
		}
		
		if(share.getShareTopics()!=null){
			for(ShareTopic shareTopic:share.getShareTopics()){ 
				shareTopic.setShareid(share.getId());
				ShareTopic addedShareTopic = (ShareTopic) this.sessionFactory
						.getCurrentSession().merge(shareTopic);
				shareTopic.setId(addedShareTopic.getId());
			}			
		}
		
		return share;
	}
	
	public Share updateShare(Share share){
		Session session = sessionFactory.getCurrentSession();
		StringBuffer ql = new StringBuffer();
		ql.append("update Share t set t.sharecontent =:sharecontent,date=:date ")
			.append("  where t.id =:id");
		Query query = session.createQuery(ql.toString());
		query.setString("sharecontent", share.getSharecontent())
		.setString("date", LdUtils.getDateStr())
		.setInteger("id", share.getId());
		query.executeUpdate();
		
		session.createSQLQuery("delete from share_img where shareid=:shareid")
		.setInteger("shareid", share.getId()).executeUpdate();
		if(share.getShareImgs()!=null){
			for(ShareImg shareImg:share.getShareImgs()){ 
				shareImg.setShareid(share.getId());
				shareImg.setImg(LdUtils.getPicUrl(shareImg.getImg()));
				shareImg.setDate(LdUtils.getDateStr());
				ShareImg addedShareImg = (ShareImg) this.sessionFactory
						.getCurrentSession().merge(shareImg);
				shareImg.setId(addedShareImg.getId());
			}			
		}
		
		session.createSQLQuery("delete from share_topic where shareid=:shareid")
		.setInteger("shareid", share.getId()).executeUpdate();
		if(share.getShareTopics()!=null){
			for(ShareTopic shareTopic:share.getShareTopics()){ 
				shareTopic.setShareid(share.getId());
				ShareTopic addedShareTopic = (ShareTopic) this.sessionFactory
						.getCurrentSession().merge(shareTopic);
				shareTopic.setId(addedShareTopic.getId());
			}		
		}
		
		return share;
	}
	
	public List<ShareResult> getShareList(QueryCond queryCond){
		Integer page = 0;
		if(queryCond.getPageindex()>0){
			page = queryCond.getPageindex() - 1;
		}
		int startIndex = page*queryCond.getPageCount();
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql = new StringBuffer();
		sql.append("select u.sharecontent as sharecontent,b.img as userimg,u.userid as userid,")
					.append("b.name as username,u.id as id ")
					.append("from share u,user b ")
					.append("where u.userid=b.id ")
					.append("order by u.date desc ");
		Query query = session.createSQLQuery(sql.toString())
				.addScalar("sharecontent")
				.addScalar("userimg")
				.addScalar("userid")
				.addScalar("username")
				.addScalar("id")
				.setFirstResult(startIndex).setMaxResults(queryCond.getPageCount());
		
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.list();
		List<ShareResult> results = new ArrayList<ShareResult>();
		for(Object[] obj : result) {
			ShareResult rr = new ShareResult();
			rr.setSharecontent((String)obj[0]);
			rr.setUserimg((String)obj[1]);
			rr.setUserid((Integer)obj[2]);
			rr.setUsername((String)obj[3]);
			rr.setId((Integer)obj[4]);
			results.add(rr);
		}
		return results;
	}
	
	public ShareImg getOneImg(Integer shareid){
		Session session  = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from ShareImg m where m.shareid=:shareid order by m.date ")
				.setInteger("shareid", shareid);
		@SuppressWarnings("unchecked")
		List<ShareImg> imgs = query.list();
		if(imgs!=null && imgs.size()>0){
			return imgs.get(0);
		}
		return null;
	}
	
	public List<ShareTopic> getTopics(Integer shareid){
		Session session  = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from ShareTopic m where m.shareid=:shareid ")
				.setInteger("shareid", shareid);
		@SuppressWarnings("unchecked")
		List<ShareTopic> topics = query.list();
		return topics;
	}
	
	public Share getShare(Integer shareId) {
		Share share = this.queryOne("Share", shareId);
		
		Session session  = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from ShareImg m where m.shareid=:shareid order by date desc ")
							.setInteger("shareid", shareId);
		@SuppressWarnings("unchecked")
		List<ShareImg> imgs = query.list();
		share.setShareImgs(imgs);
		
		share.setShareTopics(this.getTopics(shareId));
		
		return share;
	}
	
}
