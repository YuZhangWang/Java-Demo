package com.flower.core.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.flower.core.dao.ShopingCarDao;
import com.flower.core.po.ShopingCar;
import com.flower.core.service.ShopingCarService;

@Service("shopingCarService")
@Transactional
public class ShopingCarServiceImpl implements ShopingCarService{
	@Autowired
	private ShopingCarDao shopingCarDao;
	
	@Override
	public int insert(Integer productID, Integer userID, Integer num) {
		int row=shopingCarDao.insert(productID, userID, num);
		return row;
	}

	@Override
	public List<ShopingCar> findAll() {
		List<ShopingCar> shopingCars=shopingCarDao.findAll();
		return shopingCars;
	}

	@Override
	public List<ShopingCar> findShopingCarByUserId(Integer userId) {
		List<ShopingCar> shopingCars=shopingCarDao.findShopingCarByUserId(userId);
		return shopingCars;
	}

	@Override
	public ShopingCar findShopingCarById(Integer userId) {
		ShopingCar shopingCar=shopingCarDao.findShopingCarById(userId);
		return shopingCar;
	}


}
