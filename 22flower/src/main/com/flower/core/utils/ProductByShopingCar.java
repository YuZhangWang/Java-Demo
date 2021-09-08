package com.flower.core.utils;

import java.io.Serializable;

import com.flower.core.po.Product;
import com.flower.core.po.ShopingCar;


public class ProductByShopingCar implements Serializable {
    private static final long serialVersionUID = 1L;
    private Product product;
    private ShopingCar shopingCar;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShopingCar getShopingCar() {
        return shopingCar;
    }

    public void setShopingCar(ShopingCar shopingCar) {
        this.shopingCar = shopingCar;
    }


}
