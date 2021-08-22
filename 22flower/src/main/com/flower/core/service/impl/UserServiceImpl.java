package com.flower.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flower.core.dao.UserDao;
import com.flower.core.po.User;
import com.flower.core.service.UserService;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	@Override
	public User login(String usercode, String password) {
		User user=this.userDao.login(usercode, password);
		return user;
	}
	@Override
	public int add(String phone,String usercode,String password) {
		// TODO Auto-generated method stub
		int row=this.userDao.add(phone,usercode, password);
		return row;
	}
	@Override
	public User getUserByUserCode(String usercode) {
		User user=this.userDao.getUserByUserCode(usercode);
		return user;
	}
	@Override
	public User getUserByPhone(String phone) {
		User user=this.userDao.getUserByPhone(phone);
		return user;
	}

}
