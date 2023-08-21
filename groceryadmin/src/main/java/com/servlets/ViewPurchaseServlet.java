package com.servlets;

import com.dao.PurchaseDAO;
import com.entity.Customer;
import com.entity.Purchase;
import com.dao.CustomerDAO;
import com.connection.DBConnection;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewPurchase")
public class ViewPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PurchaseDAO purchaseDAO;
	private CustomerDAO customerDAO;

	public void init() {
		purchaseDAO = new PurchaseDAO(DBConnection.getConnection());
		customerDAO = new CustomerDAO(DBConnection.getConnection());
	}

	public ViewPurchaseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String customerName = request.getParameter("customer_name");
		String selectedMonth = request.getParameter("month");
		

		Calendar cal = Calendar.getInstance();
		int currentMonth = cal.get(Calendar.MONTH) + 1; // Adding 1 to match SQL MONTH function

		
	
		request.setAttribute("customerName", customerName);
		request.setAttribute("selectedMonth",selectedMonth);
		request.setAttribute("currentMonth", currentMonth);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_viewhistory.jsp");
		dispatcher.forward(request, response);

	}

}
