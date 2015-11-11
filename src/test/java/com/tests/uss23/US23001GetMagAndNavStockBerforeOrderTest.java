package com.tests.uss23;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApiCalls;
import com.connectors.mongo.MongoConnector;
import com.connectors.navSqlServer.NavQueries;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.tests.BaseTest;
import com.tools.SoapKeys;
import com.tools.data.navision.SyncInfoModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US23.1 Stock Sync", type = "Scenarios")
@Story(Application.StockSync.US23_1.class)
@RunWith(ThucydidesRunner.class)
public class US23001GetMagAndNavStockBerforeOrderTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;

	private List<SyncInfoModel> magentoProductsToBeDeacreased = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> navProductsToBeDecreased = new ArrayList<SyncInfoModel>();

	private List<SyncInfoModel> constantStockMagentoProducts = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> constantStockNavProducts = new ArrayList<SyncInfoModel>();

	private static List<String> idListToBeDecreased = new ArrayList<String>(Arrays.asList("1292", "1658"));
	private static List<String> skuListToBeDecreased = new ArrayList<String>(Arrays.asList("R065SV-18", "N093SV"));

	private static List<String> constantStockIdList = new ArrayList<String>(Arrays.asList("5037"));
	private static List<String> constantStockSkuList = new ArrayList<String>(Arrays.asList("M164"));

	@Before
	public void setUp() throws Exception {

		for (String id : idListToBeDecreased) {
			magentoProductsToBeDeacreased.add(ApiCalls.getMagProductInfo(id));
		}
		for (String sku : skuListToBeDecreased) {
			String[] skuParts = sku.split("-");
			navProductsToBeDecreased.add(NavQueries.getSyncProductInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}
		for (String id : constantStockIdList) {
			constantStockMagentoProducts.add(ApiCalls.getMagProductInfo(id));
		}
		for (String sku : constantStockSkuList) {
			String[] skuParts = sku.split("-");
			constantStockNavProducts.add(NavQueries.getSyncProductInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CONSTANT_STOCK);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CONSTANT_STOCK);
	}

	@Test
	public void us23001GetMagAndNavStockBerforeOrderTest() throws SQLException {

	}

	@After
	public void saveData() {

		for (SyncInfoModel product : magentoProductsToBeDeacreased) {
			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		}
		for (SyncInfoModel product : navProductsToBeDecreased) {
			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK);
		}
		for (SyncInfoModel product : constantStockMagentoProducts) {
			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CONSTANT_STOCK);
		}
		for (SyncInfoModel product : constantStockNavProducts) {
			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CONSTANT_STOCK);
		}

	}
}
