package com.connectors.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnector {

	public static void main(String[] args) {

		String url = "jdbc:mysql://phpmyadmin.pippajean.com/index.php?pippajean_staging";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "pippajeanstaging";
		String password = "Raku7ach";

		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url, userName, password);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
