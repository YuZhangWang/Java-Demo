package com.flower.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.flower.core.po.Product;


public interface ProductDao {

    public int addProduct(@Param("productName") String productName,
                          @Param("price") double price, @Param("talkTo") String talkTo, @Param("picture") String picture);

    public List<Product> findProducts();

    public int delProduct(@Param("id") Integer id);

    public Product getProductById(@Param("id") Integer id);

    public int updateProductById(Product product);

    public int findProductCount();
}
