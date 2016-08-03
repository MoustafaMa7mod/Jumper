package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Rate {
	
	
	int  rate;

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	public static void tripUserRat(int userID,int tripID, int rate) throws SQLException

	{
		Connection con = DBConnection.getActiveConnection();
		String sql="select * from trip_rate where userID = "+userID + "  and  tripID = " +tripID + "  ;";
		PreparedStatement stmt=con.prepareStatement(sql);
		ResultSet rs=stmt.executeQuery();
		if(rs.next())
		{
			sql="update trip_rate set rate = "+rate +" where userID = "+userID + "  and  tripID = " +tripID + "  ;";
			stmt=con.prepareStatement(sql);
			stmt.executeUpdate();	
			return ;
		}
		sql="insert into trip_rate values ( "+userID+" , "+tripID+" , "+rate+" );";
		stmt=con.prepareStatement(sql);
		stmt.executeUpdate();	
		
	}
	
	
	
	public static void companyUserRate(int userID , int companyID , int rate) throws SQLException
	{
		Connection con = DBConnection.getActiveConnection();
		String sql="select * from company_rate where companyID = " +companyID +" and userID = " + userID + " ; " ;
	
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
		{
		  
			sql = "Update company_rate set rate = " + rate + " where userID = " + userID + " and companyID = " + companyID + " ; "  ;
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
			return ; 
		}
		
		 sql = "insert into company_rate "
				 	+ " ( userID , companyID , rate ) "
				 	+ " values ( " + userID + " , " + companyID + " , " + rate  + " ) ; " ;
				 
				stmt = con.prepareStatement(sql);
				stmt.executeUpdate();
	}
	
}
