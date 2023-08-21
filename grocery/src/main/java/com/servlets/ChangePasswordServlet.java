package com.servlets;
import com.connection.DBConnection;
import com.dao.ChangePasswordDAO;
import com.entity.ChangePassword;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oldpass=request.getParameter("previous-password");
		String newpass=request.getParameter("new-password");
		String confirmpass=request.getParameter("confirm-password");
		HttpSession session=request.getSession();
		String currentPass=(String)session.getAttribute("password");
		String username=(String) session.getAttribute("username");
		
		if(oldpass.equals(null)||oldpass==""||newpass.equals(null)||newpass==""||confirmpass.equals(null)||confirmpass=="") {
			session.setAttribute("msg", "All Fields are mandatory");
        	response.sendRedirect("change_password.jsp");
		}
		else if(!newpass.equals(confirmpass)){
			session.setAttribute("msg", "Password does not match");
        	response.sendRedirect("change_password.jsp");
		}
		else if(!currentPass.equals(oldpass)) {
			session.setAttribute("msg", "Old Password is not correct");
        	response.sendRedirect("change_password.jsp");
		}
		else
		{
			ChangePassword c=new ChangePassword();
			ChangePasswordDAO dao=new ChangePasswordDAO(DBConnection.getConnection());
			String username1=c.setUsername(username);
			String newpassword1=c.setPassword(newpass);
			
			
			int i=dao.changePassword(username1,newpassword1);
			if(i!=0) {
				session.setAttribute("msg", "Password updated successfully");
            	response.sendRedirect("change_password.jsp");
			}else {
				session.setAttribute("msg", "Password not updated successfully");
            	response.sendRedirect("change_password.jsp");
			}
		}
	}


}
