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
public class DBUtilProduct {
     private DataSource datasource;

    public DBUtilProduct(DataSource thedataSource) {
        datasource = thedataSource;
    }

    public void addProduct(Product theNewProd) {
        Connection conn=null;
        PreparedStatement prep = null; 
        try {
             conn = datasource.getConnection();
             String sql = " insert into products " + " (prodName,description,price,numItems) " + "values (?,?,?,?) ";
             prep=conn.prepareStatement(sql);
             prep.setString(1,theNewProd.getProdName());
             prep.setString(2,theNewProd.getDesc());
             prep.setInt(3,theNewProd.getPrice());
             prep.setInt(4,theNewProd.getNumberItem());
             prep.execute();
         } catch (SQLException ex) {
             Logger.getLogger(DBUtilProduct.class.getName()).log(Level.SEVERE, null, ex);
         }
        finally{
          close(conn, prep, null);
        }
    }

   

    public List<Product> getProduct() {
        List<Product> product = new ArrayList<>();
        Connection conn = null;
        Statement myStm = null;
        ResultSet rst = null;
        try {
            conn = datasource.getConnection();
            String sql = "SELECT * FROM products";
            myStm = conn.createStatement();
            rst = myStm.executeQuery(sql);
            while (rst.next()) {
                String prodName = rst.getString("prodName");
                String desc = rst.getString("description");
                int price = rst.getInt("price");
                int numItems = rst.getInt("numItems");
                int id = rst.getInt("id");
                Product tempProduct = new Product(prodName, desc, price, numItems, id);
                product.add(tempProduct);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(conn, myStm, rst);

        }
        return product;
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

    Product getProduct(int productId) {
             Product reqProd = null;
             Connection conn=null;
            PreparedStatement prep = null;
             ResultSet rst = null;
         try {
             conn=datasource.getConnection();
             String sql = "SELECT * FROM products WHERE ID=?";
             prep=conn.prepareStatement(sql);
             prep.setInt(1, productId);
             rst=prep.executeQuery();
             if(rst.next())
             {
              String prodName = rst.getString("prodName");
              String desc = rst.getString("description");
              int price = rst.getInt("price");
              int numberItems = rst.getInt("numItems");
              reqProd= new Product(prodName, desc, price, numberItems,productId);
             }
             else{
             throw new Exception("couldn't find this product id"+productId);
             }
             
             
             
             
             
         } catch (SQLException ex) {
             Logger.getLogger(DBUtilProduct.class.getName()).log(Level.SEVERE, null, ex);
         } catch (Exception ex) {
             Logger.getLogger(DBUtilProduct.class.getName()).log(Level.SEVERE, null, ex);
         }
         finally{
         close(conn,prep,rst);
         }     
       return reqProd;
    }

    void updateProduct(Product theUpdateProd) {
        Connection conn=null;
        PreparedStatement prep = null; 
        try {
             conn = datasource.getConnection();
             String sql = "update products " + "set prodName=?, description=?, price=?, numItems=? " +" where id=? ";
             prep=conn.prepareStatement(sql);
             prep.setString(1,theUpdateProd.getProdName());
             prep.setString(2,theUpdateProd.getDesc());
             prep.setInt(3,theUpdateProd.getPrice());
             prep.setInt(4,theUpdateProd.getNumberItem());
             prep.setInt(5,theUpdateProd.getId());
             prep.execute();
         } catch (SQLException ex) {
             Logger.getLogger(DBUtilProduct.class.getName()).log(Level.SEVERE, null, ex);
         }
        finally{
          close(conn, prep, null);
        }

    }

    void deleteProduct(int productId) {
        Connection conn=null;
        PreparedStatement prep = null; 
        try {
             conn = datasource.getConnection();
             String sql = "delete from products where id=?";
             prep=conn.prepareStatement(sql);
             prep.setInt(1,productId);
            
             prep.execute();
         } catch (SQLException ex) {
             Logger.getLogger(DBUtilProduct.class.getName()).log(Level.SEVERE, null, ex);
         }
        finally{
          close(conn, prep, null);
        }

    }
}

