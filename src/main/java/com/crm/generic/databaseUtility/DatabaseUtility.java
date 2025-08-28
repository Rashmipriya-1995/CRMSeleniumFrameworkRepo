package com.crm.generic.databaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	Connection conn; // declare globally
	public void getDbConnection(String url, String username, String password) throws SQLException
	{
		try{
			Driver driver=new Driver();
		
			DriverManager.registerDriver(driver);
		
			conn=DriverManager.getConnection(url,username,password);
		}
		catch(Exception e) {
			}
	}
	
	public void closeDbConnection() throws SQLException
	{
		try {
			conn.close();
		}
		catch(Exception e) {
		}
	}
	public ResultSet executeSelectQuery(String query)
	{
		ResultSet result=null;
		try {
			Statement stat = conn.createStatement();
			result=stat.executeQuery(query);
		}
		catch(Exception e) {
		}
		return result;
	}
	
	public int executenonSelectQuery(String query)
	{
		int result=0;
		try {
			Statement stat = conn.createStatement();
			result=stat.executeUpdate(query);
		}
		catch(Exception e) {
		}
		return result;
	}
	
	
}
