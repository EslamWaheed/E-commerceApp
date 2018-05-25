<%-- 
    Document   : default
    Created on : Feb 2, 2018, 11:31:02 AM
    Author     : Aya
--%>

        <%@include file="header.jsp" %>
        <div class="row">
            <div class="col-md-4">
        <%@include file="sidebar.jsp"%>
            </div>
        <div class="col-md-8 table100 ver2 m-b-110">
            <div class="table100-head">
                <table>
                <thead>
                <tr class="row100 head">
                    <th class="cell100 column11">category's name </th>
                    <th class="cell100 column12" style="width: 50%;">Action</th>
              </tr>
              </thead>
                </table>
            </div>
            <div class="table100-body js-pscroll ps ps--active-y">
                <table>
                    <tbody>
                <c:forEach var="tempCat" items="${Cat_List}">
                    <c:url var="delLink" value="../displayProd">
                        <c:param name="process" value="deleteC"/>
                        <c:param name="catId" value="${tempCat.id}"/>
                    </c:url>
                    <tr class="row100 body">
                        <td class="cell100 column11">${tempCat.name}</td>
                       <td class="cell100 column12"><a href="${delLink}" onclick="if(!(confirm ('Are you sure you want to delete this product?!!'))) return false" style="color: red" > Delete </a></td>
                    </tr>
                    </c:forEach>
                    </tbody>
            </table>
        </div>
        </div>
        </div>
         <%@include file="Footer.jsp" %>

