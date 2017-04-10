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
public class US23001GetMagAndNavStockBerforeOrderTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;

	private List<SyncInfoModel> magentoProductsToBeDeacreased = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> navProductsToBeDecreased = new ArrayList<SyncInfoModel>();
	
	private List<SyncInfoModel> magentoProductsToNotBeDeacreased = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> navProductsToNotBeDecreased = new ArrayList<SyncInfoModel>();
	

	private List<SyncInfoModel> constantStockMagentoProducts = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> constantStockNavProducts = new ArrayList<SyncInfoModel>();


	
	
	private static List<String> idListToBeDecreased = new ArrayList<String>(Arrays.asList("1292", "1658", "3120"));
	private static List<String> idListToNotBeDecreased=new ArrayList<String>(Arrays.asList("1872", "2552"));
	private static List<String> skuListToBeDecreased = new ArrayList<String>(Arrays.asList("R065SV-18", "N093SV", "N105MC"));
	private static List<String> skuListToNotBeDecreased = new ArrayList<String>(Arrays.asList("N094SV", "B098BK"));

	
	private static List<String> constantStockIdList = new ArrayList<String>(Arrays.asList("957"));
	private static List<String> constantStockSkuList = new ArrayList<String>(Arrays.asList("M014"));
	

	@Before
	public void setUp() throws Exception {

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CONSTANT_STOCK);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CONSTANT_STOCK);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_NOT_CHANGING_STOCK);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_NOT_CHANGING_STOCK);
		
	}

	@Test
	public void us23001GetMagAndNavStockBerforeOrderTest() throws Exception {

		for (String id : idListToBeDecreased) {
			magentoProductsToBeDeacreased.add(MagentoProductCalls.getMagProductInfo(id));
		}
		
		for (String id : idListToNotBeDecreased) {
			magentoProductsToNotBeDeacreased.add(MagentoProductCalls.getMagProductInfo(id));
		}
		for (String sku : skuListToBeDecreased) {
			String[] skuParts = sku.split("-");
			
			navProductsToBeDecreased.add(NavisionInventorySyncCalls.getItemInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}
		
		for (String sku : skuListToNotBeDecreased) {
			String[] skuParts = sku.split("-");
			navProductsToNotBeDecreased.add(NavisionInventorySyncCalls.getItemInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}
		
		for (String id : constantStockIdList) {
			constantStockMagentoProducts.add(MagentoProductCalls.getMagProductInfo(id));
		}
		for (String sku : constantStockSkuList) {
			String[] skuParts = sku.split("-");
			constantStockNavProducts.add(NavisionInventorySyncCalls.getItemInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}
		

	}

	@After
	public void saveData() {

		for (SyncInfoModel product : magentoProductsToBeDeacreased) {
			
			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
			System.out.println("1 magentoProductsToBeDeacreased "+product.toString());
			
		}
		for (SyncInfoModel product : magentoProductsToNotBeDeacreased) {
			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_NOT_CHANGING_STOCK);
			System.out.println("2 magentoProductsToNotBeDeacreased "+product.toString());
		}
		for (SyncInfoModel product : navProductsToBeDecreased) {
			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK);
			System.out.println("3 navProductsToBeDecreased "+product.toString());
		}
		
		for (SyncInfoModel product : navProductsToNotBeDecreased) {
			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_NOT_CHANGING_STOCK);
			System.out.println("4 navProductsToNotBeDecreased "+product.toString());
		}
		for (SyncInfoModel product : constantStockMagentoProducts) {
			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName() + SoapKeys.MAGENTO_INITIAL_CONSTANT_STOCK);
			System.out.println("5 constantStockMagentoProducts "+product.toString());
		}
		for (SyncInfoModel product : constantStockNavProducts) {
			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName() + SoapKeys.NAVISION_INITIAL_CONSTANT_STOCK);
			System.out.println("6 constantStockNavProducts "+product.toString());
		}
	}
}
