/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import dao.implementations.CategoryDAOImp;
import dao.implementations.OrderHistoryDAOImp;
import dao.implementations.ProductsDAOImp;
import dao.implementations.UserDAOImp;
import dto.CategoryDTO;
import dto.OrderHistoryDTO;
import dto.ProductsDTO;
import dto.UserDTO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import servletListeners.AppContextListener;


/**
 *
 * @author Aya
 */

@MultipartConfig(maxFileSize = 16177215) 

public class displayProd extends HttpServlet {
   
   ProductsDAOImp prodDaoImp = new ProductsDAOImp(); 
    CategoryDAOImp catDaoImp = new CategoryDAOImp();
    UserDAOImp userDaoImp=new UserDAOImp();
    OrderHistoryDAOImp orderHistoryDaoImp = new OrderHistoryDAOImp();
    
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      try{
            String theProcess = request.getParameter("process");
            System.out.println(theProcess+"hello");
            if(theProcess==null)
            {
              theProcess="Base";
            }
            
            switch(theProcess)
            {
                case "Base": 
                      ListProduct(request,response);
                      break; 
                  case "load":
                      loadProduct(request,response);
                      break; 
                   case "delete":
                       deleteProduct(request,response);
                       break;
                       
                case "deleteC":
                       deleteCat(request,response);
                       break; 
                       
                case "logout":
                      logoutAdmin(request,response);
                      break;
                case "sendId":
                   sendId(request,response);
                   break;
                case "updatePriv":
                       changePrivi(request,response);
                       break;
                case "orderHistory":
                       ListOrderHistory(request,response);
                       break;
                    
                default:
                    ListProduct(request,response);
                      break;
        
    }
      }
       catch(Exception ex)
        {
         throw new ServletException(ex);
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        try{
            String theProcess = request.getParameter("process");
            System.out.println(theProcess+"hello");
            if(theProcess==null)
            {
              theProcess="Base";
            }
            
            switch(theProcess)
            {
                case "Base": 
                      ListProduct(request,response);
                      break;
                      
                case "customer": 
                      ListUsers(request,response);
                      break;      
                      
                case "add":
                      addProduct(request,response);
                      break;
                      
                      
                  case "AvaCat":
                      loadCat(request,response);
                      break;     
                      
                 case "update":
                       updateProduct(request,response);
                       break;      
                       
                case "Cat":
                       ListCategory(request,response);
                       break;
                       
                case "addCateg":
                       AddCategory(request,response);
                       break;       
                       
                   
                    
                default:
                    ListProduct(request,response);
                      break;
                    
            }
        }
        catch(Exception ex)
        {
         throw new ServletException(ex);
        }
        

    }

    private void ListProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HashMap <CategoryDTO, ArrayList<ProductsDTO>> product = prodDaoImp.getAllOfCatagoryProducts();
    
        
        ServletContext ctx = request.getServletContext();
        ctx.setAttribute("products1", product);
    //RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/default.jsp");
   // dispatcher.forward(request, response);
       request.getSession().setAttribute("productCat_List",product);
       response.sendRedirect("admin/default.jsp");
    }

   private void addProduct(HttpServletRequest request, HttpServletResponse response) {
       HttpSession session = request.getSession();
    try {
        boolean er = false;
        int price = 0;
        int numberItem = 0;
        InputStream inputStream = null;
        System.out.println("hello2");       
        PrintWriter out = response.getWriter();
        String prodName = request.getParameter("prodName");
        String desc=request.getParameter("prodDesc");
        String Sprice =request.getParameter("prodPrice");
        String SnumItems =request.getParameter("prodAvItems");
        try {
               price = Integer.parseInt(Sprice); 
               numberItem = Integer.parseInt(SnumItems);
            } catch (NumberFormatException nfe) {
               er=true;
            }
         Part filePart = request.getPart("myimg"); 
        if (filePart != null) {            
            inputStream = filePart.getInputStream();
        }
        String catSelect = request.getParameter("categ");
         int catSel = Integer.parseInt(catSelect);
        if (er==true) {
            session.setAttribute("inserterror", "insert error");
            response.sendRedirect("admin/addProd.jsp");
        } else { 
        Set<CategoryDTO>categories= catDaoImp.getAllCategory();
        ProductsDTO theNewProd = new ProductsDTO(prodName, desc, price, numberItem,inputStream,catSel);
        prodDaoImp.insertProducts(theNewProd);
        
        Set<ProductsDTO> allProducts = prodDaoImp.getAllProducts();
        for (ProductsDTO product : allProducts) {
            File file = null;
            try {
                System.out.println("pic downloaded");
                file = prodDaoImp.downloadImage(product.getId());
            } catch (IOException ex) {
                Logger.getLogger(AppContextListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        ListProduct(request,response);
        
    }
    }catch (ServletException | IOException ex) {
        Logger.getLogger(displayProd.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    private void loadProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String SproductId = request.getParameter("productId");
      int productId = Integer.parseInt(SproductId);
      ProductsDTO loadProd = prodDaoImp.getProducts(productId);
      request.getSession().setAttribute("TheProduct",loadProd);
      
      //RequestDispatcher dispater = request.getRequestDispatcher("/admin/productUpdateForm.jsp");
     // dispater.forward(request, response);
      response.sendRedirect("admin/productUpdateForm.jsp");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        InputStream input = null;
        String SprodId = request.getParameter("prodId");
        int prodId=Integer.parseInt(SprodId);
        String ScatId=request.getParameter("catId");
        int catId = Integer.parseInt(ScatId);
       String prodName = request.getParameter("prodName");
        String desc=request.getParameter("prodDesc");
        String Sprice =request.getParameter("prodPrice");
        int price = Integer.parseInt(Sprice);
        String SnumItems =request.getParameter("prodAvItems");
        int numberItem = Integer.parseInt(SnumItems);  
         Part filePart = request.getPart("newimg"); 
        if (filePart != null) {            
            input = filePart.getInputStream();
        }
        ProductsDTO theUpdateProd = new ProductsDTO(prodId,prodName, desc, price, numberItem,input,catId);
        prodDaoImp.updateProducts(theUpdateProd);
        Set<ProductsDTO> allProducts = prodDaoImp.getAllProducts();
        
        prodDaoImp.downloadImage(prodId);
        /*
        for (ProductsDTO product : allProducts) {
            File file = null;
            try {
                System.out.println("pic downloaded");
                file = prodDaoImp.downloadImage(product.getId());
            } catch (IOException ex) {
                Logger.getLogger(AppContextListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        */
        ListProduct(request,response);  

    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
    try {
        
        String SproductId = request.getParameter("productId");
        int productId = Integer.parseInt(SproductId);
        prodDaoImp.deleteProducts(productId);
        
        
        ListProduct(request,response);
    } catch (ServletException | IOException ex) {
        Logger.getLogger(displayProd.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    private void ListUsers(HttpServletRequest request, HttpServletResponse response) {
    try {
         Set <UserDTO> users = userDaoImp.getAllUser();
        request.getSession().setAttribute("user_List",users);
        response.sendRedirect("admin/usersData.jsp");
        /*
        for (UserDTO user: users) {
            File file = null;
            try {
                file = userDaoImp.downloadImage(user.getId());
            } catch (IOException ex) {
                Logger.getLogger(AppContextListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                */
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/usersData.jsp");
        //dispatcher.forward(request, response);
    } catch (IOException ex) {
        Logger.getLogger(displayProd.class.getName()).log(Level.SEVERE, null, ex);
    }


    }

    private void ListCategory(HttpServletRequest request, HttpServletResponse response) {
     try{
         Set<CategoryDTO>categories= catDaoImp.getAllCategory();
         request.getSession().setAttribute("cat_List",categories);
        // RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/addProd.jsp");
        // dispatcher.forward(request, response);
         response.sendRedirect("admin/addProd.jsp");
     
     } catch (IOException ex) {
        Logger.getLogger(displayProd.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    private void AddCategory(HttpServletRequest request, HttpServletResponse response) {
       try {
           String name= request.getParameter("newCat");   
           CategoryDTO newCat = new CategoryDTO(name);
           catDaoImp.insertCategory(newCat); 
           ListProduct(request,response);
       } catch (ServletException ex) {
           Logger.getLogger(displayProd.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(displayProd.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    private void ListOrderHistory(HttpServletRequest request, HttpServletResponse response) {
       try {
            String SusedId = request.getParameter("userHisId");
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
          response.sendRedirect("admin/history.jsp");
       } catch (IOException ex) {
           Logger.getLogger(displayProd.class.getName()).log(Level.SEVERE, null, ex);
       }

    }

    private void logoutAdmin(HttpServletRequest request, HttpServletResponse response) {
       try {
           HttpSession session=request.getSession();
           session.invalidate();
           Cookie emailCookie = new Cookie("emailCookie", "invalid");
           Cookie passwordCookie = new Cookie("passwordCookie", "0");

           emailCookie.setMaxAge(60 * 60 * 24 * 365);
           passwordCookie.setMaxAge(60 * 60 * 24 * 365);

           response.addCookie(emailCookie);
           response.addCookie(passwordCookie);
           response.sendRedirect("Login.jsp");
       } catch (IOException ex) {
           Logger.getLogger(displayProd.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    private void loadCat(HttpServletRequest request, HttpServletResponse response) {
       try {
           Set cats = catDaoImp.getAllCategory();
           request.getSession().setAttribute("Cat_List",cats);
          // RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/catList.jsp");
          // dispatcher.forward(request, response);
          response.sendRedirect("admin/catList.jsp");
       } catch (IOException ex) {
           Logger.getLogger(displayProd.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }

    private void deleteCat(HttpServletRequest request, HttpServletResponse response) {
      
           String ScatId = request.getParameter("catId");
           int CatId = Integer.parseInt(ScatId);
           HashMap<CategoryDTO, ArrayList<ProductsDTO>> catProd= prodDaoImp.getAllOfCatagoryProducts();
           Set set =(Set) catProd.entrySet();
          Iterator iterator = set.iterator();
          Map.Entry element = null;
          while(iterator.hasNext()) {
           element = (Map.Entry)iterator.next();
           CategoryDTO cat1 = (CategoryDTO) element.getKey(); 
           int ID=cat1.getId();
           if(ID==CatId)
           {
               ArrayList<ProductsDTO> prod1 = (ArrayList<ProductsDTO>) element.getValue();
                 for (ProductsDTO s : prod1)
                 {     
                     int pid=s.getId();
                    prodDaoImp.deleteProducts(pid);
               
                  }    
                 catDaoImp.deleteCategory(CatId);
                 loadCat(request,response);
                  break; 
              }
          }
          
       
      
    }
    
    private void changePrivi(HttpServletRequest request, HttpServletResponse response) {
       String SnewPriv = request.getParameter("priv");
       int newPriv = Integer.parseInt(SnewPriv);
       String SupUser = request.getParameter("userData");
       int upUser = Integer.parseInt(SupUser);
       UserDTO userdto = userDaoImp.getUser(upUser);
       userdto.setPrivalege(newPriv);
       userDaoImp.updatePrivUser(userdto);
       ListUsers(request, response);

    }

    private void sendId(HttpServletRequest request, HttpServletResponse response) {
       try {
           String SuserId = request.getParameter("user");
           request.getSession().setAttribute("IDUsed",SuserId);
           response.sendRedirect("admin/changePriv.jsp");
       } catch (IOException ex) {
           Logger.getLogger(displayProd.class.getName()).log(Level.SEVERE, null, ex);
       }

    }
    

  }
    

