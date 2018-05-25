<%-- 
    Document   : default
    Created on : Feb 2, 2018, 11:31:02 AM
    Author     : Aya
--%>

        <%@include file="header.jsp" %>
        <div class="row">
            <div class="col-3">
        <%@include file="sidebar.jsp"%>
            </div>
        <div class="table100 ver2">
            <div class="table100-head">
                <table>
                <thead>
                <tr class="row100 head">
                    <th class="cell100 column1">category </th>
                    <th class="cell100 column2">Product</th>
                    <th class="cell100 column3">Description</th>
                    <th class="cell100 column4">Price</th>
                    <th class="cell100 column5">avaliable</th>
                    <th class="cell100 column6">Image</th>
                    <th class="cell100 column7">Action<th>
              </tr>
              </thead>
                </table>
            </div>
            <div class="table100-body">
                <table>
                    <tbody>
                <c:forEach var="tempProdCat" items="${productCat_List}">
                    <c:forEach var="tempProd" items="${productCat_List[tempProdCat.key]}">
                    <c:url var="tempLink" value="../displayProd">
                        <c:param name="process" value="load"/>
                        <c:param name="productId" value="${tempProd.id}"/>
                    </c:url>
                    <c:url var="delLink" value="../displayProd">
                        <c:param name="process" value="delete"/>
                        <c:param name="productId" value="${tempProd.id}"/>
                    </c:url>
                    <tr class="row100 body">
                        <td class="cell100 column1">${tempProdCat.key.name}</td>
                        <td class="cell100 column2"> ${tempProd.name}</td>
                        <td class="cell100 column3">${tempProd.description}</td>
                        <td class="cell100 column4">${tempProd.price}</td>
                        <td class="cell100 column5">${tempProd.quantities}</td>
                        <td class="cell100 column7"><img class="prodimg" style="width: 50px;height: 50px;" src="../images/products/${tempProd.id}.png"></td>
                       <td class="cell100 column6"> <a href="${tempLink}" style="color:blue"> update </a>  
                           <a href="${delLink}" onclick="if(!(confirm ('Are you sure you want to delete this product?!!'))) return false" style="color: red" > Delete </a></td>
                    
                    </tr>
                    </c:forEach>
            </c:forEach>
                    </tbody>
            </table>
        </div>
        </div>
        </div>
         <%@include file="Footer.jsp" %>

