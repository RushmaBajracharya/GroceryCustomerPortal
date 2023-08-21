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
    <link rel="stylesheet" href="css/admin_login.css">
</head>
<body>
    
        <form id="login-form" method="post" action="LoginServlet">
            <h1>Login</h1>
            <c:if test="${not empty errorMsg}">
           <p class="error_message">${errorMsg}</p>
            <c:remove var="errorMsg"/>
            </c:if>
            <label for="username">Username:</label>
            <input type="text" id="username" placeholder="Enter Username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password"  placeholder="Enter Password" name="password" required>

            <input type="submit" value="Login">
        </form>
    
    <script src="js/loginpg.js"></script>
</body>
</html>
