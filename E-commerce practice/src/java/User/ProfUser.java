/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import dao.implementations.UserDAOImp;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aya
 */
@WebServlet(urlPatterns = {"/ProfUser"})
public class ProfUser extends HttpServlet {

   
    UserDAOImp userDaoImp=new UserDAOImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String theProf = request.getParameter("prof");
         switch(theProf)
            {
                    
                case "load":
                   loadUser(request,response);
                   break;
                case "update":
                  updateUser(request,response);
                  break;     
                    
            }
        }

    
              
    

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String theProf = request.getParameter("prof");
         switch(theProf)
            {
                    
                case "load":
                   loadUser(request,response);
                   break;
                case "update":
                  updateUser(request,response);
                  break;     
                    
            }
    }

    private void loadUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            String SuserId=request.getParameter("userId");
            int userId = Integer.parseInt(SuserId);
            UserDTO loadUser = userDaoImp.getUser(userId);
            request.setAttribute("TheUser",loadUser);
            RequestDispatcher dispater = request.getRequestDispatcher("profileUser.jsp");
            dispater.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ProfUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProfUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            String Sid=request.getParameter("userId");
            int id=Integer.parseInt(Sid);
            String name = request.getParameter("userName");
            String Sbd = request.getParameter("Birthd");
            DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date parsed = new Date(0);
            parsed=format.parse(Sbd);
            java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
            String sPassword=request.getParameter("password");
            int password = Integer.parseInt(sPassword);
            String job = request.getParameter("job");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String sCredit = request.getParameter("credit");
            int credit=Integer.parseInt(sCredit);
            UserDTO upUser = new UserDTO(id, name, sqlDate, password, job, email, address, credit, null);
            userDaoImp.updateUser(upUser);
            request.setAttribute("TheUser",upUser);
            RequestDispatcher dispater = request.getRequestDispatcher("main.jsp");
            dispater.forward(request, response);
            
        } catch (ParseException ex) {
            Logger.getLogger(ProfUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(ProfUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProfUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}