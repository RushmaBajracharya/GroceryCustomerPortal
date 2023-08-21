<%@page import="java.util.Calendar" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@page import="com.connection.DBConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@page import="com.dao.PurchaseDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.entity.Purchase" %>

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
     <link rel="stylesheet" href="css/admin_purchase.css">
</head>
<body>
    <%@include file="utilities/sidemenu.jsp" %>
    <div class="container">
        <%@include file="utilities/searchbar.jsp" %>
        <div class="purchase_detail">
            <div class="table_header">
	        <%
			// Get the current month using Calendar
			java.util.Calendar cal = java.util.Calendar.getInstance();
			int month = cal.get(java.util.Calendar.MONTH);
			// Array of month names
			String[] monthNames = {"January", "February", "March", "April", "May", "June", "July",
								   "August", "September", "October", "November", "December"};

			// Get the month name using the integer as index
			String monthName = monthNames[month];
			%>
                <p>Month - <%= monthName %> </p>
                
            </div>
            <div class="content-1">
                <form id="purchase-form" method="post" action="addPurchase">
                    <h1>Add Purchase</h1>
                    <c:if test="${not empty successMsg}">
		            <p><center>${successMsg}</center></p>
		            <c:remove var="successMsg"/>
		            </c:if>
		            
		            <c:if test="${not empty errorMsg}">
		            <p><center>${errorMsg}</center></p>
		            <c:remove var="errorMsg"/>
		            </c:if>
		            <label for="customer-name">Enter Customer name:</label>
		            <%
					 	Connection con=null;
					    PreparedStatement pstmt = null;
					    ResultSet rs = null;
					
					    try {
					        // establish database connection
					       con=DBConnection.getConnection();
					        
					        // prepare SQL query to fetch customer names
					        String sql = "SELECT c_name,c_id FROM customer";
					        pstmt = con.prepareStatement(sql);
					        
					        // execute query and retrieve result set
					        rs = pstmt.executeQuery();
					%>
				    <select id="customer" name="customer">
				        <option value="">-- Select Customer --</option>
				        <% while (rs.next()) { %>
				            <option value="<%= rs.getInt("c_id") %>"><%= rs.getString("c_name") %></option>
				        <% } %>
				        </select>
				        <%} catch (Exception e) {
					        e.printStackTrace();
					    } %> 
                    <label for="product-name">Enter Product name:</label>
                    <input type="text" id="name" name="name"> 
                    <label for="product-name">Enter Price:</label>
                    <input type="text" id="price" name="price"> 
                    <label for="quantity">Quantity:</label>
                    <input type="text" id="quantity" name="quantity">
                    <label for="product-name">Select unit:</label>
                        <select id="unit" name="unit">
                        <option value="">-- Select Unit --</option>
                            <option value="Kilograms">Kilograms</option>
                            <option value="Liters">Liters</option>
                            <option value="Grams">Grams</option>
                            <option value="Pieces">Pieces</option>
                            <option value="Pounds ">Pounds </option>
                           
						</select>
					<!--  <input type="hidden" name="customer" value=">
					<input type="hidden" name="customer_id" value=""> --> 
					
                    <input type="submit" value="Add Product">
                </form>    
                <div class="purchase">
                    <div class="title">
                        <h1>Purchase items</h1>
                    </div>
                    <table>
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Customer</th>
                                <th>Product</th>
                                <th>Price</th>
                                <th>Quantity</th> 
                                <th>Unit</th>   
                                <th>Amount</th>    
                                <th>Delete</th>                  
                            </tr>
                        </thead>
                        <tbody>
                         <%
	                    PurchaseDAO dao=new PurchaseDAO(DBConnection.getConnection());
	                    List<Purchase> list=dao.getAllPurchase();
	                    double total = 0; // initialize total to zero
	                    for(Purchase p: list){
	                    double amount = Double.parseDouble(p.getPrice()) * Double.parseDouble(p.getQuantity());
	                    
	                    %>
	                     <tr>
	                            <td><%=p.getPurchase_date()%></td>
	                            <td><%=p.getCustomer_name()%></td> 
	                            <td><%=p.getProduct_name()%></td>                          
	                            <td><%=p.getPrice()%></td>                           
	                            <td><%=p.getQuantity()%></td>                           
	                            <td><%=p.getUnit()%></td>                                                     
	                            <td><%=amount%></td>   
	                            <td><button><a href="deletePurchase?purchase_id=<%=p.getPurchase_id()%>"><i class="fa-solid fa-trash"></i></a></button></td>
	                                                     
	                        </tr>
	                    <%
	         
	                    total += Double.parseDouble(p.getPrice()) * Double.parseDouble(p.getQuantity()); // add the purchase amount to the total
	                    }
	                    %>
                        <tr>
			                <td colspan="6" style="text-align:right">Total:</td>
			                <td colspan="2" style="text-align:left"><%= total %></td>
			            </tr>   
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</body>
</html>