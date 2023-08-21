package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DBConnection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class PaymentServlet
 */
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customer_id=Integer.parseInt(request.getParameter("customer_id"));
		String username=request.getParameter("username");
		String amount=request.getParameter("amount");
		/*
		 * System.out.println(customer_id); System.out.println(username);
		 * System.out.println(amount);
		 */
		double total=0.0;
		double paid_amount=Double.parseDouble(amount);
		int payment_id=0;
		Connection con=DBConnection.getConnection();;
		
		try {
			
			String sql="select total,payment_id from payment where customer_id=?";
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, customer_id);
		
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
				payment_id=rs.getInt("payment_id");
            	total = rs.getDouble("total");
            }
        }catch(Exception e) {
			e.printStackTrace();
		}
		double remaining_amount=total-paid_amount;
		try {
			String sql1="update payment set total=?,paid_status=?,paid_amount=? where payment_id=?";
			PreparedStatement ps1=(PreparedStatement) con.prepareStatement(sql1);
			
			ps1.setDouble(1, remaining_amount);
			ps1.setString(2, "Paid");
			ps1.setDouble(3, paid_amount);
			ps1.setInt(4, payment_id);
			int i=ps1.executeUpdate();
			
			  if(i>0) {
				  
			  response.sendRedirect("purchase.jsp"); }
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
