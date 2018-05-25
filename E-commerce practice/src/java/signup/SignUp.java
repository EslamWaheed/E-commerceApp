/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signup;

import dao.implementations.UserDAOImp;
import dao.implementations.UserInterestsDAOImp;
import dto.UserDTO;
import dto.UserInterestsDTO;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Set;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;


/**
 *
 * @author EslamWaheed
 */
@MultipartConfig(maxFileSize = 16177215)
public class SignUp extends HttpServlet {

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

        HttpSession session = request.getSession(true);
        InputStream inputStream = null;
        UserDTO userDTO = new UserDTO();
        UserDAOImp userDAOImp = new UserDAOImp();

        ArrayList<UserInterestsDTO> userInterestsDTOs = new ArrayList<>();
        UserInterestsDAOImp userInterestsDAOImp = new UserInterestsDAOImp();

        int MaxID = userDAOImp.getMaxID();

        userDTO.setId(MaxID + 1);
        userDTO.setName(request.getParameter("name"));
        userDTO.setPassword(Integer.parseInt(request.getParameter("password")));
        userDTO.setBd(Date.valueOf(request.getParameter("db")));
        userDTO.setEmail(request.getParameter("email"));
        userDTO.setJob(request.getParameter("job"));
        userDTO.setAddress(request.getParameter("address"));
        userDTO.setCredit_limit(Integer.parseInt(request.getParameter("credit_limit")));
        Part filePart = request.getPart("myimg"); 
        if (filePart != null) {            
            inputStream = filePart.getInputStream();
        }
        userDTO.setImage(inputStream);
        if (request.getParameter("clothes") != null) {
            UserInterestsDTO userInterestsDTO = new UserInterestsDTO();
            userInterestsDTO.setUserID(MaxID + 1);
            userInterestsDTO.setInterests(request.getParameter("clothes"));
            userInterestsDTOs.add(userInterestsDTO);
        }

        if (request.getParameter("food") != null) {
            UserInterestsDTO userInterestsDTO = new UserInterestsDTO();
            userInterestsDTO.setUserID(MaxID + 1);
            userInterestsDTO.setInterests(request.getParameter("food"));
            userInterestsDTOs.add(userInterestsDTO);
        }

        if (request.getParameter("electronic") != null) {
            UserInterestsDTO userInterestsDTO = new UserInterestsDTO();
            userInterestsDTO.setUserID(MaxID + 1);
            userInterestsDTO.setInterests(request.getParameter("electronic"));
            userInterestsDTOs.add(userInterestsDTO);
        }

        boolean inserted = userDAOImp.insertUser(userDTO);

        if (inserted) {
            for (UserInterestsDTO userInterestsDTO : userInterestsDTOs) {
                userInterestsDAOImp.insertUserInterests(userInterestsDTO);
            }
            session.setAttribute("signup", "Successful SignUp");
            userDAOImp.downloadImage(userDTO.getId());
            response.sendRedirect("Login.jsp");
        }else{
            session.setAttribute("error", "User Already Exists");
            response.sendRedirect("SignUp.jsp");
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
