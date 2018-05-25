/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.implementations.CategoryDAOImp;
import dao.implementations.OrderHistoryDAOImp;
import dao.implementations.ProductsDAOImp;
import dao.implementations.UserDAOImp;
import dto.OrderHistoryDTO;
import dto.ProductsDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import product.displayProd;
import product.prodUserHistory;

/**
 *
 * @author Hazem
 */
@WebServlet(urlPatterns = {"/userHistory"})
public class userHistory extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            
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
        ProductsDAOImp prodDaoImp = new ProductsDAOImp(); 
    CategoryDAOImp catDaoImp = new CategoryDAOImp();
    UserDAOImp userDaoImp=new UserDAOImp();
    OrderHistoryDAOImp orderHistoryDaoImp = new OrderHistoryDAOImp();
        
        try {
            String SusedId = request.getParameter("userId");
            int usedId = Integer.parseInt(SusedId);
            ArrayList<OrderHistoryDTO> history = orderHistoryDaoImp.getAllOrdersHistory(usedId);
            ArrayList<prodUserHistory> listNew = new ArrayList<prodUserHistory>();
           UserDTO user=userDaoImp.getUser(usedId);
           String uName = user.getName();
           for (OrderHistoryDTO s : history){
           int pId=s.getProductID();
           ProductsDTO prod = prodDaoImp.getProducts(pId);
           String pName=prod.getName();
           Timestamp oTime=s.getTime();
               System.out.println(uName);
               System.out.println(pName);
               System.out.println(oTime);
               System.out.println(s.getProductQuantity());
           prodUserHistory prodUserHis = new prodUserHistory(uName, pName, oTime,s.getProductQuantity());
           listNew.add(prodUserHis);  
         } 
          request.getSession().setAttribute("history_List",listNew);
           //RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/history.jsp");
           //dispatcher.forward(request, response);
          response.sendRedirect("orderhistory.jsp");
       } catch (IOException ex) {
           Logger.getLogger(displayProd.class.getName()).log(Level.SEVERE, null, ex);
       }
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
