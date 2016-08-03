package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class Feedback {

	public static void feedBack (String email_user , String feedBack) throws SQLException
	{	
        Connection con = DBConnection.getActiveConnection() ; 
		
	    String sql = "Select ID from user where 'email' = " + "'" + email_user +"';" ;
		PreparedStatement stmt = null;
		
        ResultSet rs = stmt.executeQuery();
		
		stmt = con.prepareStatement(sql);
		int ID_user = 0 ;
		
		if (rs.next())
		{
			ID_user = rs.getInt("ID") ; 
		}
		
		sql = "insert into feedback (feedBack , userID) values ( " + "'" +feedBack+"'" + "," + ID_user +");" ;
	    
		stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);	
		stmt.executeUpdate();
		rs = stmt.getGeneratedKeys();
	
	}

	
}
