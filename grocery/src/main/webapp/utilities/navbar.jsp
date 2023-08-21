<%
HttpSession session2=request.getSession();
String username1=(String)session2.getAttribute("username");
if(username1==null){
	response.sendRedirect("loginpg.jsp");
}
%>
<header class="header">
    <a href="index.jsp" class="logo">
        <i class="fas fa-shopping-basket"></i>Gautam's Groceries
        <!-- <img src="/image/logo.png"> -->
    </a>
            <nav class="navbar">
                <a href="index.jsp"><b>Home</b></a>
                <a href="purchase.jsp"><b>Purchase</b></a> 
				<a href="chart.jsp"><b>Chart</b></a>
            </nav>
            <div class="icon">
                <div id="menu-btn" class="fas fa-bars"></div>
              <!--   <div id="notification-btn" class="fa-solid fa-bell"></div> -->
                <div id="account-btn" class="fas fa-user-circle"></div>
            </div>

         <!--    <div class="notification-box" style="display: none;">        
                <p>The end of the month is approaching! Please make the payment for the purchase. </p>
            </div> -->

            <div class="user-account">
                <div class="name"><%= (String) session.getAttribute("username") %></div>
                <hr>
                <a href="change_password.jsp" class="btn">Change Password</a>
                <a href="LogoutServlet" class="btn">Logout</a>
            </div>
</header>
