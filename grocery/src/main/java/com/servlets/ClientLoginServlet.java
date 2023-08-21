package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ClientLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
   
    public ClientLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
		 try{
             Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/GroceryCustomerPortal", "root", "");
            PreparedStatement pst=con.prepareStatement("Select * from customer where c_username=? and c_password=?");
            pst.setString(1,username);
            pst.setString(2,password);
            ResultSet rs=pst.executeQuery();
            if(rs.next()) {
            	//System.out.print(rs);
            	session.setAttribute("username", username);
            	session.setAttribute("password", password);
            	//session.setAttribute("id", rs);
            	response.sendRedirect("index.jsp");         	
            }
            else {
            	session.setAttribute("errorMsg","Invalid username or password");
            	response.sendRedirect("loginpg.jsp");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
	}

}
