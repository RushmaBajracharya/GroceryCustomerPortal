<%@page import="com.dao.CustomerDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.entity.Customer" %>
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
     <link rel="stylesheet" href="css/admin_editcustomer.css">
</head>
<body>
    <%@include file="utilities/sidemenu.jsp" %>
    <div class="container">
        <%@include file="utilities/searchbar.jsp" %>
         <%
            int id=Integer.parseInt(request.getParameter("id"));
            CustomerDAO dao=new CustomerDAO(DBConnection.getConnection());
            Customer c=dao.getCustomerById(id);
         %>
        <form id="registration-form" method="post" action="update">
            <h1>Edit Customer Details</h1>
                     
            <label for="name">Enter Name:</label>
            <input type="text" id="name" name="name" value="<%=c.getName()%>">
            
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="<%=c.getUsername()%>">
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="<%=c.getPassword()%>">
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%=c.getEmail()%>">

            <label for="contact">Contact:</label>
            <input type="number" id="contact" name="contact" value="<%=c.getContact()%>">

			<input type="hidden" name="id" value="<%=c.getId()%>">
			
            <input type="submit" onclick="validate()" value="Edit Customer">
        </form>
    </div>
    <script src="js/admin_addcustomer.js"></script>

</body>
</html>