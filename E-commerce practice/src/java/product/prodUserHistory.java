/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author Aya
 */
public class prodUserHistory {
    private String userName;
    private String productName;
    private   Timestamp time;
    private int quantity;
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getProductName() {
        return productName;
    }

    public   Timestamp getTime() {
        return time;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public prodUserHistory(String userName, String productName,   Timestamp time,int quantity) {
        this.userName = userName;
        this.productName = productName;
        this.time = time;
        this.quantity = quantity;
    }

    public prodUserHistory() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
