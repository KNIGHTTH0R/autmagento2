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
import com.connectors.http.NavisionInventorySyncCalls;
import com.connectors.mongo.MongoConnector;
import com.connectors.navSqlServer.NavQueries;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.external.navision.NavisionHomeSteps;
import com.steps.external.navision.NavisionImportSteps;
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
	@Steps
	public NavisionImportSteps navisionImportSteps;
	@Steps
	public NavisionHomeSteps navisionSteps;

	private List<SyncInfoModel> magentoProductsToBeDeacreased = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> navProductsToBeDecreased = new ArrayList<SyncInfoModel>();
	
	private List<SyncInfoModel> magentoProductsToBeDeacreasedOldTP = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> navProductsToBeDecreasedOldTp = new ArrayList<SyncInfoModel>();

	private static List<String> idListToBeDecreased = new ArrayList<String>(Arrays.asList("4469", "2358", "4271", "4304"));
	private static List<String> skuListToBeDecreased = new ArrayList<String>(Arrays.asList("R185SV-18", "B096SV", "E195SV", "B170BR"));
	
	private static List<String> idListToBeDecreasedOldTp = new ArrayList<String>(Arrays.asList("4599", "4575", "4346", "4345"));
	private static List<String> skuListToBeDecreasedOldTP = new ArrayList<String>(Arrays.asList("R194RS-18", "N348SV", "A023SV", "A022SV"));

	@Before
	public void setUp() throws Exception {

	//	navisionSteps.syncQtyAndSetAvDateOnItem("B096SV");
		
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK_OLD_TP);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK_OLD_TP);
		
	}

	@Test
	public void us23001GetMagAndNavStockBerforeOrderTest() throws Exception {

		
//		navisionSteps.syncEarliestAvDateOnItem("B096SV");
//		navisionSteps.syncQtyOnItem("B096SV", "");
		for (String id : idListToBeDecreased) {
			magentoProductsToBeDeacreased.add(MagentoProductCalls.getMagProductInfo(id));
		}
		for (String sku : skuListToBeDecreased) {
			String[] skuParts = sku.split("-");
			//ioana
			//navProductsToBeDecreased.add(NavQueries.getSyncProductInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
			navProductsToBeDecreased.add(NavisionInventorySyncCalls.getItemInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}
		
		//add also produtuct with OldTp (witch earliest Date) on common Lists
		for (String id : idListToBeDecreasedOldTp) {
			magentoProductsToBeDeacreasedOldTP.add(MagentoProductCalls.getMagProductInfo(id));
		}
		for (String sku : skuListToBeDecreasedOldTP) {
			String[] skuParts = sku.split("-");
			//ioana
			//navProductsToBeDecreased.add(NavQueries.getSyncProductInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
			navProductsToBeDecreasedOldTp.add(NavisionInventorySyncCalls.getItemInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
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
		
		for (SyncInfoModel product : magentoProductsToBeDeacreasedOldTP) {
			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK_OLD_TP);
		}
		for (SyncInfoModel product : navProductsToBeDecreasedOldTp) {
			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK_OLD_TP);
		}
	}
}
