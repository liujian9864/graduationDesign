package com.ecut.frozenpearassistant.orm.entity;

import java.io.Serializable;

/**
 * (Orders)实体类
 *
 * @author makejava
 * @since 2020-04-07 22:01:18
 */
public class OrdersEntity implements Serializable {
    private static final long serialVersionUID = 770341125440960104L;
    /**
    * 订单id
    */
    private String orderId;
    /**
    * 产品id
    */
    private String productId;
    /**
    * 发布人
    */
    private String userId;
    /**
    * 收货人
    */

    private String  title ;

    private String  image ;

    private String recName;
    /**
    * 收货电话
    */
    private String recPhone;
    /**
    * 省
    */
    private String recProvice;
    /**
    * 市
    */
    private String recCity;
    /**
    * 区
    */
    private String recArea;
    /**
    * 收货地址
    */
    private String recAddress;
    /**
    * 商品价格
    */
    private String price;
    /**
    * 状态
    */
    private String status;
    /**
    * 下单时间
    */
    private String ordertime;
    /**
    * 下单时间
    */
    private String paytime;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getRecPhone() {
        return recPhone;
    }

    public void setRecPhone(String recPhone) {
        this.recPhone = recPhone;
    }

    public String getRecProvice() {
        return recProvice;
    }

    public void setRecProvice(String recProvice) {
        this.recProvice = recProvice;
    }

    public String getRecCity() {
        return recCity;
    }

    public void setRecCity(String recCity) {
        this.recCity = recCity;
    }

    public String getRecArea() {
        return recArea;
    }

    public void setRecArea(String recArea) {
        this.recArea = recArea;
    }

    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

}