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

import com.connectors.http.MagentoProductCalls;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.navision.SyncInfoModel;
import com.tools.env.constants.SoapKeys;
import com.tools.generalCalculation.StockCalculations;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.stockSynk.StockSyncValidations;

@WithTag(name = "US23.1 Stock Sync", type = "Scenarios")
@Story(Application.StockSync.US23_1.class)
@RunWith(ThucydidesRunner.class)
public class US23001VerifyMagAndNavStockAfterTPOrderTest extends BaseTest {

	@Steps
	StockSyncValidations stockSyncValidations;
	@Steps
	public CustomVerification customVerifications;

	List<SyncInfoModel> initialChangingMagentoProducts = new ArrayList<SyncInfoModel>();
	private List<String> boughtProductsQuantities = new ArrayList<String>();

	private List<SyncInfoModel> changingStockMagentoProducts = new ArrayList<SyncInfoModel>();

	private static List<String> changingStockIdList = new ArrayList<String>(Arrays.asList("1292", "1658", "2558", "1872", "2552"));

	@Before
	public void setUp() throws Exception {

		initialChangingMagentoProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeOrderTest" + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		boughtProductsQuantities = MongoReader.grabStringValue("US23001PlaceTermPurchaseOrderTest");
		
		initialChangingMagentoProducts = StockCalculations.calculateNewStock(initialChangingMagentoProducts, boughtProductsQuantities);

		for (String id : changingStockIdList) {
			changingStockMagentoProducts.add(MagentoProductCalls.getMagProductInfo(id));
		}

	}

	@Test
	public void us23001VerifyMagAndNavStockAfterOrderTest() throws SQLException {

		stockSyncValidations.setValidateProductsModels(initialChangingMagentoProducts, changingStockMagentoProducts);
		stockSyncValidations.validateProducts("VALIDATE MAGENTO STOCK IS DECREASED -  CHANGING STOCK MAGENTO PRODUCTS");

		customVerifications.printErrors();
	}
}
