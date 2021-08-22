package com.flower.core.dao;

import com.flower.core.po.Product;
import com.flower.core.po.User;

import org.apache.ibatis.annotations.Param;
public interface UserDao {
		
		public User login(@Param("usercode") String usercode,
				@Param("password") String password);
		public User getUserByUserCode(@Param("usercode")String usercode);
		public User getUserByPhone(@Param("phone")String phone);
		public int add(@Param("phone")String phone,@Param("usercode")String usercode,@Param("password")String password);
		
}
