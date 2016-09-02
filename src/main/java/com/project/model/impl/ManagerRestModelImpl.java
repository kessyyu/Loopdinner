package com.project.model.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project.dao.ManageRestDao;
import com.project.domain.ManagerRest;
import com.project.model.ManagerRestModel;
@Service(value="managerService")
public class ManagerRestModelImpl extends BaseModelImpl<ManagerRest> implements ManagerRestModel{
	@Resource(name="managerDao")
	private ManageRestDao managerDao;
	
}
