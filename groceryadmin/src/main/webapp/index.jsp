<%@page import="com.dao.NoticeDAO"%>
<%@page import="com.dao.SalesDAO"%>
<%@page import="com.dao.PurchaseDAO"%>
<%@page import="com.dao.PaymentDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.entity.Notice"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.connection.DBConnection"%>
<%@ page import="com.dao.CustomerDAO"%>
<%@ page import="com.entity.Customer"%>
<%@ page import="java.time.Year" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Panel</title>
<!-- font awesom cdn link -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
<!-- css file link -->
<link rel="stylesheet" href="css/index.css">
</head>
<body>

	<%@include file="utilities/sidemenu.jsp"%>
	<%
	CustomerDAO cdao = new CustomerDAO(DBConnection.getConnection());
	%>
	<%
	PaymentDAO paydao = new PaymentDAO(DBConnection.getConnection());
	%>
	<div class="container">
		<%@include file="utilities/searchbar.jsp"%>
		<div class="content">
			<div class="cards">
				<div class="card">
					<div class="box">
						<h1><%=cdao.getCustomerCount()%></h1>
						<h3>Customers</h3>
					</div>
					<div class="icon-case">
						<i class="fas fa-user customer-icon"></i>
					</div>
				</div>
				<div class="card">
					<div class="box">
						<h1><%=paydao.getCompletedPayment()%></h1>
						<h3>
							Completed<br>Payments
						</h3>
					</div>
					<div class="icon-case">
						<i class="fas fa-money-bill-alt money-icon"></i>
					</div>
				</div>
			</div>
			<div class="content-2">
				<div class="chart_container">
				<div class="title">
						<h2>Monthly Sales</h2>
				</div>
				<canvas id="salesChart" width="500" height="200"></canvas>
				    
					<%--<table>
						<tr>
							<th>Name</th>
							<th>Username</th>
							<th>Contact</th>

						</tr>
						<%
					
						List<Customer> clist = cdao.getAllCustomer();
						for (Customer c : clist) {
						%>
						<tr>
							<td><%=c.getName()%></td>
							<td><%=c.getUsername()%></td>
							<td><%=c.getContact()%></td>

						</tr>
						<%
						}
						%>
						
					</table> --%>
					
				</div>

				<div class="notice">
					<div class="title">
						<h2>Notice</h2>
					</div>
					<form id="notice-form" method="post" action="notice">
						<textarea id="notice-text" name="content"
							placeholder="Type your notice here..."></textarea>
						<button type="submit" class="btn">Add Notice</button>
					</form>
					<table>
						<%
						NoticeDAO dao = new NoticeDAO(DBConnection.getConnection());
						List<Notice> list = dao.getAllNotices();
						for (Notice n : list) {
						%>
						<tr>
							<td colspan="4"><%=n.getCreatedAt()%><br><%=n.getContent()%></td>
							<td><button>
									<a href="deleteNotice?id=<%=n.getId()%>"><i
										class="fa-solid fa-trash"></i></a>
								</button></td>
						</tr>
						<%
						}
						%>
					</table>

				</div>
				
			</div>
				<!-- <div class="chart_container">				
				
					<canvas id="salesChart" width="500" height="300"></canvas>
			 	</div>  -->

		</div>

	</div>
	<!-- custom js file link -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<%
    // Get the current year
    int currentYear = Year.now().getValue();

    // instance of the SalesDAO class
    SalesDAO salesDAO = new SalesDAO(DBConnection.getConnection());

    // list to store monthly sales totals
    List<Double> monthlySalesTotals = new ArrayList<Double>();

    // Retrieve the monthly sales totals for the current year
    for (int month = 1; month <= 12; month++) {
        double monthlyTotal = salesDAO.getMonthlySalesTotal(currentYear, month);
        monthlySalesTotals.add(monthlyTotal);
    }
	%>
    <script>
        // Retrieve the monthly sales totals from the request attribute    
		var monthlySalesTotals = <%= monthlySalesTotals %>;			  
        // Create a labels array for the chart
       var labels = [];
		var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
		
		for (var month = 0; month < 12; month++) {
		    labels.push(monthNames[month]);
		}
        // Create a new bar chart
        var ctx = document.getElementById('salesChart').getContext('2d');
        var chart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Sales',
                    data: monthlySalesTotals,
                    backgroundColor: '#27ae60', // Set the bar color
                    borderColor: 'darkgreen', // Set the border color
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                         ticks: {
                            callback: function(value) {
                                return 'Rs.' + value; // Add RS sign to y-axis ticks
                            } 
                        }
                    }
                }
            }
        });
    </script>
</body>
</html>