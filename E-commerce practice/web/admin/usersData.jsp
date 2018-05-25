<%-- 
    Document   : usersData
    Created on : Feb 8, 2018, 2:01:53 AM
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
                    <th class="cell100 column7">user/admin</th> 
                    <th class="cell100 column1">Name</th>
                    <th class="cell100 column2">EMAIL</th>
                    <th class="cell100 column3">Birth Date</th>
                    <th class="cell100 column4">Address</th>
                    <th class="cell100 column5">JOB</th>
                    <th class="cell100 column6">Credit</th>
                     <th class="cell100 column7">Action</th>
                    <th class="cell100 column7">Image</th>
                  
              </tr>
              </thead>
                </table>
            </div>
            <div class="table100-body">
                <table>
                    <tbody>
                        <c:forEach var="tempCustomer" items="${user_List}">
                         <c:url var="tempLink" value="../displayProd">
                           <c:param name="user" value="${tempCustomer.id}"/>
                           <c:param name="process" value="sendId"/>
                         </c:url>
                         <c:url var="historyLink" value="../displayProd">
                          <c:param name="process" value="orderHistory"/>
                          <c:param name="userHisId" value="${tempCustomer.id}"/>
                    </c:url>
                   <tr>
                        <c:if test = "${tempCustomer.privalege==0}">
                            <td class="cell100 column6"> <a href="${tempLink}" style="color:blue"><c:out value="User"></c:out></a>
                            </td>
                            </c:if>
                                
                        <c:if test = "${tempCustomer.privalege==1}">
                        <td class="cell100 column6"> <a href="${tempLink}" style="color:blue"><c:out value="Admin"></c:out></a>
                        </td>    
                        </c:if>
                                
                         <td class="cell100 column1">${tempCustomer.name}</td>
                         <td class="cell100 column2">${tempCustomer.email}</td>
                         <td class="cell100 column3">${tempCustomer.bd}</td>
                        <td class="cell100 column4">${tempCustomer.address}</td>
                        <td class="cell100 column5">${tempCustomer.job}</td>
                        <td class="cell100 column6">${tempCustomer.credit_limit}</td>
                        <td class="cell100 column6"> <a href="${historyLink}" style="color:blue"> or.History </a>  
                        <td class="cell100 column7"><img class="prodimg" style="width: 30px;height: 30px; align-items: center;" src="../images/users/${tempCustomer.id}.png"></td>   
                        
                   </tr>
                    </c:forEach>
                    </tbody>
            </table>
        </div>
        </div>
        </div>
         <%@include file="Footer.jsp" %>