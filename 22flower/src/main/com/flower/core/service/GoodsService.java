package com.flower.core.service;

import org.apache.ibatis.annotations.Param;

import com.flower.core.po.Goods;



public interface GoodsService {
	public int insert(Goods goods);
	public Goods findGoodById(Integer id);
}
