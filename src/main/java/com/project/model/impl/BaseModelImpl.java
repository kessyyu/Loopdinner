package com.project.model.impl;

import java.util.List;

import javax.annotation.Resource;

import com.project.dao.BaseDao;
import com.project.model.BaseModel;


public class BaseModelImpl<T> implements BaseModel<T>{
	@Resource(name="baseDao")
	private BaseDao<T> bdao;
	
	
	public BaseDao<T> getBdao() {
		return bdao;
	}

	public void setBdao(BaseDao<T> bdao) {
		this.bdao = bdao;
	}

	@Override
	public T add(T t) {
		return bdao.add(t);
	}

	@Override
	public void del(T t) {
		bdao.del(t);
	}

	@Override
	public T update(T t) {
		return bdao.update(t);
	}

	@Override
	public List<T> queryAll(String hql) {
		
		return bdao.queryAll(hql);
	}

	@Override
	public T queryOne(String hql, Integer id) {
		
		return bdao.queryOne(hql, id);
	}

	@Override
	public List<T> search(String table, String name,int firstResult, int MaxResults) {
	
		return bdao.search(table, name, firstResult, MaxResults);
	}

	@Override
	public List<T> h_Page(String hql, int firstResult, int MaxResults) {
		return bdao.h_Page(hql, firstResult, MaxResults);
	}

	@Override
	public List<T> searchTotals(String table, String name) {
		return bdao.searchTotals(table, name);
	}

	@Override
	public T queryName(String hql, String name) {
		// TODO Auto-generated method stub
		return bdao.queryName(hql, name);
	}

}
