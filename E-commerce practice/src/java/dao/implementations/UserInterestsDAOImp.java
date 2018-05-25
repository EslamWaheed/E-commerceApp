/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementations;

import dao.interfaces.UserInterestsDAOInt;
import dbconnection.DBConnection;
import dto.UserInterestsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EslamWaheed
 */
public class UserInterestsDAOImp implements UserInterestsDAOInt {

    Connection connection = null;

    @Override
    public UserInterestsDTO getUserInterests(int userID) {
        UserInterestsDTO userInterestsDTO = new UserInterestsDTO();
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String myQuery = "select * from userinterests where id = ?";

        try {
            ps = connection.prepareStatement(myQuery);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            if (rs.next()) {
                userInterestsDTO.setUserID(rs.getInt(1));
                userInterestsDTO.setInterests(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserInterestsDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userInterestsDTO;
    }

    @Override
    public boolean insertUserInterests(UserInterestsDTO userInterestsDTO) {
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String myQuery = "insert into userinterests(userid, interests) values(?, ?)";
        int return_flage = 0;
        try {
            ps = connection.prepareStatement(myQuery);
            ps.setInt(1, userInterestsDTO.getUserID());
            ps.setString(2, userInterestsDTO.getInterests());
            return_flage = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserInterestsDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }

    @Override
    public boolean updateUserInterests(UserInterestsDTO userInterestsDTO) {
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String myQuery = "UPDATE userinterests SET interests = ? WHERE userid=?";
        int return_flage = 0;

        try {
            ps = connection.prepareStatement(myQuery);
            ps.setString(1, userInterestsDTO.getInterests());
            ps.setInt(2, userInterestsDTO.getUserID());
            return_flage = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserInterestsDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }

    @Override
    public boolean deleteUserInterests(int userID) {
        connection = DBConnection.getConnection();
        Statement s = null;
        String myQuery = "DELETE FROM interests WHERE userid=";
        int return_flage = 0;
        try {
            s = connection.createStatement();
            return_flage = s.executeUpdate(myQuery + userID);
        } catch (SQLException ex) {
            Logger.getLogger(UserInterestsDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }

}
