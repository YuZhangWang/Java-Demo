package com.flower.core.po;

import java.io.Serializable;
import java.util.Date;


public class Orders implements Serializable{
	private static final long serialVersionUID=1L;
	private int ordersID;
	private int userID;
	private String userName;
	private String userPhone;
	private String userEmail;
	private String consigneeName;
	private String consigneePhone;
	private String consigneeAddress;
	private Date ordersTime;
	private String verify;
	private Double ordersMoney;
	public Orders(){}
	public Orders(int userID, String userName, String userPhone,
			String userEmail, String consigneeName, String consigneePhone,
			String consigneeAddress, Date ordersTime, String verify,
			Double ordersMoney) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.consigneeName = consigneeName;
		this.consigneePhone = consigneePhone;
		this.consigneeAddress = consigneeAddress;
		this.ordersTime = ordersTime;
		this.verify = verify;
		this.ordersMoney = ordersMoney;
	}
	public int getOrdersID() {
		return ordersID;
	}
	public void setOrdersID(int ordersID) {
		this.ordersID = ordersID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public String getConsigneePhone() {
		return consigneePhone;
	}
	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}
	public String getConsigneeAddress() {
		return consigneeAddress;
	}
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}
	public Date getOrdersTime() {
		return ordersTime;
	}
	public void setOrdersTime(Date ordersTime) {
		this.ordersTime = ordersTime;
	}
	public String getVerify() {
		return verify;
	}
	public void setVerify(String verify) {
		this.verify = verify;
	}
	public Double getOrdersMoney() {
		return ordersMoney;
	}
	public void setOrdersMoney(Double ordersMoney) {
		this.ordersMoney = ordersMoney;
	}
	
	
	
}
