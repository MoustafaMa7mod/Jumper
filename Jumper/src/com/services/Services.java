package com.services;

import java.sql.Connection; 
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.models.*;

import org.glassfish.*;
import org.json.simple.JSONArray;
//import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;

import com.models.DBConnection;
import com.models.User;

import Search.*;
import Sort.SortStrategy;
import Sort.*;
@Path("/")
public class Services {

	@POST
	@Path("/signup")
	@Produces(MediaType.TEXT_PLAIN)
	public String signUp(@FormParam("fullName") String fullName,
			@FormParam("phoneNumber") String phoneNumber, @FormParam("email") String email ,@FormParam("password") String password) throws SQLException {
		User user = User.signup(fullName, phoneNumber, email, password) ; 
		JSONObject json = new JSONObject();
		json.put("ID", user.getID());
		json.put("fullName", user.getFullName());
		json.put("phoneNumer", user.getPhoneNumber());
		json.put("email", user.getEmail());
		json.put("password", user.getPassword());
		
		return json.toJSONString();
	}
	//
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("email") String email,
			@FormParam("password") String pass) throws SQLException {
		
		User user = User.login(email, pass);
		JSONObject json = new JSONObject();
		json.put("ID", user.getID());
		json.put("fullName", user.getFullName());
		json.put("email", user.getEmail());
		json.put("password", user.getPassword());
		json.put("phoneNumber", user.getPhoneNumber());
		
		return json.toJSONString();
	}
	
	@POST
	@Path("/editName")
	@Produces(MediaType.TEXT_PLAIN)
	public String EditName(@FormParam("userID") int userID, @FormParam("newName") String newName,@FormParam("password") String password) throws SQLException {
		Boolean user = User.EditName(userID, newName,password) ; 
		JSONObject json = new JSONObject();
		json.put("status", user ? 1 : 0);
		return json.toJSONString();
	}

	@POST
	@Path("/editNumber")
	@Produces(MediaType.TEXT_PLAIN)
	public String EditNumber(@FormParam("userID") int  userID, @FormParam("newNumber") String newNumber,@FormParam("password") String password) throws SQLException {
		Boolean user = User.EditPhoneNumber(userID, newNumber,password) ; 
		JSONObject json = new JSONObject();
		json.put("status", user ? 1 : 0);
		return json.toJSONString();
	}

	@POST
	@Path("/editPassword")
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePosition(@FormParam("userID") int userID,@FormParam("oldPassword") String oldPassword ,@FormParam("newPassword") String newPassword) throws SQLException {
		Boolean user = User.EditPassowrd(userID,oldPassword,newPassword) ; 
		JSONObject json = new JSONObject();
		json.put("status", user ? 1 : 0);
		return json.toJSONString();
	}
	
	
	@POST
	@Path("/AddCompany")
	@Produces(MediaType.TEXT_PLAIN)
	public String Addcompany(@FormParam("name") String name,
			@FormParam("des") String des, @FormParam("website") String website ,@FormParam("lat") String lat ,@FormParam("lng") String lng) throws SQLException {
		
		
		Company company = Company.AddCompany(name, des, website, lat, lng )  ; 
		
		JSONObject json = new JSONObject();
		json.put("ID", company.getID());
		json.put("name", company.getCompanyName());
		json.put("des", company.getDescription());
		json.put("website", company.getWebsite());
		json.put("lat", company.getLatitude());
		json.put("lng", company.getLongtude());
		
		return json.toJSONString();
	}

	@POST
	@Path("/Addlocation")
	@Produces(MediaType.TEXT_PLAIN)
	public String AddLocation(@FormParam("companyID") int companyID, @FormParam("location") String location) throws SQLException {
		
	   String s=Company.AddLocation(companyID, location) ; 
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}
	
	@POST
	@Path("/RemoveCompany")
	@Produces(MediaType.TEXT_PLAIN)
	public String RemoveCompany(@FormParam("companyID") int companyID) throws SQLException {
		
	    String s=Company.RemoveCompany(companyID); 
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}
	@POST
	@Path("/RemoveLocation")
	@Produces(MediaType.TEXT_PLAIN)
	public String RemoveLocation(@FormParam("companyID") int companyID,@FormParam("location") String location) throws SQLException {
		
	    String s=Company.RemoveLocation(companyID,location); 
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}
	
	@POST
	@Path("/UpdateLocation")
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateLocation(@FormParam("companyID") int companyID ,@FormParam("oldLocation") String oldLocation, @FormParam("newLocation") String newLocation ) throws SQLException {
		
	    String s = Company.UpdateLocation(companyID,oldLocation ,newLocation); 
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}
	@POST
	@Path("/UpdateCompany")
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateCompany(@FormParam("companyID") int companyID , @FormParam("newCompanyName") String newCompanyName ) throws SQLException {
		
	    String s = Company.UpdateCompany(companyID,newCompanyName); 
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}

	@POST
	@Path("/AddNumber")
	@Produces(MediaType.TEXT_PLAIN)
	public String AddNumber(@FormParam("companyID") int companyID, @FormParam("newNumber") String number) throws SQLException {
		
	    String s=Company.AddNumber(companyID, number); 
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}
	
	@POST
	@Path("/RemoveNumber")
	@Produces(MediaType.TEXT_PLAIN)
	public String RemoveNumber(@FormParam("companyID") int companyID,@FormParam("Number") String number) throws SQLException {
		
	   String s= Company.RemoveNumber(companyID,number);
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}
	
	@POST
	@Path("/UpdateNumber")
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateNumber(@FormParam("companyID") int companyID , @FormParam("oldNumber") String oldNumber, @FormParam("newNumber") String newNumber ) throws SQLException {
		
	    String s = Company.UpdateNumber(companyID,oldNumber,newNumber); 
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}

	@POST
	@Path("/AddEmail")
	@Produces(MediaType.TEXT_PLAIN)
	public String AddEmail(@FormParam("companyID") int companyID, @FormParam("Email") String email) throws SQLException {
		
	    String s=Company.AddEmail(companyID, email);
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}
	
	@POST
	@Path("/RemoveEmail")
	@Produces(MediaType.TEXT_PLAIN)
	public String RemoveEmail(@FormParam("companyID") int companyID,@FormParam("Email") String email) throws SQLException {
		
	    String s=Company.RemoveEmail(companyID,email);
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}
	
	@POST
	@Path("/UpdateEmail")
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateEmail(@FormParam("companyID") int companyID, @FormParam("oldEmail") String oldEmail , @FormParam("newEmail") String newEmail ) throws SQLException {
		
	    String s = Company.UpdateEmail(companyID, oldEmail, newEmail) ;
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}
	@POST
	@Path("/SearchDuration")
	@Produces(MediaType.TEXT_PLAIN)
	public String SearchDuration(@FormParam("Duration")  int duration ) throws SQLException {
		
	    ArrayList<Trip>t= SearchStrategy.searchDuration(duration);
	    JSONArray jsonArray =new JSONArray();
	    for (int i=0;i<t.size();i++)
	    {
	    	  JSONObject j=new JSONObject();
	   	   j.put("ID",t.get(i).ID );
	   	   j.put("companyID",t.get(i).companyID );
	   	   j.put("name",t.get(i).tripName);
	   	   j.put("companyName",t.get(i).companyName);
	   	   j.put("location",t.get(i).locationCompany );
	   	   j.put("budget",t.get(i).budget);
	   	   j.put("programme",t.get(i).programme );
	   	   j.put("duration",t.get(i).duration );
	   	   j.put("startDate",t.get(i).startDate );
	   	   j.put("endDate",t.get(i).endDate );
	   	   j.put("fav", t.get(i).fav);
	   	   j.put("rate",t.get(i).rate );
		   j.put("transportation",t.get(i).transportation);
		   j.put("category",t.get(i).category );
	    	jsonArray.add(j);
	    	
	    }
		
		
		return jsonArray.toJSONString();
	}
	
	@POST
	@Path("/SearchTime")
	@Produces(MediaType.TEXT_PLAIN)
	public String SearchTime(@FormParam("Time")  Date time ) throws SQLException {
		
	    ArrayList<Trip>t = SearchStrategy.searchTime(time);
	    JSONArray jsonArray =new JSONArray();
	    for (int i=0;i<t.size();i++)
	    {
	    	  JSONObject j=new JSONObject();
	    	  j.put("ID",t.get(i).ID );
		   	   j.put("companyID",t.get(i).companyID );
		   	   j.put("name",t.get(i).tripName);
		   	   j.put("companyName",t.get(i).companyName);
		   	   j.put("location",t.get(i).locationCompany );
		   	   j.put("budget",t.get(i).budget);
		   	   j.put("programme",t.get(i).programme );
		   	   j.put("duration",t.get(i).duration );
		   	   j.put("startDate",t.get(i).startDate );
		   	   j.put("endDate",t.get(i).endDate );
		   	   j.put("fav", t.get(i).fav);
		   	   j.put("rate",t.get(i).rate );
			   j.put("transportation",t.get(i).transportation);
			   j.put("category",t.get(i).category );
	    	jsonArray.add(j);
	    	
	    }
		
		
		return jsonArray.toJSONString();
	}
	
	@POST
	@Path("/SearchBudget")
	@Produces(MediaType.TEXT_PLAIN)
	public String SearchBudget(@FormParam("Budget")  double budget ) throws SQLException {
		
	    ArrayList<Trip>t = SearchStrategy.searchBudget(budget);
	    JSONArray jsonArray =new JSONArray();
	    for (int i=0;i<t.size();i++)
	    {
	    	  JSONObject j=new JSONObject();
	    	  j.put("ID",t.get(i).ID );
		   	   j.put("companyID",t.get(i).companyID );
		   	   j.put("name",t.get(i).tripName);
		   	   j.put("companyName",t.get(i).companyName);
		   	   j.put("location",t.get(i).locationCompany );
		   	   j.put("budget",t.get(i).budget);
		   	   j.put("programme",t.get(i).programme );
		   	   j.put("duration",t.get(i).duration );
		   	   j.put("startDate",t.get(i).startDate );
		   	   j.put("endDate",t.get(i).endDate );
		   	   j.put("fav", t.get(i).fav);
		   	   j.put("rate",t.get(i).rate );
			   j.put("transportation",t.get(i).transportation);
			   j.put("category",t.get(i).category );
	    	jsonArray.add(j);
	    	
	    }
		
		
		return jsonArray.toJSONString();
	}	
	
	@POST
	@Path("/SearchCompanyName")
	@Produces(MediaType.TEXT_PLAIN)
	public String SearchCompanyName(@FormParam("companyName")  String companyName ) throws SQLException {
		
	    ArrayList<Trip>t = SearchStrategy.searchCompany(companyName);
	    JSONArray jsonArray =new JSONArray();
	    for (int i=0;i<t.size();i++)
	    {
	    	  JSONObject j=new JSONObject();
	    	  j.put("ID",t.get(i).ID );
		   	   j.put("companyID",t.get(i).companyID );
		   	   j.put("name",t.get(i).tripName);
		   	   j.put("companyName",t.get(i).companyName);
		   	   j.put("location",t.get(i).locationCompany );
		   	   j.put("budget",t.get(i).budget);
		   	   j.put("programme",t.get(i).programme );
		   	   j.put("duration",t.get(i).duration );
		   	   j.put("startDate",t.get(i).startDate );
		   	   j.put("endDate",t.get(i).endDate );
		   	   j.put("fav", t.get(i).fav);
		   	   j.put("rate",t.get(i).rate );
			   j.put("transportation",t.get(i).transportation);
			   j.put("category",t.get(i).category );
	    	jsonArray.add(j);
	    	
	    }
		
		
		return jsonArray.toJSONString();
	}

	
	
	@POST
	@Path("/SearchTripName")
	@Produces(MediaType.TEXT_PLAIN)
	public String SearchTripName(@FormParam("tripName")  String tripName ) throws SQLException {
		
	    ArrayList<Trip>t = SearchStrategy.searchTrip(tripName);
	    JSONArray jsonArray =new JSONArray();
	    for (int i=0;i<t.size();i++)
	    {
	    	  JSONObject j=new JSONObject();
	    	  j.put("ID",t.get(i).ID );
		   	   j.put("companyID",t.get(i).companyID );
		   	   j.put("name",t.get(i).tripName);
		   	   j.put("companyName",t.get(i).companyName);
		   	   j.put("location",t.get(i).locationCompany );
		   	   j.put("budget",t.get(i).budget);
		   	   j.put("programme",t.get(i).programme );
		   	   j.put("duration",t.get(i).duration );
		   	   j.put("startDate",t.get(i).startDate );
		   	   j.put("endDate",t.get(i).endDate );
		   	   j.put("fav", t.get(i).fav);
		   	   j.put("rate",t.get(i).rate );
			   j.put("transportation",t.get(i).transportation);
			   j.put("category",t.get(i).category );
	    	jsonArray.add(j);
	    	
	    }
		
		
		return jsonArray.toJSONString();
	}
	
	@POST
	@Path("/addTrip")
	@Produces(MediaType.TEXT_PLAIN)
	public String addTrip(@FormParam("companyID") int companyID,@FormParam("name") String name ,
			@FormParam("budget") double budget,@FormParam("programme") String programme,@FormParam("duration") int duration ,@FormParam("startDate") Date startDate 
			,@FormParam("endDate") Date endDate,@FormParam("transportation") String transportation,@FormParam("category") String category) throws SQLException {
		
	    String s=Trip.addTrip(companyID, name, budget, programme,  duration, startDate, endDate,transportation, category); 
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}
	
	@POST
	@Path("/getCompany")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCompany(@FormParam("companyID") int companyID) throws SQLException {
		 
		Company c = Company.getCompany(companyID) ; 
	    ArrayList <String> arr = Company.getCompanyNumber(companyID) ;
	    ArrayList <String> arr1 = Company.getCompanyLocation(companyID) ;
	    JSONArray jsonArray=new JSONArray();
	    JSONObject json=new JSONObject();
	    json.put("ID",c.getID() );
	    json.put("companyName",c.getCompanyName() );
	    json.put("des", c.getDescription());
	    json.put("website",c.getWebsite() );
	    json.put("lat", c.getLatitude());
	    json.put("long",c.getLongtude() );
	    json.put("rate",c.getRate() );
	    jsonArray.add(json);
	    JSONArray j=new JSONArray();
	    for(int i=0;i<arr.size();i++)
	    {
	    	json=new JSONObject();
	    	json.put("phone", arr.get(i));
	    	j.add(json);
	    	
	    }
	    jsonArray.add(j);
	    JSONArray j1=new JSONArray();
	    for(int i=0;i<arr1.size();i++)
	    {
	    	json=new JSONObject();
	    	json.put("location", arr1.get(i));
	    	j1.add(json);
	    	
	    }
	    jsonArray.add(j1);
	    
	    
	    
		
	   
		return jsonArray.toJSONString();
	}
	
	
	@POST
	@Path("/getTrip")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTrip(@FormParam("tripID") int tripID) throws SQLException {
		 
		Trip t = Trip.getTrip(tripID);
		
	    JSONObject j=new JSONObject();
	   j.put("ID",t.getID() );
	   j.put("companyID",t.getCompanyID() );
	   j.put("name",t.getTripName() );
	   j.put("companyName",t.getCompanyName() );
	   j.put("location",t.getLocationCompany() );
	   j.put("budget",t.getBudget() );
	   j.put("programme",t.getProgramme() );
	   j.put("duration",t.getDuration() );
	   j.put("startDate",t.getStartDate() );
	   j.put("endDate",t.getEndDate() );
	   j.put("fav", t.getFav());
	   j.put("rate",t.rate );
	   j.put("transportation",t.transportation );
	   j.put("category",t.category );
	   
	    return j.toJSONString();

		
	}
	@POST
	@Path("/showHome")
	@Produces(MediaType.TEXT_PLAIN)
	public String showHome(@FormParam("userID") int userID) throws SQLException {
		 
		ArrayList<Trip>t=Trip.showAllTrips(userID);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<t.size();i++)
		{
	    JSONObject j=new JSONObject();
	    j.put("ID",t.get(i).ID );
	   	   j.put("companyID",t.get(i).companyID );
	   	   j.put("name",t.get(i).tripName);
	   	   j.put("companyName",t.get(i).companyName);
	   	   j.put("location",t.get(i).locationCompany );
	   	   j.put("budget",t.get(i).budget);
	   	   j.put("programme",t.get(i).programme );
	   	   j.put("duration",t.get(i).duration );
	   	   j.put("startDate",t.get(i).startDate );
	   	   j.put("endDate",t.get(i).endDate );
	   	   j.put("fav", t.get(i).fav);
	   	   j.put("rate",t.get(i).rate );
		   j.put("transportation",t.get(i).transportation);
		   j.put("category",t.get(i).category );
	   jsonArray.add(j);
	   
		}
	   
		return jsonArray.toJSONString();
	   
		
	}
	
	@POST
	@Path("/wishList")
	@Produces(MediaType.TEXT_PLAIN)
	public String wishList(@FormParam("userID") int userID) throws SQLException {
		 
		ArrayList<Trip>t=favorite.wishList(userID);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<t.size();i++)
		{
			  JSONObject j=new JSONObject();
			  j.put("ID",t.get(i).ID );
		   	   j.put("companyID",t.get(i).companyID );
		   	   j.put("name",t.get(i).tripName);
		   	   j.put("companyName",t.get(i).companyName);
		   	   j.put("location",t.get(i).locationCompany );
		   	   j.put("budget",t.get(i).budget);
		   	   j.put("programme",t.get(i).programme );
		   	   j.put("duration",t.get(i).duration );
		   	   j.put("startDate",t.get(i).startDate );
		   	   j.put("endDate",t.get(i).endDate );
		   	   j.put("fav", t.get(i).fav);
		   	   j.put("rate",t.get(i).rate );
			   j.put("transportation",t.get(i).transportation);
			   j.put("category",t.get(i).category );
	   jsonArray.add(j);
	   
		}
	   
		return jsonArray.toJSONString();
	   
		
	}
	
	
	@POST
	@Path("/tripsForA_company")
	@Produces(MediaType.TEXT_PLAIN)
	public String tripsForA_company(@FormParam("companyID") int companyID,@FormParam("userID") int userID) throws SQLException {
		 
		ArrayList<Trip>t=Trip.tripsForA_company(companyID,userID);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<t.size();i++)
		{
			  JSONObject j=new JSONObject();
			  j.put("ID",t.get(i).ID );
		   	   j.put("companyID",t.get(i).companyID );
		   	   j.put("name",t.get(i).tripName);
		   	   j.put("companyName",t.get(i).companyName);
		   	   j.put("location",t.get(i).locationCompany );
		   	   j.put("budget",t.get(i).budget);
		   	   j.put("programme",t.get(i).programme );
		   	   j.put("duration",t.get(i).duration );
		   	   j.put("startDate",t.get(i).startDate );
		   	   j.put("endDate",t.get(i).endDate );
		   	   j.put("fav", t.get(i).fav);
		   	   j.put("rate",t.get(i).rate );
			   j.put("transportation",t.get(i).transportation);
			   j.put("category",t.get(i).category );
	   jsonArray.add(j);
	   
		}
	   
		return jsonArray.toJSONString();
	   
		
	}
	@POST
	@Path("/sortByRate")
	@Produces(MediaType.TEXT_PLAIN)
	public String sortByRate(@FormParam("userID") int userID) throws SQLException {
		 
		ArrayList<Trip>t=SortStrategy.sortByrate(userID);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<t.size();i++)
		{
			  JSONObject j=new JSONObject();
			  j.put("ID",t.get(i).ID );
		   	   j.put("companyID",t.get(i).companyID );
		   	   j.put("name",t.get(i).tripName);
		   	   j.put("companyName",t.get(i).companyName);
		   	   j.put("location",t.get(i).locationCompany );
		   	   j.put("budget",t.get(i).budget);
		   	   j.put("programme",t.get(i).programme );
		   	   j.put("duration",t.get(i).duration );
		   	   j.put("startDate",t.get(i).startDate );
		   	   j.put("endDate",t.get(i).endDate );
		   	   j.put("fav", t.get(i).fav);
		   	   j.put("rate",t.get(i).rate );
			   j.put("transportation",t.get(i).transportation);
			   j.put("category",t.get(i).category );
	   jsonArray.add(j);
	   
		}
	   
		return jsonArray.toJSONString();
	   
		
	}
	@POST
	@Path("/sortByBudget")
	@Produces(MediaType.TEXT_PLAIN)
	public String sortByBudget(@FormParam("userID") int userID) throws SQLException {
		 
		ArrayList<Trip>t=SortStrategy.sortByBudget(userID);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<t.size();i++)
		{
	    JSONObject j=new JSONObject();
	    j.put("ID",t.get(i).ID );
	   	   j.put("companyID",t.get(i).companyID );
	   	   j.put("name",t.get(i).tripName);
	   	   j.put("companyName",t.get(i).companyName);
	   	   j.put("location",t.get(i).locationCompany );
	   	   j.put("budget",t.get(i).budget);
	   	   j.put("programme",t.get(i).programme );
	   	   j.put("duration",t.get(i).duration );
	   	   j.put("startDate",t.get(i).startDate );
	   	   j.put("endDate",t.get(i).endDate );
	   	   j.put("fav", t.get(i).fav);
	   	   j.put("rate",t.get(i).rate );
		   j.put("transportation",t.get(i).transportation);
		   j.put("category",t.get(i).category );
	   jsonArray.add(j);
	   
		}
	   
		return jsonArray.toJSONString();
	   
		
	}
	@POST
	@Path("/sortByDuration")
	@Produces(MediaType.TEXT_PLAIN)
	public String sortByDuration(@FormParam("userID") int userID) throws SQLException {
		 
		ArrayList<Trip>t=SortStrategy.sortByDuration(userID);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<t.size();i++)
		{
			  JSONObject j=new JSONObject();
			  j.put("ID",t.get(i).ID );
		   	   j.put("companyID",t.get(i).companyID );
		   	   j.put("name",t.get(i).tripName);
		   	   j.put("companyName",t.get(i).companyName);
		   	   j.put("location",t.get(i).locationCompany );
		   	   j.put("budget",t.get(i).budget);
		   	   j.put("programme",t.get(i).programme );
		   	   j.put("duration",t.get(i).duration );
		   	   j.put("startDate",t.get(i).startDate );
		   	   j.put("endDate",t.get(i).endDate );
		   	   j.put("fav", t.get(i).fav);
		   	   j.put("rate",t.get(i).rate );
			   j.put("transportation",t.get(i).transportation);
			   j.put("category",t.get(i).category );
	   jsonArray.add(j);
	   
		}
	   
		return jsonArray.toJSONString();
	   
		
	}
	
	@POST
	@Path("/sortByDate")
	@Produces(MediaType.TEXT_PLAIN)
	public String sortByDate(@FormParam("userID") int userID) throws SQLException {
		 
		ArrayList<Trip>t=SortStrategy.sortByDate(userID);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<t.size();i++)
		{
			  JSONObject j=new JSONObject();
			  j.put("ID",t.get(i).ID );
		   	   j.put("companyID",t.get(i).companyID );
		   	   j.put("name",t.get(i).tripName);
		   	   j.put("companyName",t.get(i).companyName);
		   	   j.put("location",t.get(i).locationCompany );
		   	   j.put("budget",t.get(i).budget);
		   	   j.put("programme",t.get(i).programme );
		   	   j.put("duration",t.get(i).duration );
		   	   j.put("startDate",t.get(i).startDate );
		   	   j.put("endDate",t.get(i).endDate );
		   	   j.put("fav", t.get(i).fav);
		   	   j.put("rate",t.get(i).rate );
			   j.put("transportation",t.get(i).transportation);
			   j.put("category",t.get(i).category );
	   jsonArray.add(j);
	   
		}
	   
		return jsonArray.toJSONString();
	   
		
	}
	@POST
	@Path("/tripUserRate")
	@Produces(MediaType.TEXT_PLAIN)
	public void tripUserRate(@FormParam("userID") int userID,@FormParam("tripID") int tripID,@FormParam("rate") int rate) throws SQLException {
		Rate.tripUserRat(userID, tripID, rate);
	
	}
	
	@POST
	@Path("/notify")
	@Produces(MediaType.TEXT_PLAIN)
	public String notification(@FormParam("tripID") int tripID, @FormParam("text") String text) throws SQLException {
		
		Notification.notify(tripID, text) ; 
		JSONObject json = new JSONObject();
		json.put("status", tripID);
		return json.toJSONString();
	}
	
	@POST
	@Path("/Updatbudget")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBudget(@FormParam("tripID") int tripID , @FormParam("newBudget") double newbudget, @FormParam("password") String password ) throws SQLException {
		
	    int s = Trip.updateBadget(tripID, newbudget, password); 
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}
	
	@POST
	@Path("/Updatprogramme")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProgramme(@FormParam("tripID") int tripID , @FormParam("newprogramme") String newProgramme, @FormParam("password") String password ) throws SQLException {
		
	    int s = Trip.updateProgramme(tripID, newProgramme, password); 
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}
	
	@POST
	@Path("/UpdatstartTime")
	@Produces(MediaType.TEXT_PLAIN)
	public String updatestartTime(@FormParam("tripID") int tripID,@FormParam("newTime") Date newStratTime , @FormParam("newDuration") int newDuration, @FormParam("password") String password ) throws SQLException {
		
	    int s = Trip.updateStratTimeandDuration(tripID, newStratTime, newDuration, password); 
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}
	
	@POST
	@Path("/UpdatendTime")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEndTime(@FormParam("tripID") int tripID,@FormParam("newTime") Date newEndTime , @FormParam("newDuration") int newDuration, @FormParam("password") String password ) throws SQLException {
		
	    int s = Trip.updateEndTimeandDuration(tripID, newEndTime, newDuration, password); 
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}
	
	@POST
	@Path("/deleteTrip")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEndTime(@FormParam("tripID") int tripID,@FormParam("password") String password ) throws SQLException {
		
	    int s = Trip.deleteTrip(tripID, password); 
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}
	
	@POST
	@Path("/companyUserRate")
	@Produces(MediaType.TEXT_PLAIN)
	public void companyUserRate(@FormParam("userID") int userID, @FormParam("companyID") int companyID , @FormParam("rate") int rate ) throws SQLException {
		
		Rate.companyUserRate(userID, companyID, rate);
	
	}
	

	@POST
	@Path("/addTransportation")
	@Produces(MediaType.TEXT_PLAIN)
	public String addTransportation (@FormParam("transportation") String transportation) throws SQLException {
		
	    String s = Transportation.addTransportation(transportation); 
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}
	
	@POST
	@Path("/category")
	@Produces(MediaType.TEXT_PLAIN)
	public String addCategory(@FormParam("category") String category) throws SQLException {
		
	    String s = Category.addCategory(category); 
		JSONObject json = new JSONObject();
		json.put("status", s);
		return json.toJSONString();
	}

	
	@POST
	@Path("/relatedTrips")
	@Produces(MediaType.TEXT_PLAIN)
	public String relatedTrips(@FormParam("userID") int  userID,@FormParam("tripID") int tripID) throws SQLException {
		 
		ArrayList<Trip>t=Trip.relatedTrips(userID, tripID);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<t.size();i++)
		{
			  JSONObject j=new JSONObject();
			  j.put("ID",t.get(i).ID );
		   	   j.put("companyID",t.get(i).companyID );
		   	   j.put("name",t.get(i).tripName);
		   	   j.put("companyName",t.get(i).companyName);
		   	   j.put("location",t.get(i).locationCompany );
		   	   j.put("budget",t.get(i).budget);
		   	   j.put("programme",t.get(i).programme );
		   	   j.put("duration",t.get(i).duration );
		   	   j.put("startDate",t.get(i).startDate );
		   	   j.put("endDate",t.get(i).endDate );
		   	   j.put("fav", t.get(i).fav);
		   	   j.put("rate",t.get(i).rate );
			   j.put("transportation",t.get(i).transportation);
			   j.put("category",t.get(i).category );
	   jsonArray.add(j);
	   
		}
	   
		return jsonArray.toJSONString();
	   
		
	}
	
	@POST
	@Path("/searchTransportation")
	@Produces(MediaType.TEXT_PLAIN)
	public String searchTransportation(@FormParam("transportation") String trans,@FormParam("userID") int userID) throws SQLException {
		 
		ArrayList<Trip>t=SearchStrategy.searchTransportation(trans, userID);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<t.size();i++)
		{
			  JSONObject j=new JSONObject();
			  j.put("ID",t.get(i).ID );
		   	   j.put("companyID",t.get(i).companyID );
		   	   j.put("name",t.get(i).tripName);
		   	   j.put("companyName",t.get(i).companyName);
		   	   j.put("location",t.get(i).locationCompany );
		   	   j.put("budget",t.get(i).budget);
		   	   j.put("programme",t.get(i).programme );
		   	   j.put("duration",t.get(i).duration );
		   	   j.put("startDate",t.get(i).startDate );
		   	   j.put("endDate",t.get(i).endDate );
		   	   j.put("fav", t.get(i).fav);
		   	   j.put("rate",t.get(i).rate );
			   j.put("transportation",t.get(i).transportation);
			   j.put("category",t.get(i).category );
	   jsonArray.add(j);
	   
		}
	   
		return jsonArray.toJSONString();
	   
		
	}	
}
