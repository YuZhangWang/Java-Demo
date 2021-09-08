package com.wbhz.service;

import com.wbhz.entity.Admin;
import com.wbhz.entity.MyMessage;

import java.util.List;

//处理t_admin表的业务逻辑
public interface AdminService {
    //登录功能
    public MyMessage login(Admin admin);

    //注册功能
    public MyMessage regist(Admin admin);

    //获得全部
    public List<Admin> getList();

    //删除
    public MyMessage delete(Admin admin);

    //修改
    public MyMessage modify(Admin admin);

    //通过Id获得管理员信息
    public MyMessage getAdminById(Admin admin);

    List<Admin> getAll();
}
