package com.connectors.navSqlServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tools.data.navision.OrderStatusModel;
import com.tools.data.navision.SyncInfoModel;

public class NavQueries {

	private static Connection connection = SqlServerConnector.connectToSqlServer();

	public static OrderStatusModel getProductSyncronizedStatus(String orderId) throws SQLException {

		OrderStatusModel result = new OrderStatusModel();

		Statement statement = connection.createStatement();
		String queryString = "select * from OrderStatusAut WHERE OrderId = '" + orderId + "'";
		ResultSet rs = statement.executeQuery(queryString);
		while (rs.next()) {

			result.setSyncStatus(rs.getString("Synced"));
			result.setSyncDate(rs.getString("SyncDateTime"));
		}

		return result;

	}

	public static SyncInfoModel getSyncProductInfo(String sku, String variantCode) throws SQLException {

		SyncInfoModel result = new SyncInfoModel();

		Statement statement = connection.createStatement();
		String queryString = "select * from SyncInfoAut WHERE SyncInfoAut.[Variant Code] = '" + variantCode + "' AND SyncInfoAut.[Item No_] = '" + sku + "'";
		ResultSet rs = statement.executeQuery(queryString);
		while (rs.next()) {

			result.setSku(sku);
			result.setQuantity(rs.getString("Quantity"));
			String[] parts = rs.getString("Earliest Av_ Date").split(" ");
			result.setEarliestAvailability(parts[0]);
			result.setMinumimQuantity(rs.getString("Minimum Quantity"));
			result.setIsDiscontinued(rs.getString("Shop IsDiscontinued"));
			result.setTotalQuantity(rs.getString("Total Qty"));
			result.setMaxPercentToBorrow(rs.getString("Max_ Percent to Borrow"));
		}

		return result;

	}

	public static void main(String[] args) throws SQLException {
//		SyncInfoModel result = NavQueries.getSyncProductInfo("B098BK", "");
//		System.out.println(result.getQuantity());

		OrderStatusModel result2 = NavQueries.getProductSyncronizedStatus("STAGING-INT00012107");
		System.out.println(result2.getSyncDate());
		

	}

}
