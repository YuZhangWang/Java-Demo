package com.wbhz.dao;

import com.wbhz.entity.Admin;

import java.util.List;

public interface AdminDao {
    //通过用户名查询对应的记录
    public Admin getAdminByLoginName(Admin admin) throws Exception;

    //修改错误次数与状态
    public int modifyAdminErrorAndState(Admin admin) throws Exception;

    //增加管理员信息
    public int insert(Admin admin) throws Exception;

    //获得表中所有的数据
    public List<Admin> getList() throws Exception;

    //删除管理员信息
    public int deleteAdminById(Admin admin) throws Exception;

    //修改管理员信息（登录名，密码，姓名，爱好）
    public int ModifyAdminById(Admin admin) throws Exception;

    //通过查询对应的记录
    public Admin getAdminById(Admin admin) throws Exception;
}
