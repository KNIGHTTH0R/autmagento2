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
import com.steps.external.navision.NavisionHomeSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.SoapKeys;
import com.tools.data.navision.SyncInfoModel;
import com.tools.generalCalculation.StockCalculations;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.stockSynk.StockSyncValidations;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US23.1 Stock Sync", type = "Scenarios")
@Story(Application.StockSync.US23_1.class)
@RunWith(SerenityRunner.class)
public class US23001TranferQuantityFromQSin1000Test extends BaseTest{
	@Steps
	StockSyncValidations stockSyncValidations;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public NavisionHomeSteps navisionSteps;

	@Steps
	CustomVerification customVerification;
	int counter=0;
	

	List<SyncInfoModel> initialChangingMagentoProducts = new ArrayList<SyncInfoModel>();
	List<SyncInfoModel> initialChangingMagentoProductsNewTp = new ArrayList<SyncInfoModel>();

	private List<SyncInfoModel> changingStockMagentoProducts = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> changingStockMagentoProductsCopy = new ArrayList<SyncInfoModel>();

	private List<SyncInfoModel> changingStockNavisionProducts = new ArrayList<SyncInfoModel>();
	
	private List<SyncInfoModel> expectedStockMagentoProductsAfterTransferQty1 = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> expectedSimpleQtyInMagentoAfterTransferQty = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> changingStockMagentoProductsAfterTransfer = new ArrayList<SyncInfoModel>();
	
	
	private static List<String> idListToBeDecreased = new ArrayList<String>(Arrays.asList("4469", "2358", "4271", "4304"));
	private static List<String> skuListToBeDecreased = new ArrayList<String>(Arrays.asList("R185SV-18", "B096SV", "E195SV", "B170BR"));
	
	private List<String> boughtProductsQuantities = new ArrayList<String>();
	
	@Before
	public void setUp() throws Exception {
	
		boughtProductsQuantities = MongoReader.grabStringValue("US23001PlaceTermPurchaseOrderTest"+SoapKeys.BOUGHT_PRODUCTS_QUANTITIES);
		
		
		changingStockMagentoProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeTpOrderTest" + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		
		System.out.println("changingStockMagentoProducts "+ changingStockMagentoProducts);
		
//		for (String id : idListToBeDecreased) {
//			changingStockMagentoProducts.add(MagentoProductCalls.getMagProductInfo(id));
//		}
		
		///////////////transferul
//		navisionSteps.loginToTransferQuantityPage();
//	
//		for (SyncInfoModel product : changingStockMagentoProducts) {
//			String[] skuParts = product.getSku().split("-");
//			navisionSteps.transferQtyFromQSin1000(skuParts[0],skuParts.length == 1 ? "" : skuParts[1],boughtProductsQuantities.get(counter));
//			counter++;
//		}
		
		
	
		expectedStockMagentoProductsAfterTransferQty1=StockCalculations.calculateStockAfterTransferQuantity(changingStockMagentoProducts,boughtProductsQuantities);	
		
		
		///grab after transfer 
		
		for (String id : idListToBeDecreased) {
			changingStockMagentoProductsAfterTransfer.add(MagentoProductCalls.getMagProductInfo(id));
		}
		for (String sku : skuListToBeDecreased) {
			String[] skuParts = sku.split("-");
			changingStockNavisionProducts
					.add(NavisionInventorySyncCalls.getItemInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}
		
		
		//calculate simple qty: navision qty- shop pending qty
		System.out.println("aici 3 ??");
		expectedSimpleQtyInMagentoAfterTransferQty=StockCalculations.calculateStockAfterTransferQty(changingStockMagentoProductsAfterTransfer,changingStockNavisionProducts);
		System.out.println("expectedSimpleQtyInMagentoAfterTransferQty"+ expectedSimpleQtyInMagentoAfterTransferQty);
	}

	@Test
	public void us23001TranferQuantityFromQSin1000Test() {	
		//validate expected after transfer with magento grabbed
		stockSyncValidations.setValidateProductsModels(expectedStockMagentoProductsAfterTransferQty1, changingStockMagentoProductsAfterTransfer);
		stockSyncValidations.validateProducts("VALIDATE MAGENTO STOCK IS INCREASED AFTER TRANSFER QTY IN NAVISION");

		
		stockSyncValidations.setValidateProductsModels(expectedStockMagentoProductsAfterTransferQty1, changingStockNavisionProducts);
		stockSyncValidations.validateProducts("VALIDATE MAGENTO STOCK IS THE SAME AS IN NAVISION AFTER TRANSFER QTY IN NAVISION");
		
		//validate only qty in magento with : navision qty-shop pending qty
		stockSyncValidations.setValidateProductsModels(expectedStockMagentoProductsAfterTransferQty1, expectedSimpleQtyInMagentoAfterTransferQty);
		stockSyncValidations.validateProductsQty("VALIDATE MAGENTO STOCK(without pending) IS CORRECT AFTER TRANSFER QTY IN NAVISION");
//		
		customVerification.printErrors();
		
	}
	
	@After
	public void saveData() {
		
	}
	
}



