<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
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
     <link rel="stylesheet" href="css/admin_addcustomer.css">
</head>
<body>
    <%@include file="utilities/sidemenu.jsp" %>
    <%--<%
     Connection con= DBConnection.getConnection();
     out.print("hello");
	%> --%>  
    <div class="container">
        <%@include file="utilities/searchbar.jsp" %>
        
        <form id="registration-form" method="post" action="RegisterServlet">
            <h1>Registration Form</h1>
            <c:if test="${not empty successMsg}">
            <p><center>${successMsg}</center></p>
            <c:remove var="successMsg"/>
            </c:if>
            
            <c:if test="${not empty errorMsg}">
            <p><center>${errorMsg}</center></p>
            <c:remove var="errorMsg"/>
            </c:if>
          <%--  	<% String errorMsg = (String) request.getAttribute("errorMsg"); %>
			<% if (errorMsg != null) { %>
			<div class="message" style="color: red;"><%= errorMsg %></div>
			<% } %> --%>
            
            <label for="name">Enter Name:</label>
            <input type="text" id="name" name="name" required>
             <!--  <span id="name-error" class="error"></span> -->
            
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
           <!--  <span id="username-error" class="error"></span> -->
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <!-- <span id="password-error" class="error"></span> -->
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            <!--  <span id="email-error" class="error"></span> -->

            <label for="contact">Contact:</label>
            <input type="number" id="contact" name="contact" required>
          <!--   <span id="contact-error" class="error"></span> -->

          <!--   <input type="submit" onclick="validate()" value="Add Customer"> -->
            <input type="submit" value="Add Customer">
        </form>
    </div>
   <!--  <script src="js/admin_addcustomer.js"></script> -->

</body>
</html>