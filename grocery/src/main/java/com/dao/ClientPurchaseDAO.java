package com.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.entity.ClientPayment;
import com.entity.ClientPurchase;
import com.mysql.jdbc.Statement;
public class ClientPurchaseDAO {
	private Connection con;

	public ClientPurchaseDAO(Connection con) {
		super();
		this.con = con;
	}
	public List<ClientPurchase> getPurchaseDetail(String username,int month){
		// Get the current month
		
	    Calendar cal = Calendar.getInstance();
	    int currentMonth = cal.get(Calendar.MONTH) + 1; // Adding 1 to match SQL MONTH function
		
	    List<ClientPurchase> list=new ArrayList<ClientPurchase>();
		
		try {
			Statement stmt = (Statement) con.createStatement();
			String sql1=null;
			String sql2=null;
			String name=null;
			//int id;
			if(username!=null) {
				sql1="SELECT * FROM customer WHERE c_username ='" + username +"'";
				ResultSet r = stmt.executeQuery(sql1);
				 while (r.next()) {		           
			            name= r.getString("c_name");
			           // id=r.getInt("c_id");
			            
				 }
				 // Check if a month is selected or not
				 sql2="SELECT * FROM purchase WHERE customer_name ='" + name + "' AND MONTH(purchase_date) ='" + month + "' AND YEAR(purchase_date) = YEAR(CURRENT_DATE())";
					
					ResultSet rs = stmt.executeQuery(sql2);
			        while (rs.next()) {
			           
			            Date date= rs.getDate("purchase_date");
			            String product_name=rs.getString("product_name");
			            String price=rs.getString("price");
			            String quantity=rs.getString("quantity");
			            String unit=rs.getString("unit");			           
			            ClientPurchase p=new ClientPurchase(date,product_name,price,quantity,unit);
			            list.add(p);
			        }
			}
		   

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ClientPayment getPayment(String username) {
		//System.out.print(username);
		 ClientPayment payment = null;
		try {
			Statement stmt = (Statement) con.createStatement();
			String sql1=null;
			String sql2=null;
			String name=null;
			int id=0;
			
			if(username!=null) {
				sql1="SELECT * FROM customer WHERE c_username ='" + username +"'";
				ResultSet r = stmt.executeQuery(sql1);
				 while (r.next()) {		           
			            name= r.getString("c_name");
			            id=r.getInt("c_id");
				 }	 // Check if a month is selected or not
				 sql2="SELECT * FROM payment WHERE customer_id ='" + id + "'";

				
					ResultSet rs = stmt.executeQuery(sql2);
			        while (rs.next()) {
			            int customer_id=rs.getInt("customer_id");
			            double total=rs.getDouble("total");
			            double paid_amount=rs.getDouble("paid_amount");
			            String paid_status=rs.getString("paid_status");
			           payment = new ClientPayment(customer_id,total, paid_amount,paid_status);
			       
			        }
			}
		}catch (Exception e) {
	        e.printStackTrace();
	    }
	
		 return payment;
	}
}
