package com.connectors.navSqlServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class PippaDBConnection {
	public static Connection connectToMySql() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No MySQL JDBC Driver was found!");
			e.printStackTrace();

		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
		try {

			connection = DriverManager.getConnection("jdbc:mysql://staging.pippajean.com/pippajean_prod","pippajeancloud","3H9e$Ne@");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}

		if (connection != null) {
			System.out.println("Connected to sql server!");
			return connection;
		} else {
			System.out.println("Failed to make connection!");
		}
		return connection;
		
		
	
	}
	
	public static void test() throws SQLException{
		Map<String, String> curs = new HashMap<String, String>();
		curs.put("149", "1");
		curs.put("150", "2");
		curs.put("144", "3");
		curs.put("145", "4");
		curs.put("146", "5");
		curs.put("147", "6");
		curs.put("148", "7");
		
		
		String cursuri="";
		Connection connection = PippaDBConnection.connectToMySql();
		Statement statement = connection.createStatement();
		String queryString = "select * from pippajean_prod.customer_entity_varchar where attribute_id = 298 and entity_id = 119838;";
		ResultSet rs = statement.executeQuery(queryString);
		//System.out.println(rs.getString(1));
		while(rs.next()) {
			cursuri=rs.getString(5);
			System.out.println("Complete courses: "+rs.getString(5));  
			 
		}
		
		String[] ary = cursuri.split(",");
		System.out.println("ary: "+Arrays.toString(ary));
		connection.close(); 
	}

	public static void main(String[] args) throws SQLException {
		PippaDBConnection.test();
	}
}
