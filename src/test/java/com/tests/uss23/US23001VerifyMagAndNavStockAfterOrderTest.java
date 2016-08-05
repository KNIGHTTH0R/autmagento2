package com.tests.uss23;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.navSqlServer.NavQueries;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.SoapKeys;
import com.tools.data.navision.SyncInfoModel;
import com.tools.generalCalculation.StockCalculations;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.stockSynk.StockSyncValidations;

@WithTag(name = "US23.1 Stock Sync", type = "Scenarios")
@Story(Application.StockSync.US23_1.class)
@RunWith(SerenityRunner.class)
public class US23001VerifyMagAndNavStockAfterOrderTest extends BaseTest {

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

	private static List<String> changingStockIdList = new ArrayList<String>(Arrays.asList("1292", "1658", "2558", "1872", "2552"));
	private static List<String> changingStockSkuList = new ArrayList<String>(Arrays.asList("R065SV-18", "N093SV", "N052NL", "N094SV", "B098BK"));

	private static List<String> constantStockIdList = new ArrayList<String>(Arrays.asList("5037"));
	private static List<String> constantStockSkuList = new ArrayList<String>(Arrays.asList("M164"));

	@Before
	public void setUp() throws Exception {

		initialChangingMagentoProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeOrderTest" + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		initialChangingNavProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeOrderTest" + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK);
		initialConstantMagentoProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeOrderTest" + SoapKeys.MAGENTO_INITIAL_CONSTANT_STOCK);
		initialConstantNavProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeOrderTest" + SoapKeys.NAVISION_INITIAL_CONSTANT_STOCK);

		initialChangingMagentoProducts = StockCalculations.calculateNewStock(initialChangingMagentoProducts, "1", false);
		initialConstantMagentoProducts = StockCalculations.calculateNewStock(initialConstantMagentoProducts, "1", false);

		for (String id : changingStockIdList) {
			changingStockMagentoProducts.add(MagentoProductCalls.getMagProductInfo(id));
		}
		for (String sku : changingStockSkuList) {
			String[] skuParts = sku.split("-");
			changingStockNavProduct.add(NavQueries.getSyncProductInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}
		for (String id : constantStockIdList) {
			constantStockMagentoProducts.add(MagentoProductCalls.getMagProductInfo(id));
		}
		for (String sku : constantStockSkuList) {
			String[] skuParts = sku.split("-");
			constantStockNavProducts.add(NavQueries.getSyncProductInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}
	}

	@Test
	public void us23001VerifyMagAndNavStockAfterOrderTest() throws SQLException {

		stockSyncValidations.setValidateProductsModels(initialChangingMagentoProducts, changingStockMagentoProducts);
		stockSyncValidations.validateProducts("VALIDATE MAGENTO STOCK IS DECREASED -  CHANGING STOCK MAGENTO PRODUCTS");

		stockSyncValidations.setValidateProductsModels(initialConstantMagentoProducts, constantStockMagentoProducts);
		stockSyncValidations.validateProducts("VALIDATE MAGENTO STOCK IS DECREASED -  CONSTANT STOCK MAGENTO PRODUCTS");

		stockSyncValidations.setValidateProductsModels(initialChangingNavProducts, changingStockNavProduct);
		stockSyncValidations.validateProducts("VALIDATE NAVISION STOCK IS THE SAME - CHANGING STOCK NAVISION PRODUCTS");

		stockSyncValidations.setValidateProductsModels(initialConstantNavProducts, constantStockNavProducts);
		stockSyncValidations.validateProducts("VALIDATE NAVISION STOCK IS THE SAME -  CONSTANT STOCK NAVISION PRODUCTS");

		customVerifications.printErrors();
	}
}
