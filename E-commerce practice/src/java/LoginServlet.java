/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Timestamp;
import dao.implementations.OrderHistoryDAOImp;
import dao.implementations.ShoppingCartDAOImp;
import dao.implementations.UserDAOImp;
import dto.OrderHistoryDTO;
import dto.ShoppingCartDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import util.MyCart;
import java.util.*;
/**
 *
 * @author Hazem
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        
        
        
        /*
        test all orders fn
        */
        
        HashMap<Integer , ArrayList<OrderHistoryDTO>> sortedOrders = new HashMap<Integer , ArrayList<OrderHistoryDTO>>();
        int x = 1;
        OrderHistoryDAOImp orderdao = new OrderHistoryDAOImp();
        ArrayList<OrderHistoryDTO> allOrders = orderdao.getAllOrdersHistory(1);
        OrderHistoryDTO orderdto = new OrderHistoryDTO();
        ArrayList<OrderHistoryDTO> orderhis = new ArrayList<OrderHistoryDTO>();
        Timestamp time1 = new Timestamp(1);
        Timestamp time2 = new Timestamp(1);
        for(int i=0;i<allOrders.size();i++){
            if(i==0){
                orderdto = allOrders.get(i);
                orderhis = new ArrayList<OrderHistoryDTO>();
                orderhis.add(orderdto);
                time1 = orderdto.getTime();
                
            }
            else{
                boolean notEqual = true;
                orderdto = allOrders.get(i);
                time2 = orderdto.getTime();
                
                //System.out.println(time1 + " " + time2);
                if(time2.getYear() == time1.getYear()){
                    
                    if(time2.getMonth() == time1.getMonth()){
                        
                        if(time2.getDate() == time1.getDate()){
                            //System.out.println(time2.getDate() + " " + time1.getDate());
                            if(time2.getHours() == time1.getHours()){
                                if(time2.getMinutes() == time1.getMinutes()){
                                    if(time2.getSeconds() - time1.getSeconds() <5 ){
                                        orderhis.add(orderdto);
                                        time1 = time2;
                                        notEqual = false;
                                        //System.out.println("same order");
                                    }
                                }else if(time2.getMinutes() - time1.getMinutes() == 1){
                                    if(time1.getSeconds() - time2.getSeconds() > 55 ){
                                        orderhis.add(orderdto);
                                        time1 = time2;
                                        notEqual = false;
                                        //System.out.println("same order");
                                    }
                                }
                            }
                        }
                    }
                }
                if(notEqual){
                    sortedOrders.put(x, orderhis);
                    x++;
                    orderhis.clear();
                    time1 = time2;
                }
                
                
            }
            
            
            
 //           System.out.println(allOrders.get(i).getTime().getYear());
//            System.out.println(allOrders.get(i).getTime().compareTo(allOrders.get(i+1).getTime()) );
        }
        for(Map.Entry order : sortedOrders.entrySet()){
            System.out.println("order number: " + order.getKey());
            ArrayList<OrderHistoryDTO> orderhis1 =(ArrayList<OrderHistoryDTO>) order.getValue();
            for(int j=0;j<orderhis1.size();j++){
                System.out.println("product in order : " + orderhis1.get(j));
            }
        }
        
        
        
        
        
        ResultSet rs = null;
        String usernameparam = request.getParameter("username");
        String passwordparam = request.getParameter("password");
        String page = "";
        HttpSession session = request.getSession(true);
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/commerce", "hazem", "12621992");
        } catch (ClassNotFoundException ex) {
            System.out.println("cannot connect to the database");
        } catch (SQLException ex) {
            System.out.println("sql command error");
        }
        
        try{
            
            PreparedStatement pst = connection.prepareStatement("select name,password from users where name = ? "); 
            pst.setString(1, usernameparam);
            rs = pst.executeQuery();
            if(rs.next()){
                if(passwordparam.equals(rs.getString(2))){
                    UserDAOImp userdao = new UserDAOImp();
                    UserDTO user = userdao.getUser(1);
                    session.setAttribute("user", user);
                    File file = null;
                    file = userdao.downloadImage(1);
                    if(session.getAttribute("mycart") != null){
                        List<MyCart> myCart = (List<MyCart>)session.getAttribute("mycart");
                        
                        ShoppingCartDAOImp cart = new ShoppingCartDAOImp();
                        for(int i=0 ; i<myCart.size() ; i++){
                            ShoppingCartDTO cartdto = new ShoppingCartDTO(user.getId(),myCart.get(i).getProductId(),1 );
                            if(cart.checkcart(cartdto)){
                                System.out.println("item already exist");
                            }else{

                                /* TODO output your page here. You may use following sample code. */
                                cart.insertShoppingCart(cartdto);
                          }
                        }
                    }
                    //session.setAttribute("credit", 10000);
                    if(session.getAttribute("lastvisted") == "mycart"){
                        
                        page = "myCart.jsp";
                    }else{
                        page = "main.jsp";
                    }
                    
                    //response.sendRedirect("main.jsp");
                    
                }else{
                    page = "login.jsp";
                }
            }else{
                page = "login.jsp";
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
            ex.printStackTrace();
        }
        
        
        session.setAttribute("error", "incorrectUser");
        response.sendRedirect(page);

            
        
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
