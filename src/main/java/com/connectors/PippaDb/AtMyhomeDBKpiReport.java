package com.connectors.PippaDb;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tools.utils.DateUtils;

public class AtMyhomeDBKpiReport {

	private static Connection connection;
	private static Statement statement;
	private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
	private static String result = "0";
	private static List<String> performingSFList = new ArrayList<String>();
	private static List<String> testersList = new ArrayList<String>();

	public static void main(String[] args) throws SQLException, ParseException {
		// System.out.println();
		String kpi_report_month = "2018-06-30 12:00:00";
		String firstday = DateUtils.getFirstDayOfAGivenMonth(kpi_report_month, dateFormat);
		String lastDay = DateUtils.getLastDayOfAGivenMonth(kpi_report_month, dateFormat);
	/*	String firstdayPrev = DateUtils.getFirstDayOfPreviousMonth(kpi_report_month, dateFormat);
		String lastDayPrev = DateUtils.getLastDayOfPreviousMonth(kpi_report_month, dateFormat);*/

		// String average party = getAverageGuest()/getTotalParties()
		// String average buying party = getAverageBuyingGuest/getTotalParties()

		
		
	//	String[] plannedInWeek2 = DateUtils.getASpecifWeekAfterCurrentDate("yyyy-MM-dd HH:mm:ss", "5");
		
		
		AtMyhomeDBKpiReport kpi = new AtMyhomeDBKpiReport();

		System.out.println(kpi.getPerformingSfExclDeactivated(firstday, lastDay));

		
		kpi.ConnectionClose();
		// System.out.println(kpi.getStylistWithAtLeastOneInvoice("", ""));

		/*
		 * calculate Performing SF excl.deactivated :
		 * 
		 * performingSFList.addAll(kpi.getStylistWithAtLeastOneInvoice("", ""));
		 * performingSFList.addAll(kpi.getStylistWithAtLeastOneOrder("", ""));
		 * performingSFList.addAll(kpi.getStylistWithAtLeastOneParty("", ""));
		 * 
		 * 
		 * List<String> listWithoutDuplicates =
		 * performingSFList.stream().distinct().collect(Collectors.toList());
		 * System.out.println(listWithoutDuplicates.size());
		 * 
		 */

	}
	
	
	public String getPerformingSfExclDeactivated(String firstdayOfMonth, String lastDayOfMonth) throws SQLException {
		 performingSFList.addAll(AtMyhomeDBKpiReport.getStylistWithAtLeastOneInvoice(firstdayOfMonth, lastDayOfMonth));
		 performingSFList.addAll(AtMyhomeDBKpiReport.getStylistWithAtLeastOneOrder(firstdayOfMonth, lastDayOfMonth));
		 performingSFList.addAll(AtMyhomeDBKpiReport.getStylistWithAtLeastOneParty(firstdayOfMonth, lastDayOfMonth));
		
		 testersList=getTesterStylists();
		 
		 List<String> listWithoutDuplicates =
		  performingSFList.stream().distinct().collect(Collectors.toList());
		 
		 listWithoutDuplicates.removeAll(testersList);
		 
		 
		return String.valueOf(listWithoutDuplicates.size());
	}

	public AtMyhomeDBKpiReport() throws SQLException, ParseException {
		connection = VanDelVeldeDBConnection.connectToMySql();
		statement = connection.createStatement();
	
		/*String kpi_report_month = "2018-06-30 12:00:00";
		String firstDayCurrentMonth = DateUtils.getFirstDayOfAGivenMonth(kpi_report_month, dateFormat);
		String lastDayCurrentMonth = DateUtils.getLastDayOfAGivenMonth(kpi_report_month, dateFormat);
		String firstdayOfPrevMonth = DateUtils.getFirstDayOfPreviousMonth(kpi_report_month, dateFormat);
		String lastDayOfPrevMonth = DateUtils.getLastDayOfPreviousMonth(kpi_report_month, dateFormat);*/

	}
	
	public void ConnectionClose() throws SQLException {
		connection.close();

	}

	public String getSalesforceBeginning(String firstdayOfPrevMonth, String lastDayOfPrevMonth)
			throws SQLException, ParseException {

		String queryString = "select count(*) from vdv_stage.stylist\n" + 
				" where\n" + 
				" not exists (SELECT * FROM vdv_stage.customer_entity_int where stylist.customer_id=customer_entity_int.entity_id and attribute_id=352 and value=1 ) \n" + 
				"and \n" + 
				" not exists (SELECT * FROM vdv_stage.customer_entity_datetime where vdv_stage.stylist.customer_id=vdv_stage.customer_entity_datetime.entity_id and attribute_id=209 and value<'"+lastDayOfPrevMonth+"' )\n" + 
				"\n" + 
				" and status = '1'  and activated_at<'"+lastDayOfPrevMonth+"'";
		ResultSet rs = statement.executeQuery(queryString);
		while (rs.next()) {
			result = rs.getString(1);
		}
		return result;
	}

	public String getRecruits(String firstdayOfMonth, String lastDayOfMonth) throws SQLException {

		String queryString = "select count(*) from stylist where  not exists (SELECT * FROM vdv_stage.customer_entity_int where stylist.customer_id=customer_entity_int.entity_id"
				+ " and attribute_id=352 and value=1 ) and status = '1' and activated_at>='"+firstdayOfMonth+"' and activated_at<='"+lastDayOfMonth+"';\n" + 
				"";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			result = rs.getString(1);
		}

		return result;
	}

	public String getDeactivated(String firstdayOfMonth, String lastDayOfMonth) throws SQLException {

		String queryString = "select count(*) from stylist\n" + 
				"where\n" + 
				" not exists (SELECT * FROM vdv_stage.customer_entity_int where stylist.customer_id=customer_entity_int.entity_id and attribute_id=352 and value=1 ) \n" + 
				"and exists (SELECT * FROM vdv_stage.customer_entity_datetime where stylist.customer_id=customer_entity_datetime.entity_id and attribute_id=209 and value>='"+firstdayOfMonth+"' and value<='"+lastDayOfMonth+"')\n" + 
				"and status=0;";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			result = rs.getString(1);
		}


		return result;
	}

	public String getSfEnding(String salesForceBeggining, String recruits,String deactivated) throws SQLException {

		int result = Integer.parseInt(salesForceBeggining) + Integer.parseInt(recruits)- Integer.parseInt(deactivated);

		return String.valueOf(result);
	}

	/// need calculation
/*	public String getPerformingSfExclDeactivated(String firstdayOfMonth, String lastDayOfMonth) throws SQLException {

		String queryString = "select count(*)  from stylist where status = '1' and activated_at>='" + firstdayOfMonth
				+ "' and activated_at<='" + lastDayOfMonth + "' and status  not in (1) ;";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			result = rs.getString(1);
		}


		return result;
	}*/

	public static List<String> getStylistWithAtLeastOneInvoice(String firstdayOfMonth, String lastDayOfMonth)
			throws SQLException {

		List<String> listResult = new ArrayList<String>();
		String queryString = "select distinct (sales_flat_order.stylist_id) from sales_flat_order inner join sales_flat_invoice on sales_flat_order.entity_id = sales_flat_invoice.order_id \n" + 
				"where not exists (SELECT * FROM vdv_stage.customer_entity_int where sales_flat_order.customer_id=customer_entity_int.entity_id and attribute_id=352 and value=1 ) and sales_flat_invoice.created_at > '"+firstdayOfMonth+"' and sales_flat_invoice.created_at < '"+lastDayOfMonth+"';";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			listResult.add(rs.getString(1));
		}

		System.out.println(listResult);
		return listResult;
	}
	
	public static List<String> getTesterStylists()
			throws SQLException {

		List<String> listResult = new ArrayList<String>();
		String queryString = "SELECT stylist.stylist_id FROM vdv_stage.customer_entity_int inner join stylist on stylist.customer_id=customer_entity_int.entity_id\n" + 
				" and  attribute_id=352 and value=1;";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			listResult.add(rs.getString(1));
		}

		System.out.println(listResult);
		return listResult;
	}
	
	

	public static List<String> getStylistWithAtLeastOneOrder(String firstdayOfMonth, String lastDayOfMonth)
			throws SQLException {

		List<String> listResult = new ArrayList<String>();
		String queryString = "select distinct (stylist.stylist_id) from stylist inner join sales_flat_order on stylist.stylist_id = sales_flat_order.stylist_id where  not exists (SELECT * FROM vdv_stage.customer_entity_int where stylist.customer_id=customer_entity_int.entity_id and attribute_id=352 and value=1 )  and sales_flat_order.created_at > '"+firstdayOfMonth+"' and sales_flat_order.created_at < '"+lastDayOfMonth+"' and stylist.status='1' ;\n" + 
				"";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			listResult.add(rs.getString(1));
		}
		System.out.println(listResult);

		return listResult;
	}

	public static List<String> getStylistWithAtLeastOneParty(String firstdayOfMonth, String lastDayOfMonth)
			throws SQLException {
		

		List<String> listResult = new ArrayList<String>();
		String queryString = "select distinct (stylist.stylist_id) from stylist \n" + 
				"inner join stylist_party \n" + 
				"on stylist.stylist_id = stylist_party.stylist_id\n" + 
				"where\n" + 
				"not exists (SELECT * FROM vdv_stage.customer_entity_int where stylist.customer_id=customer_entity_int.entity_id and attribute_id=352 and value=1 )\n" + 
				"and\n" + 
				" stylist_party.deleted_at is null \n" + 
				"and stylist_party.closed_at is not null \n" + 
				"and stylist_party.party_date_time > '"+firstdayOfMonth+"' \n" + 
				"and stylist_party.party_date_time < '"+lastDayOfMonth+"'\n" + 
				"and stylist.status='1'; ";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			listResult.add(rs.getString(1));
		}

		System.out.println(listResult);

		return listResult;
	}

	public String getTotalParties(String firstdayOfMonth, String lastDayOfMonth) throws SQLException {

		String queryString = "SELECT count( distinct vdv_stage.stylist_party.party_id)  FROM vdv_stage.stylist_party INNER JOIN vdv_stage.sales_flat_order ON vdv_stage.stylist_party.party_id = vdv_stage.sales_flat_order.style_party_id \n" + 
				"where not exists (SELECT * FROM vdv_stage.customer_entity_int where sales_flat_order.customer_id=customer_entity_int.entity_id and attribute_id=352 and value=1 )\n" + 
				"and vdv_stage.sales_flat_order.grand_total>0 \n" + 
				"and vdv_stage.stylist_party.closed_at >'"+firstdayOfMonth+"'  and vdv_stage.stylist_party.closed_at <'"+lastDayOfMonth+"' and deleted_at is null";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			result = rs.getString(1);
		}

		return result;
	}

	public String getAveragePartiesPerActiveSF(String totalParties, String performingSF) throws SQLException {

		double result = Double.valueOf(totalParties) / Double.valueOf(performingSF);

		return String.valueOf(result);
	}

	public String getTotalFollowUpPaties(String firstdayOfMonth, String lastDayOfMonth) throws SQLException {

		String queryString = "select * FROM vdv_stage.stylist_party  \n" + 
				"inner join vdv_stage.stylist on stylist_party.stylist_id=stylist.stylist_id\n" + 
				"where  not exists (SELECT * FROM vdv_stage.customer_entity_int where stylist.customer_id=customer_entity_int.entity_id\n" + 
				"and attribute_id=352 and value=1 )\n" + 
				"and parent_party_id  != 0  and deleted_at is null \n" + 
				"and party_date_time>'"+firstdayOfMonth+"' and party_date_time<'"+lastDayOfMonth+"';";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			result = rs.getString(1);
		}

		return result;
	}

	public String getAverageFollowUpPartiesPerActiveSF(String followUpTotalParties, String performingSF)
			throws SQLException {

		double result = Double.valueOf(followUpTotalParties) / Double.valueOf(performingSF);

		return BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String getGuestNo(String firstdayOfMonth, String lastDayOfMonth) throws SQLException {

		String queryString = "select sum(stylist_party.guest_count) from stylist_party \n" + 
				"inner join vdv_stage.stylist on stylist_party.stylist_id=stylist.stylist_id\n" + 
				"where  not exists (SELECT * FROM vdv_stage.customer_entity_int where stylist.customer_id=customer_entity_int.entity_id and attribute_id=352 and value=1 )\n" + 
				"and stylist_party.deleted_at is null \n" + 
				"and stylist_party.closed_at > '"+firstdayOfMonth+"' \n" + 
				"and stylist_party.closed_at < '"+lastDayOfMonth+"';";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			result = rs.getString(1);
		}

		return result;
	}

	public String getBuyingGuestNo(String firstdayOfMonth, String lastDayOfMonth) throws SQLException {

		String queryString = "select count(sales_flat_order.increment_id) from vdv_stage.sales_flat_order inner join vdv_stage.stylist_party on vdv_stage.sales_flat_order.style_party_id= vdv_stage.stylist_party.party_id \n" + 
				"where  not exists (SELECT * FROM vdv_stage.customer_entity_int where sales_flat_order.customer_id=customer_entity_int.entity_id and attribute_id=352 and value=1 ) \n" + 
				"and sales_flat_order. cart_type !='4'\n" + 
				"and vdv_stage.stylist_party.closed_at >'"+firstdayOfMonth+"' \n" + 
				"and  vdv_stage.stylist_party.closed_at<'"+lastDayOfMonth+"';";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			result = rs.getString(1);
		}

		return result;
	}

	public String getBuyingRate(String averageBuyingGuest, String averageGuest) throws SQLException {

		double result = Double.valueOf(averageBuyingGuest) / Double.valueOf(averageGuest);

		return BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String getPartyBeforeReturnsIncVat(String firstdayOfMonth, String lastDayOfMonth) throws SQLException {

		String queryString = "select sum(sales_flat_order.grand_total) from sales_flat_order \n" + 
				"				inner join stylist_party on sales_flat_order.style_party_id = stylist_party.party_id\n" + 
				"				and sales_flat_order.cart_type in (1,3,4) \n" + 
				"				and sales_flat_order.style_party_id is not null \n" + 
				"				and stylist_party.deleted_at is null \n" + 
				"				and stylist_party.closed_at >= '"+firstdayOfMonth+"'\n" + 
				"				and stylist_party.closed_at <= '"+lastDayOfMonth+"'  and not exists (SELECT * FROM vdv_stage.customer_entity_int where sales_flat_order.customer_id=customer_entity_int.entity_id and attribute_id=352 and value=1 ); ";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			result = rs.getString(1);
		}

		if(result==null) {
			result="0";
		}
		
		return result;
	}

	
	public String getOnlineSalesInclVat(String firstdayOfMonth, String lastDayOfMonth) throws SQLException {

		String queryString = "SELECT sum(grand_total) FROM vdv_stage.sales_flat_order\n" + 
				"where  not exists (SELECT * FROM vdv_stage.customer_entity_int where sales_flat_order.customer_id=customer_entity_int.entity_id and attribute_id=352 and value=1 ) \n" + 
				"and  order_type in (1,2) and cart_type in (1,3) and style_party_id is null and created_at>='"+firstdayOfMonth+"' and created_at<='"+lastDayOfMonth+"';";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			result = rs.getString(1);
		}
		if(result==null) {
			result="0";
		}
		
		return BigDecimal.valueOf(Double.valueOf(result)).setScale(2,RoundingMode.HALF_UP).toString();
	}
	
	
	public String getSfmInclVat(String firstdayOfMonth, String lastDayOfMonth) throws SQLException {

		String queryString = "SELECT sum(grand_total) FROM vdv_stage.sales_flat_order \n" + 
				"where  not exists (SELECT * FROM vdv_stage.customer_entity_int where sales_flat_order.customer_id=customer_entity_int.entity_id and attribute_id=352 and value=1 ) \n" + 
				"and cart_type= '2' and order_type='2' and created_at>'"+firstdayOfMonth+"' and created_at<'"+lastDayOfMonth+"';";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			result = rs.getString(1);
		}

		if(result==null) {
			result="0";
		}
		return BigDecimal.valueOf(Double.valueOf(result)).setScale(2,RoundingMode.HALF_UP).toString();
	}
	
	public String getReturnInclVat(String firstdayOfMonth, String lastDayOfMonth) throws SQLException {

		String queryString = "select sum(sales_flat_creditmemo.grand_total) \n" + 
				"from sales_flat_creditmemo  \n" + 
				"inner join sales_flat_order \n" + 
				"on sales_flat_creditmemo.order_id=sales_flat_order.entity_id\n" + 
				"where not exists (SELECT * FROM vdv_stage.customer_entity_int where sales_flat_order.customer_id=customer_entity_int.entity_id and attribute_id=352 and value=1 )\n" + 
				"and sales_flat_creditmemo.created_at >= '"+firstdayOfMonth+"'\n" + 
				"and sales_flat_creditmemo.created_at <= '"+lastDayOfMonth+"'\n" + 
				"and sales_flat_creditmemo.state=2;";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			result = rs.getString(1);
		}
		if(result==null) {
			result="0";
		}
		
		return BigDecimal.valueOf(Double.valueOf(result)).setScale(2,RoundingMode.HALF_UP).toString();
	}
	
	
	public String getPlannedPartyInMonth(String weekLimit1, String weekLimit2) throws SQLException {

		String queryString = "select count(*) from stylist_party inner join stylist\n" + 
				"on stylist_party.stylist_id=stylist.stylist_id\n" + 
				"where\n" + 
				"not exists (SELECT * FROM vdv_stage.customer_entity_int where stylist.customer_id=customer_entity_int.entity_id and attribute_id=352 and value=1 )\n" + 
				"and stylist_party.party_date_time>'"+weekLimit1+"' and stylist_party.party_date_time<'"+weekLimit2+"' and deleted_at is null;";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			result = rs.getString(1);
		}

		return result;
	}
	
	public String getPlannedPartyInWeek(String weekLimit1, String weekLimi2) throws SQLException {
		

		String queryString = "select count(*) from stylist_party inner join stylist on stylist_party.stylist_id=stylist.stylist_id \n" + 
				"where  not exists (SELECT * FROM vdv_stage.customer_entity_int where stylist.customer_id=customer_entity_int.entity_id and attribute_id=352 and value=1 ) \n" + 
				"and  stylist_party.party_date_time>'"+weekLimit1+"' and stylist_party.party_date_time<'"+weekLimi2+"' and deleted_at is null;";
		ResultSet rs = statement.executeQuery(queryString);

		while (rs.next()) {
			result = rs.getString(1);
		}

		return result;
	}
	
}
