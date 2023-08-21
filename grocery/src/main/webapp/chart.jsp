<%@page import="com.dao.ChartDAO"%>
<%@ page import="java.util.ArrayList" %>
<%@page import="java.util.List"%>
<%@ page import="java.time.Year" %>
<%@page import="java.sql.Connection"%>
<%@page import="com.connection.DBConnection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>grocery customer portal</title>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
    <!-- css file link -->
    <link rel="stylesheet" href="css/chart.css">
</head>
<body>
 	<!-- header section starts -->
	<%@include file="utilities/navbar.jsp" %>
    <!-- header section ends -->
    <div class="chart"><canvas id="purchaseChart" width="500" height="200"></canvas></div>
    
    
    <!-- custom js file link -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<%
    // Get the current year
    int currentYear = Year.now().getValue();
	String username=(String) session.getAttribute("username");
    // instance of the SalesDAO class
    ChartDAO chartDAO = new ChartDAO(DBConnection.getConnection());

    // list to store monthly sales totals
    List<Double> monthlyPurchaseTotals = new ArrayList<Double>();

    // Retrieve the monthly sales totals for the current year
    for (int month = 1; month <= 12; month++) {
        double monthlyTotal = chartDAO.getMonthlyPurchaseTotal(username,currentYear, month);
        monthlyPurchaseTotals.add(monthlyTotal);
    }
	%>
    <script>
        // Retrieve the monthly sales totals from the request attribute    
		var monthlyPurchaseTotals = <%= monthlyPurchaseTotals %>;			  
        // Create a labels array for the chart
       var labels = [];
		var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
		
		for (var month = 0; month < 12; month++) {
		    labels.push(monthNames[month]);
		}
        // Create a new bar chart
        var ctx = document.getElementById('purchaseChart').getContext('2d');
        var chart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Monthly Purchases',
                    data: monthlyPurchaseTotals,
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
    <!-- custom js file link -->
    <script src="js/mainpg.js"></script>
</body>
</html>