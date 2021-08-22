package com.flower.core.dao;


import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.flower.core.po.Orders;

public interface OrderDao {
	public int insert(Orders orders);

}
