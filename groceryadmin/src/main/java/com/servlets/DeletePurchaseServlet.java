package com.servlets;
import com.dao.PurchaseDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DBConnection;

@WebServlet("/deletePurchase")

public class DeletePurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public DeletePurchaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// int purchase_id=Integer.parseInt(request.getParameter("purchase_id"));
		String purchase_id=request.getParameter("purchase_id");
	
		PurchaseDAO dao=new PurchaseDAO(DBConnection.getConnection());
		boolean f=dao.deletePurchase(purchase_id);

	
		
		
		if(f) {
			//session.setAttribute("successMsg","Customer Detail deleted successfully...");
			response.sendRedirect("admin_purchase.jsp");
			//System.out.println("Customer Detail added successfully...");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
