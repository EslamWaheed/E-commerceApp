/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementations;

import dao.interfaces.ProductsDAOInt;
import dbconnection.DBConnection;
import dto.CategoryDTO;
import dto.ProductsDTO;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EslamWaheed
 */
public class ProductsDAOImp implements ProductsDAOInt {

    Connection connection = null;

    @Override
    public ProductsDTO getProducts(int id) {
        ProductsDTO productsDTO = new ProductsDTO();
        connection = DBConnection.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String myQuery = "select * from products where id = ?";
        try {
            ps = connection.prepareStatement(myQuery);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                productsDTO.setId(rs.getInt(1));
                productsDTO.setName(rs.getString(2));
                productsDTO.setDescription(rs.getString(3));
                productsDTO.setPrice(rs.getInt(4));
                productsDTO.setQuantities(rs.getInt(5));
               // productsDTO.setImage((Image) rs.getBlob(6));
                productsDTO.setCategoryID(rs.getInt(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productsDTO;
    }

    @Override
    public boolean insertProducts(ProductsDTO productsDTO) {
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String myQuery = "insert into products( name, description, price, quantities, image, categoryID) values(?, ?, ?, ?, ?, ?)";
        int return_flage = 0;
        try {
            ps = connection.prepareStatement(myQuery);
            
            ps.setString(1, productsDTO.getName());
            ps.setString(2, productsDTO.getDescription());
            ps.setInt(3, productsDTO.getPrice());
            ps.setInt(4, productsDTO.getQuantities());
            
            if (productsDTO.getImage() != null) {
                ps.setBlob(5,productsDTO.getImage());
            }
//                // fetches input stream of the upload file for the blob column
            
            
            ps.setInt(6, productsDTO.getCategoryID());
            return_flage = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }

    @Override
    public boolean updateProducts(ProductsDTO productsDTO) {
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String myQuery = "UPDATE products SET name = ?, description = ?, price = ?, quantities = ?, image = ?, categoryID = ? WHERE id=?";
        int return_flage = 0;

        try {
            ps = connection.prepareStatement(myQuery);
            ps.setString(1, productsDTO.getName());
            ps.setString(2, productsDTO.getDescription());
            ps.setInt(3, productsDTO.getPrice());
            ps.setInt(4, productsDTO.getQuantities());
//            ps.setBlob(5, (Blob) productsDTO.getImage());
             if (productsDTO.getImage() != null) {
                ps.setBlob(5,productsDTO.getImage());
            }
            ps.setInt(6, productsDTO.getCategoryID());
            ps.setInt(7, productsDTO.getId());
            return_flage = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }

   @Override
    public boolean deleteProducts(int id) {
        connection = DBConnection.getConnection();
        Statement s = null;
        PreparedStatement ps = null;
        String myQuery3 = "UPDATE products SET available = ? where id=?";
        String myQuery = "DELETE FROM products WHERE id=";
        int return_flage = 0;
        try {
            s = connection.createStatement();
            return_flage = s.executeUpdate(myQuery + id);
        } catch (SQLException ex) {
            //Logger.getLogger(ProductsDTO.class.getName()).log(Level.SEVERE, null, ex);
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
    public Set<ProductsDTO> getAllProducts() {
        connection = DBConnection.getConnection();
        Set<ProductsDTO> productsDTOs = new HashSet<>();
        Statement s = null;
        ResultSet rs = null;
        String myQuery = "select * from products";

        try {
            s = connection.createStatement();
            rs = s.executeQuery(myQuery);
            while (rs.next()) {
                ProductsDTO productsDTO = new ProductsDTO();
                productsDTO.setId(rs.getInt(1));
                productsDTO.setName(rs.getString(2));
                productsDTO.setDescription(rs.getString(3));
                productsDTO.setPrice(rs.getInt(4));
                productsDTO.setQuantities(rs.getInt(5));
//                productsDTO.setImage((Image) rs.getBlob(6));
                productsDTO.setCategoryID(rs.getInt(7));
                productsDTOs.add(productsDTO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productsDTOs;
    }

    @Override
    public int getNumberOfProducts() {
        connection = DBConnection.getConnection();
        Statement s = null;
        ResultSet rs = null;
        String myQuery = "select * from products";
        int countProducts = 0;
        try {
            s = connection.createStatement();
            rs = s.executeQuery(myQuery);
            while (rs.next()) {
                countProducts++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return countProducts;
    }

    @Override
    public HashMap<CategoryDTO, ArrayList<ProductsDTO>> getAllOfCatagoryProducts() {
        connection = DBConnection.getConnection();
        Statement s = null;
        ResultSet rs = null;
        PreparedStatement ps= null;
        String myQuery = "select * from products where categoryID = ? and available = '1' ";
        CategoryDAOImp categoryDAOImp = new CategoryDAOImp();
        Set<CategoryDTO> categoryDTOs = categoryDAOImp.getAllCategory();
        //Set<ProductsDTO> productsDTOs = new HashSet<ProductsDTO>();
        HashMap<CategoryDTO, ArrayList<ProductsDTO>> hashMap = new HashMap<>();
        try {
            ps = connection.prepareStatement(myQuery);
            for (CategoryDTO categoryDTO : categoryDTOs) {
                ArrayList<ProductsDTO> productsDTOs = new ArrayList<ProductsDTO>();
                ps.setInt(1, categoryDTO.getId());
                rs = ps.executeQuery();
                while (rs.next()) {
                    ProductsDTO productsDTO = new ProductsDTO();
                    productsDTO.setId(rs.getInt(1));
                    productsDTO.setName(rs.getString(2));
                    productsDTO.setDescription(rs.getString(3));
                    productsDTO.setPrice(rs.getInt(4));
                    productsDTO.setQuantities(rs.getInt(5));
//                    productsDTO.setImage((Image) rs.getBlob(6));
                    productsDTO.setCategoryID(rs.getInt(7));
                    productsDTOs.add(productsDTO);
                    
                }
                hashMap.put(categoryDTO, productsDTOs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hashMap;
    }
    
    public boolean updateProductQuantity(int productID,int quantity) {
        connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String myQuery = "UPDATE products SET quantities = ? WHERE id=?";
        int return_flage = 0;

        try {
            ps = connection.prepareStatement(myQuery);
            
            ps.setInt(1, quantity);
            ps.setInt(2, productID);
            
            return_flage = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_flage == 1;
    }
    
    public File downloadImage(int id) throws FileNotFoundException, IOException{
        ResultSet rs = null;
        Image image;
        File file = null;
        try {
            //InputStream  fis = new FileInputStream(file);
            connection = DBConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select image from products where id = ?");
            //pst.setBinaryStream(1, fis,(int)(file.length()));
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            if(rs.next()){
                InputStream is = rs.getBinaryStream(1);
                
                file = new File("E:\\ITI\\study\\Servlets and JSP\\laps\\E-commerce practice\\E-commerce practice\\web\\images\\products\\"+id+".png");
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
    
    
    public int getMaxID() {
        connection = DBConnection.getConnection();
        Statement s = null;
        ResultSet rs = null;
        String myQuery = "select max(id) from products";
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

}
