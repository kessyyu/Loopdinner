package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.UserDao;
import com.project.domain.User;
@Repository(value="userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

}
