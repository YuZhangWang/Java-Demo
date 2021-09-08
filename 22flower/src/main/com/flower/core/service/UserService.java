package com.flower.core.service;

import org.apache.ibatis.annotations.Param;

import com.flower.core.po.User;

public interface UserService {
    public User login(String usercode, String password);

    public User getUserByUserCode(String usercode);

    public User getUserByPhone(String phone);

    public int add(String phone, String usercode, String password);

}
