package com.project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.loopdiner.restaurant.MenuTable;
import com.project.dao.MenuDao;

@Repository(value="menuDao")
public class MenuDaoImpl extends BaseDaoImpl<MenuTable> implements MenuDao{
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void deleteByRestaurantId(Integer restuanrantId) {
		Session session = sessionFactory.getCurrentSession();
		session.createSQLQuery("delete from menutable where restaurant_id=:restaurant_id")
				.setInteger("restaurant_id", restuanrantId).executeUpdate();		
	}

//	@Override
//	public List<MenuTable> listMenuForRest(int id) {
//		String hql =  "select a from com.project.domain.MenuTable a where a.rest_menu.id="+id;
//		List<MenuTable> list = this.queryAll(hql);
//		if(list.size()>0 && list != null){
//			return list;
//		}
//		return null;
//	}

}
