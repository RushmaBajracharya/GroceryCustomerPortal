package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DBConnection;
import com.dao.CustomerDAO;
import com.entity.Customer;

@WebServlet("/register")

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RegisterServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String contact=request.getParameter("contact");
		  HttpSession session = request.getSession();
			/*
			 * // Perform field validations if (name.isEmpty() || username.isEmpty() ||
			 * password.isEmpty() || email.isEmpty() || contact.isEmpty()) {
			 * //System.out.println("isempty"); request.setAttribute("errorMsg",
			 * "All fields are required"); RequestDispatcher rd =
			 * request.getRequestDispatcher("admin_addcustomer.jsp"); rd.forward(request,
			 * response); // return; }
			 * 
			 * if (name.length() < 3) {
			 * 
			 * request.setAttribute("errorMsg", "Name must be at least 3 characters long");
			 * RequestDispatcher rd = request.getRequestDispatcher("admin_addcustomer.jsp");
			 * rd.forward(request, response); // return; }
			 * 
			 * // Perform email validation using regular expression String emailRegex =
			 * "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"; if (!email.matches(emailRegex)) {
			 * 
			 * request.setAttribute("errorMsg", "Invalid email address"); RequestDispatcher
			 * rd = request.getRequestDispatcher("admin_addcustomer.jsp");
			 * rd.forward(request, response); // return; }
			 */
		Customer customer=new Customer(name,username,password,email,contact);
		
		CustomerDAO dao=new CustomerDAO(DBConnection.getConnection());
		
	
		
		
		boolean f=dao.addCustomer(customer);
		if(f) {
			session.setAttribute("successMsg","Customer Details added successfully...");
			response.sendRedirect("admin_addcustomer.jsp");
			//System.out.println("Customer Details added successfully...");
		}
		else {
			session.setAttribute("errorMsg","Something went wrong...");
			response.sendRedirect("admin_addcustomer.jsp");
			//System.out.println("Customer Details arenot added...");
		}
	}

}
