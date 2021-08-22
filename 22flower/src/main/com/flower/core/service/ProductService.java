package com.flower.core.service;

import java.util.List;


import com.flower.core.po.Product;


public interface ProductService {
	//����ʻ�
	public int addProduct(String productName,double price,String talkTo,String picture);
	//�鿴�����ʻ�
	public List<Product> findProducts();
	//ɾ���ʻ�
	public int delProduct(Integer id);
	//�鿴�����ʻ�
	public Product getProductById(Integer id);
	//�����ʻ�
	public int updateProduct(Product product);
	//�õ��ʻ�����
	public int findProductCount();

}
