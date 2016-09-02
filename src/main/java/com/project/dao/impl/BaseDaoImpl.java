package com.project.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.BaseDao;
@Repository(value="baseDao")

@Transactional
public class BaseDaoImpl<T> implements BaseDao<T>{
	
	protected SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T add(T t) {
		return (T)this.sessionFactory.getCurrentSession().merge(t);
	}

	@Override
	public void del(T t) {
		this.sessionFactory.getCurrentSession().delete(t);
		
	}
	
	@Override
	public T update(T t) {
		return (T)this.sessionFactory.getCurrentSession().merge(t);		
	}

	@Override
	public List<T> queryAll(String hql) {
		Session session  = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<T> result = query.list();
		return result;
	}

	@Override
	public T queryOne(String hql, Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select u from " + hql + " u where u.id=" + id);
		List<T> result = query.list();
		if (result != null && result.size() > 0)
			return result.get(0);
		else
			return null;
	}

	@Override
	public List<T> search(String table, String name,int firstResult, int MaxResults) {
		Session session  = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select u from "+table+" u where u.name like '%"+ name +"%'");
		
		query.setFirstResult(firstResult);
		query.setMaxResults(MaxResults);
		
			return query.list();
	}

	@Override
	public List<T> h_Page(String hql, int firstResult, int MaxResults) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(firstResult);
		query.setMaxResults(MaxResults);
		
		return query.list();
	}

	@Override
	public List<T> searchTotals(String table, String name) {
		Session session  = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select u from "+table+" u where u.name like '%"+ name +"%'");
		List<T> result = query.list();
		if (result != null && result.size() > 0)
		return result;
		else
			return null;
	}

	@Override
	public T queryName(String hql, String name) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select u from " + hql + " u where u.name='" + name + "'");
		List<T> result = query.list();
		if (result != null && result.size() > 0)
			return result.get(0);
		else
			return null;
	}

}
