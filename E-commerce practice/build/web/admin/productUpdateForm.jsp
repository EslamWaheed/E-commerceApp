<%-- 
    Document   : productUpdateForm
    Created on : Feb 3, 2018, 4:22:03 PM
    Author     : Aya
--%>
        <%@include file="header.jsp" %>
        <div class="row">
            <div class="col-md-3">
        <%@include file="sidebar.jsp"%>
            </div>
            <div class="col-md-8">
        <div>
            <h1>update Product</h1>
            <form action="../displayProd" method="post" enctype="multipart/form-data">
              <input type="hidden" name="process" value="update">
               <input type="hidden" name="prodId" value="${TheProduct.id}">
               <input type="hidden" name="catId" value="${TheProduct.categoryID}"> <br>
              <ul>
                  <li>
                      <div> <img style="width: 300px;height: 400px;margin-left:30%; margin-right: 30%" id="upImg" src="../images/products/${TheProduct.id}.png"><br>
                          <input type="file" name="newimg" style="margin-left:40%; margin-right: 30%"><br> </div><br>
                  </li>    
              <li>
                  <label class="word">Name</label>
                <input type="text" name="prodName" value="${TheProduct.name}" class="updId">
              </li> <br>
             <li>
                 <label class="word"> Description</label><br>
               <input type="text" name="prodDesc" value="${TheProduct.description}" class="updId">
             </li> <br>
             <li>
              <label class="word" >Price</label><br>
              <input  type="number" name="prodPrice" value="${TheProduct.price}" class="updId">
             </li> <br>
            <li>
               <label class="word">Number of avaliable Items</label><br>
               <input type="number" name="prodAvItems" value="${TheProduct.quantities}"class="updId">
            </li><br>
           <li>
               <input type="submit" id="updProd" value="Save"/>
           </li> <br>
            </ul>
            </form>
        </div>
            </div>
        
         <%@include file="Footer.jsp" %>
    </body>
</html>
