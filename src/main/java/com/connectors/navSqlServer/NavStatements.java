package com.connectors.navSqlServer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class NavStatements {

	public static void getProductSyncronizedStatus(String sku) throws SQLException {

		Connection connection = SqlServerConnector.connectToSqlServer();
		Statement statement = connection.createStatement();
		String queryString = "select * from OrderStatusAut WHERE ItemNo = '" + sku + "'";
		ResultSet rs = statement.executeQuery(queryString);
		while (rs.next()) {
			System.out.print(rs.getString("Synced"));
			System.out.print(" ");
			System.out.print(rs.getString("SyncDateTime"));
			System.out.println();
		}

	}

	public static void main(String[] args) throws SQLException {
		NavStatements.getProductSyncronizedStatus("R132RS");
	}

}
