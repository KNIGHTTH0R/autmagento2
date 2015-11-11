package com.tests.uss23;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApiCalls;
import com.connectors.navSqlServer.NavQueries;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.SoapKeys;
import com.tools.calculation.StockCalculations;
import com.tools.data.backend.OrderModel;
import com.tools.data.navision.OrderStatusModel;
import com.tools.data.navision.SyncInfoModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.workflows.stockSynk.StockSyncValidations;

@WithTag(name = "US23.1 Stock Sync", type = "Scenarios")
@Story(Application.StockSync.US23_1.class)
@RunWith(ThucydidesRunner.class)
public class US23001VerifyStockSyncAfterOrderImportTest extends BaseTest {

	@Steps
	StockSyncValidations stockSyncValidations;
	@Steps
	public CustomVerification customVerifications;

	List<SyncInfoModel> initialChangingMagentoProducts = new ArrayList<SyncInfoModel>();
	List<SyncInfoModel> initialChangingNavProducts = new ArrayList<SyncInfoModel>();
	List<SyncInfoModel> initialConstantMagentoProducts = new ArrayList<SyncInfoModel>();
	List<SyncInfoModel> initialConstantNavProducts = new ArrayList<SyncInfoModel>();

	private List<SyncInfoModel> changingStockMagentoProducts = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> changingStockNavProduct = new ArrayList<SyncInfoModel>();

	private List<SyncInfoModel> constantStockMagentoProducts = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> constantStockNavProducts = new ArrayList<SyncInfoModel>();

	private static List<String> changingStockIdList = new ArrayList<String>(Arrays.asList("1292", "1658"));
	private static List<String> changingStockSkuList = new ArrayList<String>(Arrays.asList("R065SV-18", "N093SV"));

	private static List<String> constantStockIdList = new ArrayList<String>(Arrays.asList("5037"));
	private static List<String> constantStockSkuList = new ArrayList<String>(Arrays.asList("M164"));

	private OrderModel orderModel;
	String syncDate;
	boolean isSyncronyzed = false;

	@Before
	public void setUp() throws Exception {

		orderModel = MongoReader.getOrderModel("US23001BuyProductsOnShopforMyselfTest").get(0);
		OrderStatusModel orderStatusModel = NavQueries.getProductSyncronizedStatus(orderModel.getOrderId().toUpperCase());
		System.out.println(orderStatusModel.getSyncDate());
		String[] parts = orderStatusModel.getSyncDate().split(".");
		syncDate = parts[1];
		System.out.println(parts[1]);

		initialChangingMagentoProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeOrderTest" + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		initialChangingNavProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeOrderTest" + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK);
		initialConstantMagentoProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeOrderTest" + SoapKeys.MAGENTO_INITIAL_CONSTANT_STOCK);
		initialConstantNavProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeOrderTest" + SoapKeys.NAVISION_INITIAL_CONSTANT_STOCK);

		initialChangingNavProducts = StockCalculations.calculateNewStock(initialChangingNavProducts, "-1", false);
		initialConstantNavProducts = StockCalculations.calculateNewStock(initialConstantNavProducts, "-1", true);

		for (String id : changingStockIdList) {
			changingStockMagentoProducts.add(ApiCalls.getMagProductInfo(id));
		}
		for (String sku : changingStockSkuList) {
			String[] skuParts = sku.split("-");
			changingStockNavProduct.add(NavQueries.getSyncProductInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}
		for (String id : constantStockIdList) {
			constantStockMagentoProducts.add(ApiCalls.getMagProductInfo(id));
		}
		for (String sku : constantStockSkuList) {
			String[] skuParts = sku.split("-");
			constantStockNavProducts.add(NavQueries.getSyncProductInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}

		changingStockMagentoProducts = StockCalculations.calculateStockBasedOnPendingOrders(changingStockMagentoProducts);
		constantStockMagentoProducts = StockCalculations.calculateStockBasedOnPendingOrders(constantStockMagentoProducts);

		if (DateUtils.isDateAfter(DateUtils.getCurrentDate("MM/dd/YYYY HH:mm:ss"), syncDate, "MM/dd/YYYY HH:mm:ss") && orderStatusModel.getSyncStatus().contentEquals("Yes")) {
			isSyncronyzed = true;
		}

		System.out.println("isSyncronyzed: " + isSyncronyzed);

	}

	@Test
	public void us23001VerifyStockSyncAfterOrderImportTest() throws SQLException {

		if (isSyncronyzed) {

			stockSyncValidations.setValidateProductsModels(initialChangingNavProducts, changingStockNavProduct);
			stockSyncValidations.validateProducts("VALIDATE NAVISION STOCK IS DECREASED - CHANGING STOCK NAVISION PRODUCTS");

			stockSyncValidations.setValidateProductsModels(initialConstantNavProducts, constantStockNavProducts);
			stockSyncValidations.validateProducts("VALIDATE NAVISION STOCK IS THE SAME - CONSTANT STOCK NAVISION PRODUCTS");

			stockSyncValidations.setValidateProductsModels(changingStockNavProduct, changingStockMagentoProducts);
			stockSyncValidations.validateProducts("VALIDATE MAGENTO STOCK IS SYNCRONIZED WITH MAGENTO STOCK - CHANGING STOCK PRODUCTS");

			stockSyncValidations.setValidateProductsModels(constantStockNavProducts, constantStockMagentoProducts);
			stockSyncValidations.validateProducts("VALIDATE MAGENTO STOCK IS SYNCRONIZED WITH MAGENTO STOCK - CONSTANT STOCK PRODUCTS");

			customVerifications.printErrors();
		}
	}
}
