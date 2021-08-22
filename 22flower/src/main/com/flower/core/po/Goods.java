package com.flower.core.po;

import java.io.Serializable;

public class Goods implements Serializable{
	private static final long serialVersionUID=1L;
	
	private int goodsID;
	private int ordersID;
	private int productID;
	private int num;
	private double price;
	public Goods(){}
	public Goods(int ordersID, int productID, int num, double price) {
		super();
		this.ordersID = ordersID;
		this.productID = productID;
		this.num = num;
		this.price = price;
	}
	public int getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}
	public int getOrdersID() {
		return ordersID;
	}
	public void setOrdersID(int ordersID) {
		this.ordersID = ordersID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
