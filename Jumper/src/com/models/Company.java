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
public class Company {
	private Integer ID ; 
	private String companyName ; 
	private String description ; 
	private String website ; 
	private Double longtude ; 
	private Double latitude ; 
	private Double rate ;
	private String phoneNumber ; 
	private String location ; 
	private String password ;
	private String email;
	
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Double getLongtude() {
		return longtude;
	}
	public void setLongtude(Double longtude) {
		this.longtude = longtude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public static Company getCompany (int companyID) throws SQLException 
	{
		Company company = new Company() ;
		Connection con = DBConnection.getActiveConnection();
		String sql ="select * from company where ID = " + companyID + " ; " ;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next())
		{
			 
			company.ID = rs.getInt("ID") ; 
			company.companyName = rs.getString("companyName") ;
			company.description = rs.getString("description") ;
			company.website = rs.getString("website") ;
			company.latitude = rs.getDouble("latitude") ;
			company.longtude = rs.getDouble("longtude") ;
			company.rate = rs.getDouble("rate") ;
			
		
		}
		
		return company ; 	
	}
	
	public static ArrayList <String> getCompanyNumber (int companyID) throws SQLException
	{
		ArrayList <String> arr = new ArrayList<>() ; 
		Connection con = DBConnection.getActiveConnection();
		String sql ="select Number from company_number where companyID = " + companyID + " ; " ;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next())
		{
			arr.add(rs.getString("Number")) ; 
			
		}
		
		return arr ;
	}
	
	public static ArrayList <String> getCompanyEmail (int companyID) throws SQLException
	{
		ArrayList <String> arr = new ArrayList<>() ; 
		Connection con = DBConnection.getActiveConnection();
		String sql ="select email from company_email where companyID = " + companyID + " ; " ;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next())
		{
			arr.add(rs.getString("email")) ; 
			
		}
		
		return arr ;
	}
	
	
	
	
	
	public static ArrayList <String> getCompanyLocation (int companyID) throws SQLException
	{
		ArrayList <String> arr = new ArrayList<>() ; 
		Connection con = DBConnection.getActiveConnection();
		String sql ="select location from company_location where companyID = " + companyID + " ; " ;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next())
		{
			arr.add(rs.getString("location")) ; 
			
		}
		
		return arr ;
		
	}
	
	public static Company AddCompany(String companyName ,String password , String email , String description , String website , String latitude , String longtude ) throws SQLException
	{
		Connection con = DBConnection.getActiveConnection();
		String sql ="select longtude ,latitude from company where longtude="+longtude+" and  latitude="+latitude+";";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			Company company = new Company();
			company.companyName="" ;
			return company;
		}

		 sql =" Insert into company (companyName , password , description , website , longtude , latitude  ) values ('"+companyName+"','"+password+"','"+description+"','"+website+"',"+longtude+","+latitude+");"; 
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				Company company = new Company();
				company.ID = rs.getInt(1);
				company.companyName = companyName;
				company.description=description;
				company.website=website;
				company.latitude = Double.parseDouble(latitude);
				company.longtude = Double.parseDouble(longtude);
				company.password=password;
				sql =" Insert into company_email values ( "+company.ID +" , '"+email+"' );"; 
				stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.executeUpdate();
				company.email=email;
				return company;
			}
			Company company = new Company();
			company.companyName="" ; 
		
	    return company ; 
	}
	
	public static String AddLocation ( int companyID , String location) throws SQLException
	{
		Connection con = DBConnection.getActiveConnection();
		
		
		String sql= "select companyID from company_location where companyID= "+companyID+ " and location= '"+location+"';";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(); 
		if(rs.next())
			return "existed";
		
		
		sql = "insert into company_location (companyID , location) values ("+ companyID+ " , " + "'"+location+"'"+");";
		stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		 stmt.executeUpdate(sql);
		return "added";
	
		
		
	}
	
	public static String RemoveCompany (int companyID) throws SQLException
	{
        Connection con = DBConnection.getActiveConnection();
        
			String sql = "delete from company_location where companyID ="+ companyID + ";" ; 
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.executeUpdate();
			
			sql = "delete from company where ID = "+companyID+" ;" ; 
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
			return "deleted";
		
		
		
	}
	public static String RemoveLocation (int companyID,String location) throws SQLException
	{
        Connection con = DBConnection.getActiveConnection();
      
			 
			String sql = "delete from company_location where companyID = "+companyID+ "   and location='"+location+"' ;" ; 
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			///////////////////////////
			sql="select * from company_location where  companyID = "+companyID+ ";";
			stmt = con.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			if(!res.next())
			{
				sql = "delete from company where ID = "+companyID+" ;" ; 
				stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt = con.prepareStatement(sql);
				stmt.executeUpdate();
				
				
			}
			return "deleted";
		
		
	}
	
	
	
	
	public static String UpdateLocation(int companyID, String oldLocation,String newLocation) throws SQLException
	{
        Connection con = DBConnection.getActiveConnection();
        
		
			String sql="select location from company_location where companyID="+companyID+"  and location='"+oldLocation+"';";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			if(res.next())
			{
			sql = "Update company_location set location = " + "'"+newLocation+"'  where companyID="+companyID+"  and location='"+oldLocation+"';" ;
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
			return "updated";
			}
			else
			return "locationNotFound";
		
		
	 
	}
	public static String UpdateCompany(int companyID , String newCompanyName) throws SQLException
	{
        Connection con = DBConnection.getActiveConnection();
       
			String sql = "Update company set companyName = " + "'"+newCompanyName+"'  where  ID = "+companyID+" ;" ;
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
			return "updated";
		
	}
	
	public static String AddNumber (int companyID , String number) throws SQLException
	{
		Connection con = DBConnection.getActiveConnection();
       
			
			String sql="select * from company_number where companyID = "+companyID+ "  and Number = '"+number+"' ;";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(); 
			if(!rs.next()){
			sql = "insert into company_number (companyID , Number) values (" + companyID + "," + " '"+number+"'"+");";
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate(sql);
			return "Added";
			}
			return "Existed before";
		
		
	}
	
	public static String RemoveNumber (int companyID, String number) throws SQLException
	{
        Connection con = DBConnection.getActiveConnection();
       
			String sql ="select * from company_number where companyID = "+companyID+ " and Number = '"+number+"';";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				sql = "delete from company_number where companyID ="+ companyID + "  and Number = '"+number+"';" ; 
				stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.executeUpdate();
				return "Removed";
			}
			return "Number not found";
		
		
	}

	public static String UpdateNumber(int companyID , String oldNumber,String newNumber) throws SQLException
	{
        Connection con = DBConnection.getActiveConnection();
       
			String  sql = "select * from company_number where Number = "+ "'"+oldNumber+"' ;" ; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				sql = "Update company_number set  Number = " + "'"+ newNumber +"'   where Number = '"+oldNumber+"' ;" ;
				stmt = con.prepareStatement(sql);
				stmt.executeUpdate();
				return "Updaated";
			}
			return "Number not found ";
		
	}

	public static String AddEmail ( int companyID, String email) throws SQLException
	{
		Connection con = DBConnection.getActiveConnection();
      
			String sql= "select * from company_email where companyID = "+companyID+"  and email = '"+email+"';";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(); 
			if(!rs.next())
			{
				sql = "insert into company_email (companyID , email) values (" + companyID + "," + "'"+ email +"'"+");";
				stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.executeUpdate(sql);
				return "Added";
			}
			return "Existed before";
	
		
		
			
		
	}
	
	public static String RemoveEmail (int companyID,String email) throws SQLException
	{
        Connection con = DBConnection.getActiveConnection();
        
			
			String sql = "select * from company_email where companyID = "+ companyID +"  and  email = '"+email+"';" ; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				sql = "delete from company_email where companyID ="+ companyID +"  and  email = '"+email+"';" ; 
				stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.executeUpdate();
				return "Removed";
			}
			return "Email not found";
		
		
	}

	public static String UpdateEmail(int companyID , String oldEmail ,String newEmail) throws SQLException
	{
        Connection con = DBConnection.getActiveConnection();
      
			String sql = "select * from company_email where companyID = " +companyID +"  and email = '"+oldEmail+"';" ; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				sql = "Update company_email set  email = '"+newEmail+"'   where email = '"+oldEmail+"';" ;
				stmt = con.prepareStatement(sql);
				stmt.executeUpdate();
				return "Updated";
			}
			return "Email not found";
		
	}
	
	public static String setCompanyRate(int userID,int companyID,int rate) throws SQLException
	{
		Connection con = DBConnection.getActiveConnection();
		String sql = "select * from company_rate where userID = " + userID + "  and companyID = "+companyID+" ;";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
		{
			sql = "update company_rate set rate = "+rate+" where userID = " + userID + "  and companyID = "+companyID+" ;";
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		}
		else
		{
			sql = "insert into company_rate values ( "+userID+" , "+companyID+" , "+rate+" );";
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		}
		
		sql = "select count(*) ,sum(rate) from company_rate where  companyID = "+companyID+" ;";
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
	if(rs.next())
	{
		int noOfUsers=rs.getInt("count(*)");
		int summationOfRate=rs.getInt("sum(rate)");
		double finalRate=((double)summationOfRate/noOfUsers);
		sql = "update company set rate = "+finalRate+" where ID = "+companyID+" ;";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

	}
	return "Done";	
		
	}
	
	
	
	
	
	
}
