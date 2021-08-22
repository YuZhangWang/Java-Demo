package com.flower.core.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flower.core.dao.AdminDao;
import com.flower.core.po.Admin;
import com.flower.core.service.AdminService;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDao adminDao;
	@Override
	public Admin findadmin(String adminCode, String password) {
		 Admin admin=this.adminDao.findAdmin(adminCode, password);
		return admin;
	}
	
	

}
