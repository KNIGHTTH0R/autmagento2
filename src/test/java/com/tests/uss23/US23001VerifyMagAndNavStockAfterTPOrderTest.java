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
public class US23001VerifyMagAndNavStockAfterTPOrderTest extends BaseTest {

	@Steps
	StockSyncValidations stockSyncValidations;
	@Steps
	public CustomVerification customVerifications;

	List<SyncInfoModel> initialChangingMagentoProducts = new ArrayList<SyncInfoModel>();
	List<SyncInfoModel> initialChangingMagentoProductsOldTp = new ArrayList<SyncInfoModel>();
	private List<String> boughtProductsQuantities = new ArrayList<String>();
	private List<String> boughtProductsQuantitiesOldTp = new ArrayList<String>();

	private List<SyncInfoModel> changingStockMagentoProducts = new ArrayList<SyncInfoModel>();

	private static List<String> changingStockIdList = new ArrayList<String>(Arrays.asList("4469", "2358", "4271", "4304"));
	private static List<String> changingStockIdListOldTp = new ArrayList<String>(Arrays.asList("4599", "4575", "4346", "4345"));
	@Before
	public void setUp() throws Exception {

		initialChangingMagentoProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeTpOrderTest" + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		initialChangingMagentoProductsOldTp=MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeTpOrderTest" + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK_OLD_TP);
		
		boughtProductsQuantities = MongoReader.grabStringValue("US23001PlaceTermPurchaseOrderTest");
		boughtProductsQuantitiesOldTp=MongoReader.grabStringValue("US23001PlaceTermPurchaseOrderTest" +"OLDTP");
		
		initialChangingMagentoProducts = StockCalculations.calculateNewStock(initialChangingMagentoProducts, boughtProductsQuantities);
		initialChangingMagentoProductsOldTp=StockCalculations.calculateNewStock(initialChangingMagentoProductsOldTp, boughtProductsQuantitiesOldTp);

		for (String id : changingStockIdList) {
			changingStockMagentoProducts.add(MagentoProductCalls.getMagProductInfo(id));
		}
		for (String id : changingStockIdListOldTp) {
			changingStockMagentoProducts.add(MagentoProductCalls.getMagProductInfo(id));
		}
		

	}

	@Test
	public void us23001VerifyMagAndNavStockAfterTPOrderTest() throws SQLException {

		stockSyncValidations.setValidateProductsModels(initialChangingMagentoProducts, changingStockMagentoProducts);
		stockSyncValidations.validateProducts("VALIDATE MAGENTO STOCK IS DECREASED -  CHANGING STOCK MAGENTO PRODUCTS");
		
//		stockSyncValidations.setValidateProductsModels(initialChangingMagentoProductsOldTp, changingStockMagentoProducts);
//		stockSyncValidations.validateProducts("VALIDATE MAGENTO STOCK IS DECREASED -  CHANGING STOCK MAGENTO PRODUCTS");
		

		customVerifications.printErrors();
	}
}
