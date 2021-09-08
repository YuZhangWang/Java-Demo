package com.flower.core.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.flower.core.po.Goods;
import com.flower.core.po.ShopingCar;


public interface ShopingCarService {
    public int insert(Integer productID, Integer userID, Integer num);

    public List<ShopingCar> findAll();

    public ShopingCar findShopingCarById(Integer userId);

    public List<ShopingCar> findShopingCarByUserId(Integer userId);
}
