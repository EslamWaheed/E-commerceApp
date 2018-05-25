/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author EslamWaheed
 */
public class ShoppingCartDTO {

    private Integer userID;
    private Integer productID;
    private Integer productQuantity;

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(Integer userID, Integer productID,Integer productQuantity) {
        this.userID = userID;
        this.productID = productID;
        this.productQuantity = productQuantity;
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

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

}
