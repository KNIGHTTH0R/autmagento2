package com.connectors.navSqlServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.tools.env.constants.NavisionConstants;

public class SqlServerConnector {

	public static Connection connectToSqlServer() {

		System.out.println("-------- MySQL JDBC Connection Started ------------");

		try {
			Class.forName(NavisionConstants.DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("No MySQL JDBC Driver was found!");
			e.printStackTrace();

		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {

			connection = DriverManager.getConnection("jdbc:sqlserver://" + NavisionConstants.DOMAIN + ":" + NavisionConstants.PORT + ";databaseName=" + NavisionConstants.DB_NAME
					+ ";", NavisionConstants.DB_USERNAME, NavisionConstants.DB_PASSWORD);

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

}
