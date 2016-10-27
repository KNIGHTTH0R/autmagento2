package com.connectors.navSqlServer;


public class MySqlConnector {

//	public static Connection connectToMySql() {
//
//		System.out.println("-------- MySQL JDBC Connection Started ------------");
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			System.out.println("No MySQL JDBC Driver was found!");
//			e.printStackTrace();
//
//		}
//
//		System.out.println("MySQL JDBC Driver Registered!");
//		Connection connection = null;
//
//		try {
//
//			connection = DriverManager.getConnection("jdbc:mysql://phpmyadmin.pippajean.com/pippajean_staging_aut_int","pippajeanstaging","Raku7ach");
//
//		} catch (SQLException e) {
//			System.out.println("Connection Failed! Check output console");
//			e.printStackTrace();
//		}
//
//		if (connection != null) {
//			System.out.println("Connected to sql server!");
//			return connection;
//		} else {
//			System.out.println("Failed to make connection!");
//		}
//		return connection;
//	}
//	
//	public static void main(String args[]){
//		MySqlConnector.connectToMySql();
//	}

}
