<%-- 
    Document   : addCategory
    Created on : Feb 6, 2018, 5:03:02 AM
    Author     : Aya
--%>
        <%@include file="header.jsp" %>
        <div class="row">
            <div class="col-md-3">
        <%@include file="sidebar.jsp"%>
            </div>
        <div class="col-md-8">
        <div>
            <h1>Add new category</h1> <br>
            <form method="post" action="/E-commercepractice/displayProd">
            <input type="hidden" name="process" value="addCateg"/>    
            <input type="text" name="newCat" placeholder="Category Name*" id="catId" required><br>
            <input type="submit" value="Add" id="addCat"/> <br>
            </form>
        </div>
        </div>
        </div>
        
         <%@include file="Footer.jsp" %>
 
