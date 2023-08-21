package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.entity.ClientPurchase;
import com.mysql.jdbc.Statement;
public class ChartDAO {
	private Connection con;

	public ChartDAO(Connection con) {
		super();
		this.con = con;
	}
	public double getMonthlyPurchaseTotal(String username,int year, int month) {
        double total = 0.0;
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
				 sql2="SELECT SUM(amount) AS total FROM purchase WHERE customer_name =? AND YEAR(purchase_date) = ? AND MONTH(purchase_date) = ?";
				 PreparedStatement pstmt = con.prepareStatement(sql2);
				 	pstmt.setString(1, name);
		            pstmt.setInt(2, year);
		            pstmt.setInt(3, month);
		          
		            ResultSet rs = pstmt.executeQuery();
		                if (rs.next()) {
		                    total = rs.getDouble("total");
		                }
					
			        }

	 } catch (Exception e) {
         e.printStackTrace();
     }
     
     return total;
    }
}
