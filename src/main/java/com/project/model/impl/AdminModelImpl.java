package com.project.model.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project.dao.AdminDao;
import com.project.domain.Admin;
import com.project.model.AdminModel;

@Service(value="adminService")
public class AdminModelImpl extends BaseModelImpl<Admin> implements AdminModel{
	@Resource(name="adminDao")
	private AdminDao adminDao;

	@Override
	public Admin login(Admin a) {
		
		return adminDao.login(a);
	}

}
