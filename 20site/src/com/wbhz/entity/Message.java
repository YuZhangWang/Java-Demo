package com.wbhz.entity;

public class Message {
    private boolean success;//是否成功
    private String msg;//提示的消息
    private Object obj;//返回的对象

    public Message(boolean success, String msg, Object obj) {
        super();
        this.success = success;
        this.msg = msg;
        this.obj = obj;
    }

    public Message() {
        super();
        // TODO Auto-generated constructor stub
    }

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
        return "Message [msg=" + msg + ", obj=" + obj + ", success=" + success
                + "]";
    }
}
