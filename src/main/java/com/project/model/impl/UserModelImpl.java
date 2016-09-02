package com.project.model.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project.dao.UserDao;
import com.project.domain.User;
import com.project.model.UserModel;
@Service(value="userService")
public class UserModelImpl extends BaseModelImpl<User> implements UserModel{
	@Resource(name="userDao")
	private UserDao userDao;
}
