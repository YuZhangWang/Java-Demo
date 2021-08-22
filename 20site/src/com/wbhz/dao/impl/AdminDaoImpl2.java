package com.wbhz.dao.impl;

import java.util.List;

import com.wbhz.dao.AdminDao;
import com.wbhz.entity.Admin;

public class AdminDaoImpl2 implements AdminDao {
	//查询的sql语句
	public Admin getAdminByLoginName(Admin admin) throws Exception {
		String sql="select * from t_admin where admin_login_name=?";
		Object[] params={admin.getAdminLoginName()};
		List<Admin> list = BaseDao.getList(sql, Admin.class, params);
		if(list==null || list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}

	public int modifyAdminErrorAndState(Admin admin) throws Exception {
		String sql="update t_admin set admin_error=?, admin_state=? where admin_id=?";
		Object[] params={admin.getAdminError(),admin.getAdminState(),admin.getAdminId()};
		int count = BaseDao.execute(sql, params);
		return count;
	}
	//添加的功能
	public int insert(Admin admin) throws Exception {
		String sql="insert into t_admin values(null,?,?,?,?,now(),0,0)";
		Object[] params={admin.getAdminLoginName(),admin.getAdminPwd(),admin.getAdminName(),admin.getAdminHobby()};
		int count = BaseDao.execute(sql, params);
		return count;
	}

	public List<Admin> getList() throws Exception {
		String sql="select * from t_admin";
		List<Admin> list = BaseDao.getList(sql, Admin.class, null);
		return list;
	}
	//删除
	public int deleteAdminById(Admin admin) throws Exception {
		String sql="delete from t_admin where admin_id=?";
		Object[] params={admin.getAdminId()};
		int count = BaseDao.execute(sql, params);
		return count;
	}
	//修改
	public int ModifyAdminById(Admin admin) throws Exception {
		String sql="update t_admin set admin_login_name=?,admin_pwd=?,admin_name=?,admin_hobby=? where admin_id=?";
		Object[] params={admin.getAdminLoginName(),admin.getAdminPwd(),admin.getAdminName(),admin.getAdminHobby(),admin.getAdminId()};
		int count = BaseDao.execute(sql, params);
		return count;
	}
	//通过Id获得管理员信息
	public Admin getAdminById(Admin admin) throws Exception {
		String sql="select * from t_admin where admin_id=?";
		Object[] params={admin.getAdminId()};
		List<Admin> list = BaseDao.getList(sql, Admin.class, params);
		if(list==null || list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}

}
