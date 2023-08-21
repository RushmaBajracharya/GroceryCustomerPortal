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
@WebServlet("/delete")

public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		CustomerDAO dao=new CustomerDAO(DBConnection.getConnection());
		boolean f=dao.deleteCustomer(id);
		
		HttpSession session=request.getSession();
		
		
		if(f) {
			session.setAttribute("successMsg","Customer Detail deleted successfully...");
			response.sendRedirect("admin_customer.jsp");
			//System.out.println("Customer Detail added successfully...");
		}
		else {
			session.setAttribute("errorMsg","Something went wrong...");
			response.sendRedirect("admin_customer.jsp");
			//System.out.println("Customer Detail arenot added...");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
