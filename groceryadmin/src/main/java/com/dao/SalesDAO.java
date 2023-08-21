package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SalesDAO {
	private Connection con;

	public SalesDAO(Connection con) {
		super();
		this.con = con;
	}
	public double getMonthlySalesTotal(int year, int month) {
        double total = 0.0;
        
        
        try {
        	String query = "SELECT SUM(amount) AS total FROM purchase WHERE YEAR(purchase_date) = ? AND MONTH(purchase_date) = ?";
             PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, year);
            stmt.setInt(2, month);
          
            ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    total = rs.getDouble("total");
                }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return total;
    }
}

