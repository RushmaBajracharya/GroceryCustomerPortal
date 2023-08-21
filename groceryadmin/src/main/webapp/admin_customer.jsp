<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
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
     <link rel="stylesheet" href="css/admin_customer.css">
</head>
<body>
     <%@include file="utilities/sidemenu.jsp" %>
 <%--<%
     Connection con= DBConnection.getConnection();
     out.print("hello");
	%> --%>  
     
    <div class="container">
        <%@include file="utilities/searchbar.jsp" %>
        <div class="table">
            <div class="table_header">
           
                <p>Customer Details</p>
                <div>
                    <button class="add_new"><a href="admin_addcustomer.jsp">Add Customer</a></button>
                </div>
            </div>
            <c:if test="${not empty successMsg}">
            <p><center>${successMsg}</center></p>
            <c:remove var="successMsg"/>
            </c:if>
            
            <c:if test="${not empty errorMsg}">
            <p><center>${errorMsg}</center></p>
            <c:remove var="errorMsg"/>
            </c:if>
            <div class="table_section">
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Join Date</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Email</th>
                            <th>Contact</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                    CustomerDAO dao=new CustomerDAO(DBConnection.getConnection());
                    List<Customer> list=dao.getAllCustomer();
                    for(Customer c: list){
                    %>
                     <tr>
                            <td><%=c.getName()%></td>
                            <td><%=c.getDate()%></td> 
                            <td><%=c.getUsername()%></td>                          
                            <td><%=c.getPassword()%></td>                           
                            <td><%=c.getEmail()%></td>                           
                            <td><%=c.getContact()%></td>                                                     
                            <td>
                                <button><a href="admin_editcustomer.jsp?id=<%=c.getId()%>"><i class="fa-solid fa-pen-to-square"></i></a></button>
                                <button><a href="delete?id=<%=c.getId()%>"><i class="fa-solid fa-trash"></i></a></button>
                            </td>                           
                        </tr>
                    <%
                    }
                    %>
                    
                     
                    </tbody>
                </table>
            </div>
        </div>
    </div>
 
</body>
</html>