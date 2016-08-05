package com.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Trip {
	public int ID;
	public String tripName;
	public String locationCompany;
	public String companyName;
	public Double budget;
	public String programme;
	public Integer duration;
	public Date startDate;
	public Date endDate;
	public int companyID;
	public int fav;
	public double rate;
	public String transportation;
	public String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getFav() {
		return fav;
	}

	public void setFav(int fav) {
		this.fav = fav;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public Trip(int ID, String tripName, String locationCompany,
			String companyName, Double budget, String programme,
			Integer duration, Date startDate, Date endDate) {
		this.ID = ID;
		this.tripName = tripName;
		this.locationCompany = locationCompany;
		this.companyName = companyName;
		this.budget = budget;
		this.programme = programme;
		this.duration = duration;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Trip() {

	}

	public String getTripName() {
		return tripName;
	}

	public void setTripName(String tripName) {
		this.tripName = tripName;
	}

	public String getLocationCompany() {
		return locationCompany;
	}

	public void setLocationCompany(String locationCompany) {
		this.locationCompany = locationCompany;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public static String addTrip(int companyID, String name, double budget,
			String programme, int duration, Date startDate, Date endDate,
			String transportation, String category) throws SQLException {
		String cName = "", location = "";
		Connection conn = DBConnection.getActiveConnection();
		String sql = "select companyName from company where ID =" + companyID
				+ " ;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			cName = rs.getString("companyName");
		}
		sql = "select location from company_location where companyID ="
				+ companyID + " ;";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		if (rs.next()) {
			location = rs.getString("location");
		}

		sql = "insert into trips "
				+ "(companyID , name , companyName , companyLocation ,budget, programme , duration , startDate , endDate , transportation , category )"
				+ " values ( "
				+ companyID
				+ " , '"
				+ name
				+ "' , '"
				+ cName
				+ "' , '"
				+ location
				+ "' , "
				+ budget
				+ " , '"
				+ programme
				+ "' , "
				+ duration
				+ " , '"
				+ startDate
				+ "'  , '"
				+ endDate
				+ "' ,  '"
				+ transportation + "' , '" + category + "' ) ;";

		stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();
		return "Done";

	}

	public static Trip getTrip(int tripID) throws SQLException {
		Trip t = new Trip();
		Connection con = DBConnection.getActiveConnection();
		String sql = "select * from trips where ID = " + tripID + " ; ";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {

			t.ID = rs.getInt("ID");
			t.companyID = rs.getInt("companyID");
			t.tripName = rs.getString("name");
			t.companyName = rs.getString("companyName");
			t.locationCompany = rs.getString("companyLocation");
			t.budget = rs.getDouble("budget");
			t.programme = rs.getString("programme");
			t.duration = rs.getInt("duration");
			t.startDate = rs.getDate("startDate");
			t.endDate = rs.getDate("endDate");
			t.rate = rs.getDouble("rate");
			t.category = rs.getString("category");
			t.transportation = rs.getString("transportation");

		}

		return t;

	}

	public static ArrayList<Trip> showAllTrips(int userID) throws SQLException {
		ArrayList<Trip> trip = new ArrayList<>();
		ArrayList<Integer> ID = new ArrayList<>();
		ArrayList<Integer> rate = new ArrayList<>();
		Connection con = DBConnection.getActiveConnection();
		String sql = "select ID from company order by rate ; ";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			rate.add(rs.getInt("ID"));
		}
		for (int i = rate.size() - 1; i > -1; i--) {
			sql = "select * from trips where companyID = " + rate.get(i)
					+ "  ; ";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Trip t = new Trip();
				t.ID = rs.getInt("ID");
				t.companyID = rs.getInt("companyID");
				t.tripName = rs.getString("name");
				t.companyName = rs.getString("companyName");
				t.locationCompany = rs.getString("companyLocation");
				t.budget = rs.getDouble("budget");
				t.programme = rs.getString("programme");
				t.duration = rs.getInt("duration");
				t.startDate = rs.getDate("startDate");
				t.endDate = rs.getDate("endDate");
				t.rate = rs.getDouble("rate");
				t.category = rs.getString("category");
				t.transportation = rs.getString("transportation");
				trip.add(t);
			}
		}

		sql = "select tripID from favorite_trips where userID = " + userID
				+ " ; ";
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		while (rs.next()) {
			ID.add(rs.getInt("tripID"));
		}
		for (int i = 0; i < trip.size(); i++) {
			for (int y = 0; y < ID.size(); y++) {
				if (trip.get(i).ID == ID.get(y)) {
					trip.get(i).fav = 1;
					break;
				}
			}
		}
		return trip;
	}

	public static ArrayList<Trip> tripsForA_company(int companyID, int userID)
			throws SQLException {
		ArrayList<Trip> trip = new ArrayList<>();
		ArrayList<Integer> tripID = new ArrayList<>();
		Connection con = DBConnection.getActiveConnection();
		String sql = "select * from trips where companyID = " + companyID
				+ "  \n  order by name  ;";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Trip t = new Trip();
			t.ID = rs.getInt("ID");
			t.companyID = rs.getInt("companyID");
			t.tripName = rs.getString("name");
			t.companyName = rs.getString("companyName");
			t.locationCompany = rs.getString("companyLocation");
			t.budget = rs.getDouble("budget");
			t.programme = rs.getString("programme");
			t.duration = rs.getInt("duration");
			t.startDate = rs.getDate("startDate");
			t.endDate = rs.getDate("endDate");
			t.rate = rs.getDouble("rate");
			t.category = rs.getString("category");
			t.transportation = rs.getString("transportation");
			trip.add(t);
		}

		sql = "select tripID from favorite_trips where userID = " + userID
				+ " ;";
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		while (rs.next()) {
			tripID.add(rs.getInt("tripID"));
		}
		for (int i = 0; i < trip.size(); i++) {
			for (int y = 0; y < tripID.size(); y++) {
				if (trip.get(i).ID == tripID.get(y)) {
					trip.get(i).fav = 1;
				}
			}
		}

		return trip;

	}
	
	
	public static String setTripRate(int userID,int tripID,int rate) throws SQLException
	{
		Connection con = DBConnection.getActiveConnection();
		String sql = "select * from trip_rate where userID = " + userID + "  and tripID = "+tripID+" ;";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
		{
			sql = "update trip_rate set rate = "+rate+" where userID = " + userID + "  and tripID = "+tripID+" ;";
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		}
		else
		{
			sql = "insert into trip_rate values ( "+userID+" , "+tripID+" , "+rate+" );";
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		}
		
		sql = "select count(*) ,sum(rate) from trip_rate where  tripID = "+tripID+" ;";
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
	if(rs.next())
	{
		int noOfUsers=rs.getInt("count(*)");
		int summationOfRate=rs.getInt("sum(rate)");
		double finalRate=((double)summationOfRate/noOfUsers);
		sql = "update trips set rate = "+finalRate+" where ID = "+tripID+" ;";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

	}
	return "Done";	
		
	}

	
	
	
	
	
	
	
	public static int updateBadget(int tripID, double newbudget, String password)
			throws SQLException {
		Connection con = DBConnection.getActiveConnection();

		int companyID = 0;

		String sql = "select companyID from trips where ID = " + tripID + " ;";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			companyID = rs.getInt("companyID");
		}

		sql = "select * from company where ID = " + companyID
				+ " and password = " + "'" + password + "'" + ";";

		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		if (rs.next()) {
			sql = "Update trips set budget = " + newbudget + "  where ID = "
					+ tripID + ";";

			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();

			Notification notification = new Notification();
			notification.notify(tripID, "Updated Budget");
		}

		return tripID;

	}

	public static int updateProgramme(int tripID, String newProgramme,
			String password) throws SQLException {
		Connection con = DBConnection.getActiveConnection();
		int companyID = 0;
		String sql = "select companyID from trips where ID = " + tripID + " ;";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			companyID = rs.getInt("companyID");
		}

		sql = "select * from company where ID = " + companyID
				+ " and password = " + "'" + password + "'" + ";";

		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		if (rs.next()) {
			sql = "Update trips set programme = " + "'" + newProgramme + "'"
					+ " where ID = " + tripID + ";";

			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();

			Notification notification = new Notification();
			notification.notify(tripID, "Updated Programme");
		}

		return tripID;

	}

	public static int updateStratTimeandDuration(int tripID, Date newStratTime,
			int newDuration, String password) throws SQLException {
		Connection con = DBConnection.getActiveConnection();
		int companyID = 0;
		String sql = "select companyID from trips where ID = " + tripID + " ;";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			companyID = rs.getInt("companyID");
		}

		sql = "select * from company where ID = " + companyID
				+ " and password = " + "'" + password + "'" + ";";

		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		if (rs.next()) {
			sql = "Update trips set startDate = " + "'" + newStratTime + "'"
					+ " , duration = " + newDuration + " where ID = " + tripID
					+ ";";

			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();

			Notification notification = new Notification();
			notification.notify(tripID, " Updated Strat Time and Duration ");
		}

		return tripID;

	}

	public static int updateEndTimeandDuration(int tripID, Date newEndTime,
			int newDuration, String password) throws SQLException {
		Connection con = DBConnection.getActiveConnection();
		int companyID = 0;
		String sql = "select companyID from trips where ID = " + tripID + " ;";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			companyID = rs.getInt("companyID");
		}

		sql = "select * from company where ID = " + companyID
				+ " and password = " + "'" + password + "'" + ";";

		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		if (rs.next()) {
			sql = "Update trips set endDate = " + "'" + newEndTime + "'"
					+ " , duration = " + newDuration + " where ID = " + tripID
					+ ";";

			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();

			Notification notification = new Notification();
			notification.notify(tripID, " Updated End Time and Duration ");
		}

		return tripID;

	}

	public static int deleteTrip(int tripID, String password)
			throws SQLException {
		Connection con = DBConnection.getActiveConnection();
		int companyID = 0;
		ArrayList<Integer> arr = new ArrayList<>();

		String sql = "select companyID from trips where ID = " + tripID + " ;";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			companyID = rs.getInt("companyID");
		}

		sql = "select * from company where ID = " + companyID
				+ " and password = " + "'" + password + "'" + ";";

		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		if (rs.next()) {
			sql = "delete from favorite_trips where tripID = " + tripID + ";";

			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();

			sql = "delete from notification where tripID = " + tripID + ";";

			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();

			sql = "delete from trips where ID = " + tripID + ";";

			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		}

		return tripID;

	}

	public static ArrayList<Trip> relatedCategoryTrips(int userID, int tripID)
			throws SQLException {
		ArrayList<Trip> trip = new ArrayList<>();
		ArrayList<Integer> tr = new ArrayList<>();
		String category = "";
		Connection con = DBConnection.getActiveConnection();
		String sql = "select category from trips where ID = " + tripID + "   ;";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			category = rs.getString("category");
		}
		sql = "select * from trips where category = '" + category
				+ "' and ID <> " + tripID + "  order by rate  ;";
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();

		while (rs.next()) {
			Trip t = new Trip();
			t.ID = rs.getInt("ID");
			t.companyID = rs.getInt("companyID");
			t.tripName = rs.getString("name");
			t.companyName = rs.getString("companyName");
			t.locationCompany = rs.getString("companyLocation");
			t.budget = rs.getDouble("budget");
			t.programme = rs.getString("programme");
			t.duration = rs.getInt("duration");
			t.startDate = rs.getDate("startDate");
			t.endDate = rs.getDate("endDate");
			t.rate = rs.getDouble("rate");
			t.category = rs.getString("category");
			t.transportation = rs.getString("transportation");
			trip.add(t);
		}
		sql = "select tripID from favorite_trips where userID = " + userID
				+ "  ;";
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		while (rs.next()) {
			tr.add(rs.getInt("tripID"));
		}

		for (int i = 0; i < trip.size(); i++) {
			for (int y = 0; y < tr.size(); y++) {
				if (trip.get(i).ID == tr.get(y)) {

					trip.get(i).fav = 1;

				}
			}
		}

		return trip;

	}

	public static ArrayList<Trip> relatedcompanyTrips(int userID, int tripID)
			throws SQLException {
		ArrayList<Trip> trip = new ArrayList<>();
		ArrayList<Integer> tr = new ArrayList<>();
		int companyID = 0;
		Connection con = DBConnection.getActiveConnection();
		String sql = "select companyID from trips where ID = " + tripID
				+ "   ;";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			companyID = rs.getInt("companyID");
		}
		sql = "select * from trips where companyID = " + companyID
				+ "  and ID <> " + tripID + "  order by rate  ;";
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();

		while (rs.next()) {
			Trip t = new Trip();
			t.ID = rs.getInt("ID");
			t.companyID = rs.getInt("companyID");
			t.tripName = rs.getString("name");
			t.companyName = rs.getString("companyName");
			t.locationCompany = rs.getString("companyLocation");
			t.budget = rs.getDouble("budget");
			t.programme = rs.getString("programme");
			t.duration = rs.getInt("duration");
			t.startDate = rs.getDate("startDate");
			t.endDate = rs.getDate("endDate");
			t.rate = rs.getDouble("rate");
			t.category = rs.getString("category");
			t.transportation = rs.getString("transportation");
			trip.add(t);
		}
		sql = "select tripID from favorite_trips where userID = " + userID
				+ "  ;";
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		while (rs.next()) {
			tr.add(rs.getInt("tripID"));
		}

		for (int i = 0; i < trip.size(); i++) {
			for (int y = 0; y < tr.size(); y++) {
				if (trip.get(i).ID == tr.get(y)) {

					trip.get(i).fav = 1;

				}
			}
		}

		return trip;

	}

	public static ArrayList<Trip> relatedPlaceTrips(int userID, int tripID)
			throws SQLException {
		ArrayList<Trip> trip = new ArrayList<>();
		ArrayList<Integer> tr = new ArrayList<>();
		String name = "";
		Connection con = DBConnection.getActiveConnection();
		String sql = "select name from trips where ID = " + tripID + "   ;";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			name = rs.getString("name");
		}
		sql = "select * from trips where name = '" + name + "' and ID <> "
				+ tripID + "  order by rate  ;";
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();

		while (rs.next()) {
			Trip t = new Trip();
			t.ID = rs.getInt("ID");
			t.companyID = rs.getInt("companyID");
			t.tripName = rs.getString("name");
			t.companyName = rs.getString("companyName");
			t.locationCompany = rs.getString("companyLocation");
			t.budget = rs.getDouble("budget");
			t.programme = rs.getString("programme");
			t.duration = rs.getInt("duration");
			t.startDate = rs.getDate("startDate");
			t.endDate = rs.getDate("endDate");
			t.rate = rs.getDouble("rate");
			t.category = rs.getString("category");
			t.transportation = rs.getString("transportation");
			trip.add(t);
		}
		sql = "select tripID from favorite_trips where userID = " + userID
				+ "  ;";
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		while (rs.next()) {
			tr.add(rs.getInt("tripID"));
		}

		for (int i = 0; i < trip.size(); i++) {
			for (int y = 0; y < tr.size(); y++) {
				if (trip.get(i).ID == tr.get(y)) {

					trip.get(i).fav = 1;

				}
			}
		}

		return trip;

	}

}
