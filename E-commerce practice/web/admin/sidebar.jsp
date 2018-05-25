<%-- 
    Document   : Header
    Created on : Jan 25, 2018, 1:19:11 AM
    Author     : Aya
--%>


        <div id="sidebar-header">
         <nav id="sidebar">
        <div class="header-product" class="active">
            
            <form action="/E-commercepractice/displayProd" method="post">
                 <input type="hidden" name="process" value="AvaCat"/>
                <input  type="submit" value="Avaliable Categories" class="itemsSide"/>
            </form>
            
            <form action="/E-commercepractice/admin/addCategory.jsp" method="post">
                <input  type="submit" value="Add New Category" class="itemsSide"/>
             </form>
            
            <form action="/E-commercepractice/displayProd">
                <input type="submit" value="Avaliable Products" class="itemsSide"/>
            </form>
            
             <form action="/E-commercepractice/displayProd" method="post">
                 <input type="hidden" name="process" value="Cat"/>
                <input  type="submit" value="Add New Product" class="itemsSide"/>
            </form>
            
            <form action="/E-commercepractice/displayProd"method="post">
                <input type="hidden" name="process" value="customer" />
                <input type="submit" value="Customer Data" class="itemsSide"/> 
            </form> 
            
             
         
        </div>
         </nav>
        </div>