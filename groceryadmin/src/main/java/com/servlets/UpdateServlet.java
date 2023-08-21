package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DBConnection;
import com.dao.CustomerDAO;
import com.entity.Customer;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		int id=Integer.parseInt(request.getParameter("id"));
		
		Customer customer=new Customer(id,name,username,password,email,contact);
		
		CustomerDAO dao=new CustomerDAO(DBConnection.getConnection());
		
		HttpSession session=request.getSession();
		boolean f=dao.updateCustomer(customer);
		if(f) {
			session.setAttribute("successMsg","Customer Detail updated successfully...");
			response.sendRedirect("admin_customer.jsp");
			//System.out.println("Customer Details added successfully...");
		}
		else {
			session.setAttribute("errorMsg","Something went wrong...");
			response.sendRedirect("admin_customer.jsp");
			//System.out.println("Customer Details arenot added...");
		}
	}

}
