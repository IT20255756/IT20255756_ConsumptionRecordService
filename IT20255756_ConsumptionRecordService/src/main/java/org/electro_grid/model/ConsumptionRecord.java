package org.electro_grid.model;

import java.sql.*;

public class ConsumptionRecord {

	//A common method to connect to the Database
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro_grid", "root", "Group20");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
	//Insert method
	public String insertConsumptionRecord(String recordDate, String meterNo, String consumedUnits, String payStatus)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			//checking the database connection before inserting
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
			
			
			// create a prepared statement
			String query = " insert into consumptionrecord (`consumptionId`,`recordDate`,`meterNo`,`consumedUnits`,`payStatus`) values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, recordDate);
			preparedStmt.setString(3, meterNo);
			preparedStmt.setString(4, consumedUnits);
			preparedStmt.setString(5, payStatus);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			//output = "Consumption record is inserted successfully";
			
			String newRecord = readConsumptionRecord();
			output = "{\"status\":\"success\", \"data\": \"" + newRecord + "\"}";
		}
		catch (Exception e)
		{
			//output = "Error while inserting the consumption record.";
			//System.err.println(e.getMessage());
			
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the consumption record Service.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//Read method
	public String readConsumptionRecord()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			//checking the database connection before inserting
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Record Date</th><th>Meter Number</th>" +
			"<th>Consumed Units</th>" +
			"<th>Pay Status</th>"+ 
			"<th>Update</th><th>Remove</th></tr>";
			
			//Query for reading the data from the database
			String query = "select * from consumptionrecord";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//Iterate through the rows in the result set
			while (rs.next())
			{
				String consumptionId = Integer.toString(rs.getInt("consumptionId"));
				String recordDate = rs.getString("recordDate");
				String meterNo = rs.getString("meterNo");
				String consumedUnits = Integer.toString(rs.getInt("consumedUnits"));
				String payStatus = rs.getString("payStatus");
				
				// Add into the html table
				output += "<tr><td>" + recordDate + "</td>";
				output += "<td>" + meterNo + "</td>";
				output += "<td>" + consumedUnits + "</td>";
				output += "<td>" + payStatus + "</td>";
				
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-customerid='" + consumptionId + "'>" + "</td></tr>"; 
				
			}
			
			con.close();
			
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the consumption records.";
			System.err.println(e.getMessage());
		}
		
		return output;
	
	}
	
	//update method
		public String updateConsumptionRecord(int consumptionID, String recordDate, String meterNo, String consumedUnits, String payStatus)
		{
			String output = "";
			
			try
			{
				Connection con = connect();
				
				//checking the database connection before inserting
				if (con == null)
				{
					return "Error while connecting to the database for updating.";
				}
				
				// create a prepared statement
				String query = "UPDATE consumptionrecord SET recordDate=?,meterNo=?,consumedUnits=?,payStatus=? WHERE consumptionID=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, recordDate);
				preparedStmt.setString(2, meterNo);
				preparedStmt.setInt(3, Integer.parseInt(consumedUnits));
				preparedStmt.setBoolean(4, Boolean.parseBoolean(payStatus));
				preparedStmt.setInt(5, consumptionID);
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				//output = "Consumption data is updated successfully.";
				String newRecord = readConsumptionRecord();
				output = "{\"status\":\"success\", \"data\": \"" + newRecord + "\"}";
				
			}
			catch (Exception e)
			{
				//output = "Error while updating the consumption record.";
				//System.err.println(e.getMessage());
				
				output = "{\"status\":\"error\", \"data\":\"Error while updating the consumption record Service.\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
	//Delete method
	public String deleteConsumptionRecord(String consumptionID)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			//checking the database connection before inserting
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}
			
			// create a prepared statement
			String query = "delete from consumptionrecord where consumptionID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(consumptionID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			//output = "Consumption record is deleted successfully";
			String newRecord = readConsumptionRecord();
			output = "{\"status\":\"success\", \"data\": \"" + newRecord + "\"}";
			
		}
		catch (Exception e)
		{
			//output = "Error while deleting the consumption record.";
			//System.err.println(e.getMessage());
			
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the consumption record Service.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
		
		}
}