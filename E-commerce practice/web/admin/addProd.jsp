<%-- 
    Document   : addProd
    Created on : Feb 3, 2018, 11:58:39 AM
    Author     : Aya
--%>
 <%@include file="header.jsp" %>
        <div class="row">
            <div class="col-md-3">
        <%@include file="sidebar.jsp"%>
            </div>
        <div class="col-md-8">
            <div>
            <h1>Add new Product</h1>
            
            <form action="/E-commercepractice/displayProd" method="post" enctype="multipart/form-data">
                <input type="hidden" name="process" value="add"><br>
             <select name="categ" class="prodDet">
                    <c:forEach var="tempCat" items="${cat_List}">       
                    <option value="${tempCat.id}">${tempCat.name}</option>
                    </c:forEach> 
             </select> <br>
             <input type="text" name="prodName" class="prodDet" placeholder="Product Name*" required><br>
             <input type="text" name="prodDesc" class="prodDet" placeholder="Product Description" required><br>
             <input type="text" name="prodPrice" class="prodDet" placeholder="Product Price*" required><br>
             <input type="text" name="prodAvItems" class=" prodDet" placeholder="Product Number Items*" required><br>
             <input type="file" name="myimg" style="margin-left:10px;" accept="image/x-png,image/gif,image/jpeg"><br><br>
             <input type="submit" id="AddProd" value="Add"/> <br> <br>
            </form>
        </div>
        </div>
        </div>
        
         <%@include file="Footer.jsp" %>
 
