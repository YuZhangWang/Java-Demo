package com.flower.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flower.core.dao.ProductDao;
import com.flower.core.po.Product;
import com.flower.core.service.ProductService;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDao productDao;
	
	@Override
	public int addProduct(String Productname, double price,String talkTo,String picture) {
		// TODO Auto-generated method stub
		int row=this.productDao.addProduct( Productname,price,talkTo,picture);
		return row;
	}
	@Override
	public List<Product> findProducts() {
		// TODO Auto-generated method stub
		List<Product> products=this.productDao.findProducts();
		return products;
	}
	@Override
	public int delProduct(Integer id) {
		// TODO Auto-generated method stub
		int row=this.productDao.delProduct(id);
		return row;
	}
	@Override
	public Product getProductById(Integer id) {
		Product product=this.productDao.getProductById(id);
		return product;
	}
	@Override
	public int updateProduct(Product product) {
		// TODO Auto-generated method stub
		
		return this.productDao.updateProductById(product);
	}
	
	@Override
	public int findProductCount() {
		// TODO Auto-generated method stub
		int count=this.productDao.findProductCount();
		return count;
	}

}
