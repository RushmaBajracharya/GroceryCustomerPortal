<%
HttpSession session1=request.getSession();
String username=(String)session1.getAttribute("username");
if(username==null){
	response.sendRedirect("admin_login.jsp");
}
%>
<div class="side-menu">
        <div class="shop-name">
            <h1><center><i class="fas fa-shopping-basket"> </i> Gautam's Groceries</center></h1>
        </div>
        <ul>
            <li><a href="index.jsp"><i class="fas fa-tachometer-alt"></i><span>Dashboard</span></a></li>
            <li><a href="admin_customer.jsp"><i class="fas fa-user"></i><span>Customer</span></a></li>
            <li><a href="admin_purchase.jsp"><i class="fas fa-shopping-basket"></i><span>Purchase</span></a></li>
            <li><a href="admin_payment.jsp"><i class="fas fa-money-bill-alt money-icon"></i><span>Payment</span></a></li>
            <li><a href="SignoutServlet"><i class="fas fa-sign-out-alt"></i><span>Signout</span></a></li>
        </ul>
    </div>