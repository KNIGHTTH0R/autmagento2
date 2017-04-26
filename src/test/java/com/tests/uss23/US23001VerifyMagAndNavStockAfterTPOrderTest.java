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
	List<SyncInfoModel> initialChangingMagentoProductsNewTp = new ArrayList<SyncInfoModel>();
	private List<String> boughtProductsQuantities = new ArrayList<String>();
	private List<String> boughtProductsQuantitiesNewTp = new ArrayList<String>();

	private List<SyncInfoModel> changingStockMagentoProducts = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> changingStockMagentoProductsNewTp = new ArrayList<SyncInfoModel>();

	private static List<String> changingStockIdList = new ArrayList<String>(Arrays.asList("4469", "2358", "4271", "4304"));
	private static List<String> changingStockIdListNewTp = new ArrayList<String>(Arrays.asList("4599", "4575", "4346", "4345"));
	@Before
	public void setUp() throws Exception {

		initialChangingMagentoProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeTpOrderTest" + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		initialChangingMagentoProductsNewTp=MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeTpOrderTest" + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK_NEW_TP);
		System.out.println("1 "+ initialChangingMagentoProducts.size());
		System.out.println("2 "+ initialChangingMagentoProductsNewTp.size());
		boughtProductsQuantities = MongoReader.grabStringValue("US23001PlaceTermPurchaseOrderTest"+SoapKeys.BOUGHT_PRODUCTS_QUANTITIES);
		boughtProductsQuantitiesNewTp=MongoReader.grabStringValue("US23001PlaceTermPurchaseOrderTest" +SoapKeys.BOUGHT_PRODUCTS_QUANTITIES_NEW_TP);
		System.out.println("3 "+ boughtProductsQuantities.size());
		System.out.println("4 "+ boughtProductsQuantitiesNewTp.size());
		initialChangingMagentoProducts = StockCalculations.calculateNewStock(initialChangingMagentoProducts, boughtProductsQuantities);
		System.out.println("vrrerrrr");
		initialChangingMagentoProductsNewTp=StockCalculations.calculateNewStock(initialChangingMagentoProductsNewTp, boughtProductsQuantitiesNewTp);

		System.out.println("3 "+ initialChangingMagentoProducts.size());
		System.out.println("4 "+ initialChangingMagentoProductsNewTp.size());
		
		for (String id : changingStockIdList) {
			changingStockMagentoProducts.add(MagentoProductCalls.getMagProductInfo(id));
			
			System.out.println("ceva 1" +MagentoProductCalls.getMagProductInfo(id).getSku());
		}
		for (String id : changingStockIdListNewTp) {
			changingStockMagentoProductsNewTp.add(MagentoProductCalls.getMagProductInfo(id));
			System.out.println("ceva 1" +MagentoProductCalls.getMagProductInfo(id).getSku());
		}
		
		System.out.println("am ajuns 1");
	}

	@Test
	public void us23001VerifyMagAndNavStockAfterTPOrderTest() throws SQLException {

		stockSyncValidations.setValidateProductsModels(initialChangingMagentoProducts, changingStockMagentoProducts);
		stockSyncValidations.validateProducts("VALIDATE MAGENTO STOCK IS DECREASED -  CHANGING STOCK MAGENTO PRODUCTS");
		
		stockSyncValidations.setValidateProductsModels(initialChangingMagentoProductsNewTp, changingStockMagentoProductsNewTp);
		
		System.out.println("da da "+ initialChangingMagentoProductsNewTp);
		stockSyncValidations.validateProducts("VALIDATE MAGENTO STOCK IS DECREASED -  CHANGING STOCK MAGENTO PRODUCTS");
		

		customVerifications.printErrors();
	}
}
