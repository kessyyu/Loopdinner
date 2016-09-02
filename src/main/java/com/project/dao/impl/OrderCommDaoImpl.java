package com.project.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.project.dao.OrderCommDao;
import com.project.domain.OrderComm;

@Repository(value="ordercommDao")
public class OrderCommDaoImpl extends BaseDaoImpl<OrderComm> implements OrderCommDao{
	private SessionFactory sessionFactory;
	
	@Override
	public List<OrderComm> queryOrderCommByUser(int uid) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select u from com.project.domain.OrderComm u where u.ordertocomm.user_order.id=" + uid);
		List<OrderComm> list = query.list();
		if (list != null && list.size() > 0)
			return list;
		else
			return null;
	}

	@Override
	public List<OrderComm> queryOrderCommByRestaurant(int rid) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select u from com.project.domain.OrderComm u where u.ordertocomm.rest_order.id=" + rid);
		List<OrderComm> list = query.list();
		if (list != null && list.size() > 0)
			return list;
		else
			return null;
	}

	@Override
	public List<OrderComm> queryOrderCommByOrder(int oid) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select u from com.project.domain.OrderComm u where u.ordertocomm.id=" + oid);
		List<OrderComm> list = query.list();
		if (list != null && list.size() > 0)
			return list;
		else
			return null;
	}

}
