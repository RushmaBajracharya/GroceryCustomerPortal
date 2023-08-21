<%@page import="com.dao.PaymentDAO" %>
<%@page import="com.entity.Payment" %>
<%@page import="com.connection.DBConnection" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
     <!-- font awesom cdn link -->
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
     <!-- css file link -->
     <link rel="stylesheet" href="css/admin_payment.css">
</head>
<body>
    <%@include file="utilities/sidemenu.jsp" %>
    <div class="container">
        <%@include file="utilities/searchbar.jsp" %>
        <div class="table">
            <div class="table_header">
                <p>Payment Details</p>
            </div>
            
             <c:if test="${not empty eMsg}">
            <p>${eMsg}</p>
            <c:remove var="eMsg"/>
            </c:if>
            
            <div class="table_section">
                <table>
                    <thead>
                        <tr>
                            <th>S.No</th>
                            <th>Customer Name</th>                        
                            <th>Remaining Amount</th>
                            <th>Paid Amount</th>
                            <th>Status</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                      <%
                    PaymentDAO dao=new PaymentDAO(DBConnection.getConnection());
                    List<Payment> list=dao.getAllPayment();
                    int i=1;
                    for(Payment pay: list){
                    %>
                     <tr>
                            <td><%=i%></td>
                            <td><%=pay.getCustomer_name()%></td> 
                            <td><%=pay.getTotal()%></td> 
                            <td><%=pay.getPaid_amount()%></td>                          
                            <td><p class="status <%=pay.getStatus()%>"><%=pay.getStatus()%></p></td>                           
                                                                     
                            <td>
                                <button><a href="admin_updatePayment.jsp?id=<%=pay.getPayment_id()%>"><i class="fa-solid fa-pen-to-square"></i></a></button>
                               
                            </td>                           
                        </tr>
                    <%
                   i++;
                    }
                    %>
                        
                        <!--     <td><p class="status unpaid">Unpaid</p></td>                                                       
                                         
                            <td><p class="status paid">Paid</p></td>                                                       
                                   
                            <td><p class="status pending">Pending</p></td>       -->                                                 
                        
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- <script src="/js/admin_payment.js"></script> -->

</body>
</html>