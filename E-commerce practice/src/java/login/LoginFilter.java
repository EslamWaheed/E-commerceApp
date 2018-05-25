/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import dao.implementations.ShoppingCartDAOImp;
import dao.implementations.UserDAOImp;
import dto.ShoppingCartDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import util.MyCart;
/**
 *
 * @author EslamWaheed
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/Login.jsp"})
public class LoginFilter implements Filter {

    private FilterConfig filterConfig = null;

    public LoginFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        String page = "";
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        HttpSession session = request1.getSession();
        System.out.println("Filter Start");
        UserDTO userDTO = new UserDTO();
        UserDAOImp userDAOImp = new UserDAOImp();

        ServletContext servletContext = filterConfig.getServletContext();
        Cookie[] arrOfCookie = request1.getCookies();
        if (arrOfCookie != null) {
            System.out.println("inside arrOfCookie if");
            String emailC = null;
            int passwordC = 0;
            for (int i = 0; i < arrOfCookie.length; i++) {
                Cookie cookie = arrOfCookie[i];
                if (cookie.getName().equals("emailCookie")) {
                    System.out.println("inside emailCookie if");
                    emailC = cookie.getValue();
                }
                if (cookie.getName().equals("passwordCookie")) {
                    System.out.println("inside passwordCookie if");
                    passwordC = Integer.parseInt(cookie.getValue());
                }
            }
            if (emailC != null & passwordC != 0) {
                userDTO = userDAOImp.getUserByEmailAndPassword(emailC, passwordC);
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
                    }
                session.setAttribute("user", userDTO);
                if(session.getAttribute("lastvisted") == "mycart"){
                        
                        page = "myCart.jsp";
                    }else{
                        page = "main.jsp";
                    }
                response1.sendRedirect(page);
                System.out.println(userDTO.getBd());
            }
        }
        chain.doFilter(request1, response1);
        //session.invalidate();
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("LoginFilter()");
        }
        StringBuffer sb = new StringBuffer("LoginFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
