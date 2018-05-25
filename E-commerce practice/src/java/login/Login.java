/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import dao.implementations.CategoryDAOImp;
import dao.implementations.ProductsDAOImp;
import dao.implementations.ShoppingCartDAOImp;
import dao.implementations.UserDAOImp;
import dto.CategoryDTO;
import dto.ProductsDTO;
import dto.ShoppingCartDTO;
import dto.UserDTO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.MyCart;

/**
 *
 * @author EslamWaheed
 */
public class Login extends HttpServlet {

    ServletConfig myConfig;

    @Override
    public void init(ServletConfig config) throws ServletException {
        myConfig = config;
        System.out.println("I am inside the init method");
    }

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
        String page = "";
        UserDTO userDTO = new UserDTO();
        UserDAOImp userDAOImp = new UserDAOImp();

        HttpSession session = request.getSession(true);

        ServletContext servletContext = myConfig.getServletContext();

        String email = request.getParameter("email");

        int password = Integer.parseInt(request.getParameter("password"));

        userDTO = userDAOImp.getUserByEmailAndPassword(email, password);

        if (userDTO.getName() == null) {
            session.setAttribute("loginerror", "Login error");
            response.sendRedirect("Login.jsp");
        } else {
            session.setAttribute("user", userDTO);
            //File file = null;
            //file = userDAOImp.downloadImage(userDTO.getId());
            if(session.getAttribute("mycart") != null){
                        List<MyCart> myCart = (List<MyCart>)session.getAttribute("mycart");
                        
                        ShoppingCartDAOImp cart = new ShoppingCartDAOImp();
                        for(int i=0 ; i<myCart.size() ; i++){
                            ShoppingCartDTO cartdto = new ShoppingCartDTO(userDTO.getId(),myCart.get(i).getProductId(),1 );
                            if(cart.checkcart(cartdto)){
                                System.out.println("item already exist");
                            }else{

                                /* TODO output your page here. You may use following sample code. */
                                cart.insertShoppingCart(cartdto);
                          }
                        }
                    }else{
                List<MyCart> myCart = new ArrayList<MyCart>();
                ArrayList<ShoppingCartDTO> allShopingCarts = new ArrayList<ShoppingCartDTO>();
                ShoppingCartDAOImp cart =new ShoppingCartDAOImp();
                ProductsDAOImp products = new ProductsDAOImp();
                CategoryDAOImp categories = new CategoryDAOImp();
                allShopingCarts = cart.getAllShoppingCarts(userDTO.getId());
                for(int i=0;i<allShopingCarts.size();i++){
                    ProductsDTO product = products.getProducts(allShopingCarts.get(i).getProductID());
                    CategoryDTO category = categories.getCategory(product.getCategoryID());
                    MyCart mycart = new MyCart(product.getId(),product.getPrice() * allShopingCarts.get(i).getProductQuantity(),product.getName(),category.getName(),allShopingCarts.get(i).getProductQuantity(),product.getQuantities());
                    myCart.add(mycart);
                }
                int countPrices = 0;
                int countProducts = 0;
                for(int i=0;i<myCart.size();i++){
                    countPrices += myCart.get(i).getProductPrice();
                    countProducts++;
                }
                session.setAttribute("countprices", countPrices);
                session.setAttribute("mycart", myCart);
                session.setAttribute("countproducts", countProducts);
            }
            
            
            
            String chRemember = request.getParameter("RemeberMe");
            
            if (chRemember != null) {
                Cookie emailCookie = new Cookie("emailCookie", email);
                Cookie passwordCookie = new Cookie("passwordCookie", String.valueOf(password));

                emailCookie.setMaxAge(60 * 60 * 24 * 365);
                passwordCookie.setMaxAge(60 * 60 * 24 * 365);

                response.addCookie(emailCookie);
                response.addCookie(passwordCookie);
            }
            if(userDTO.getPrivalege() == 0){
                
                if(session.getAttribute("lastvisted") == "mycart"){
                        
                        page = "myCart.jsp";
                    }else{
                        page = "main.jsp";
                    }
                response.sendRedirect(page);
            }else{
                response.sendRedirect("/E-commercepractice/displayProd");
            }
            
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
    }

}
