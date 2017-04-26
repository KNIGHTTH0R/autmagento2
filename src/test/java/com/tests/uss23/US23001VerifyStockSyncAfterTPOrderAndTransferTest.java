package com.tests.uss23;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.http.NavisionInventorySyncCalls;
import com.connectors.http.OrderInfoMagCalls;
import com.steps.backend.ImportOrdersToNavSteps;
import com.steps.external.navision.NavisionHomeSteps;
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
import com.workflows.stockSynk.StockSyncValidations;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;


@WithTag(name = "US23.1 Stock Sync", type = "Scenarios")
@Story(Application.StockSync.US23_1.class)
@RunWith(SerenityRunner.class)
public class US23001VerifyStockSyncAfterTPOrderAndTransferTest extends BaseTest {
	@Steps
	StockSyncValidations stockSyncValidations;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public NavisionHomeSteps navisionSteps;
	@Steps
	CustomVerification customVerification;
	@Steps
	ImportOrdersToNavSteps importedOrderValidation;
	
	int counter=0;

	List<SyncInfoModel> initialChangingMagentoProducts = new ArrayList<SyncInfoModel>();
	List<SyncInfoModel> initialChangingMagentoProductsNewTp = new ArrayList<SyncInfoModel>();

	private List<SyncInfoModel> changingStockMagentoProducts = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> changingStockMagentoProductsNewTp = new ArrayList<SyncInfoModel>();

	List<SyncInfoModel> initialChangingNavProducts = new ArrayList<SyncInfoModel>();
	List<SyncInfoModel> changingStockNavProduct = new ArrayList<SyncInfoModel>();
	
	List<SyncInfoModel> initialChangingNavProductsNewTp = new ArrayList<SyncInfoModel>();
	List<SyncInfoModel> changingStockNavProductNewTp = new ArrayList<SyncInfoModel>();
	
	private static List<String> idListToBeDecreased = new ArrayList<String>(Arrays.asList("4469", "2358", "4271", "4304"));
	private static List<String> skuListToBeDecreased = new ArrayList<String>(Arrays.asList("R185SV-18", "B096SV", "E195SV", "B170BR"));
	
	private static List<String> idListToBeDecreasedNewTp = new ArrayList<String>(
			Arrays.asList("4599", "4575", "4346", "4345"));
	private static List<String> skuListToBeDecreasedNewTP = new ArrayList<String>(
			Arrays.asList("R194RS-18", "N348SV", "A023SV", "A022SV"));
	
	private List<String> boughtProductsQuantities = new ArrayList<String>();
	private List<String> boughtProductsQuantitiesNewTp = new ArrayList<String>();
	private OrderModel orderModel;
	private DBOrderModel shopOrder = new DBOrderModel();
	
	@Before
	public void setUp() throws Exception {
		orderModel = MongoReader.getOrderModel("US23001PlaceTermPurchaseOrderTest").get(0);
		boughtProductsQuantities = MongoReader.grabStringValue("US23001PlaceTermPurchaseOrderTest"+SoapKeys.BOUGHT_PRODUCTS_QUANTITIES);
		boughtProductsQuantitiesNewTp= MongoReader.grabStringValue("US23001PlaceTermPurchaseOrderTest"+SoapKeys.BOUGHT_PRODUCTS_QUANTITIES_NEW_TP);
		
		initialChangingNavProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeTpOrderTest" + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK);
		initialChangingNavProductsNewTp=MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeTpOrderTest" + SoapKeys.NAVISION_INITIAL_CHANGING_STOCK_NEW_TP);
		
		initialChangingNavProducts = StockCalculations.calculateNewStockAfterSyncAndTransfer(initialChangingNavProducts, boughtProductsQuantities,boughtProductsQuantities);
		initialChangingNavProductsNewTp = StockCalculations.calculateNewStockAfterSync(initialChangingNavProductsNewTp, boughtProductsQuantitiesNewTp);
		
		
		for (String id : idListToBeDecreased) {
			changingStockMagentoProducts.add(MagentoProductCalls.getMagProductInfo(id));
		}
		for (String sku : skuListToBeDecreased) {
			String[] skuParts = sku.split("-");
			changingStockNavProduct.add(NavisionInventorySyncCalls.getItemInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}
		
		for (String id : idListToBeDecreasedNewTp) {
			changingStockMagentoProductsNewTp.add(MagentoProductCalls.getMagProductInfo(id));
		}
		for (String sku : skuListToBeDecreasedNewTP) {
			String[] skuParts = sku.split("-");
			changingStockNavProductNewTp.add(NavisionInventorySyncCalls.getItemInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}
		
		changingStockMagentoProducts = StockCalculations.calculateStockBasedOnPendingOrders(changingStockMagentoProducts);
		changingStockMagentoProductsNewTp=StockCalculations.calculateStockBasedOnPendingOrders(changingStockMagentoProductsNewTp);
		
		shopOrder = OrderInfoMagCalls.getOrderInfo(orderModel.getOrderId());
	}
	
	@Test
	public void us23001VerifyStockSyncAfterTPOrderAndTransferTest() throws ParseException {	
		importedOrderValidation.validateUpdatedNavDate(shopOrder.getUpdatedNav(),DateUtils.getCurrentDateTwoHoursBack("YYYY-MM-dd HH:mm:ss"),"yyyy-MM-dd");
		
		stockSyncValidations.setValidateProductsModels(initialChangingNavProducts, changingStockNavProduct);
		stockSyncValidations.validateProducts("VALIDATE NAVISION STOCK IS DECREASED - CHANGING STOCK NAVISION PRODUCTS");
		
		stockSyncValidations.setValidateProductsModels(initialChangingNavProductsNewTp, changingStockNavProductNewTp);
		stockSyncValidations.validateProducts("VALIDATE NAVISION STOCK IS DECREASED - CHANGING STOCK NAVISION PRODUCTS WITH NEW TP");
		
		stockSyncValidations.setValidateProductsModels(changingStockNavProduct, changingStockMagentoProducts);
		stockSyncValidations.validateProducts("VALIDATE MAGENTO STOCK IS SYNCRONIZED WITH MAGENTO STOCK - CHANGING STOCK PRODUCTS");

		stockSyncValidations.setValidateProductsModels(changingStockNavProductNewTp, changingStockMagentoProductsNewTp);
		stockSyncValidations.validateProducts("VALIDATE MAGENTO STOCK IS SYNCRONIZED WITH MAGENTO STOCK - CHANGING STOCK PRODUCTS WITH NEW TP");
		
		
		customVerifications.printErrors();
	}
	
	@After
	public void saveData() {
		
	}
}
