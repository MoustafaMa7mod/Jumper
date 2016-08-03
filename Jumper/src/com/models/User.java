package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.User;

import com.models.DBConnection;
import com.models.User;
import com.mysql.jdbc.Statement;

public class User {
	private String fullName ; 
	private String phoneNumber ; 
	private String email ;
	private String password ;
	private Integer ID; 
	

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer ID) {
		this.ID = ID;
	}
	
	
	public static User signup (String fullName , String phoneNumber , String emailAddress , String password) throws SQLException
	{
		Connection con = DBConnection.getActiveConnection() ; 
		
		String sql ="select email from user where email =  '"+emailAddress+"' ;"; 
		
		PreparedStatement stmt;
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			User user = new User();
			user.email = "";
			return user;
		}
		//
		 sql = "insert into user (fullName , Number , email , userPassword) values ( " + "'"+fullName+"'" + "," +"'"+phoneNumber+"'"+ ","+ "'"+emailAddress+"'"+ "," +"'"+password+"'" + ");" ;
		
		 stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.executeUpdate();
		  rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			User user = new User();
			user.ID = rs.getInt(1);
			user.email = emailAddress;
			user.phoneNumber = phoneNumber;
			user.password = password;
			user.fullName = fullName;
			
			return user;
		}
		
		User user = new User();
		user.email = "";
		
		return user;	 
   }
	
	public static User login(String email, String pass) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Select * from user where `email` = ? and `userPassword` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.ID=rs.getInt(1);
				user.email = rs.getString("email");
				user.password = rs.getString("userPassword");
				user.fullName = rs.getString("fullName");
				user.phoneNumber = rs.getString("Number");
				return user;
			}
			User user = new User();
			user.email = "";
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static boolean EditName (int userID , String newName,String password) 
	{
	   ////
	    Connection conn = DBConnection.getActiveConnection();
	    String sql = "Select * from user where ID = "+userID+"  and  userPassword='"+password+"';";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();;
			
		    
		    if(rs.next()){
		    	sql = "Update user set fullName = " + "'"+newName+"'  where ID =  "+userID+" ;" ;
				try {
					stmt = conn.prepareStatement(sql);
					stmt.executeUpdate();
					return true;
				} catch (SQLException e1) {
				
				}
		    }
		} catch (SQLException e) {
			
		return false;
			
			
		}
		return false;
		
		
		
			
		
	}

	
	
	
	
	public static boolean EditPhoneNumber (int userID , String newNumber,String password) throws SQLException
	{
	    Connection conn = DBConnection.getActiveConnection();
	    String sql = "Select * from user where ID= "+userID+"   and  userPassword='"+password+"';";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();;
			
		    
		    if(rs.next()){
		    	sql = "Update user set Number = " + "'"+newNumber+"'  where ID=  "+userID+";" ;
				try {
					stmt = conn.prepareStatement(sql);
					stmt.executeUpdate();
					return true;
				} catch (SQLException e1) {
				
				}
		    }
		} catch (SQLException e) {
			
		return false;
			
			
		}
		return false;
	}
	
	public static boolean EditPassowrd (int userID ,String oldPassword, String newPassword) throws SQLException
	{
		 Connection conn = DBConnection.getActiveConnection();
		    String sql = "Select * from user where ID = "+userID+"  and  userPassword='"+oldPassword+"';";
			PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();;
				
			    
			    if(rs.next()){
			    	sql = "Update user set Number = " + "'"+newPassword+"'  where ID = "+userID+" ;" ;
					try {
						stmt = conn.prepareStatement(sql);
						stmt.executeUpdate();
						return true;
					} catch (SQLException e1) {
					
					}
			    }
			} catch (SQLException e) {
				
			return false;
				
				
			}
			return false;
	}
  
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
