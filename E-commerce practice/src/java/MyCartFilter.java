/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.implementations.CategoryDAOImp;
import dao.implementations.ProductsDAOImp;
import dao.implementations.ShoppingCartDAOImp;
import dao.implementations.UserDAOImp;
import dto.CategoryDTO;
import dto.ProductsDTO;
import dto.ShoppingCartDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
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
import javax.servlet.http.HttpSession;
import util.MyCart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Hazem
 */
@WebFilter(filterName = "MyCartFilter", urlPatterns = {"/myCart.jsp","/main.jsp"})
public class MyCartFilter implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public MyCartFilter() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("MyCartFilter:DoBeforeProcessing");
        }

	// Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
	// For example, a logging filter might log items on the request object,
        // such as the parameters.
	/*
         for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         String values[] = request.getParameterValues(name);
         int n = values.length;
         StringBuffer buf = new StringBuffer();
         buf.append(name);
         buf.append("=");
         for(int i=0; i < n; i++) {
         buf.append(values[i]);
         if (i < n-1)
         buf.append(",");
         }
         log(buf.toString());
         }
         */
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("MyCartFilter:DoAfterProcessing");
        }

	// Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
	// For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
	/*
         for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         Object value = request.getAttribute(name);
         log("attribute: " + name + "=" + value.toString());

         }
         */
	// For example, a filter might append something to the response.
	/*
         PrintWriter respOut = new PrintWriter(response.getWriter());
         respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
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
        /*
        if (debug) {
            log("MyCartFilter:doFilter()");
        }
        
        doBeforeProcessing(request, response);
        
        Throwable problem = null;
        try {
         */   
        
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
                session.setAttribute("user", userDTO);
            }
        }
            //HttpServletRequest request1 = (HttpServletRequest) request;
            //HttpSession session = request1.getSession(true);
            
            if(session.getAttribute("user") != null){
                UserDTO user = (UserDTO)  session.getAttribute("user");
                System.out.println("in mycart filter");
                List<MyCart> myCart = new ArrayList<MyCart>();
                ArrayList<ShoppingCartDTO> allShopingCarts = new ArrayList<ShoppingCartDTO>();
                ShoppingCartDAOImp cart =new ShoppingCartDAOImp();
                ProductsDAOImp products = new ProductsDAOImp();
                CategoryDAOImp categories = new CategoryDAOImp();
                allShopingCarts = cart.getAllShoppingCarts(user.getId());
                for(int i=0;i<allShopingCarts.size();i++){
                    ProductsDTO product = products.getProducts(allShopingCarts.get(i).getProductID());
                    CategoryDTO category = categories.getCategory(product.getCategoryID());
                    MyCart mycart = new MyCart(product.getId(),product.getPrice() * allShopingCarts.get(i).getProductQuantity(),product.getName(),category.getName(),allShopingCarts.get(i).getProductQuantity(),product.getQuantities());
                    myCart.add(mycart);
                }
                int countPrices = 0;
                for(int i=0;i<myCart.size();i++){
                    countPrices += myCart.get(i).getProductPrice();
                }
                session.setAttribute("countprices", countPrices);
                session.setAttribute("mycart", myCart);
            }
            
            
            
            
            
            chain.doFilter(request, response);
    /*    
    } catch (Throwable t) {
	    // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }
      */  
        doAfterProcessing(request, response);

	// If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        /*
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
        */
    
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
            if (debug) {                
                log("MyCartFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("MyCartFilter()");
        }
        StringBuffer sb = new StringBuffer("MyCartFilter(");
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
