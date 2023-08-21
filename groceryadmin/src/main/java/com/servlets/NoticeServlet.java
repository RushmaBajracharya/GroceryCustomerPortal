package com.servlets;
import com.connection.DBConnection;
//import java.util.Date;
import com.entity.Notice;
import com.dao.NoticeDAO;

import java.io.IOException;
//import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public NoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String content = request.getParameter("content");
	    String createdAt=request.getParameter("createdAt");
	    
	    Notice notice = new Notice(content,createdAt);

	    NoticeDAO dao=new NoticeDAO(DBConnection.getConnection());
	    boolean success=dao.addNotice(notice);
	    if(success)
	    {
	    	response.sendRedirect("index.jsp");
	    }else {
	    	response.sendRedirect("index.jsp");
	    }
	}

}
