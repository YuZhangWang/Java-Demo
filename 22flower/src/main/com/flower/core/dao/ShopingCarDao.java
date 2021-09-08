package com.flower.core.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.flower.core.po.ShopingCar;

public interface ShopingCarDao {
    public int insert(@Param("productID") Integer productID, @Param("userID") Integer userID,
                      @Param("num") Integer num);

    public List<ShopingCar> findAll();

    public ShopingCar findShopingCarById(Integer userId);

    public List<ShopingCar> findShopingCarByUserId(Integer userId);
}
