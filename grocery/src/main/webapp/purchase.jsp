<%@page import="com.dao.ClientNoticeDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.entity.ClientNotice" %>
<%@page import="com.entity.ClientPayment" %>
<%@page import="java.sql.Connection"%>
<%@page import="com.connection.DBConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@page import="com.dao.ClientPurchaseDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.entity.ClientPurchase" %>
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
    <link rel="stylesheet" href="css/purchase.css">
     <script src="https://khalti.s3.ap-south-1.amazonaws.com/KPG/dist/2020.12.17.0.0.0/khalti-checkout.iffe.js"></script>
	<!-- link for popup after success from sweetlert -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <!-- header section starts -->
	<%@include file="utilities/navbar.jsp" %>
    <!-- header section ends -->
    
    <div class="container">
    <%
			// Get the current month using Calendar
			
			java.util.Calendar cal = java.util.Calendar.getInstance();
			int current_month = cal.get(java.util.Calendar.MONTH);
		
			// Array of month names
			String[] monthNames = {"January", "February", "March", "April", "May", "June", "July",
								   "August", "September", "October", "November", "December"};

			// Get the month name using the integer as index
			String monthName = monthNames[current_month];
			current_month=current_month+1;
			String selectedMonthStr = request.getParameter("month");
			int selectedMonth = selectedMonthStr != null ? Integer.parseInt(selectedMonthStr) : 0;
			String[] mNames = {"","January", "February", "March", "April", "May", "June", "July",
					   "August", "September", "October", "November", "December"};
			String monthSelected = mNames[selectedMonth];
			
			%>
        <section class="heading" id="heading">
                <h1>
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
			    </h1>   
        </section>
        <div class="drop">
     
            <form method="post" action="viewMonthlyPurchase">
                <!-- <label for="month">Month:</label> -->
                <select id="month" name="month">
                  <option value="01">January</option>
                  <option value="02">February</option>
                  <option value="03">March</option>
                  <option value="04">April</option>
                  <option value="05">May</option>
                  <option value="06">June</option>
                  <option value="07">July</option>
                  <option value="08">August</option>
                  <option value="09">September</option>
                  <option value="10">October</option>
                  <option value="11">November</option>
                  <option value="12">December</option>
                </select>
                <input type="hidden" name="username" value="<%=(String) session.getAttribute("username") %>">
                <button type="submit">Submit</button>
              </form>
        </div>
        
          <% 		  
			    if (selectedMonth > current_month) {
			%>
			        <div class="error_message">
			           <center> Invalid month selected. Please choose a month less than or equal to the current month.</center>
			        </div>
			<% } %>
			
        <section class="content" id="content">
        		<div class="left-column"> 
	                <table>
	                    <thead>
	                        <tr>
	                            <th>Date</th>
	                            <th>Product Name</th>
	                            <th>Price</th>
	                            <th>Quantity</th>
	                            <th>Unit</th>
	                            <th>Amount</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    <%
	                    String username = (String) session.getAttribute("username");
	   			     
	   			        ClientPurchaseDAO cdao = new ClientPurchaseDAO(DBConnection.getConnection());
	   			 
	   				 
	   				 
	   					// assign month1
	   					int month1;
	   					if (selectedMonth != 0) {
	   					    month1 = selectedMonth;
	   					} else {
	   					    month1 = current_month;
	   					}
	   				   
	   				   
	   				    List<ClientPurchase> list = cdao.getPurchaseDetail(username, month1);
	   				    double total = 0;	   			
	   				    for(ClientPurchase p: list){	   				    
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
		                    }   
		                    %>
		                    
		                    <tfoot>
	                       <tr> 
				             <td colspan="5" style="text-align:right">Total:</td>
				                <td><%= total %></td>
				            </tr>    
	                       
	                    </tfoot>
	                </table>  
	            </div>   
	            <div class="right-column"> 
	                <div class="notice_box">
	                    <h3>Notice:</h3>
	                    <div class="notice-container"></div>
	                     <%
	                     ClientNoticeDAO dao=new ClientNoticeDAO(DBConnection.getConnection());
	                    List<ClientNotice> l=dao.getAllNotices();
	                    for(ClientNotice n:l){
	                    %>
	                    <p class="notice">
	                       <%=n.getCreatedAt() %><br><%=n.getContent() %><br>
	                    </p>
	                     <% 	
	                    }
	                    %>
	                </div>   
				    <div class="payment">
			         <h3>Payment:</h3>
			        <div class="payment_status">
			       
			        <%ClientPayment payment  =cdao.getPayment(username);
			        if (payment != null) {
			        %>
						<p>
			            Payment status: <%=payment.getStatus() %> <br>
			            Remaining Amount: <%=payment.getTotal() %> <br>
			             Paid Amount: <%=payment.getPaid_amount() %> <br>
			              <button id="payment-button">Pay with Khalti</button>
			              </p>
			       <% } %>
			        </div>
			        </div> 
			    </div>  
                
        </section>  
        
 </div>
   
    

    <!-- footer section starts -->
   <%@include file="utilities/footer.jsp" %>
    <!-- footer section ends -->
 <%--  <%int customer_id=(Integer)session.getAttribute("customer_id");%> --%>
    <!-- custom js file link -->
    <script src="js/mainpg.js"></script>
     <script>   
        var config = {
            // replace the publicKey with yours
            "publicKey": "test_public_key_e3dbaa9e830f4600883a33f8b327545d",
            "productIdentity": <%=payment.getCustomer_id()%>,
            "productName": "<%=username%>",
            "productUrl": "http://gameofthrones.wikia.com/wiki/Dragons",
            "paymentPreference": [
                "KHALTI",
                "EBANKING",
                "MOBILE_BANKING",
                "CONNECT_IPS",
                "SCT",
                ],
            "eventHandler": {
                onSuccess (payload) {
                	
                  <%--   window.location.href="PaymentServlet?customer_id=<%=payment.getCustomer_id()%>&username=<%=username%>&amount=<%=payment.getTotal()%>"; --%>
              		swal({
              		  title: "Payment Successful!",
              		  //text: "You clicked the button!",
              		  icon: "success",
              		  button: "OK!",
              		}).then(function() {              		
                        window.location.href="PaymentServlet?customer_id=<%=payment.getCustomer_id()%>&username=<%=username%>&amount=<%=payment.getTotal()%>";
                    });
                   console.log(payload);
                },
                onError (error) {
                    console.log(error);
                },
                onClose () {
                    console.log('widget is closing');
                }
            }
        };

        var checkout = new KhaltiCheckout(config);
        var btn = document.getElementById("payment-button");
        btn.onclick = function () {
            // minimum transaction amount must be 10, i.e 1000 in paisa.
            checkout.show({amount: <%=payment.getTotal()*100 %>});
        }
    </script>
    
</body>
</html>