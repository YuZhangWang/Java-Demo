package com.wbhz.service.impl;

import com.wbhz.dao.AdminDao;
import com.wbhz.dao.impl.AdminDaoImpl2;
import com.wbhz.entity.Admin;
import com.wbhz.entity.MyMessage;
import com.wbhz.service.AdminService;
import com.wbhz.util.MD5;

import java.util.List;

public class AdminServiceImpl implements AdminService {
	//StrutsPrepareAndExecuteFilter
	private AdminDao adminDao = new AdminDaoImpl2();
	public MyMessage login(Admin admin) {
		MyMessage msg = new MyMessage();
		// 首先通过用户名查找对应管理员信息
		try {
			Admin result = adminDao.getAdminByLoginName(admin);
			
			if(result==null){
				//没有找到，也是提示用户名或密码错误
				msg.setMsg("用户名或密码错误");
				msg.setSuccess(false);
			}else{
				// 如果找到，(对密码进行加密)比较密码是否正确
				msg.setObj(result);
				if(result.getAdminState()==1){
					//该账户已经被锁定，不需要再比较密码了
					msg.setMsg("该账户已经被锁定,请联系管理员");
					msg.setSuccess(false);
				}else{
					if(result.getAdminPwd().equalsIgnoreCase(MD5.toMD5(admin.getAdminPwd()))){
						//两次密码相同，登录成功
						msg.setMsg("登录成功");
						msg.setSuccess(true);
						result.setAdminError(0);
						msg.setObj(result);
					}else{
						//密码不同
						if(result.getAdminError()<=3){//小于4次
							result.setAdminError(result.getAdminError()+1);
							msg.setMsg("登录失败");
							msg.setSuccess(false);
						}else{
							result.setAdminError(5);
							result.setAdminState(1);
							msg.setMsg("登录失败，由于连续5次密码输入错误，该账户被锁定");
							msg.setSuccess(true);
						}
					}
					//修改数据库的
					adminDao.modifyAdminErrorAndState(result);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return msg;
	}
	//注册
	public MyMessage regist(Admin admin) {
		MyMessage msg = new MyMessage();
		admin.setAdminPwd(MD5.toMD5(admin.getAdminPwd()));
		int count=0;
		try {
			count = adminDao.insert(admin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(count==1){
			//注册成功
			msg.setSuccess(true);
			msg.setMsg("注册成功");
			System.out.println("注册成功");
		}else{
			//注册失败
			msg.setSuccess(false);
			msg.setMsg("注册失败");
			System.out.println("注册失败");
		}
		return msg;
	}
	public List<Admin> getList() {
		// TODO Auto-generated method stub
		List<Admin> list=null;
		try {
			list = adminDao.getList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public MyMessage delete(Admin admin) {
		MyMessage msg = new MyMessage();
		int count=0;
		try {
			count = adminDao.deleteAdminById(admin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(count==1){
			msg.setSuccess(true);
			msg.setMsg("删除成功");
		}else{
			msg.setSuccess(false);
			msg.setMsg("删除失败");
		}
		return msg;
	}
	public MyMessage getAdminById(Admin admin) {
		MyMessage msg = new MyMessage();
		try {
			Admin result = adminDao.getAdminById(admin);
			if(result!=null){
				msg.setObj(result);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public List<Admin> getAll() {
		return null;
	}

	public MyMessage modify(Admin admin) {
		MyMessage msg = new MyMessage();
		admin.setAdminPwd(MD5.toMD5(admin.getAdminPwd()));//加密
		try {
			int count = adminDao.ModifyAdminById(admin);
			if(count==1){
				msg.setSuccess(true);
				msg.setMsg("修改成功");
			}else{
				msg.setSuccess(false);
				msg.setMsg("修改失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

}
