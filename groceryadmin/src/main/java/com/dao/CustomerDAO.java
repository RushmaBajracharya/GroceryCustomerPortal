package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;


import com.entity.Customer;
import com.mysql.jdbc.PreparedStatement;

public class CustomerDAO {
	private Connection con;

	public CustomerDAO(Connection con) {
		super();
		this.con = con;
	}
	public boolean addCustomer(Customer customer) {
		boolean f=false;
		try {
			String sql="insert into customer(c_name,c_username,c_password,c_email,c_contact) values(?,?,?,?,?)";
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getUsername());
			ps.setString(3, customer.getPassword());
			ps.setString(4, customer.getEmail());
			ps.setString(5, customer.getContact());
			
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
	
	public List<Customer> getAllCustomer(){
		List<Customer> list=new ArrayList<Customer>();
		Customer c=null;
		try {
			String sql="select * from customer";
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				c=new Customer();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setDate(rs.getString(3));
				c.setUsername(rs.getString(4));
				c.setPassword(rs.getString(5));
				c.setEmail(rs.getString(6));
				c.setContact(rs.getString(7));
				list.add(c);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Customer getCustomerById(int id) {
		Customer c=null;
		try {
			String sql="select * from customer where c_id=?";
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				c=new Customer();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setDate(rs.getString(3));
				c.setUsername(rs.getString(4));
				c.setPassword(rs.getString(5));
				c.setEmail(rs.getString(6));
				c.setContact(rs.getString(7));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return c;
	}
	
	public boolean deleteCustomer(int id) {
		boolean f=false;
		try {
			String sql="delete from customer where c_id=?";
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1,id);
			int i=ps.executeUpdate();
			if(i==1) {
				f=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	public boolean updateCustomer(Customer customer) {
		boolean f=false;
		try {
			String sql="update customer set c_name=?,c_username=?,c_password=?,c_email=?,c_contact=? where c_id=?";
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getUsername());
			ps.setString(3, customer.getPassword());
			ps.setString(4, customer.getEmail());
			ps.setString(5, customer.getContact());
			ps.setInt(6, customer.getId());
			
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
	public String getCustomerNameById(int customer_id) {
		String customer_name =null;
		try {
			
			String sql="select c_name from customer where c_id=?";
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, customer_id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				customer_name=rs.getString("c_name");
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return customer_name;
		
	}
	
	public int getCustomerCount() {
		
        try {
             Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM customer");
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Return 0 if an error occurs or no count is retrieved
	}
	
}