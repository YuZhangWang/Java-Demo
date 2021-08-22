package com.flower.core.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flower.core.dao.OrderDao;
import com.flower.core.po.Orders;
import com.flower.core.service.OrderService;
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public int insert(Orders orders) {
		int row=orderDao.insert(orders);
		return row;
	}
	

//	@Override
//	public int insert(int userID, String userName, String userPhone,
//			String userEmail, String consigneeName, String consigneePhone,
//			String consigneeAddress, Date ordersTime, String verify,
//			Double ordersMoney) {
//		int row=orderDao.insert(userID, userName, userPhone, userEmail,
//					consigneeName, consigneePhone, consigneeAddress, ordersTime, verify, ordersMoney);
//		return row;
//	}

}
