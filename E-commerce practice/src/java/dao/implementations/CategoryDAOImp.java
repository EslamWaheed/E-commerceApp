/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementations;

import dao.interfaces.CategoryDAOInt;
import dbconnection.DBConnection;
import dto.CategoryDTO;
import dto.ProductsDTO;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EslamWaheed
 */
public class CategoryDAOImp implements CategoryDAOInt {

    Connection connection = null;

    @Override
    public CategoryDTO getCategory(int id) {
        CategoryDTO categoryDTO = new CategoryDTO();
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String myQuery = "select * from category where id = ?";

        try {
            ps = connection.prepareStatement(myQuery);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                categoryDTO.setId(rs.getInt(1));
                categoryDTO.setName(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoryDTO;
    }

    @Override
    public boolean insertCategory(CategoryDTO categoryDTO) {
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String myQuery = "insert into category(name) values(?)";
        int return_flage = 0;
        try {
            ps = connection.prepareStatement(myQuery);
//            ps.setInt(1, categoryDTO.getId());
            ps.setString(1, categoryDTO.getName());
            return_flage = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }

    @Override
    public boolean updateCategory(CategoryDTO categoryDTO) {
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String myQuery = "UPDATE categroy SET name=? WHERE id=?";
        int return_flage = 0;

        try {
            ps = connection.prepareStatement(myQuery);
            ps.setString(1, categoryDTO.getName());
            ps.setInt(2, categoryDTO.getId());
            return_flage = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }

    @Override
    public boolean deleteCategory(int id) {
        connection = DBConnection.getConnection();
        Statement s = null;
        PreparedStatement ps = null;
        String myQuery3 = "UPDATE category SET available=? where id=?";
        String myQuery = "DELETE FROM category WHERE id=";
        int return_flage = 0;

        try {
            s = connection.createStatement();
            return_flage = s.executeUpdate(myQuery + id);
        } catch (SQLException ex) {
           // Logger.getLogger(CategoryDAOImp.class.getName()).log(Level.SEVERE, null, ex);
           try {
                //  Logger.getLogger(ProductsDTO.class.getName()).log(Level.SEVERE, null, ex)
                      ps = connection.prepareStatement(myQuery3);
                      ps.setInt(1,0);
                      ps.setInt(2, id);
                      return_flage = ps.executeUpdate();
                 } catch (SQLException ex1) {
                Logger.getLogger(ProductsDAOImp.class.getName()).log(Level.SEVERE, null, ex1);
        }
        }
        return return_flage == 1;
    }

    @Override
    public int getNumberOfCategory() {
        connection = DBConnection.getConnection();
        Statement s = null;
        ResultSet rs = null;
        String myQuery = "select * from category";
        int countCategorys = 0;
        try {
            s = connection.createStatement();
            rs = s.executeQuery(myQuery);
            while (rs.next()) {
                countCategorys++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return countCategorys;
    }

    @Override
    public Set<CategoryDTO> getAllCategory() {
        connection = DBConnection.getConnection();
        Set<CategoryDTO> categoryDTOs = new HashSet<>();
        Statement s = null;
        ResultSet rs = null;
        String myQuery = "select * from category where available = '1'";

        try {
            s = connection.createStatement();
            rs = s.executeQuery(myQuery);
            while (rs.next()) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setId(rs.getInt(1));
                categoryDTO.setName(rs.getString(2));
                categoryDTOs.add(categoryDTO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoryDTOs;
    }

}
