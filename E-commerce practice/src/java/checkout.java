/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.implementations.OrderHistoryDAOImp;
import dao.implementations.ProductsDAOImp;
import dao.implementations.ShoppingCartDAOImp;
import dao.implementations.UserDAOImp;
import dto.CategoryDTO;
import dto.OrderHistoryDTO;
import dto.ProductsDTO;
import dto.ShoppingCartDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.MyCart;
import util.User;


/**
 *
 * @author Hazem
 */
@WebServlet(urlPatterns = {"/checkout"})
public class checkout extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            
            if(session.getAttribute("user") == null){
               session.setAttribute("lastvisted", "mycart");
               response.sendRedirect("Login.jsp");
            }else{
                ProductsDAOImp prodDao = new ProductsDAOImp();
                ProductsDTO product = new ProductsDTO();
                List<MyCart> myCart = (List<MyCart>)session.getAttribute("mycart");
                Integer totalPrices = (Integer)session.getAttribute("countprices");
                UserDTO user = (UserDTO) session.getAttribute("user");
                Integer credet = user.getCredit_limit();
                boolean broductNotExist = false;
                if(myCart.size() == 0){
                    session.setAttribute("noproducts", "yes");
                    response.sendRedirect("myCart.jsp");
                }
                for(int i=0;i<myCart.size();i++){
                    product = prodDao.getProducts(myCart.get(i).getProductId());
                    if(myCart.get(i).getProductQuantity() > product.getQuantities()){
                        broductNotExist = true;
                        session.setAttribute("broductNotExist", myCart.get(i).getProductName());
                        
                    }
                }
                if(totalPrices>credet){
                    session.setAttribute("nocredit", "yes");
                    response.sendRedirect("myCart.jsp");
                }else if(broductNotExist){
                    response.sendRedirect("myCart.jsp");
                    //check if product quantity still available
                }else{
                    
                    OrderHistoryDAOImp orderdao = new OrderHistoryDAOImp();
                    ShoppingCartDAOImp shopcartdao = new ShoppingCartDAOImp();
                    ProductsDAOImp productdao = new ProductsDAOImp();
                    //Hashtable<String ,ArrayList<User>> hm = new Hashtable<String ,ArrayList<User>>();
                    HashMap<CategoryDTO, ArrayList<ProductsDTO>> productsWithCategories = new HashMap<CategoryDTO, ArrayList<ProductsDTO>>();
                    ProductsDAOImp productsDAOImp = new ProductsDAOImp();
                    UserDAOImp userdao = new UserDAOImp();
                    boolean insertHistory = false;
                    boolean deletecart = false;
                    boolean updateproduct = false;
                    for(int i=0;i<myCart.size();i++){
                        OrderHistoryDTO orderdto = new OrderHistoryDTO(user.getId(),myCart.get(i).getProductId(),myCart.get(i).getProductQuantity());
                        insertHistory = orderdao.insertOrderHistory(orderdto);
                        if(insertHistory){
                            user.setCredit_limit(user.getCredit_limit() - myCart.get(i).getProductPrice());
                            userdao.updateUser(user);
                            session.setAttribute("user", user);
                            ShoppingCartDTO shopcartdto = new ShoppingCartDTO(user.getId(),myCart.get(i).getProductId(),myCart.get(i).getProductQuantity());
                            deletecart = shopcartdao.deleteShoppingCart(shopcartdto);
                        }
                        if(deletecart){
                            updateproduct = productdao.updateProductQuantity(myCart.get(i).getProductId(),myCart.get(i).getMaxQuantity() - myCart.get(i).getProductQuantity());
                            session.setAttribute("mycart", null);
                            session.setAttribute("countproducts", 0);
                            session.setAttribute("countprices", 0);
                        }
                        
                    }
                    if(updateproduct){
                        productsWithCategories = productsDAOImp.getAllOfCatagoryProducts();
                        ServletContext ctx = request.getServletContext();
                        ctx.setAttribute("products1", productsWithCategories);
                        response.sendRedirect("main.jsp");    
                        }
                }
            }
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
