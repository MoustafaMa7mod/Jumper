package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class favorite {

	int tripID , userID;

	public int getTripID() {
		return tripID;
	}

	public void setTripID(int tripID) {
		this.tripID = tripID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public favorite(int tripID, int userID) {
		
		this.tripID = tripID;
		this.userID = userID;
	}
public favorite() {
		
		
	}
public static void addOrRemoveFavTrip(int tripID , int userID) throws SQLException
{
	Connection con = DBConnection.getActiveConnection();
	String sql ="select * from favorite_trips where tripID = "+tripID+ "  and  userID = "+userID+ " ;" ;
	PreparedStatement stmt = con.prepareStatement(sql);
	ResultSet rs = stmt.executeQuery();
	
	if (rs.next())
	{
		sql ="delete from favorite_trips where tripID = "+tripID+ "  and  userID = "+userID+ " ;" ;
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		return;
	}
	sql ="insert into favorite_trips values( "+userID+" , "+tripID+" )" ;
	stmt = con.prepareStatement(sql);
	stmt.executeUpdate();
	return;


}
public static ArrayList<Trip> wishList(int userID) throws SQLException
{
	ArrayList<Trip>trip=new ArrayList<>();
	ArrayList<Trip>favTrips=new ArrayList<>();
	ArrayList<Integer>ID=new ArrayList<>();
	Connection con = DBConnection.getActiveConnection();
	String sql="select tripID from favorite_trips where userID = "+userID+ " ;";
	PreparedStatement stmt = con.prepareStatement(sql);
	ResultSet rs = stmt.executeQuery();
	while(rs.next())
	{
		ID.add(rs.getInt("tripID"));
	}
	sql="select * from trips order by name  ;";
	stmt = con.prepareStatement(sql);
	rs = stmt.executeQuery();
	while (rs.next())
	{
		Trip t=new Trip();
		t.ID=rs.getInt("ID");
		t.companyID=rs.getInt("companyID");
		t.tripName=rs.getString("name");
		t.companyName=rs.getString("companyName");
		t.locationCompany=rs.getString("companyLocation");
		t.budget=rs.getDouble("budget");
		t.programme=rs.getString("programme");
		t.duration=rs.getInt("duration");
		t.startDate=rs.getDate("startDate");
		t.endDate=rs.getDate("endDate");
		t.rate=rs.getDouble("rate");
		t.category=rs.getString("category");
		t.transportation=rs.getString("transportation");
		trip.add(t);
	}
	
	for(int i=0;i<trip.size();i++)
	{
		for(int y=0;y<ID.size();y++)
		{
			if(trip.get(i).ID==ID.get(y))
			{
				favTrips.add(trip.get(i));
			}
			
			
		}
		
	}
	return favTrips;
	
	
	
	
	
	
	


}
	
	
	
	
	
}
