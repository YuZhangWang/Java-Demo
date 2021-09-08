package com.flower.core.po;

import java.io.Serializable;


public class ShopingCar implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer carID;
    private Integer productID;
    private Integer userID;
    private Integer num;

    public Integer getCarID() {
        return carID;
    }

    public void setCarID(Integer carID) {
        this.carID = carID;
    }

    public Integer getProudctID() {
        return productID;
    }

    public void setProudctID(Integer productID) {
        this.productID = productID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


}
