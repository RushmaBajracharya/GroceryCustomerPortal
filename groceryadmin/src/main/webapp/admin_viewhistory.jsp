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
     <link rel="stylesheet" href="css/admin_viewhistory.css">
</head>
<body>
    <%@include file="utilities/sidemenu.jsp" %>
    <div class="container">
        <%@include file="utilities/searchbar.jsp" %>
        <div class="table">
            <div class="table_header">
            <%
			// Get the current month using Calendar
			
			java.util.Calendar cal = java.util.Calendar.getInstance();
			int current_month = cal.get(java.util.Calendar.MONTH);
			//System.out.println("a="+current_month);
		

			// Array of month names
			String[] monthNames = {"January", "February", "March", "April", "May", "June", "July",
								   "August", "September", "October", "November", "December"};

			// Get the month name using the integer as index
			String monthName = monthNames[current_month];
			
			//System.out.println("b="+monthName);
			//System.out.println(monthName.getClass().getName());

			current_month=current_month+1;
			//System.out.println("c="+current_month);
		
			
			String selectedMonthStr = request.getParameter("month");
			int selectedMonth = selectedMonthStr != null ? Integer.parseInt(selectedMonthStr) : 0;
			String[] mNames = {"","January", "February", "March", "April", "May", "June", "July",
					   "August", "September", "October", "November", "December"};
			String monthSelected = mNames[selectedMonth];
			
			%>
                <p><%=(String) session.getAttribute("customer_name") %> - 
		         <% if (selectedMonth != 0) {
		            %>
		            <%= monthSelected %>
		            <% 
		        } else {
		            %>
		            <%= monthName %>
		            <% 
		        }
		    %>
		</p>
                <div class="drop">
                    <form method="post" action="viewPurchase">
                        <!-- <label for="month">Month:</label> -->
                        <select id="month" name="month">
                          <option value="1">January</option>
                          <option value="2">February</option>
                          <option value="3">March</option>
                          <option value="4">April</option>
                          <option value="5">May</option>
                          <option value="6">June</option>
                          <option value="7">July</option>
                          <option value="8">August</option>
                          <option value="9">September</option>
                          <option value="10">October</option>
                          <option value="11">November</option>
                          <option value="12">December</option>
                        </select>
				     <%
				   // String customerName = (request.getAttribute("customer_name") != null) ? request.getAttribute("customer_name").toString() : "";
					%>

					<input type="hidden" name="customer_name" value="<%=(String) session.getAttribute("customer_name") %>">
                        <button type="submit">View</button>
                      </form>
                </div>
            </div>
            <% 		  
			    if (selectedMonth > current_month) {
			%>
			        <div class="error_message">
			            Invalid month selected. Please choose a month less than or equal to the current month.
			        </div>
			<% } %>
            <div class="table_section">
                <table>
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Product</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Unit</th>
                            <th>Amount</th>
                        </tr>
                    </thead>
                    <tbody>
                  
            
                   
			     <%
			     
			     String customerName = (String) session.getAttribute("customer_name");
			     
			     PurchaseDAO dao = new PurchaseDAO(DBConnection.getConnection());
			 
				 
				 
					// assign month1
					int month1;
					if (selectedMonth != 0) {
					    month1 = selectedMonth;
					} else {
					    month1 = current_month;
					}
				   
				   
				    List<Purchase> list = dao.getPurchaseDetail(customerName, month1);
				    double total = 0;
				    for(Purchase p: list){
				        double amount = Double.parseDouble(p.getPrice()) * Double.parseDouble(p.getQuantity());
				        total += amount;
				    
				%>

	                     <tr>
	                            <td><%=p.getPurchase_date()%></td>	                          
	                            <td><%=p.getProduct_name()%></td>                          
	                            <td><%=p.getPrice()%></td>                           
	                            <td><%=p.getQuantity()%></td>                           
	                            <td><%=p.getUnit()%></td>                                                     
	                            <td><%=amount%></td>                             
	                        </tr> 
	                    <%
	         
	                   // total += Double.parseDouble(p.getPrice()) * Double.parseDouble(p.getQuantity()); // add the purchase amount to the total
	                   }
	                    %>
                       <tr> 
			             <td colspan="5" style="text-align:right">Total:</td>
			                <td><%= total %></td>
			            </tr> 
			           
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</body>
</html>