<%@page import="com.dao.PaymentDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.entity.Payment" %>
<%@page import="java.sql.Connection"%>
<%@page import="com.connection.DBConnection"%>
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
     <link rel="stylesheet" href="css/admin_updatePayment.css">
</head>
<body>
    <%@include file="utilities/sidemenu.jsp" %>
    <div class="container">
        <%@include file="utilities/searchbar.jsp" %>
         <%
            int id=Integer.parseInt(request.getParameter("id"));
            PaymentDAO dao=new PaymentDAO(DBConnection.getConnection());
            Payment pay=dao.getPaymentById(id);
         %>
        <form id="purchase-form" method="post" action="updatePayment">
            <h1>Edit Payment Details</h1>
            
        <%--     <c:if test="${not empty sMsg}">
            <p><center>${sMsg}</center></p>
            <c:remove var="sMsg"/>
            </c:if> --%>
            
           <%--  <c:if test="${not empty eMsg}">
            <p><center>${eMsg}</center></p>
            <c:remove var="eMsg"/>
            </c:if>   --%>     
            
            <label for="customerName">Customer Name:</label>
            <input type="text" id="customerName" name="customerName" value="<%=pay.getCustomer_name()%>" readonly>
            
            <label for="remainingAmount">Remaining Amount:</label>
            <input type="text" id="remainingAmount" name="remainingAmount" value="<%=pay.getTotal()%>" readonly>
            
            <label for="paidAmount">Paid Amount:</label>
            <input type="text" id="paidAmount" name="paidAmount" value="<%=pay.getPaid_amount()%>">
            
            <input type="hidden" name="status" value="<%=pay.getStatus()%>">
  
			<%-- <input type="hidden" name="paymentId" value="<%=request.getParameter("id")%>"> --%>
			<input type="hidden" name="paymentId" value="<%=id%>">
			
            <input type="submit" value="Update">
        </form>
    </div>
   <!--  <script src="js/admin_addcustomer.js"></script> -->

</body>
</html>