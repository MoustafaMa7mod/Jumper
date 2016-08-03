package com.models;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Statement;

public class Notification {
	
	private String description ;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	} 
	
	
	public static void notify (int tripID , String text) throws SQLException
	{
	 
		DateFormat dateFormatDay = new SimpleDateFormat("dd/MM");
		DateFormat dateFormatTime = new SimpleDateFormat("HH/mm");
		
		Date Day = new Date();
		Date time = new Date();
		
		String date = "Day:" + dateFormatDay.format(Day) + ",Time:" + dateFormatTime.format(time)  ;
		
		ArrayList <Integer> IDs = new ArrayList<>() ; 
		String name = "" , tripName = "" ; 
		Connection con = DBConnection.getActiveConnection();
		String sql ="select userID from favorite_trips where tripID  = " + tripID  +";";
		PreparedStatement stmt= con.prepareStatement(sql) ;
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			IDs.add(rs.getInt("userID")) ; 
			
		}
		
		sql ="select companyName from trips where ID  = " + tripID  +";" ;
		stmt= con.prepareStatement(sql) ;
		rs = stmt.executeQuery();
			
		if (rs.next()) {
			name = rs.getString("companyName") ; 	
			}
		

		sql ="select name from trips where ID  = " + tripID  +";" ;
		stmt= con.prepareStatement(sql) ;
		rs = stmt.executeQuery();
			
		if (rs.next()) {
			tripName = rs.getString("name") ; 	
			}
		
		
		for (int i = 0 ; i < IDs.size() ; i++)
		{
			sql =" Insert into notification (tripID , userID , companyName , notifyText , notifyTime) values"
					+ " ("+tripID+","+IDs.get(i)+",'"+name+"', '"+name+" "+text+" of "+ tripName+" trip " +"' ,'"+date+"');"; 
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
		} 
	}
	
	

}
