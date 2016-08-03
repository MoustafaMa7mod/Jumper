package com.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Transportation {
	
	
	public static String addTransportation(String transportation) throws SQLException
	{
		Connection conn =  (Connection) DBConnection.getActiveConnection();
	    String sql = " select * from transportation where transportation = " + " ' "+ transportation + " ' " +";";
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql); 
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
			   return "Exists Before" ; 
			}
			
			sql = "insert into transportation ( transportation ) values ( " + " ' " + transportation  + " ' " +" ); " ;
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.executeUpdate();
		return "Done" ; 	
	}
	
	

}
