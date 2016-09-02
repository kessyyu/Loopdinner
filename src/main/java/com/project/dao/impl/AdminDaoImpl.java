package com.project.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.dao.AdminDao;
import com.project.domain.Admin;
@Repository(value="adminDao")
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{
	@Override
	public Admin login(Admin a) {
		String hql =  "select a from com.project.domain.Admin a where a.name='"
				+ a.getName() + "' and a.password='" + a.getPassword() + "'";
		List<Admin>list = this.queryAll(hql);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	
	

	
}
