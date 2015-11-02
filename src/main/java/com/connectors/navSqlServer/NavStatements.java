package com.connectors.navSqlServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tools.data.navision.OrderStatusModel;
import com.tools.data.navision.SyncInfoModel;

public class NavStatements {

	public static OrderStatusModel getProductSyncronizedStatus(String sku) throws SQLException {

		OrderStatusModel result = new OrderStatusModel();

		Connection connection = SqlServerConnector.connectToSqlServer();
		Statement statement = connection.createStatement();
		String queryString = "select * from OrderStatusAut WHERE ItemNo = '" + sku + "'";
		ResultSet rs = statement.executeQuery(queryString);
		while (rs.next()) {
			result.setSyncStatus(rs.getString("Synced"));
			result.setSku(sku);
			result.setSyncDate(rs.getString("SyncDateTime"));
		}

		return result;

	}

	public static SyncInfoModel getSyncProductInfo(String sku) throws SQLException {

		SyncInfoModel result = new SyncInfoModel();

		Connection connection = SqlServerConnector.connectToSqlServer();
		Statement statement = connection.createStatement();
		String queryString = "select * from SyncInfoAut WHERE ItemNo = '" + sku + "'";
		ResultSet rs = statement.executeQuery(queryString);
		while (rs.next()) {
			result.setSku(sku);
			result.setQuantity(rs.getString("Quantity"));
		}

		return result;

	}

	public static void main(String[] args) throws SQLException {
		OrderStatusModel result = NavStatements.getProductSyncronizedStatus("R132RS");
		System.out.println(result.getSyncStatus());
	}

}
