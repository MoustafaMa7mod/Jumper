package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Category {
	
	
	String category ;

	

	
	public String getCategory() {
		return category;
	}




	public void setCategory(String category) {
		this.category = category;
	}




	public static String addCategory(String category) throws SQLException
	{
		Connection conn = DBConnection.getActiveConnection();
	    String sql = " select * from category where category = " + " ' "+ category + " ' " +";";
		PreparedStatement stmt = conn.prepareStatement(sql); 
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
			   return "Exists Before" ; 
			}
			
			sql = "insert into category ( category ) values ( " + " ' " + category  + " ' " +" ); " ;
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		return "Done" ; 	
	}

}
