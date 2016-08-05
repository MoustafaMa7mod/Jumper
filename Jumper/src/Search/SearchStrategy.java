package Search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.models.Company;
import com.models.DBConnection;
import com.models.Trip;

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
import com.models.Trip;
import java.util.ArrayList;

public class SearchStrategy  {

	
	public static ArrayList<Trip> searchDuration(int duration) {
		ArrayList<Trip>trip=new ArrayList<>();
		 
			try {
				Connection con = DBConnection.getActiveConnection();
		        String sql = "select * from trips where duration = " + duration + ";" ; 
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
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
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return trip;
	}

	
	
	
	
	
	
	
	public static ArrayList<Trip> searchTime(Date StartDate) {
		ArrayList<Trip>trip=new ArrayList<>();
		 
		try {
			Connection con = DBConnection.getActiveConnection();
	        String sql = "select * from trips where startDate = " + StartDate + ";" ; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
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
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trip;
		
	}

	
	public static ArrayList<Trip> searchBudget(double budget) throws SQLException {
		ArrayList<Trip>trip=new ArrayList<>();
		double low=(budget-(budget/2));
		double high=(budget+(budget/2));
		 
			Connection con = DBConnection.getActiveConnection();
	        String sql = "select * from trips where budget = " + budget + ";" ; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
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
		
		sql = "select * from trips where budget  >= "+low+"  and budget <= "+high+" and budget <>  "+budget +" ;" ; 
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
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
		
		
		return trip;
	}

	

	
	public  static ArrayList<Trip> searchTrip(String Name) {
		ArrayList<Trip>trip=new ArrayList<>();
		 
		try {
			Connection con = DBConnection.getActiveConnection();
	        String sql = "select * from trips where name = '"+Name+"';" ; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
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
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trip;
	}

	
	public static ArrayList<Trip> searchCompany(String Name) {
		ArrayList<Trip>trip=new ArrayList<>();
		ArrayList<Integer>IDs=new ArrayList<>();
		int companyID=0;
		try {
			Connection con = DBConnection.getActiveConnection();
	        String sql = "select ID from company where companyName = '"+Name+"';" ; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
				companyID=rs.getInt("ID");
				sql = "select tripID from company_trips where companyID = "+companyID+" ;" ; 
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next())
				{
					IDs.add(rs.getInt("tripID"));
				}
				
				for(int i=0;i<IDs.size();i++)
				{
					sql = "select * from trips where ID = "+IDs.get(i)+" ;" ; 
					stmt = con.prepareStatement(sql);
					rs = stmt.executeQuery();
					if(rs.next()){
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
				}
			}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return trip;
	}
public static ArrayList<Trip> searchTransportation(String trans,int userID) throws SQLException
{
ArrayList<Trip>trip=new ArrayList<>();
ArrayList<Integer>tripID=new ArrayList<>();
Connection con = DBConnection.getActiveConnection();
String sql = "select * from trips where transportation = '"+trans+"'  order by  rate desc ;" ; 
PreparedStatement stmt = con.prepareStatement(sql);
ResultSet rs = stmt.executeQuery();
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
sql = "select tripID from favorite_trips where userID = "+userID+"  ;"  ; 
stmt = con.prepareStatement(sql);
rs = stmt.executeQuery();
while(rs.next())
{
tripID.add(rs.getInt("tripID"));
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
