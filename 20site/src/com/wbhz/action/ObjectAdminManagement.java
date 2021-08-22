package com.wbhz.action;

import java.util.logging.Logger;

import com.wbhz.entity.Admin;
import com.wbhz.entity.MyMessage;
import com.wbhz.service.AdminService;
import com.wbhz.service.impl.AdminServiceImpl;

public class ObjectAdminManagement {
	private static Logger logger = Logger.getLogger("com.wbhz.action.ObjectAdminManagement.class");
	private Admin admin;
	private String[] hobby;
	private AdminService adminService = new AdminServiceImpl();

	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String regist(){
		//登录名，密码，姓名已经自动填充到admin对象中
		
		//用户名不能重复
		Object result = adminService.login(admin).getObj();
		if(result==null){
			//代表没有找到，可以注册的//手动的处理爱好
			String str="";
			if(hobby!=null && hobby.length>0){
				for (int i = 0; i < hobby.length; i++) {
					if(i==hobby.length-1){
						str+=hobby[i];
					}else{
						str+=hobby[i]+",";
					}
				}
			}
			admin.setAdminHobby(str);
			MyMessage msg = adminService.regist(admin);
			if(msg.isSuccess()){
				return "success";
			}else{
				return "input";
			}
		}else{
			//用户名已经存在不能注册
			return "error";
		}
		
	}
	
	
	
}
