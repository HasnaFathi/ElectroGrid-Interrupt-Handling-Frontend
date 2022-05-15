package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class interrupt {
	
	public Connection connect()
	{
	  Connection con = null;

	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "");
		 //For testing
		 System.out.print("Successfully connected");
		 }
		 catch(Exception e)
		 {
		 e.printStackTrace();
		 }
	
		 return  con;
	}

	public String insertInterrupt(String code, String date,
			 String Duration, String start, String end, String Region, String Reason, String AdminID)
			 {
			 	String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {
					 return "Error while connecting to the database for inserting.";
				 }
				 
				 // create a prepared statement
				 String query = " insert into interrupt(`InterruptID`,`interruptCode`,`Date`,`Duration`,`Start_time`,`End_time`,`Region`,`Reason`,`AdminID`)"
				 + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				 
						 PreparedStatement preparedStmt = con.prepareStatement(query);
						 
						 // binding values
						 preparedStmt.setInt(1, 0);
						 preparedStmt.setString(2, code);
						 preparedStmt.setString(3, date);
						 preparedStmt.setDouble(4, Double.parseDouble(Duration));
						 preparedStmt.setString(5, start);
						 preparedStmt.setString(6, end);
						 preparedStmt.setString(7, Region);
						 preparedStmt.setString(8, Reason);
						 preparedStmt.setString(9, AdminID);
						 
						 // execute the statement
						 preparedStmt.execute();
						 con.close();
						 
						 String newInterrupts = readInterrupt();
						 output = "{\"status\":\"success\", \"data\": \"" 
						 + newInterrupts + "\"}";
					 }
					 catch (Exception e)
					 {
						 output = "{\"status\":\"error\", \"data\":\"Error while inserting the interrupt.\"}";
						 System.err.println(e.getMessage());
					 }
					 	return output;
					 } 

	public String readInterrupt()
	{
		String output = "";
	try
	 {
			Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading.";
		 }
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Interrupt Code</th>"
			 + "<th>Date</th><th>Duration</th>"
			 + "<th>Start Time</th><th>End Time</th>"
			 + "<th>Region</th><th>Reason</th><th>Admin ID</th>"
			 + "<th>Update</th><th>Remove</th></tr>";
			
			 String query = "select * from interrupt";
			 Statement stmt = con.createStatement();
			 
			 ResultSet rs = stmt.executeQuery(query);
			 
			 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String InterruptID = Integer.toString(rs.getInt("InterruptID"));
			 String interruptCode = rs.getString("interruptCode");
			 String date = rs.getString("date");
			 String Duration = Double.toString(rs.getDouble("Duration"));
			 String Start_time = rs.getString("Start_time"); 
			 String End_time = rs.getString("End_time");
			 String Region = rs.getString("Region");
			 String Reason = rs.getString("Reason");
			 String AdminID = rs.getString("AdminID");
			
			// Add into the html table
			 output += "<tr><td>" + interruptCode + "</td>";
			 output += "<td>" + date + "</td>";
			 output += "<td>" + Duration + "</td>";
			 output += "<td>" + Start_time + "</td>";
			 output += "<td>" + End_time + "</td>";
			 output += "<td>" + Region + "</td>";
			 output += "<td>" + Reason + "</td>";
			 output += "<td>" + AdminID + "</td>";
			// buttons
			output += "<td><input name='btnUpdate' type='button' value='Update' "
			+ "class='btnUpdate btn btn-secondary' data-interruptid='" + InterruptID + "'></td>"
			+ "<td><input name='btnRemove' type='button' value='Remove' "
			+ "class='btnRemove btn btn-danger' data-interruptid='" + InterruptID + "'></td></tr>";
		 }
		 con.close();
			 // Complete the html table
			 output += "</table>";
	 }
	catch (Exception e)
	 {
		 output = "Error while reading the interrupt.";
		 System.err.println(e.getMessage());
	 }
		return output;
	}

	
	public String updateInterrupt(String ID, String code, String date,
			 String Duration, String start, String end, String Region, String Reason, String AdminID)
			 {
					String output = "";
			 try
			 {
					 Connection con = connect();
					 if (con == null)
					 {
						 return "Error while connecting to the database for updating.";
					 }
					 
					 // create a prepared statement
					 String query = "UPDATE interrupt SET interruptCode=?,Date=?,Duration=?,Start_time=?,End_time=?,Region=?,Reason=?,AdminID=? WHERE InterruptID=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 preparedStmt.setString(1, code);
					 preparedStmt.setString(2, date);
					 preparedStmt.setDouble(3, Double.parseDouble(Duration));
					 preparedStmt.setString(4, start);
					 preparedStmt.setString(5, end);
					 preparedStmt.setString(6, Region);
					 preparedStmt.setString(7, Reason);
					 preparedStmt.setString(8, AdminID);
					
					 preparedStmt.setInt(9, Integer.parseInt(ID));
					 
					// execute the statement
					 preparedStmt.execute();
					 con.close();
					 String newInterrupts = readInterrupt();
					 output = "{\"status\":\"success\", \"data\": \"" +
					 newInterrupts + "\"}";
				 }
				 catch (Exception e)
				 {
					 output = "{\"status\":\"error\", \"data\":\"Error while updating the interrupt.\"}";
					 System.err.println(e.getMessage());
			 }
			 	return output;
			 }
			public String deleteInterrupt(String InterruptID)
			 {
					String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {
					 return "Error while connecting to the database for deleting.";
				 }
				 
				 // create a prepared statement
				 String query = "delete from interrupt where InterruptID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(InterruptID));
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 String newInterrupts = readInterrupt();
				 output = "{\"status\":\"success\", \"data\": \"" +
				 newInterrupts + "\"}";
			 }
			 catch (Exception e)
			 {
				 output = "{\"status\":\"error\", \"data\":\"Error while deleting the interrupt.\"}";
				 System.err.println(e.getMessage());
			 }
			 	return output;
			 }

}
