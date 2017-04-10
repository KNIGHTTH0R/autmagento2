package com.tests.uss23;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.http.NavisionInventorySyncCalls;
import com.connectors.http.OrderInfoMagCalls;
import com.steps.backend.ImportOrdersToNavSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.SoapKeys;
import com.tools.data.backend.OrderModel;
import com.tools.data.navision.SyncInfoModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.generalCalculation.StockCalculations;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.workflows.stockSynk.StockProductsValidations;
import com.workflows.stockSynk.StockSyncValidations;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US23.1 Stock Sync", type = "Scenarios")
@Story(Application.StockSync.US23_1.class)
@RunWith(SerenityRunner.class)
public class US23001VerifyStockSyncAfterTpOrderImportTest extends BaseTest {

	@Steps
	StockSyncValidations stockSyncValidations;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	StockProductsValidations stockProductsValidations;
	@Steps
	ImportOrdersToNavSteps importedOrderValidation;

	private List<String> boughtProductsQuantities = new ArrayList<String>();
	private List<String> boughtProductsQuantitiesNewTp = new ArrayList<String>();

	List<SyncInfoModel> initialChangingMagentoProducts = new ArrayList<SyncInfoModel>();
	List<SyncInfoModel> initialChangingNavProducts = new ArrayList<SyncInfoModel>();
	
	List<SyncInfoModel> initialChangingMagentoProductsNewTp = new ArrayList<SyncInfoModel>();
	List<SyncInfoModel> initialChangingNavProductsNewTp = new ArrayList<SyncInfoModel>();

	private List<SyncInfoModel> changingStockMagentoProducts = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> changingStockNavProduct = new ArrayList<SyncInfoModel>();

	private List<SyncInfoModel> changingStockMagentoProductsNewTp = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> changingStockNavProductNewTp = new ArrayList<SyncInfoModel>();
	
	private static List<String> changingStockIdList = new ArrayList<String>(Arrays.asList("4469", "2358", "4271", "4304"));
	private static List<String> changingStockSkuList = new ArrayList<String>(Arrays.asList("R185SV-18", "B096SV", "E195SV", "B170BR"));

	private static List<String> changingStockIdListNewTp = new ArrayList<String>(	Arrays.asList("4599", "4575", "4346", "4345"));
	private static List<String> changingStockSkuListNewTP = new ArrayList<String>(Arrays.asList("R194RS-18", "N348SV", "A023SV", "A022SV"));
	
	private DBOrderModel shopOrder = new DBOrderModel();
	private OrderModel orderModel;
	boolean isSyncronyzed = false;

	@Before
	public void setUp() throws Exception {

		orderModel = MongoReader.getOrderModel("US23001PlaceTermPurchaseOrderTest").get(0);
//		OrderStatusModel orderStatusModel = NavQueries.getProductSyncronizedStatus(orderModel.getOrderId().toUpperCase());
//		String[] parts = orderStatusModel.getSyncDate().split(Pattern.quote("."));
//		String syncDate = parts[0];

		

		initialChangingMagentoProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeTpOrderTest" + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		initialChangingNavProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeTpOrderTest" + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK);
		
		for (SyncInfoModel string : initialChangingMagentoProducts) {
			System.out.println("1 initialChangingMagentoProducts"+ string.toString() );
		}
		
		for (SyncInfoModel string : initialChangingNavProducts) {
			System.out.println("1.1 initialChangingNavProducts"+ string.toString() );
		}
		
		
		initialChangingMagentoProductsNewTp = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeTpOrderTest" + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
		initialChangingNavProductsNewTp = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeTpOrderTest" + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK_NEW_TP);
		for (SyncInfoModel string : initialChangingMagentoProductsNewTp) {
			System.out.println("2 initialChangingMagentoProductsNewTp"+ string.toString() );
		}
		
		for (SyncInfoModel string : initialChangingNavProductsNewTp) {
			System.out.println("2.2 initialChangingNavProductsNewTp"+ string.toString() );
		}
		
		
		boughtProductsQuantities = MongoReader.grabStringValue("US23001PlaceTermPurchaseOrderTest"+"BPQ");
		boughtProductsQuantitiesNewTp = MongoReader.grabStringValue("US23001PlaceTermPurchaseOrderTest"+"BPQNTP");
		
		for (String string : boughtProductsQuantities) {
			System.out.println("3 boughtProductsQuantities"+ string.toString() );
		}
		
		for (String string : boughtProductsQuantitiesNewTp) {
			System.out.println("3.3 boughtProductsQuantitiesNewTp"+ string.toString() );
		}
//		initialChangingNavProducts = StockCalculations.calculateNewStock(initialChangingNavProducts, boughtProductsQuantities);
//		initialChangingNavProductsNewTp=StockCalculations.calculateNewStock(initialChangingNavProductsNewTp, boughtProductsQuantitiesNewTp);
		
		
		initialChangingNavProducts = StockCalculations.calculateNewStockAfterSync(initialChangingNavProducts, boughtProductsQuantities);
		initialChangingNavProductsNewTp=StockCalculations.calculateNewStockAfterSync(initialChangingNavProductsNewTp, boughtProductsQuantitiesNewTp);

		for (SyncInfoModel string : initialChangingNavProducts) {
			System.out.println("calculated initialChangingNavProducts"+ string.toString() );
		}
		
		for (SyncInfoModel string : initialChangingNavProductsNewTp) {
			System.out.println("calculated initialChangingNavProductsNewTp"+ string.toString() );
		}
		
		
		
		System.out.println("before changingStockIdList api calll");
		
		for (String id : changingStockIdList) {
			changingStockMagentoProducts.add(MagentoProductCalls.getMagProductInfo(id));
		}
		for (String sku : changingStockSkuList) {
			String[] skuParts = sku.split("-");
			//ioana
			//changingStockNavProduct.add(NavQueries.getSyncProductInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
			changingStockNavProduct.add(NavisionInventorySyncCalls.getItemInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}
		
		for (String id : changingStockIdListNewTp) {
			changingStockMagentoProductsNewTp.add(MagentoProductCalls.getMagProductInfo(id));
		}
		for (String sku : changingStockSkuListNewTP) {
			String[] skuParts = sku.split("-");
			//ioana
			//changingStockNavProduct.add(NavQueries.getSyncProductInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
			changingStockNavProductNewTp.add(NavisionInventorySyncCalls.getItemInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}

		
		System.out.println("before calculateStockBasedOnPendingOrders");
		changingStockMagentoProducts = StockCalculations.calculateStockBasedOnPendingOrders(changingStockMagentoProducts);
		changingStockMagentoProductsNewTp=StockCalculations.calculateStockBasedOnPendingOrders(changingStockMagentoProductsNewTp);

//		if (DateUtils.isDateAfter(DateUtils.getCurrentDateTwoHoursBack("YYYY-MM-dd HH:mm:ss"), syncDate, "YYYY-MM-dd HH:mm:ss")
//				&& orderStatusModel.getSyncStatus().contentEquals("Yes")) {
//			isSyncronyzed = true;
//		}
		
		shopOrder = OrderInfoMagCalls.getOrderInfo(orderModel.getOrderId());
	}

	@Test
	public void us23001VerifyStockSyncAfterTpOrderImportTest() throws SQLException, ParseException {

	///	stockProductsValidations.validateSyncronizedStatus(isSyncronyzed);
		importedOrderValidation.validateUpdatedNavDate(shopOrder.getUpdatedNav(),DateUtils.getCurrentDateTwoHoursBack("YYYY-MM-dd HH:mm:ss"),"yyyy-MM-dd");

		stockSyncValidations.setValidateProductsModels(initialChangingNavProducts, changingStockNavProduct);
		stockSyncValidations.validateProducts("VALIDATE NAVISION STOCK IS DECREASED - CHANGING STOCK NAVISION PRODUCTS");

		stockSyncValidations.setValidateProductsModels(initialChangingNavProductsNewTp, changingStockNavProductNewTp);
		stockSyncValidations.validateProducts("VALIDATE NAVISION STOCK IS DECREASED - CHANGING STOCK NAVISION PRODUCTS");
		
		
		stockSyncValidations.setValidateProductsModels(changingStockNavProduct, changingStockMagentoProducts);
		stockSyncValidations.validateProducts("VALIDATE MAGENTO STOCK IS SYNCRONIZED WITH MAGENTO STOCK - CHANGING STOCK PRODUCTS");
		
		
		stockSyncValidations.setValidateProductsModels(changingStockNavProductNewTp, changingStockMagentoProductsNewTp);
		stockSyncValidations.validateProducts("VALIDATE MAGENTO STOCK IS SYNCRONIZED WITH MAGENTO STOCK - CHANGING STOCK PRODUCTS");

		customVerifications.printErrors();
	}

}
