package com.connectors.PippaDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tools.data.soap.ProductDetailedModel;
import com.tools.utils.DateUtils;

public class VanDelVeldeDBConnection {
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

			// Vdv -aut
			 connection = DriverManager.getConnection(
			 "jdbc:mysql://81.173.114.77:3306/vdv_qa_aut",
			 "vdv_aut", "44Wd3xx67");
			 

			/*connection = DriverManager.getConnection(
					"jdbc:mysql://staging.pippajean.com/pippajean_prod?zeroDateTimeBehavior=convertToNull",
					"pippajeancloud", "3H9e$Ne@");*/

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
		Connection connection = VanDelVeldeDBConnection.connectToMySql();
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
		Connection connection = VanDelVeldeDBConnection.connectToMySql();
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
		Connection connection = VanDelVeldeDBConnection.connectToMySql();
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
		Connection connection = VanDelVeldeDBConnection.connectToMySql();
		Statement statement = connection.createStatement();
		String queryString = "update vdv_qa_aut.stylist_party SET party_date_time='" + date + "' where party_id="
				+ partyIde + ";";
		statement.executeUpdate(queryString);
		System.out.println("Party date was changed with: " + date);
		connection.close();
	}
	
	
	public static void updateCloseParty(String partyId) throws SQLException {
		// TODO Auto-generated method stub
		String partyIde = partyId.replaceAll("\\D+", "");
		String date = DateUtils.getYesterdayDateString();
		Connection connection = VanDelVeldeDBConnection.connectToMySql();
		Statement statement = connection.createStatement();
		String queryString = "update vdv_qa_aut.stylist_party SET closed_at='" + date + "' where party_id="
				+ partyIde + ";";
		statement.executeUpdate(queryString);
		System.out.println("Party was closed in: " + date);
		connection.close();
	}

	public static void updateCustomerStoreView(String customerId, String website, String storeView)
			throws SQLException {
		Connection connection = VanDelVeldeDBConnection.connectToMySql();
		Statement statement = connection.createStatement();
		String queryString = "update pippajean_prod.customer_entity_varchar SET value='" + website + "_lang_"
				+ storeView + "' where attribute_id=266 and entity_id=" + customerId + ";";
		statement.executeUpdate(queryString);
		System.out
				.println("Customer " + customerId + " updated with " + website + "_lang_" + storeView + " store view");
		connection.close();

	}

	public static List<ProductDetailedModel> productList(String productIdFrom, String productIdTo) throws SQLException {
		List<ProductDetailedModel> productList = new ArrayList<ProductDetailedModel>();

		Connection connection = VanDelVeldeDBConnection.connectToMySql();
		Statement statement = connection.createStatement();
		
		String queryString = "SELECT e.catalog_row_number,e.catalog_page_number, e.sku, e.name,cisi.product_id,cisi.qty,cisi.min_qty,cisi.is_in_stock,cisi.is_discontinued,cisi.earliest_availability,GROUP_CONCAT(ccp.category_id, ', ') AS category_list "
				+ "FROM `catalog_product_flat_7` AS `e` "
				+ "INNER JOIN `catalog_product_website` AS `product_website` ON product_website.product_id = e.entity_id AND product_website.website_id IN('1') "
				+ "LEFT JOIN `cataloginventory_stock_item` AS `cisi` ON cisi.product_id = e.entity_id "
				+ " LEFT JOIN `catalog_category_product` AS `ccp` ON ccp.product_id = cisi.product_id "
				+ "WHERE (e.status = 1) AND (e.type_id = 'simple') AND (e.status = '1') AND cisi.product_id>="
				+ productIdFrom + " and cisi.product_id<=" + productIdTo
				+ " GROUP BY `e`.`entity_id` ORDER BY `e`.`sku` ASC ;";

		ResultSet rs = statement.executeQuery(queryString);
		String defaultDate = "1960-11-24 00:00:00.0";
		while (rs.next()) {
			ProductDetailedModel product = new ProductDetailedModel();
			product.setSku(rs.getString("sku"));
			product.setName(rs.getString("name"));
			product.setProductId(rs.getString("product_id"));
			product.setQuantity(rs.getString("qty"));
			product.setMinQty(rs.getString("min_qty"));
			product.setIsDiscountinued(rs.getString("is_discontinued"));
			product.setIsInStock(rs.getString("is_in_stock"));
			product.setCatalogPageNumber(rs.getString("catalog_page_number") != null?rs.getString("catalog_page_number"):"");
			product.setCatalogRowNumber(rs.getString("catalog_row_number") != null?rs.getString("catalog_row_number"):"");
			product.setEarliestAvDate(rs.getString("earliest_availability") == null?defaultDate:rs.getString("earliest_availability"));
			

			if (rs.getString("category_list") != null) {
				List<String> categoryList = new ArrayList<String>();
				List<String> list = new ArrayList<String>();
				list = Arrays.asList(rs.getString("category_list").split(","));
				for (String category : list) {
					categoryList.add(category.replaceAll("\\s+", ""));
				}
				product.setCategoriesArray(categoryList);
			}

			productList.add(product);
		}

		System.out.println(productList.size());
		connection.close();
		return productList;

	}

	public static List<ProductDetailedModel> productBackInStockList() throws SQLException {
		List<ProductDetailedModel> productList = new ArrayList<ProductDetailedModel>();
		String date = DateUtils.getCurrentDate("yyyy-MM-dd");
		Connection connection = VanDelVeldeDBConnection.connectToMySql();
		Statement statement = connection.createStatement();
		

		String queryString = "SELECT `e`.`entity_id`, `e`.`type_id`, `e`.`attribute_set_id`, `e`.`entity_id`, `e`.`status`, `e`.`sku`, `e`.`name`, `e`.`news_from_date`, `e`.`news_to_date`, `e`.`catalog_page_number`, `e`.`catalog_row_number`, `e`.`created_at`, `catalog_product_parent`.`parent_id`, `ccp`.*, group_concat(if (coalesce(cce_2.value,cce_1.value),coalesce(cc_2.value,cc_1.value,''),'') SEPARATOR ';') AS `category_names`, `cish`.`product_id`, `cish`.`stock_item_id`, `cish`.`last_in_stock`, `cish2`.`last_out_of_stock` FROM `catalog_product_flat_7` AS `e` "
				+ "INNER JOIN `catalog_product_website` AS `product_website` ON product_website.product_id = e.entity_id AND product_website.website_id IN('1') LEFT JOIN `catalog_product_super_link` AS `catalog_product_parent` ON catalog_product_parent.product_id = e.entity_id LEFT JOIN `catalog_category_product` AS `ccp` ON ccp.product_id = e.entity_id LEFT JOIN `catalog_category_entity_varchar` AS `cc_1` ON ccp.category_id=cc_1.entity_id and cc_1.attribute_id=35 and cc_1.store_id = 0 "
				+ "LEFT JOIN `catalog_category_entity_varchar` AS `cc_2` ON ccp.category_id=cc_2.entity_id and cc_2.attribute_id=35 and cc_2.store_id = 7 LEFT JOIN `catalog_category_entity_int` AS `cce_1` ON ccp.category_id=cce_1.entity_id and cce_1.attribute_id=36 and cce_1.store_id = 0 "
				+ "LEFT JOIN `catalog_category_entity_int` AS `cce_2` ON ccp.category_id=cce_2.entity_id and cce_2.attribute_id=36 and cce_2.store_id = 7 LEFT JOIN `cataloginventory_stock_history` AS `cish` ON cish.entity_id = ( SELECT entity_id  FROM cataloginventory_stock_history WHERE product_id = e.entity_id and last_in_stock IS NOT NULL ORDER BY entity_id DESC LIMIT 1  ) "
				+ "LEFT JOIN `cataloginventory_stock_history` AS `cish2` ON cish2.entity_id =( SELECT entity_id FROM cataloginventory_stock_history WHERE product_id = e.entity_id and last_out_of_stock IS NOT NULL ORDER BY entity_id DESC  LIMIT 1 )"
				+ "INNER JOIN `cataloginventory_stock_item` AS `cis` ON cis.product_id = cish.product_id and cis.item_id = cish.stock_item_id and cis.is_in_stock = 1 and cis.qty > 0 WHERE (e.status = 1) AND (e.type_id = 'simple') AND (e.status = '1') AND (category_id != '99' or category_id is null) AND (category_id != '55' or category_id is null) AND (category_id != '40' or category_id is null) AND (category_id != '23' or category_id is null) AND (category_id != '15' or category_id is null) AND (category_id != '41' or category_id is null) AND (category_id != '74' or category_id is null) AND (category_id != '42' or category_id is null) AND (category_id != '51' or category_id is null) AND (category_id != '68' or category_id is null) AND (category_id != '97' or category_id is null) AND (category_id != '98' or category_id is null) AND (category_id != '52' or category_id is null) AND (cish.status = 1) AND (cish.last_in_stock IS NOT NULL and cish2.last_out_of_stock IS NOT NULL)"
				+ " AND (TIMESTAMPDIFF(DAY,DATE(cish.last_in_stock),'" + date
				+ "') <= '7') AND (TIMESTAMPDIFF(HOUR,cish2.last_out_of_stock,cish.last_in_stock) >= 0) GROUP BY `e`.`entity_id` ORDER BY `e`.`sku` ASC;";

		ResultSet rs = statement.executeQuery(queryString);
		while (rs.next()) {
			List<String> list = new ArrayList<String>();
			ProductDetailedModel product = new ProductDetailedModel();
			product.setProductId(rs.getString("product_id"));
			product.setSku(rs.getString("sku"));
			product.setName(rs.getString("name"));
			product.setCatalogRowNumber("");
			product.setCatalogPageNumber("");
			product.setStatus("Back in stock");
			product.setIsDiscountinued("0");
			list = Arrays.asList(rs.getString("category_names").split(","));
			product.setCategoriesArray(list);
			productList.add(product);
		}

		System.out.println(productList.size());
		connection.close();
		return productList;

	}

	public static void main(String[] args) throws SQLException {

		
		VanDelVeldeDBConnection.productList("1", "700");
	}

	
}
