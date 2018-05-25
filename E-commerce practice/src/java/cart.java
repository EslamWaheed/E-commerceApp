/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.implementations.ShoppingCartDAOImp;
import dto.ShoppingCartDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.util.*;
import util.MyCart;
/**
 *
 * @author Hazem
 */
@WebServlet(urlPatterns = {"/cart"})
public class cart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        int x = 1;
        try (PrintWriter out = response.getWriter()) {
            
            
                System.out.println("not logged in user");
                //List<String> myCart = (List<String>)session.getAttribute("mycart");
                List<MyCart> myCart = (List<MyCart>)session.getAttribute("mycart");
                if(myCart == null){
                    myCart = new ArrayList<MyCart>();
                    session.setAttribute("mycart", myCart);
                    System.out.println("cart created");
                }
                MyCart cartproduct = new MyCart(Integer.parseInt(request.getParameter("cart")),Integer.parseInt(request.getParameter("productprice")),request.getParameter("productname"),request.getParameter("categoryname"),1,Integer.parseInt(request.getParameter("availablequantity")));
                for(int i=0;i<myCart.size();i++){
                    if(myCart.get(i).getProductId() == Integer.parseInt(request.getParameter("cart")) ){
                        x=0;
                    }
                }
                if(x!=0){
                    myCart.add(cartproduct);
                }
                
                session.setAttribute("mycart", myCart);
                Integer countPrices = 0;
                int countProducts = 0;
                for(int i=0;i<myCart.size();i++){
                  countPrices += myCart.get(i).getProductPrice();
                  countProducts++;
                }
                
                session.setAttribute("countprices", countPrices);
                session.setAttribute("countproducts", countProducts);
            if(session.getAttribute("user") != null){
                UserDTO user1 = (UserDTO)session.getAttribute("user");
                System.out.println("session founded");
                ShoppingCartDTO cartdto = new ShoppingCartDTO(user1.getId(),Integer.parseInt(request.getParameter("cart")),1 );
                ShoppingCartDAOImp cart = new ShoppingCartDAOImp();
                if(cart.checkcart(cartdto)){
                    System.out.println("item already exist");
                }else{
                    System.out.println("added to cart: "+ Integer.parseInt(request.getParameter("cart")) );
                    /* TODO output your page here. You may use following sample code. */
                    cart.insertShoppingCart(cartdto);
                }
                
            }
            
            
            response.sendRedirect("main.jsp");
            //RequestDispatcher rd=request.getRequestDispatcher("main.jsp");  
            //rd.forward(request, response);
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
