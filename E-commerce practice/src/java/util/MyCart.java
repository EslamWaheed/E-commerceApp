/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
/**
 *
 * @author Hazem
 */
public class MyCart implements Serializable{
    private Integer productId;
    private Integer productPrice;
    private String productName;
    private String categoryName;
    private Integer productQuantity;
    private Integer maxQuantity;

    public MyCart() {
    }
    public MyCart(Integer productId, Integer productPrice, String productName, String categoryName,Integer productQuantity,Integer maxQuantity) {
        this.productId = productId;
        this.productPrice = productPrice;
        this.productName = productName;
        this.categoryName = categoryName;
        this.productQuantity = productQuantity;
        this.maxQuantity = maxQuantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}
