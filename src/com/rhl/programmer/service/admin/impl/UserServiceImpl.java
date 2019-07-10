package com.rhl.programmer.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhl.programmer.dao.admin.UserDao;
import com.rhl.programmer.entity.admin.User;
import com.rhl.programmer.service.admin.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}

}
