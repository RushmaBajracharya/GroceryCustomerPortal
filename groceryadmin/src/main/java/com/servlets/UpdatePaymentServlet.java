package com.servlets;
import com.entity.Payment;
import com.dao.PaymentDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DBConnection;



@WebServlet("/updatePayment")
public class UpdatePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UpdatePaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerName=request.getParameter("customerName");
		String id=request.getParameter("paymentId");
		int paymentId=Integer.parseInt(id);
		double remainingAmount=Double.parseDouble(request.getParameter("remainingAmount"));
		double paidAmount=Double.parseDouble(request.getParameter("paidAmount"));
		String status=request.getParameter("status");
		
		if(remainingAmount==paidAmount){
			status="Paid";
		}else {
			status="Unpaid";
		}
		/*
		 * System.out.println(customerName); System.out.println(paymentId);
		 * System.out.println(remainingAmount); System.out.println(paidAmount);
		 * System.out.println(status);
		 */
		
		Payment payment=new Payment(paymentId,customerName,remainingAmount,paidAmount,status);
		/*
		 * System.out.println(payment.getPayment_id());
		 * System.out.println(payment.getCustomer_name());
		 * System.out.println(payment.getTotal());
		 * System.out.println(payment.getPaid_amount());
		 * System.out.println(payment.getStatus());
		 */
		  PaymentDAO paymentDao=new PaymentDAO(DBConnection.getConnection());
		  
		  HttpSession session=request.getSession(); 
		  if(paidAmount>remainingAmount) {			
			  request.setAttribute("eMsg", "Paid amount cannot be greater than the remaining amount!");
	          request.getRequestDispatcher("admin_payment.jsp").forward(request, response);
	         
		  }else {
			  boolean f=paymentDao.updatePayment(payment); 
				
				  if(f) {
				//  session.setAttribute("sMsg","Payment Detail updated successfully...");
				  response.sendRedirect("admin_payment.jsp");
				  
				  } else {
				//  session.setAttribute("eMsg","Something went wrong...");
				  response.sendRedirect("admin_payment.jsp");
			
			  }
		  } 		 
	}

}
