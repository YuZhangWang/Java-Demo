package com.wbhz.entity;

public class MyMessage {
	private boolean success;//是否处理成功
	private String msg;//返回的消息
	private Object obj;//返回对象
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	@Override
	public String toString() {
		return "MyMessage [msg=" + msg + ", obj=" + obj + ", success="
				+ success + "]";
	}
	public MyMessage(boolean success, String msg, Object obj) {
		super();
		this.success = success;
		this.msg = msg;
		this.obj = obj;
	}
	public MyMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
