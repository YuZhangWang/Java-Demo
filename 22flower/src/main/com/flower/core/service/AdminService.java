package com.flower.core.service;

import java.util.List;


import com.flower.core.po.User;
import com.flower.core.po.Admin;

public interface AdminService {
	
	public Admin findadmin(String adminCode,String password);	

}
