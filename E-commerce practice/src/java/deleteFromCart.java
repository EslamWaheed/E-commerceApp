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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.MyCart;

/**
 *
 * @author Hazem
 */
@WebServlet(urlPatterns = {"/deleteFromCart"})
public class deleteFromCart extends HttpServlet {

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
        int itemId = Integer.parseInt(request.getParameter("deleteditem"));
        try (PrintWriter out = response.getWriter()) {
            if(session.getAttribute("user") == null){
                System.out.println("no session founded");
                List<MyCart> myCart = (List<MyCart>)session.getAttribute("mycart");
                for(int i=0;i<myCart.size();i++){
                    if (myCart.get(i).getProductId() == itemId){
                        myCart.remove(i);
                    }
                }
                int countPrices = 0;
                for(int i=0;i<myCart.size();i++){
                    countPrices += myCart.get(i).getProductPrice();
                }
                session.setAttribute("countprices", countPrices);
                session.setAttribute("mycart", myCart);
            }else{
                UserDTO user1 = (UserDTO)session.getAttribute("user");
                ShoppingCartDAOImp cartDao = new ShoppingCartDAOImp();
                ShoppingCartDTO cartDto = new ShoppingCartDTO();
                System.out.println("session founded");
                List<MyCart> myCart = (List<MyCart>)session.getAttribute("mycart");
                for(int i=0;i<myCart.size();i++){
                    if (myCart.get(i).getProductId() == itemId){
                        myCart.remove(i);
                        cartDto.setUserID(user1.getId());
                        cartDto.setProductID(itemId);
                        cartDao.deleteShoppingCart(cartDto);
                    }
                }
            }
            response.sendRedirect("myCart.jsp");
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
