/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementations;

import dao.interfaces.ShoppingCartDAOInt;
import dbconnection.DBConnection;
import dto.ShoppingCartDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
/**
 *
 * @author EslamWaheed
 */
public class ShoppingCartDAOImp implements ShoppingCartDAOInt {

    Connection connection = null;

    @Override
    public ShoppingCartDTO getShoppingCart(int id) {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String myQuery = "select * from shoppingcart where id = ?";

        try {
            ps = connection.prepareStatement(myQuery);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                shoppingCartDTO.setUserID(rs.getInt(1));
                shoppingCartDTO.setProductID(rs.getInt(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return shoppingCartDTO;
    }

    @Override
    public boolean insertShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String myQuery = "insert into shoppingcart(userid, productid,quantity) values(?, ?, ?)";
        int return_flage = 0;
        try {
            ps = connection.prepareStatement(myQuery);
            ps.setInt(1, shoppingCartDTO.getUserID());
            ps.setInt(2, shoppingCartDTO.getProductID());
            ps.setInt(3, shoppingCartDTO.getProductQuantity());
            return_flage = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }

    @Override
    public boolean updateShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String myQuery = "update shoppingcart set quantity = ? where userid = ? and productid = ?";
        int return_flage = 0;
        try {
            ps = connection.prepareStatement(myQuery);
            ps.setInt(1, shoppingCartDTO.getProductQuantity());
            ps.setInt(2, shoppingCartDTO.getUserID() );
            ps.setInt(3,shoppingCartDTO.getProductID());
            return_flage = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }

    @Override
    public boolean deleteShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String myQuery = "delete from shoppingcart where userid = ? and productid = ?";
        int return_flage = 0;
        try {
            ps = connection.prepareStatement(myQuery);
            
            ps.setInt(1, shoppingCartDTO.getUserID() );
            ps.setInt(2,shoppingCartDTO.getProductID());
            return_flage = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }
    
    
    public boolean checkcart(ShoppingCartDTO shoppingCartDTO) {
        
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String myQuery = "select * from shoppingcart where userid = ? and productid = ? ";
        boolean x = false;
        try {
            ps = connection.prepareStatement(myQuery);
            ps.setInt(1, shoppingCartDTO.getUserID() );
            ps.setInt(2,shoppingCartDTO.getProductID());
            rs = ps.executeQuery();
            if (rs.next()) {
                x = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
    
    public ArrayList<ShoppingCartDTO> getAllShoppingCarts(int userId) {
        ArrayList<ShoppingCartDTO> allShoppingCartDTO = new ArrayList<ShoppingCartDTO>();
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String myQuery = "select * from shoppingcart where userid = ?";

        try {
            ps = connection.prepareStatement(myQuery);
            ps.setInt(1, userId );
            rs = ps.executeQuery();
            while (rs.next()) {
                ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
                shoppingCartDTO.setUserID(rs.getInt(1));
                shoppingCartDTO.setProductID(rs.getInt(2));
                shoppingCartDTO.setProductQuantity(rs.getInt(3));
                allShoppingCartDTO.add(shoppingCartDTO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allShoppingCartDTO;
    }

}
