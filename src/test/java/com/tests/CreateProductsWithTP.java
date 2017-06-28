package com.tests;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.joda.time.Interval;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.mongo.MongoConnector;
import com.tools.constants.SoapKeys;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.workflows.frontend.AddProductsWorkflow;

@WithTag(name = "US3.1 Shop for myself VAT valid and no SMB billing and shipping AT", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_1.class)
@RunWith(SerenityRunner.class)
public class CreateProductsWithTP extends BaseTest {

	private ProductDetailedModel genProduct1 = new ProductDetailedModel();
	private ProductDetailedModel genProduct2 = new ProductDetailedModel();
	private ProductDetailedModel genProduct3 = new ProductDetailedModel();
	private ProductDetailedModel genProduct4 = new ProductDetailedModel();
	private ProductDetailedModel genProduct5 = new ProductDetailedModel();
	private ProductDetailedModel genProduct6 = new ProductDetailedModel();
	private ProductDetailedModel genProduct7 = new ProductDetailedModel();
	private ProductDetailedModel genProduct8 = new ProductDetailedModel();
	private ProductDetailedModel genProduct9 = new ProductDetailedModel();
	private ProductDetailedModel genProduct10 = new ProductDetailedModel();
	private ProductDetailedModel genProduct11 = new ProductDetailedModel();
	private ProductDetailedModel genProduct12 = new ProductDetailedModel();
	private ProductDetailedModel genProduct13 = new ProductDetailedModel();
	private ProductDetailedModel genProduct14 = new ProductDetailedModel();
	private ProductDetailedModel genProduct15 = new ProductDetailedModel();
	private ProductDetailedModel genProduct16 = new ProductDetailedModel();
	private ProductDetailedModel genProduct17 = new ProductDetailedModel();
	private ProductDetailedModel genProduct18 = new ProductDetailedModel();
	private ProductDetailedModel genProduct19 = new ProductDetailedModel();
	private ProductDetailedModel genProduct20 = new ProductDetailedModel();
	private ProductDetailedModel genProduct21 = new ProductDetailedModel();
	private ProductDetailedModel genProduct22 = new ProductDetailedModel();
	private ProductDetailedModel genProduct23 = new ProductDetailedModel();
	private ProductDetailedModel genProduct24 = new ProductDetailedModel();
	private ProductDetailedModel genProduct25 = new ProductDetailedModel();
	private ProductDetailedModel genProduct26 = new ProductDetailedModel();
	private ProductDetailedModel genProduct27 = new ProductDetailedModel();
	private ProductDetailedModel genProduct28 = new ProductDetailedModel();
	private ProductDetailedModel genProduct29 = new ProductDetailedModel();
	private ProductDetailedModel genProduct30 = new ProductDetailedModel();
	private ProductDetailedModel genProduct31 = new ProductDetailedModel();
	private ProductDetailedModel genProduct32 = new ProductDetailedModel();
	String voucherValue;

	@Steps
	public AddProductsWorkflow addProductsWorkflow;
	public static List<ProductDetailedModel> productsList = new ArrayList<ProductDetailedModel>();

	@Before
	public void setUp() throws Exception {

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
	}

	@Test
	public void createProductsWithTP() {
		// products for Shop for myself cart- us3001
		// and regular cart-us8001, us8002,us8003,us8004

		/*
		 * Calendar cal = Calendar.getInstance();
		 * System.out.println(cal.getTimeInMillis() ); long startDate
		 * =cal.getTimeInMillis();
		 */
		// System.out.println("star date "+ DateUtils.getCurrentDate("yyyy/MM/dd
		// HH:mm:ss"));

		// immediate
		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("50.00");
		MagentoProductCalls.createApiProduct(genProduct1);
		productsList.add(genProduct1);
		// immediate with TP
		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.getStockData().setAllowedTermPurchase("1");
		genProduct2.setPrice("30.00");
		MagentoProductCalls.createApiProduct(genProduct2);
		productsList.add(genProduct2);
		// TP
		genProduct3 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct3.setPrice("25.00");
		MagentoProductCalls.createApiProduct(genProduct3);
		productsList.add(genProduct3);

		

		/*
		 * Calendar cal434 = Calendar.getInstance();
		 * System.out.println(cal434.getTimeInMillis()); long endDate
		 * =cal434.getTimeInMillis();
		 * 
		 * 
		 * long duration = endDate - startDate; System.out.println("duration "
		 * +duration);
		 * 
		 * long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
		 * 
		 * System.out.println("diffInSeconds " +diffInSeconds); long
		 * diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		 * System.out.println("diffInMinutes " +diffInMinutes); long diffInHours
		 * = TimeUnit.MILLISECONDS.toHours(duration); System.out.println(
		 * "diffInHours " +diffInHours);
		 */
		// System.out.println("end date "+ DateUtils.getCurrentDate("yyyy/MM/dd
		// HH:mm:ss"));

		/*
		 * //products for shop for myself cart - us3005 genProduct4 =
		 * MagentoProductCalls.createProductModel(); genProduct4.setIp("25");
		 * genProduct4.setPrice("29.90");
		 * MagentoProductCalls.createApiProduct(genProduct4);
		 * productsList.add(genProduct4);
		 * 
		 * genProduct5 = MagentoProductCalls.createProductModel();
		 * genProduct5.setIp("60"); genProduct5.setPrice("34.90");
		 * MagentoProductCalls.createApiProduct(genProduct5);
		 * productsList.add(genProduct5);
		 * 
		 * genProduct6 = MagentoProductCalls.createMarketingProductModel();
		 * genProduct6.setPrice("5.00");
		 * MagentoProductCalls.createApiProduct(genProduct6);
		 * productsList.add(genProduct6);
		 * 
		 * //product for REGULAR CART - us8001 genProduct7 =
		 * MagentoProductCalls.createProductModel();
		 * genProduct7.setPrice("10.00"); genProduct7.setIp("8");
		 * MagentoProductCalls.createApiProduct(genProduct7);
		 * productsList.add(genProduct7);
		 * 
		 * genProduct8 = MagentoProductCalls.createPomProductModel();
		 * genProduct8.setPrice("49.90"); voucherValue = genProduct8.getPrice();
		 * MagentoProductCalls.createApiProduct(genProduct8);
		 * productsList.add(genProduct8);
		 * 
		 * //products with TP for regular orders - us8007 genProduct9 =
		 * MagentoProductCalls.createNotAvailableYetProductModel();
		 * genProduct9.setPrice("49.90");
		 * MagentoProductCalls.createApiProduct(genProduct9);
		 * productsList.add(genProduct9);
		 * 
		 * genProduct10 = MagentoProductCalls.createProductModel();
		 * genProduct10.setPrice("5.00"); genProduct10.setStockData(
		 * MagentoProductCalls.createNotAvailableYetStockData(DateUtils.
		 * getNextMonthMiddle("yyyy-MM-dd")));
		 * MagentoProductCalls.createApiProduct(genProduct10);
		 * productsList.add(genProduct10);
		 * 
		 * genProduct11 =
		 * MagentoProductCalls.createNotAvailableYetProductModel();
		 * genProduct11.setPrice("19.90"); genProduct11.setIp("0");
		 * MagentoProductCalls.createApiProduct(genProduct11);
		 * productsList.add(genProduct11);
		 * 
		 * //products with TP for regular cart - us8009 genProduct12 =
		 * MagentoProductCalls.createProductModel();
		 * genProduct12.setPrice("50.00"); genProduct12.setStockData(
		 * MagentoProductCalls.createNotAvailableYetStockData(DateUtils.
		 * getNextMonthMiddle("yyyy-MM-dd")));
		 * MagentoProductCalls.createApiProduct(genProduct12);
		 * productsList.add(genProduct12);
		 * 
		 * genProduct13 =
		 * MagentoProductCalls.createNotAvailableYetProductModel();
		 * genProduct13.setPrice("10.00"); genProduct13.setIp("8");
		 * MagentoProductCalls.createApiProduct(genProduct13);
		 * productsList.add(genProduct13);
		 * 
		 * genProduct14 = MagentoProductCalls.createProductModel();
		 * genProduct14.setPrice("5.00");
		 * MagentoProductCalls.createApiProduct(genProduct14);
		 * productsList.add(genProduct14);
		 * 
		 * genProduct15 = MagentoProductCalls.createProductModel();
		 * genProduct15.setPrice("29.90"); genProduct15.setIp("25");
		 * MagentoProductCalls.createApiProduct(genProduct15);
		 * productsList.add(genProduct15);
		 * 
		 * genProduct16 =
		 * MagentoProductCalls.createNotAvailableYetProductModel();
		 * genProduct16.setPrice("29.00"); genProduct16.setIp("0");
		 * MagentoProductCalls.createApiProduct(genProduct16);
		 * productsList.add(genProduct16);
		 * 
		 * genProduct17 =
		 * MagentoProductCalls.createNotAvailableYetProductModel();
		 * genProduct17.setPrice("9.90"); genProduct17.setIp("0");
		 * MagentoProductCalls.createApiProduct(genProduct17);
		 * productsList.add(genProduct17);
		 * 
		 * genProduct18 = MagentoProductCalls.createProductModel();
		 * genProduct18.setPrice("120.00");
		 * genProduct18.setSpecialPrice("100.00");
		 * MagentoProductCalls.createApiProduct(genProduct18);
		 * productsList.add(genProduct18);
		 * 
		 * genProduct19 = MagentoProductCalls.createProductModel();
		 * genProduct19.setPrice("449.50");
		 * MagentoProductCalls.createApiProduct(genProduct19);
		 * productsList.add(genProduct19);
		 * 
		 * genProduct20 = MagentoProductCalls.createProductModel();
		 * genProduct20.setPrice("49.90"); //
		 * genProduct1.setSpecialPrice("40.00");
		 * MagentoProductCalls.createApiProduct(genProduct20);
		 * productsList.add(genProduct20);
		 * 
		 * genProduct21 = MagentoProductCalls.createProductModel();
		 * genProduct21.setPrice("89.00");
		 * MagentoProductCalls.createApiProduct(genProduct21);
		 * productsList.add(genProduct21);
		 * 
		 * genProduct22 = MagentoProductCalls.createProductModel();
		 * genProduct22.setPrice("49.50");
		 * MagentoProductCalls.createApiProduct(genProduct22);
		 * productsList.add(genProduct22);
		 * 
		 * genProduct23 = MagentoProductCalls.createProductModel();
		 * genProduct23.setPrice("89.00");
		 * MagentoProductCalls.createApiProduct(genProduct23);
		 * productsList.add(genProduct23);
		 * 
		 * genProduct24 = MagentoProductCalls.createProductModel();
		 * genProduct24.setPrice("49.00");
		 * MagentoProductCalls.createApiProduct(genProduct24);
		 * productsList.add(genProduct24);
		 * 
		 * genProduct25 = MagentoProductCalls.createProductModel();
		 * genProduct25.setPrice("89.00");
		 * MagentoProductCalls.createApiProduct(genProduct25);
		 * productsList.add(genProduct25);
		 * 
		 * genProduct26 = MagentoProductCalls.createProductModel();
		 * genProduct26.setPrice("49.90");
		 * MagentoProductCalls.createApiProduct(genProduct26);
		 * productsList.add(genProduct26);
		 * 
		 * genProduct27 = MagentoProductCalls.createProductModel();
		 * genProduct27.setPrice("89.00");
		 * MagentoProductCalls.createApiProduct(genProduct27);
		 * productsList.add(genProduct27);
		 * 
		 * genProduct28 = MagentoProductCalls.createProductModel();
		 * genProduct28.setPrice("49.90");
		 * MagentoProductCalls.createApiProduct(genProduct28);
		 * productsList.add(genProduct28);
		 * 
		 * genProduct29 = MagentoProductCalls.createProductModel();
		 * genProduct29.setPrice("89.00");
		 * MagentoProductCalls.createApiProduct(genProduct29);
		 * productsList.add(genProduct29);
		 * 
		 * genProduct30 = MagentoProductCalls.createProductModel();
		 * genProduct30.setPrice("49.00");
		 * MagentoProductCalls.createApiProduct(genProduct30);
		 * productsList.add(genProduct30);
		 * 
		 * genProduct31 = MagentoProductCalls.createProductModel();
		 * genProduct31.setPrice("89.00");
		 * MagentoProductCalls.createApiProduct(genProduct31);
		 * productsList.add(genProduct31);
		 * 
		 * genProduct32 = MagentoProductCalls.createProductModel();
		 * genProduct32.setPrice("100");
		 * MagentoProductCalls.createApiProduct(genProduct32);
		 * productsList.add(genProduct32);
		 * 
		 * genProduct1 = MagentoProductCalls.createProductModel();
		 * MagentoProductCalls.createApiProduct(genProduct1);
		 * genProduct1.getStockData().setEarliestAvailability(DateUtils.
		 * getCurrentDate("yyyy-MM-dd"));
		 * 
		 * // immediate with TP genProduct2 =
		 * MagentoProductCalls.createProductModel();
		 * genProduct2.getStockData().setAllowedTermPurchase("1");
		 * MagentoProductCalls.createApiProduct(genProduct2);
		 * genProduct2.getStockData().setEarliestAvailability(DateUtils.
		 * getCurrentDate("yyyy-MM-dd"));
		 * 
		 * // TP genProduct3 =
		 * MagentoProductCalls.createNotAvailableYetProductModel();
		 * MagentoProductCalls.createApiProduct(genProduct3);
		 * 
		 */

		// System.out.println("size " + productsList.size());
		// System.out.println(productsList.get(0));

	}

	@After
	public void saveData() {
		for (ProductDetailedModel product : productsList) {
			MongoWriter.saveProductDetailedModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}
	}

}
