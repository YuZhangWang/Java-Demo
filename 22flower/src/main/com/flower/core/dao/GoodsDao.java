package com.flower.core.dao;




import org.apache.ibatis.annotations.Param;

import com.flower.core.po.Goods;

public interface GoodsDao {
	public int insert(Goods goods);
	public Goods findGoodById(@Param("id") int id);
}
