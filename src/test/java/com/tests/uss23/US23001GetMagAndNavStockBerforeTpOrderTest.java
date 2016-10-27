package com.tests.uss23;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.mongo.MongoConnector;
import com.connectors.navSqlServer.NavQueries;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.tests.BaseTest;
import com.tools.constants.SoapKeys;
import com.tools.data.navision.SyncInfoModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US23.1 Stock Sync", type = "Scenarios")
@Story(Application.StockSync.US23_1.class)
@RunWith(SerenityRunner.class)
public class US23001GetMagAndNavStockBerforeTpOrderTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;

	private List<SyncInfoModel> magentoProductsToBeDeacreased = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> navProductsToBeDecreased = new ArrayList<SyncInfoModel>();

	private static List<String> idListToBeDecreased = new ArrayList<String>(Arrays.asList("1292", "1658", "2558", "1872", "2552"));
	private static List<String> skuListToBeDecreased = new ArrayList<String>(Arrays.asList("R065SV-18", "N093SV", "N052NL", "N094SV", "B098BK"));

	@Before
	public void setUp() throws Exception {

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK);
	}

	@Test
	public void us23001GetMagAndNavStockBerforeOrderTest() throws SQLException {

		for (String id : idListToBeDecreased) {
			magentoProductsToBeDeacreased.add(MagentoProductCalls.getMagProductInfo(id));
		}
		for (String sku : skuListToBeDecreased) {
			String[] skuParts = sku.split("-");
			navProductsToBeDecreased.add(NavQueries.getSyncProductInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}

	}

	@After
	public void saveData() {

		for (SyncInfoModel product : magentoProductsToBeDeacreased) {
			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		}
		for (SyncInfoModel product : navProductsToBeDecreased) {
			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK);
		}
	}
}
