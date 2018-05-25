<%-- 
    Document   : history
    Created on : Feb 8, 2018, 5:59:13 AM
    Author     : Aya
--%>
       <%@include file="header.jsp" %>
        <div class="row">
            <div class="col-3">
        <%@include file="sidebar.jsp"%>
            </div>
            <div class="table100 ver2 ver33">
            <div class="table100-head">
                <table>
                <thead>
                <tr class="row100 head">
                    <th class="cell100 column1">User's Name</th>
                    <th class="cell100 column2">Product Name</th>
                    <th class="cell100 column3">quantity</th>
                    <th class="cell100 column4">time</th>
              </tr>
              </thead>
                </table>
            </div>
            <div class="table100-body">
                <table>
                    <tbody>
                        <c:forEach var="tempCustomer" items="${history_List}">
                   <tr>
                        <td class="cell100 column1">${tempCustomer.userName}</td>
                        <td class="cell100 column2">${tempCustomer.productName}</td>
                        <td class="cell100 column3">${tempCustomer.quantity}</td>
                        <td class="cell100 column4">${tempCustomer.time}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
            </table>
        </div>
            </div>
        </div>
         <%@include file="Footer.jsp" %>
