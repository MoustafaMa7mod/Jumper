package Sort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.DBConnection;
import com.models.Trip;

public class SortStrategy {

	
	public static ArrayList<Trip> sortByrate(int userID) throws SQLException
	{
		
		ArrayList<Trip>trip=new ArrayList<>();
		ArrayList<Integer>tripID=new ArrayList<>();
		Connection con = DBConnection.getActiveConnection();
		String sql="select * from trips order by rate  desc ;";
		PreparedStatement stmt=con.prepareStatement(sql);
		ResultSet rs=stmt.executeQuery();
		while(rs.next())
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
		sql="select tripID from favorite_trips where userID =  "+userID+ "  ;";
		stmt=con.prepareStatement(sql);
		rs=stmt.executeQuery();
		while(rs.next())
		{
			
		tripID.add(rs.getInt("tripID"))	;
			
		}
		
		for(int i=0;i<trip.size();i++)
		{
			for(int y=0;y<tripID.size();y++)
			{
				if(trip.get(i).ID==tripID.get(y))
				{
					trip.get(i).fav=1;
					
				}
				
				
			}
			
		}
		return trip;
		
	}
	
	
	public static ArrayList<Trip> sortByBudget(int userID) throws SQLException
	{
		
		ArrayList<Trip>trip=new ArrayList<>();
		ArrayList<Integer>tripID=new ArrayList<>();
		Connection con = DBConnection.getActiveConnection();
		String sql="select * from trips order by budget ; ";
		PreparedStatement stmt=con.prepareStatement(sql);
		ResultSet rs=stmt.executeQuery();
		while(rs.next())
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
		sql="select tripID from favorite_trips where userID =  "+userID+ "  ;";
		stmt=con.prepareStatement(sql);
		rs=stmt.executeQuery();
		while(rs.next())
		{
			
		tripID.add(rs.getInt("tripID"))	;
			
		}
		
		for(int i=0;i<trip.size();i++)
		{
			for(int y=0;y<tripID.size();y++)
			{
				if(trip.get(i).ID==tripID.get(y))
				{
					trip.get(i).fav=1;
					
				}
				
				
			}
			
		}
		return trip;
		
	}
	
	public static ArrayList<Trip> sortByDate(int userID) throws SQLException
	{
		
		ArrayList<Trip>trip=new ArrayList<>();
		ArrayList<Integer>tripID=new ArrayList<>();
		Connection con = DBConnection.getActiveConnection();
		String sql="select * from trips order by startDate ; ";
		PreparedStatement stmt=con.prepareStatement(sql);
		ResultSet rs=stmt.executeQuery();
		while(rs.next())
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
		sql="select tripID from favorite_trips where userID =  "+userID+ "  ;";
		stmt=con.prepareStatement(sql);
		rs=stmt.executeQuery();
		while(rs.next())
		{
			
		tripID.add(rs.getInt("tripID"))	;
			
		}
		
		for(int i=0;i<trip.size();i++)
		{
			for(int y=0;y<tripID.size();y++)
			{
				if(trip.get(i).ID==tripID.get(y))
				{
					trip.get(i).fav=1;
					
				}
				
				
			}
			
		}
		return trip;
		
	}
	
	public static ArrayList<Trip> sortByDuration(int userID) throws SQLException
	{
		
		ArrayList<Trip>trip=new ArrayList<>();
		ArrayList<Integer>tripID=new ArrayList<>();
		Connection con = DBConnection.getActiveConnection();
		String sql="select * from trips order by duration ; ";
		PreparedStatement stmt=con.prepareStatement(sql);
		ResultSet rs=stmt.executeQuery();
		while(rs.next())
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
		sql="select tripID from favorite_trips where userID =  "+userID+ "  ;";
		stmt=con.prepareStatement(sql);
		rs=stmt.executeQuery();
		while(rs.next())
		{
			
		tripID.add(rs.getInt("tripID"))	;
			
		}
		
		for(int i=0;i<trip.size();i++)
		{
			for(int y=0;y<tripID.size();y++)
			{
				if(trip.get(i).ID==tripID.get(y))
				{
					trip.get(i).fav=1;
					
				}
				
				
			}
			
		}
		return trip;
		
	}
		
	
	
}
