package com.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.ClientNotice;
import com.mysql.jdbc.PreparedStatement;
public class ClientNoticeDAO {
private Connection con;
	
	public ClientNoticeDAO(Connection con) {
		super();
		this.con = con;
	}
	 // get all notices from the database
    public List< ClientNotice> getAllNotices() {
        List< ClientNotice> list = new ArrayList< ClientNotice>();
        try {
            String sql="SELECT * FROM notice ORDER BY n_createdDate DESC";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	 ClientNotice notice = new  ClientNotice();	
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
}
