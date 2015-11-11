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
import com.tools.data.navision.SyncInfoModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.stockSynk.StockSyncValidations;

@WithTag(name = "US23.1 Stock Sync", type = "Scenarios")
@Story(Application.StockSync.US23_1.class)
@RunWith(ThucydidesRunner.class)
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

	private static List<String> changingStockIdList = new ArrayList<String>(Arrays.asList("1292", "1658"));
	private static List<String> changingStockSkuList = new ArrayList<String>(Arrays.asList("R065SV-18", "N093SV"));

	private static List<String> constantStockIdList = new ArrayList<String>(Arrays.asList("5037"));
	private static List<String> constantStockSkuList = new ArrayList<String>(Arrays.asList("M164"));

	@Before
	public void setUp() throws Exception {

		initialChangingMagentoProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeOrderTest" + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		initialChangingNavProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeOrderTest" + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK);
		initialConstantMagentoProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeOrderTest" + SoapKeys.MAGENTO_INITIAL_CONSTANT_STOCK);
		initialConstantNavProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeOrderTest" + SoapKeys.NAVISION_INITIAL_CONSTANT_STOCK);

		initialChangingMagentoProducts = StockCalculations.calculateNewStock(initialChangingMagentoProducts, "-1", false);
		initialConstantMagentoProducts = StockCalculations.calculateNewStock(initialConstantMagentoProducts, "-1", false);

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
	}

	@Test
	public void us23001VerifyMagAndNavStockAfterOrderTest() throws SQLException {

		stockSyncValidations.setValidateProductsModels(initialChangingMagentoProducts, changingStockMagentoProducts);
		stockSyncValidations.validateProducts("VALIDATE CHANGING STOCK MAGENTO PRODUCTS");

		stockSyncValidations.setValidateProductsModels(initialConstantMagentoProducts, constantStockMagentoProducts);
		stockSyncValidations.validateProducts("VALIDATE CONSTANT STOCK MAGENTO PRODUCTS");
		
		stockSyncValidations.setValidateProductsModels(initialChangingNavProducts, changingStockNavProduct);
		stockSyncValidations.validateProducts("VALIDATE CONSTANT STOCK MAGENTO PRODUCTS");
		
		stockSyncValidations.setValidateProductsModels(initialConstantNavProducts, constantStockNavProducts);
		stockSyncValidations.validateProducts("VALIDATE CONSTANT STOCK MAGENTO PRODUCTS");

		customVerifications.printErrors();

		// validate initial mag stock vs current mag stock (current = initial
		// -1) for changing stock products
		// validate initial mag stock vs current mag stock(current = initial)
		// for constant stock products

		// validate nav stock vs magento stock (where mag stock = quantity +
		// pending quantity)

	}
}
