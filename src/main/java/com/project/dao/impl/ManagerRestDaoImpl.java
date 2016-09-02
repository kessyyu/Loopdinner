package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ManageRestDao;
import com.project.domain.ManagerRest;

@Repository(value="managerDao")
public class ManagerRestDaoImpl extends BaseDaoImpl<ManagerRest> implements ManageRestDao{

}
