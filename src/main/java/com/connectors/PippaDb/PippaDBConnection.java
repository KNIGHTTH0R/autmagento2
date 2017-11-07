package com.connectors.PippaDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.tools.data.UrlModel;
import com.tools.utils.DateUtils;

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

			connection = DriverManager.getConnection("jdbc:mysql://185.48.117.43:3306/pippajean_prod",
					"pippajeanprod", "3ZCb4n2z");

//			connection = DriverManager.getConnection("jdbc:mysql://staging.pippajean.com/pippajean_prod",
//					"pippajeancloud", "3H9e$Ne@");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}

		if (connection != null) {
			return connection;
		} else {
			System.out.println("Failed to make connection!");
		}
		return connection;

	}

	public static void deleteAcademyUserData(String attributeId, String entity_id) throws SQLException {
		Connection connection = PippaDBConnection.connectToMySql();
		Statement statement = connection.createStatement();
		String queryString = "delete FROM pippajean_prod.customer_entity_varchar where attribute_id =" + attributeId
				+ " and entity_id = " + entity_id + ";";
		// String queryString = "delete FROM
		// pippajean_prod.customer_entity_varchar where attribute_id = 298 and
		// entity_id = 119838;";
		statement.executeUpdate(queryString);
		System.out.println("Database updated");
		connection.close();
	}

	public static String completedCoursesInShop() throws SQLException {
		Map<String, String> curs = new HashMap<String, String>();
		curs.put("149", "1");
		curs.put("150", "2");
		curs.put("144", "3");
		curs.put("145", "4");
		curs.put("146", "5");
		curs.put("147", "6");
		curs.put("148", "7");

		String cursuri = "";
		Connection connection = PippaDBConnection.connectToMySql();
		Statement statement = connection.createStatement();
		String queryString = "select * from pippajean_prod.customer_entity_varchar where attribute_id = 298 and entity_id = 119838;";
		ResultSet rs = statement.executeQuery(queryString);
		// System.out.println(rs.getString(1));
		while (rs.next()) {
			cursuri = rs.getString(5);
			// System.out.println("Complete courses: "+rs.getString(5));

		}

		String[] ary = cursuri.split(",");
		System.out.println("ary: " + Arrays.toString(ary));
		connection.close();
		return Arrays.toString(ary);
	}

	public static void updateCustomerWebsite(String customerId, String website) throws SQLException {
		Connection connection = PippaDBConnection.connectToMySql();
		Statement statement = connection.createStatement();
		String queryString = "update pippajean_prod.customer_entity_varchar SET value='" + website
				+ "' where attribute_id=267 and entity_id=" + customerId + ";";
		statement.executeUpdate(queryString);
		System.out.println("Customer " + customerId + " updated with " + website + " website");
		connection.close();
	}

	public static void updateDateOnParty(String partyId) throws SQLException {
		String partyIde = partyId.replaceAll("\\D+", "");
		String date = DateUtils.getYesterdayDateString();
		Connection connection = PippaDBConnection.connectToMySql();
		Statement statement = connection.createStatement();
		String queryString = "update pippajean_prod.stylist_party SET party_date_time='" + date + "' where party_id="
				+ partyIde + ";";
		statement.executeUpdate(queryString);
		System.out.println("Party date was changed with: " + date);
		connection.close();
	}

	public static void updateCustomerStoreView(String customerId, String website, String storeView)
			throws SQLException {
		Connection connection = PippaDBConnection.connectToMySql();
		Statement statement = connection.createStatement();
		String queryString = "update pippajean_prod.customer_entity_varchar SET value='" + website + "_lang_"
				+ storeView + "' where attribute_id=266 and entity_id=" + customerId + ";";
		statement.executeUpdate(queryString);
		System.out
				.println("Customer " + customerId + " updated with " + website + "_lang_" + storeView + " store view");
		connection.close();

	}

	public static void main(String[] args) throws SQLException {

		// /PippaDBConnection.completedCoursesInShop();
		// PippaDBConnection.updateCustomerWebsite("122386","de");
		// PippaDBConnection.updateCustomerStoreView("122386", "de", "de");
		 PippaDBConnection.updateDateOnParty("http://staging-aut.pippajean.com/de/ioa/stylist/party/details/id/59667/");
	}
}
