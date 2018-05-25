/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementations;

import dao.interfaces.OrderHistoryDAOInt;
import dbconnection.DBConnection;
import dto.OrderHistoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author EslamWaheed
 */
public class OrderHistoryDAOImp implements OrderHistoryDAOInt {

    Connection connection = null;

    @Override
    public ArrayList<OrderHistoryDTO> getOrderHistory(int userID) {
        OrderHistoryDTO orderHistoryDTO = new OrderHistoryDTO();
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<OrderHistoryDTO> orderhistory = new ArrayList<OrderHistoryDTO>();
        String myQuery = "select * from orderhistory where userid = ? order by time asc";

        try {
            ps = connection.prepareStatement(myQuery);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                orderHistoryDTO.setUserID(rs.getInt(1));
                orderHistoryDTO.setProductID(rs.getInt(2));
                orderHistoryDTO.setTime(rs.getTimestamp(3));
                orderhistory.add(orderHistoryDTO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderhistory;
    }

    @Override
    public boolean insertOrderHistory(OrderHistoryDTO orderHistoryDTO) {
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String myQuery = "insert into orderhistory(userid, productid, quantity) values(?, ?, ?)";
        int return_flage = 0;
        try {
            ps = connection.prepareStatement(myQuery);
            ps.setInt(1, orderHistoryDTO.getUserID());
            ps.setInt(2, orderHistoryDTO.getProductID());
            ps.setInt(3, orderHistoryDTO.getProductQuantity());
            return_flage = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }

    @Override
    public boolean updateOrderHistory(OrderHistoryDTO orderHistoryDTO) {
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String myQuery = "UPDATE orderhistory SET productid=?, time=? WHERE userid=?";
        int return_flage = 0;
        
        try {
            ps = connection.prepareStatement(myQuery);
            ps.setInt(1, orderHistoryDTO.getProductID());
            ps.setTimestamp(2, orderHistoryDTO.getTime());
            ps.setInt(3, orderHistoryDTO.getUserID());
            return_flage = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }

    @Override
    public boolean deleteOrderHistory(int userID) {
        connection = DBConnection.getConnection();
        Statement s = null;
        String myQuery = "DELETE FROM orderhistory WHERE userid=";
        int return_flage = 0;
        
        try {
            s = connection.createStatement();
            return_flage = s.executeUpdate(myQuery + userID);
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }
    
    public ArrayList<OrderHistoryDTO> getAllOrdersHistory(int userID) {
        ArrayList<OrderHistoryDTO> allOrders = new ArrayList<OrderHistoryDTO>();
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String myQuery = "select * from orderhistory where userid = ? order by time asc";

        try {
            ps = connection.prepareStatement(myQuery);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderHistoryDTO orderHistoryDTO = new OrderHistoryDTO();
                orderHistoryDTO.setUserID(rs.getInt(1));
                orderHistoryDTO.setProductID(rs.getInt(2));
                orderHistoryDTO.setTime(rs.getTimestamp(3));
                orderHistoryDTO.setProductQuantity(rs.getInt(4));
                allOrders.add(orderHistoryDTO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allOrders;
    }
    
    

}
