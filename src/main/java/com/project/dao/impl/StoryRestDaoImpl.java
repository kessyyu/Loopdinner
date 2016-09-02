package com.project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.loopdiner.restaurant.StoryRest;
import com.project.dao.StoryRestDao;
@Repository(value="storyDao")
public class StoryRestDaoImpl extends BaseDaoImpl<StoryRest> implements StoryRestDao{
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void deleteByRestaurantId(Integer restuanrantId) {
		Session session = sessionFactory.getCurrentSession();
		session.createSQLQuery("delete from story_rest where restaurant_id=:restaurant_id")
				.setInteger("restaurant_id", restuanrantId).executeUpdate();
		
	}

}
