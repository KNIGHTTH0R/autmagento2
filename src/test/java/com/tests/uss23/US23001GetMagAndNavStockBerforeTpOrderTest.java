package com.tests.uss23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.http.NavisionInventorySyncCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.external.navision.NavisionHomeSteps;
import com.steps.external.navision.NavisionImportSteps;
import com.tests.BaseTest;
import com.tools.constants.SoapKeys;
import com.tools.data.navision.SyncInfoModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

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

	private List<String> skuListToBeUpdatedOnStock = new ArrayList<String>();

	private List<SyncInfoModel> magentoProductsToBeDeacreased = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> navProductsToBeDecreased = new ArrayList<SyncInfoModel>();

	private List<SyncInfoModel> magentoProductsToBeDeacreasedNewTP = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> navProductsToBeDecreasedNewTp = new ArrayList<SyncInfoModel>();

	private static List<String> idListToBeDecreased = new ArrayList<String>(Arrays.asList("4469", "2358", "4271", "4304"));
	private static List<String> skuListToBeDecreased = new ArrayList<String>(Arrays.asList("R185SV-18", "B096SV", "E195SV", "B170BR"));

	private static List<String> idListToBeDecreasedNewTp = new ArrayList<String>(	Arrays.asList("4599", "4575", "4346", "4345"));
	private static List<String> skuListToBeDecreasedNewTP = new ArrayList<String>(Arrays.asList("R194RS-18", "N348SV", "A023SV", "A022SV"));

	@Before
	public void setUp() throws Exception {
		skuListToBeUpdatedOnStock.addAll(skuListToBeDecreased);
		skuListToBeUpdatedOnStock.addAll(skuListToBeDecreasedNewTP);

		navisionSteps.accessNavisonWebClientItemList();
		
		navisionSteps.syncQtyOnItem("R185SV", "18", "0");
		
		for(int i=1;i<skuListToBeUpdatedOnStock.size()-1;i++){
			String[] skuParts = skuListToBeUpdatedOnStock.get(i).split("-");
			System.out.println(skuParts.length == 1 ? "" : skuParts[1]);
			navisionSteps.syncQtyOnItem(skuParts[0], skuParts.length == 1 ? "" : skuParts[1], "5");
		}
		
//		for (String sku : skuListToBeUpdatedOnStock) {
//			String[] skuParts = sku.split("-");
//			System.out.println(skuParts.length == 1 ? "" : skuParts[1]);
//			navisionSteps.syncQtyOnItem(skuParts[0], skuParts.length == 1 ? "" : skuParts[1], "5");
//		}

		for (String sku : skuListToBeDecreased) {
			String[] skuParts = sku.split("-");
			navisionSteps.syncEarliestAvDateOnItem(skuParts[0], skuParts.length == 1 ? "" : skuParts[1], "50");
		}

		for (String sku : skuListToBeDecreasedNewTP) {
			String[] skuParts = sku.split("-");
			navisionSteps.checkTermPurchasecheckbox(skuParts[0]);
		}

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK_NEW_TP);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK_NEW_TP);

	}

	@Test
	public void us23001GetMagAndNavStockBerforeOrderTest() throws Exception {

		for (String id : idListToBeDecreased) {
			magentoProductsToBeDeacreased.add(MagentoProductCalls.getMagProductInfo(id));
		}
		for (String sku : skuListToBeDecreased) {
			String[] skuParts = sku.split("-");
			// ioana
			// navProductsToBeDecreased.add(NavQueries.getSyncProductInfo(skuParts[0],
			// skuParts.length == 1 ? "" : skuParts[1]));
			navProductsToBeDecreased
					.add(NavisionInventorySyncCalls.getItemInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}

		// 
		for (String id : idListToBeDecreasedNewTp) {
			magentoProductsToBeDeacreasedNewTP.add(MagentoProductCalls.getMagProductInfo(id));
		}
		for (String sku : skuListToBeDecreasedNewTP) {
			String[] skuParts = sku.split("-");
			// ioana
			// navProductsToBeDecreased.add(NavQueries.getSyncProductInfo(skuParts[0],
			// skuParts.length == 1 ? "" : skuParts[1]));
			navProductsToBeDecreasedNewTp.add(NavisionInventorySyncCalls.getItemInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}

	}

	@After
	public void saveData() {

		for (SyncInfoModel product : magentoProductsToBeDeacreased) {
			MongoWriter.saveStockInfoModel(product,
					getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		}
		for (SyncInfoModel product : navProductsToBeDecreased) {
			MongoWriter.saveStockInfoModel(product,
					getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK);
		}

		for (SyncInfoModel product : magentoProductsToBeDeacreasedNewTP) {
			MongoWriter.saveStockInfoModel(product,
					getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK_NEW_TP);
		}
		for (SyncInfoModel product : navProductsToBeDecreasedNewTp) {
			MongoWriter.saveStockInfoModel(product,
					getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK_NEW_TP);
		}
	}
}
