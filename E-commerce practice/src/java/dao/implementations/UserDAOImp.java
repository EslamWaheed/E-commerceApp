/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import dao.interfaces.UserDAOInt;
import dbconnection.DBConnection;
import dto.OrderHistoryDTO;
import dto.UserDTO;
import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
/**
 *
 * @author EslamWaheed
 */
public class UserDAOImp implements UserDAOInt {

    Connection connection = null;

    @Override
    public UserDTO getUser(int id) {
        UserDTO userDTO = new UserDTO();
        connection = DBConnection.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String myQuery = "select * from users where id = ?";
        try {
            ps = connection.prepareStatement(myQuery);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                userDTO.setId(rs.getInt(1));
                userDTO.setName(rs.getString(2));
                userDTO.setBd(rs.getDate(3));
                userDTO.setPassword(rs.getInt(4));
                userDTO.setJob(rs.getString(5));
                userDTO.setEmail(rs.getString(6));
                userDTO.setAddress(rs.getString(7));
                userDTO.setCredit_limit(rs.getInt(8));
                //userDTO.setImage((Image) rs.getBlob(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                rs.close();
//                ps.close();
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        return userDTO;
    }

    @Override
    public boolean insertUser(UserDTO userDTO) {
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String myQuery = "insert into users(id, name, bd, password, job, email, address, creditlimit, image) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int return_flage = 0;
        try {
            ps = connection.prepareStatement(myQuery);
            ps.setInt(1, userDTO.getId());
            ps.setString(2, userDTO.getName());
            ps.setDate(3, (Date) userDTO.getBd());
            ps.setInt(4, userDTO.getPassword());
            ps.setString(5, userDTO.getJob());
            ps.setString(6, userDTO.getEmail());
            ps.setString(7, userDTO.getAddress());
            ps.setInt(8, userDTO.getCredit_limit());
            //ps.setBlob(9, (Blob) userDTO.getImage());
            if (userDTO.getImage() != null) {
                ps.setBlob(9,userDTO.getImage());
            }
            return_flage = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String myQuery = "UPDATE users SET name=?, bd=?, password=?, job=?, email=?, address=?, creditlimit=? WHERE id=?";
        int return_flage = 0;
        try {
            ps = connection.prepareStatement(myQuery);
            ps.setString(1, userDTO.getName());
            ps.setDate(2, (Date) userDTO.getBd());
            ps.setInt(3, userDTO.getPassword());
            ps.setString(4, userDTO.getJob());
            ps.setString(5, userDTO.getEmail());
            ps.setString(6, userDTO.getAddress());
            ps.setInt(7, userDTO.getCredit_limit());
            //ps.setBlob(8, (Blob) userDTO.getImage());
            ps.setInt(8, userDTO.getId());
            return_flage = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }

    @Override
    public boolean deleteUser(int id) {
        connection = DBConnection.getConnection();
        Statement s = null;
        String myQuery = "DELETE FROM users WHERE id=";
        int return_flage = 0;
        try {
            s = connection.createStatement();
            return_flage = s.executeUpdate(myQuery + id);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }

    @Override
    public Set<UserDTO> getAllUser() {
        connection = DBConnection.getConnection();
        Set<UserDTO> userDTOs = new HashSet<>();
        Statement s = null;
        String myQuery = "select * from users";
        ResultSet rs = null;

        try {
            s = connection.createStatement();
            rs = s.executeQuery(myQuery);
            while (rs.next()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(rs.getInt(1));
                userDTO.setName(rs.getString(2));
                userDTO.setBd(rs.getDate(3));
                userDTO.setPassword(rs.getInt(4));
                userDTO.setJob(rs.getString(5));
                userDTO.setEmail(rs.getString(6));
                userDTO.setAddress(rs.getString(7));
                userDTO.setCredit_limit(rs.getInt(8));
//                userDTO.setImage((Image) rs.getBlob(9));
                userDTO.setPrivalege(rs.getInt(10));
                userDTOs.add(userDTO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userDTOs;
    }
    
    public File downloadImage(int id) throws FileNotFoundException, IOException{
        ResultSet rs = null;
        Image image;
        File file = null;
        try {
            //InputStream  fis = new FileInputStream(file);
            connection = DBConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select image from users where id = ?");
            //pst.setBinaryStream(1, fis,(int)(file.length()));
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            if(rs.next()){
                InputStream is = rs.getBinaryStream(1);
                
                file = new File("E:\\ITI\\study\\Servlets and JSP\\laps\\E-commerce practice\\E-commerce practice\\web\\images\\users\\"+id+".png");
                OutputStream os = new FileOutputStream(file);
                byte[] content = new byte[1024];
                
                int size = 0;
                while((size = is.read(content)) != -1){
                    os.write(content,0,size);
                }
             
                os.close();
                is.close();
                
                //image = new Image ("photo.png",100,150,true,true);
                //file = new File("../../photo1.png");
                
            }
            
            pst.close();
            connection.close();
            
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
        }
        return file;
    }
    
    public HashMap<UserDTO, Set<OrderHistoryDTO>> getAllOfOrderHistoryUsers() throws SQLException {
        connection = DBConnection.getConnection();
        Statement s = null;
        ResultSet rs = null;
        
        
        PreparedStatement pst = connection.prepareStatement("select * from orderhistory where userid = ? order by time asc");
        
        //String myQuery = "select * from orderhistory where userid = ";
        UserDAOImp userDAOImp = new UserDAOImp();
        Set<UserDTO> userDTOs = userDAOImp.getAllUser();
        //Set<ProductsDTO> productsDTOs = new HashSet<ProductsDTO>();
        HashMap<UserDTO, Set<OrderHistoryDTO>> hashMap = new HashMap<>();
        try {
            s = connection.createStatement();
            for (UserDTO userDTO : userDTOs) {
                Set<OrderHistoryDTO> orderHistoryDTOs = new HashSet<OrderHistoryDTO>();
                //rs = s.executeQuery(myQuery + userDTO.getId() + " order by time asc");
                pst.setInt(1, userDTO.getId());
                rs = pst.executeQuery();
                while (rs.next()) {
                    OrderHistoryDTO orderHistoryDTO = new OrderHistoryDTO();
                    orderHistoryDTO.setUserID(rs.getInt(1));
                    orderHistoryDTO.setProductID(rs.getInt(2));
                    orderHistoryDTO.setTime(rs.getTimestamp(3));
                    orderHistoryDTO.setProductQuantity(rs.getInt(4));
                    orderHistoryDTOs.add(orderHistoryDTO);
                    
                }
                hashMap.put(userDTO, orderHistoryDTOs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hashMap;
    }

    @Override
    public int getNumberOfUser() {

        connection = DBConnection.getConnection();
        Statement s = null;
        ResultSet rs = null;
        String myQuery = "select * from users";
        int countUsers = 0;
        try {
            s = connection.createStatement();
            rs = s.executeQuery(myQuery);
            while (rs.next()) {
                countUsers++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return countUsers;
    }

    @Override
    public int getMaxID() {
        connection = DBConnection.getConnection();
        Statement s = null;
        ResultSet rs = null;
        String myQuery = "select max(id) from users";
        int MaxID = 0;
        try {
            s = connection.createStatement();
            rs = s.executeQuery(myQuery);
            if (rs.next()) {
                MaxID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MaxID;
    

    }

    @Override
    public UserDTO getUserByEmailAndPassword(String email, int password) {
        

        UserDTO userDTO = new UserDTO();
        connection = DBConnection.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String myQuery = "select * from users where email=? and password=? ";
        try {
            ps = connection.prepareStatement(myQuery);
            ps.setString(1, email);
            ps.setInt(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                userDTO.setId(rs.getInt(1));
                userDTO.setName(rs.getString(2));
                userDTO.setBd(rs.getDate(3));
                userDTO.setPassword(rs.getInt(4));
                userDTO.setJob(rs.getString(5));
                userDTO.setEmail(rs.getString(6));
                userDTO.setAddress(rs.getString(7));
                userDTO.setCredit_limit(rs.getInt(8));
                userDTO.setPrivalege(rs.getInt(10));
                //userDTO.setImage((Image) rs.getBlob(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userDTO;


    }
    
    @Override
    public boolean updatePrivUser(UserDTO userdto) {
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
       String myQuery = "UPDATE users SET name=?, bd=?, password=?, job=?, email=?, address=?, creditlimit=?, privalege=? WHERE id=?";
        int return_flage = 0;
        try {
            ps = connection.prepareStatement(myQuery);
            ps.setString(1, userdto.getName());
            ps.setDate(2, (Date) userdto.getBd());
            ps.setInt(3, userdto.getPassword());
            ps.setString(4, userdto.getJob());
            ps.setString(5, userdto.getEmail());
            ps.setString(6, userdto.getAddress());
            ps.setInt(7, userdto.getCredit_limit());
            ps.setInt(8, userdto.getPrivalege());
            //ps.setBlob(8, (Blob) userDTO.getImage());
            ps.setInt(9, userdto.getId());
            return_flage = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }
    
    

}
