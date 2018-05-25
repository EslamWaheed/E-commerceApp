/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Aya
 */
public class DBUtilCustomer {
     private DataSource datasource;

    public DBUtilCustomer(DataSource thedataSource) {
        datasource = thedataSource;
    }

    public List<Customer> getCustomer() {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        Statement myStm = null;
        ResultSet rst = null;
        try {
            conn = datasource.getConnection();
            String sql = "SELECT * FROM customers";
            myStm = conn.createStatement();
            rst = myStm.executeQuery(sql);
            while (rst.next()) {
             String name = rst.getString("cusName");
             String birthDate = rst.getString("BirthDate");
             String password= rst.getString("cusPassword");
             String job = rst.getString("job");
             String email= rst.getString("email");
             String address = rst.getString("address");
             int credit = rst.getInt("creditLimit");
             int id = rst.getInt("ID");
              Customer tempCustomer = new Customer(id,name, birthDate, password, job, email, address, credit);
              customers.add(tempCustomer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(conn, myStm, rst);

        }
        return customers;
    }

    private void close(Connection conn, Statement myStm, ResultSet rst) {
      try{
        if (rst != null) {
            rst.close();
        }
        if (myStm != null) {

            myStm.close();

        }
        if (conn != null) {

            conn.close();
        }
      }catch (SQLException ex) {
               Logger.getLogger(DBUtilProduct.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
}
    
