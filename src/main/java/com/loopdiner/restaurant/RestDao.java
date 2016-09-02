package com.loopdiner.restaurant;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.impl.BaseDaoImpl;
import com.project.domain.Restaurant;

@Repository(value="restDao")
@Transactional
public class RestDao extends BaseDaoImpl<Restaurant>{
	
	/*
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	*/
	
	//@Override
	public List<Restaurant> queryPopularRest(String recomm) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select u from Restaurant u where u.recomm='" + recomm + "'");
		@SuppressWarnings("unchecked")
		List<Restaurant> result = query.list();
		if (result != null && result.size() > 0)
			return result;
		else
			return null;
	}
	
	//@Override
	public List<RestuarantResult> getRecommRestMsg(RestQueryCondition rstQueryCondition) {
		Integer page = 0;
		if(rstQueryCondition.getPageindex()>0){
			page = rstQueryCondition.getPageindex() - 1;
		}
		int startIndex = page*rstQueryCondition.getPageCount();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select u.intro as intro,u.img as img,u.userid as userid,b.img as userimg,u.id as id from restaurant u,user b where u.userid=b.id and u.recomm=:recomm")
				.addScalar("intro")
				.addScalar("img")
				.addScalar("userid")
				.addScalar("userimg")
				.addScalar("id")
				.setParameter("recomm", rstQueryCondition.getRecomm()).setFirstResult(startIndex).setMaxResults(rstQueryCondition.getPageCount());
		
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.list();
		List<RestuarantResult> results = new ArrayList<RestuarantResult>();
		for(Object[] obj : result) {
			RestuarantResult rr = new RestuarantResult();
			rr.setIntro((String)obj[0]);
			rr.setImg((String)obj[1]);
			rr.setUserid((Integer)obj[2]);
			rr.setUserimg((String)obj[3]);
			rr.setId((Integer)obj[4]);
			results.add(rr);
		}
		return results;
	}

	//@Override
	public void updateRestuarant(Restaurant restaurant) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer ql = new StringBuffer();
		ql.append("update Restaurant t set t.name = :name,")
				.append("t.img=:img,")
				.append("t.intro=:intro,")
				.append("t.date=:date,")
				.append("t.startNum=:startNum,")
				.append("t.endNum=:endNum,")
				.append("t.price=:price,")
				.append("t.situation=:situation,")
				.append("t.location=:location,")
				.append("t.phone=:phone,")
				.append("t.email=:email")
				.append("  where id = :id");
		Query query = session.createQuery(ql.toString());
		query.setString("name", restaurant.getName())
				.setString("img", restaurant.getImg())
				.setString("intro", restaurant.getIntro())
				.setString("date", restaurant.getDate())
				.setString("startNum", restaurant.getStartNum())
				.setString("endNum", restaurant.getEndNum())
				.setString("price", restaurant.getPrice())
				.setString("situation", restaurant.getSituation())
				.setString("location", restaurant.getLocation())
				.setString("phone", restaurant.getPhone())
				.setString("email", restaurant.getEmail())
				.setInteger("id", restaurant.getId());
		query.executeUpdate();
		
	}

	//@Override
	public List<FollowRestResult> getUserFollowRestMsg(RestQueryCondition rstQueryCondition) {
		Integer page = 0;
		if(rstQueryCondition.getPageindex()>0){
			page = rstQueryCondition.getPageindex() - 1;
		}
		int startIndex = page*rstQueryCondition.getPageCount();
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql = new StringBuffer();
		sql.append("select u.intro as intro,u.img as img,u.userid as userid,b.img as userimg,u.location as location,u.id as id,u.name as name ")
			.append("from restaurant u, user b ")
			.append("where u.userid=b.id ")
			.append("and u.id in (select contentId from like_follow m where m.contentType='restaurant' and m.type='follow' and m.userid=:curUserId) ");
		
		Query query = session.createSQLQuery(sql.toString())
				.addScalar("intro")
				.addScalar("img")
				.addScalar("userid")
				.addScalar("userimg")
				.addScalar("location")
				.addScalar("id")
				.addScalar("name")
				.setParameter("curUserId", rstQueryCondition.getUserid()).setFirstResult(startIndex).setMaxResults(rstQueryCondition.getPageCount());
		
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.list();
		List<FollowRestResult> results = new ArrayList<FollowRestResult>();
		for(Object[] obj : result) {
			FollowRestResult rr = new FollowRestResult();
			rr.setIntro((String)obj[0]);
			rr.setImg((String)obj[1]);
			rr.setUserid((Integer)obj[2]);
			rr.setUserimg((String)obj[3]);
			if(null!=obj[4]){
				String tmp = (String)obj[4];
				String[] tmpArr = tmp.split(",");
				if(tmpArr.length>2){
					rr.setLocation(tmpArr[2]);
				}
			}
			rr.setId((Integer)obj[5]);
			rr.setName((String)obj[6]);
			results.add(rr);
		}
		return results;
	}
	
	public Restaurant getRestaurantById(Integer restId){
		Session session  = sessionFactory.getCurrentSession();
		
		Restaurant restaurant = this.queryOne("Restaurant", restId);
		
		Query query = session.createQuery("from MenuTable m where m.restaurant_id=:restaurant_id order by m.queueId ")
				.setInteger("restaurant_id", restId);
		@SuppressWarnings("unchecked")
		List<MenuTable> menus = query.list();
		restaurant.setMenus(menus);
		
		Query query1 = session.createQuery("from StoryRest m where m.restaurant_id=:restaurant_id  order by m.queueId ")
				.setInteger("restaurant_id", restId);
		@SuppressWarnings("unchecked")
		List<StoryRest> storys = query1.list();
		restaurant.setStorys(storys);
		
		return restaurant;
	}

}
