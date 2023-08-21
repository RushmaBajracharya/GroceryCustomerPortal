package com.servlets;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
       
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		 try{
			 HttpSession session=request.getSession();
             Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/GroceryCustomerPortal", "root", "");
            PreparedStatement pst=con.prepareStatement("Select * from admin where username=? and password=?");
            pst.setString(1,username);
            pst.setString(2,password);
            ResultSet rs=pst.executeQuery();
            if(rs.next()) {
            	session.setAttribute("username", username);
            	
            	response.sendRedirect("index.jsp");         	
            }
            else {
            	session.setAttribute("errorMsg","Invalid username or password");
            	response.sendRedirect("admin_login.jsp");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
		
	}

}
