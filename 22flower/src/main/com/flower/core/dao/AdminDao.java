package com.flower.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.flower.core.po.Product;
import com.flower.core.po.User;
import com.flower.core.po.Admin;

public interface AdminDao {
    public Admin findAdmin(@Param("adminCode") String adminCode,
                           @Param("password") String password);
}
