package com.connectors.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector {
//
//	 public static void main(String[] args) {
//	
//	 String url =
//	 "jdbc:mysql://phpmyadmin.pippajean.com/pippajean_staging?";
//	 String driver = "com.mysql.jdbc.Driver";
//	 String userName = "pippajeanstaging";
//	 String password = "Raku7ach";
//	
//	 try {
//	 Class.forName(driver).newInstance();
//	 Connection conn = DriverManager.getConnection(url, userName, password);
//	 conn.close();
//	 } catch (Exception e) {
//	 e.printStackTrace();
//	 }
//	
//	 }

	public static void main(String[] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }
        
        try {
        	Connection conn =
               DriverManager.getConnection("jdbc:mysql://phpmyadmin.pippajean.com/pippajean_staging?" +
                                           "user=pippajeanstaging&password=Raku7ach");

            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
