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
@WebServlet(urlPatterns = {"/ProductMinus"})
public class ProductMinus extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        List<MyCart> myCart = (List<MyCart>)session.getAttribute("mycart");
        response.setContentType("text/html;charset=UTF-8");
        int itemId = Integer.parseInt(request.getParameter("deleteditem"));
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if(session.getAttribute("user") == null){
                System.out.println("no session founded");
            for(int i=0;i<myCart.size();i++){
                System.out.println("no session founded");
                    if (myCart.get(i).getProductId() == itemId){
                        int quantity = myCart.get(i).getProductQuantity();
                        int price = myCart.get(i).getProductPrice()/myCart.get(i).getProductQuantity();
                        if(quantity != 1){
                            quantity--;
                            myCart.get(i).setProductPrice(price * quantity);
                            myCart.get(i).setProductQuantity(quantity);
                            System.out.println(myCart.get(i).getProductQuantity());
                            
                        }else if(quantity == 1){
                            session.setAttribute("min", "yes");
                        }
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
                System.out.println("session founded");
                ShoppingCartDAOImp cartDao = new ShoppingCartDAOImp();
                ShoppingCartDTO cartDto = new ShoppingCartDTO();
                for(int i=0;i<myCart.size();i++){
                    if (myCart.get(i).getProductId() == itemId){
                        int quantity = myCart.get(i).getProductQuantity();
                        if(quantity != 1){
                            quantity--;
                            cartDto.setUserID(user1.getId());
                            cartDto.setProductID(itemId);
                            cartDto.setProductQuantity(quantity);
                            cartDao.updateShoppingCart(cartDto);
                            System.out.println(myCart.get(i).getProductQuantity());
                            
                        }else if(quantity == 1){
                            session.setAttribute("min", "yes");
                        }
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
