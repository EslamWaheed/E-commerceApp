/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import dto.OrderHistoryDTO;
import java.util.ArrayList;

/**
 *
 * @author EslamWaheed
 */
public interface OrderHistoryDAOInt {

    ArrayList<OrderHistoryDTO> getOrderHistory(int userID);
    
    boolean insertOrderHistory(OrderHistoryDTO orderHistoryDTO);
    
    boolean updateOrderHistory(OrderHistoryDTO orderHistoryDTO);
    
    boolean deleteOrderHistory(int userID);
}
