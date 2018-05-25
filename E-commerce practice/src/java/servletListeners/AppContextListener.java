/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletListeners;

import dao.implementations.ProductsDAOImp;
import dto.CategoryDTO;
import dto.ProductsDTO;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import util.DBConnectionManager;
import util.User;

/**
 *
 * @author Hazem
 */

@WebListener
public class AppContextListener implements ServletContextListener {
    Hashtable<String ,ArrayList<User>> hm = new Hashtable<String ,ArrayList<User>>();
    HashMap<CategoryDTO, ArrayList<ProductsDTO>> productsWithCategories = new HashMap<CategoryDTO, ArrayList<ProductsDTO>>();
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("context initialized");
        ProductsDAOImp productsDAOImp = new ProductsDAOImp();
        productsWithCategories = productsDAOImp.getAllOfCatagoryProducts();
        ServletContext ctx = servletContextEvent.getServletContext();
        ctx.setAttribute("products1", productsWithCategories);
        Set<ProductsDTO> allProducts = productsDAOImp.getAllProducts();
        for (ProductsDTO product : allProducts) {
            File file = null;
            try {
                file = productsDAOImp.downloadImage(product.getId());
            } catch (IOException ex) {
                Logger.getLogger(AppContextListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        
    }
    
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("context destroyed");
    }
    
}
