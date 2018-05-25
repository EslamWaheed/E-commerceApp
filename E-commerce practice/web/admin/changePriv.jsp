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
            <h1>Change Privilege</h1> <br>
            <form method="get" action="/E-commercepractice/displayProd">
            <input type="hidden" name="process" value="updatePriv"/>  
            <input type="hidden" name="userData" value="${IDUsed}"/>
             <select name="priv" class="prodDet">    
                    <option value="0">User</option>
                    <option value="1">Admin</option>
             </select> <br>
            <input type="submit" value="Change" id="addCat"/> <br>
            </form>
        </div>
        </div>
        </div>
        
         <%@include file="Footer.jsp" %>
 
