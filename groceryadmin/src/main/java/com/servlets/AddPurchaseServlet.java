package com.servlets;
import com.entity.Purchase;
import com.entity.Payment;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DBConnection;
import com.dao.PurchaseDAO;
import com.dao.CustomerDAO;
import com.dao.PaymentDAO;
@WebServlet("/addPurchase")
public class AddPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPurchaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String product_name=request.getParameter("name");
		String price=request.getParameter("price");
		String quantity=request.getParameter("quantity");
		String unit=request.getParameter("unit");
		//String customer_name=request.getParameter("customer");
	  
	 double priceFloat = Double.parseDouble(price);
     double quantityFloat = Double.parseDouble(quantity);

        // Calculate the total amount
       double amount = priceFloat * quantityFloat; 
        
		int  customer_id=Integer.parseInt(request.getParameter("customer"));
		CustomerDAO cdao=new CustomerDAO(DBConnection.getConnection());
		String customer_name=cdao.getCustomerNameById(customer_id);
		
		 // Purchase purchase=new
		//  Purchase(customer_id,customer_name,product_name,price,quantity,unit,amount);

		  Purchase purchase=new
		  Purchase(customer_id,customer_name,product_name,price,quantity,unit,amount);
		  
		  PurchaseDAO dao=new PurchaseDAO(DBConnection.getConnection());
		  PaymentDAO paymentDao=new PaymentDAO(DBConnection.getConnection());
			
		  
		  HttpSession session=request.getSession();
		  
		  boolean f=dao.addpurchase(purchase); 
		  
	//	 double totalAmount=dao.getTotalAmountByCustomer(customer_name);
		
		  // System.out.println("totalAmount="+totalAmount);
			/*
			 * double paidAmount=paymentDao.getPaidAmountBy(customer_name);
			 * System.out.println("paidAmount="+paidAmount); double
			 * remainingAmount=totalAmount-paidAmount;
			 * System.out.println("rem="+remainingAmount);
			 */
		 
		
		 PaymentDAO paymentdao=new PaymentDAO(DBConnection.getConnection());
		 String status="Unpaid";
		 if(paymentdao.checkCustomer(customer_id)) {
			 double oldTotal=paymentdao.getTotal(customer_id);
			 double grandTotal=oldTotal+amount;
			 if(grandTotal==0) {
				 status="Paid";
			 }
			 paymentdao.updateTotal(grandTotal,customer_id,status);
			 
		 }else {
			 Payment payment=new Payment(customer_name,amount,"Unpaid",customer_id);
		//	 System.out.print("outside");
			 paymentdao.insertPayment(payment); 
		 }
		 
		 
		  if(f) {
		  session.setAttribute("successMsg","Purchase Details added successfully...");
		  response.sendRedirect("admin_purchase.jsp");
		  //System.out.println("Customer Details added successfully..."); 
		  } else {
		  session.setAttribute("errorMsg","Something went wrong...");
		  response.sendRedirect("admin_purchase.jsp");
		  //System.out.println("Customer Details arenot added...");
		  }
		 
	
	}
	}

