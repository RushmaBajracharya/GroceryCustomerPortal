package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DBConnection;
import com.mysql.jdbc.PreparedStatement;
@WebServlet("/search_customer")
public class SearchCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SearchCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		Connection con=DBConnection.getConnection();
		PreparedStatement ps=null;
		HttpSession session=request.getSession();
		try {
			ps = (PreparedStatement) con.prepareStatement("SELECT customer_id,customer_name FROM purchase WHERE customer_name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				request.setAttribute("customer_id", rs.getInt("customer_id"));
				//request.setAttribute("customer_name", rs.getString("customer_name"));
				session.setAttribute("customer_name", rs.getString("customer_name"));
				RequestDispatcher rd = request.getRequestDispatcher("admin_viewhistory.jsp");
				rd.forward(request, response);
//				response.sendRedirect("admin_purchase.jsp");
			} else {
				session.setAttribute("errorMsg","Customer not found...");
				String referer = request.getHeader("referer");
		        response.sendRedirect(referer);
		        
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
