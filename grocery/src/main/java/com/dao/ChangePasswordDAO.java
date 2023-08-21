package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.entity.ChangePassword;

public class ChangePasswordDAO {
	private Connection con;

	public ChangePasswordDAO(Connection con) {
		super();
		this.con = con;
	}
	
	public int changePassword(String username1, String newpassword1) {
		int i=0;
		try {
			String sql="update customer set c_password=? where c_username=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,newpassword1);
			ps.setString(2,username1);
			i=ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return i;
		
	}
}
