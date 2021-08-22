package com.flower.core.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flower.core.dao.GoodsDao;
import com.flower.core.po.Goods;
import com.flower.core.service.GoodsService;

@Service("goodsService")
@Transactional
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private GoodsDao goodsDao;
	
	
	@Override
	public int insert(Goods goods) {
		int row=goodsDao.insert(goods);
		return row;
	}


	@Override
	public Goods findGoodById(Integer id) {
		Goods goods=goodsDao.findGoodById(id);
		return goods;
	}

}
