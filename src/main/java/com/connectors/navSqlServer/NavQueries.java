package com.connectors.navSqlServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tools.data.navision.OrderStatusModel;
import com.tools.data.navision.SyncInfoModel;

public class NavQueries {

	private static Connection connection = SqlServerConnector.connectToSqlServer();

	public static OrderStatusModel getProductSyncronizedStatus(String sku) throws SQLException {

		OrderStatusModel result = new OrderStatusModel();

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

		Statement statement = connection.createStatement();
		String queryString = "select * from SyncInfoAut WHERE  SyncInfoAut.[Item No_] = '" + sku + "'";
		ResultSet rs = statement.executeQuery(queryString);
		while (rs.next()) {

			result.setSku(sku);
			result.setQuantity(rs.getString("Quantity"));
			result.setEarliestAvailability(rs.getString("Earliest Av_ Date"));
			result.setMinumimQuantity(rs.getString("Minimum Quantity"));
			result.setIsDiscontinued(rs.getString("Shop IsDiscontinued"));
			result.setTotalQuantity(rs.getString("Total Qty"));
			result.setMaxPercentToBorrow(rs.getString("Max_ Percent to Borrow"));
		}

		return result;

	}

	public static void main(String[] args) throws SQLException {
		SyncInfoModel result = NavQueries.getSyncProductInfo("A004BK");
		System.out.println(result.getQuantity());
		System.out.println(result.getEarliestAvailability());
		System.out.println(result.getMaxPercentToBorrow());

		OrderStatusModel result2 = NavQueries.getProductSyncronizedStatus("A004BK");
		System.out.println(result2.getSyncDate());
		System.out.println(result2.getSyncStatus());

	}

}
