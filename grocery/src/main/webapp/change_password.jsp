<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>grocery customer portal</title>
    <!-- font awesom cdn link -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
    <!-- css file link -->
    <link rel="stylesheet" href="css/change_password.css">
</head>
<body>
   <!-- header section starts -->
   <%@include file="utilities/navbar.jsp" %>
    <!-- header section ends -->
<%
String username = (String) session.getAttribute("username");
String password = (String) session.getAttribute("password");
%>
    <form id="change-password-form" method="post" action="ChangePasswordServlet">
        <h1>Change Password</h1>
        <c:if test="${not empty msg}">
            <p><center>${msg}</center></p>
            <c:remove var="msg"/>
        </c:if>
        <label for="previous-password">Previous Password:</label>
        <input type="password" id="previous-password" name="previous-password">
        
        <label for="new-password">New Password:</label>
        <input type="password" id="new-password" name="new-password">
        
        <label for="confirm-password">Confirm New Password:</label>
        <input type="password" id="confirm-password" name="confirm-password">
        
        <input type="submit" value="Change Password">
      </form>
      <!-- <script src="js/change_password.js"></script> -->
       <script src="js/mainpg.js"></script>
</body>
</html>