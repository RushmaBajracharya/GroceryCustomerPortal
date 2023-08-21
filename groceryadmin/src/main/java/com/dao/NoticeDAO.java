package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import com.entity.Notice;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class NoticeDAO {
	private Connection con;
	
	public NoticeDAO(Connection con) {
		super();
		this.con = con;
	}
	// add a new notice to the database
    public boolean addNotice(Notice notice) {
        boolean success = false;
        try {
            String sql = "INSERT INTO notice (n_content) VALUES (?)";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, notice.getContent());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                success = true;
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
	 // get all notices from the database
	    public List<Notice> getAllNotices() {
	        List<Notice> list = new ArrayList<Notice>();
	        try {
	            String sql="SELECT * FROM notice ORDER BY n_createdDate DESC";
	            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
	            
	            ResultSet rs = stmt.executeQuery(sql);
	            while (rs.next()) {
	                Notice notice = new Notice();	
	                notice.setId(rs.getInt(1));
	                notice.setCreatedAt(rs.getString(2));
	                notice.setContent(rs.getString(3));                
	                list.add(notice);
	            }
	            rs.close();
	            stmt.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }
	    
	    
	    public boolean deleteNotice(int id){
	    	boolean success=false;
	    	try {
		        String sql = "DELETE FROM notice WHERE n_id = ?";
		        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql) ;
		            ps.setInt(1, id);
		            int i = ps.executeUpdate();
		            if(i>0) {
		            	success=true;
		            }
	    	}catch(Exception e) {
	    		e.printStackTrace();
	        }
			return success;
	    }
}
