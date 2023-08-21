package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.Customer;
import com.entity.Payment;
import com.mysql.jdbc.PreparedStatement;

public class PaymentDAO {
	private Connection con;

	public PaymentDAO(Connection con) {
		super();
		this.con = con;
	}
	public void insertPayment(Payment payment) {
	
		try {
			String sql="insert into payment(customer_name,total,paid_status,customer_id) values(?,?,?,?)";
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, payment.getCustomer_name());
			ps.setDouble(2, payment.getTotal());
			ps.setString(3, payment.getStatus());
			ps.setInt(4,payment.getCustomer_id());
			ps.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void calculateRemaningAmt(int id) {
		double total=0.0;
		double paid_amount=0.0;
		try {
			String sql="select total,paid_amount from payment where payment_id=?";
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
            	paid_amount = rs.getDouble("paid_amount");
            	total = rs.getDouble("total");
            }
        }catch(Exception e) {
			e.printStackTrace();
		}
		double remaining_amount=total-paid_amount;
		try {
			String sql1="update payment set total=? where payment_id=?";
			PreparedStatement ps1=(PreparedStatement) con.prepareStatement(sql1);
			
			ps1.setDouble(1, remaining_amount);
		
			ps1.setInt(2, id);
			int i=ps1.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Payment> getAllPayment(){
		
		List<Payment> list=new ArrayList<Payment>();
		Payment pay=null;
		try {
			String sql="select * from payment";
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				pay=new Payment();
				pay.setPayment_id(rs.getInt(1));
				pay.setCustomer_name(rs.getString(2));
				
				//double paid_amount = rs.getDouble(4);
            //	double total = rs.getDouble(3);
            	//double remaining_amount=total-paid_amount;
            	
				pay.setTotal(rs.getDouble(3));
				pay.setPaid_amount(rs.getDouble(4));
				pay.setStatus(rs.getString(5));
				list.add(pay);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public double getPaidAmountById(int Payment_id){
        double paid_amount = 0.0;
        try {
        String query = "SELECT paid_amount FROM payment WHERE payment_id = ?";
        PreparedStatement statement = (PreparedStatement) con.prepareStatement(query);
         statement.setInt(1, Payment_id);

           ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                	paid_amount = resultSet.getDouble("paid_amount");
                }
            }catch(Exception e) {
    			e.printStackTrace();
    		}
        
        return paid_amount;
}
	public Payment getPaymentById(int id) {
		Payment pay=null;
		try {
			String sql="select * from payment where payment_id=?";
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				pay=new Payment();
				pay.setCustomer_name(rs.getString(2));
				pay.setTotal(rs.getDouble(3));
				pay.setPaid_amount(rs.getDouble(4));	
				pay.setStatus(rs.getString(5));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return pay;
	}

	public boolean updatePayment(Payment payment) {
		boolean f=false;
		try {
			String sql="update payment set total=?,paid_amount=?,paid_status=? where payment_id=?";
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
			double paid_amount = payment.getPaid_amount();
        	double total = payment.getTotal();
        	double remaining_amount=total-paid_amount;
			ps.setDouble(1, remaining_amount);
			ps.setDouble(2, payment.getPaid_amount());
			ps.setString(3, payment.getStatus());
			ps.setInt(4, payment.getPayment_id());
			int i=ps.executeUpdate();
			
			if(i==1)
			{
				f=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
}
	
	public int getCompletedPayment() {
		
        try {
             Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS payment_count FROM payment WHERE paid_status = 'Paid'");
            if (resultSet.next()) {
                return resultSet.getInt("payment_count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Return 0 if an error occurs or no count is retrieved
	}
	
	public boolean checkCustomer(int customer_id) {
		boolean f=false;
		try {
		String sql="select * from payment where customer_id=?";
		PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
		
		ps.setInt(1, customer_id);
		
		ResultSet rs=ps.executeQuery();
		
		while(rs.next())
		{
			
			f=true;
			//System.out.print(f);
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
		//System.out.print(f);
	return f;
	}
	
	public void updateTotal(double grandTotal,int customer_id,String status) {
		
		
		try {
			
			
			String sql2="update payment set total=?,paid_status=? where customer_id=?";
			PreparedStatement ps2=(PreparedStatement) con.prepareStatement(sql2);
			
			ps2.setDouble(1, grandTotal);
			ps2.setString(2, status);
			ps2.setInt(3, customer_id);
			ps2.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	
}
	public double getTotal(int customer_id) {
		double total=0;
		try {
			String  sql1="select total from payment where customer_id=?";
			PreparedStatement ps1=(PreparedStatement) con.prepareStatement(sql1);			
			//ps1.setDouble(1, payment.getTotal());
			ps1.setInt(1, customer_id);
			
			ResultSet rs=ps1.executeQuery();
			while(rs.next()) {
				total=rs.getDouble(1);				
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return total;
	}
}
