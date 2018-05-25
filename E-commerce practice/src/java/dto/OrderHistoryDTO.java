/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Time;
import java.sql.Timestamp;
/**
 *
 * @author EslamWaheed
 */
public class OrderHistoryDTO {

    private Integer userID;
    private Integer productID;
    private Integer productQuantity;
    private Timestamp time;

    public OrderHistoryDTO() {
    }

    public OrderHistoryDTO(Integer userID, Integer productID,Integer productQuantity) {
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

}
