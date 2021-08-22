package com.flower.core.po;

import java.io.Serializable;



public class Admin implements Serializable{
	private static final long serialVersionUID=1L;
	private Integer adminID;	//管理员编号	
	private String adminName;		//用户名
	private String adminPwd;		
	private String sex;	
	private String brith;
	private String moblie;
	private String address;
	public Integer getAdminID() {
		return adminID;
	}
	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}
	
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBrith() {
		return brith;
	}
	public void setBrith(String brith) {
		this.brith = brith;
	}
	public String getMoblie() {
		return moblie;
	}
	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	

}
