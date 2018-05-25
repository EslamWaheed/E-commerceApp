/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.implementations.CategoryDAOImp;
import dao.implementations.ProductsDAOImp;
import dao.implementations.UserDAOImp;
import dto.CategoryDTO;
import dto.ProductsDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author EslamWaheed
 */
public class TestDB extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
//        UserDTO userDTO = new UserDTO();
//        UserDAOImp userDAOImp = new UserDAOImp();
//        userDTO = userDAOImp.getUser(1);
//        out.print(userDTO.getId() + "\n");
//        out.print(userDTO.getName() + "\n");
//        out.print(userDTO.getBd() + "\n");
//        out.print(userDTO.getPassword() + "\n");
//        out.print(userDTO.getJob() + "\n");
//        out.print(userDTO.getEmail() + "\n");
//        out.print(userDTO.getAddress() + "\n");
//        out.print(userDTO.getCredit_limit() + "\n");
//        out.print(userDTO.getImage() + "\n");

//        PrintWriter out = response.getWriter();
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(2);
//        userDTO.setName("Waheed");
//        userDTO.setBd(new Date(1996, 2, 1));
//        userDTO.setPassword(708090);
//        userDTO.setJob("Teacher");
//        userDTO.setEmail("Waheed@gmail.com");
//        userDTO.setAddress("banimazer");
//        userDTO.setCredit_limit(20000);
//        userDTO.setImage(null);
//
//        UserDAOImp userDAOImp = new UserDAOImp();
//        userDAOImp.insertUser(userDTO);
//        PrintWriter out = response.getWriter();
//        CategoryDTO categoryDTO = new CategoryDTO();
//        CategoryDAOImp categoryDAOImp = new CategoryDAOImp();
//        categoryDTO = categoryDAOImp.getCategory(1);
//        out.print(categoryDTO.getId() + "\n");
//        out.print(categoryDTO.getName() + "\n");
//        categoryDTO = categoryDAOImp.getCategory(2);
//        out.print(categoryDTO.getId() + "\n");
//        out.print(categoryDTO.getName() + "\n");
//        categoryDTO.setId(2);
//        categoryDTO.setName("Clothes");
//        categoryDAOImp.insertCategory(categoryDTO);
//        ProductsDTO productsDTO = new ProductsDTO(2, "table", "this is the magic table", 20, 10, null, 2);
//        ProductsDAOImp productsDAOImp = new ProductsDAOImp();
//        ProductsDTO productsDTO = new ProductsDTO();
//        productsDTO = productsDAOImp.getProducts(1);
//        out.print(productsDTO.getId() + "\n");
//        out.print(productsDTO.getName() + "\n");
//        out.print(productsDTO.getPrice()+ "\n");
//        out.print(productsDTO.getQuantities()+ "\n");
//        out.print(productsDTO.getDescription()+ "\n");
//        out.print(productsDTO.getImage()+ "\n");
//        out.print(productsDTO.getCategoryID()+ "\n");
//        productsDAOImp.deleteProducts(1);
//        Set<UserDTO> userDTOs = userDAOImp.getAllUser();
//        for (UserDTO userDTO : userDTOs) {
//            out.print(userDTO.getId() + "\n");
//            out.print(userDTO.getName() + "\n");
//            out.print(userDTO.getBd() + "\n");
//            out.print(userDTO.getPassword() + "\n");
//            out.print(userDTO.getJob() + "\n");
//            out.print(userDTO.getEmail() + "\n");
//            out.print(userDTO.getAddress() + "\n");
//            out.print(userDTO.getCredit_limit() + "\n");
//            out.print(userDTO.getImage() + "\n");
//        }
//        Set<ProductsDTO> productsDTOs = productsDAOImp.getAllProducts();
//        for (ProductsDTO productsDTO : productsDTOs) {
//            out.print(productsDTO.getId() + "\n");
//            out.print(productsDTO.getName() + "\n");
//            out.print(productsDTO.getPrice() + "\n");
//            out.print(productsDTO.getQuantities() + "\n");
//            out.print(productsDTO.getDescription() + "\n");
//            out.print(productsDTO.getImage() + "\n");
//            out.print(productsDTO.getCategoryID() + "\n");
//        }
        ProductsDAOImp productsDAOImp = new ProductsDAOImp();
        //        int countp = productsDAOImp.getNumberOfProducts();
        //        out.print(countp + "\n");
        //        
        //        CategoryDAOImp categoryDAOImp = new CategoryDAOImp();
        //        int countc = categoryDAOImp.getNumberOfCategory();
        //        out.print(countc + "\n");
        HashMap<CategoryDTO, ArrayList<ProductsDTO>> hashMap = productsDAOImp.getAllOfCatagoryProducts();
        for (Map.Entry m : hashMap.entrySet()) {
            out.println(m.getKey() + " " + m.getValue() + "\n");
        }
//        CategoryDAOImp categoryDAOImp = new CategoryDAOImp();
//        Set<CategoryDTO> categoryDTOs = categoryDAOImp.getAllCategory();
//        for (CategoryDTO categoryDTO : categoryDTOs) {
//            out.print(categoryDTO.getId() + "\n");
//            out.print(categoryDTO.getName() + "\n");
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
