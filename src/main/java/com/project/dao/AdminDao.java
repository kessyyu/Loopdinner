package com.project.dao;

import com.project.domain.Admin;

public interface AdminDao extends BaseDao<Admin>{
	public Admin login(Admin a);
}
