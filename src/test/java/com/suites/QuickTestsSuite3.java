package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsTest;
import com.tests.us6.us6006.US6006ScBuyProductwithSpecialPriceTest;
import com.tests.uss11.us11003.US11003VerifyProductsInPlaceACustomerOrderModal;
import com.tests.uss11.us11011.US110011PlaceCustomerOrderProductWithSpecialPriceTest;

@SuiteClasses({
//	CreateProductsTest.class,
//	BorrowCartSuite.class, 
	
	
//	US6001Suite.class,
//	US6001bSuite.class,
//	US6002Suite.class,
//	US6002bSuite.class,
//	US6003Suite.class,
//	US6006ScBuyProductwithSpecialPriceTest.class,
	
	US7CreateRegularCustomersSuite.class,
	US7001Suite.class,
	US7001bSuite.class,
//	US7002Suite.class,
//	US7004Suite.class,
//	//US7004bSuite.class,
	
	
//	US7008Suite.class,
	US7009Suite.class,
	US70010Suite.class,
	US70011Suite.class,
	US70012Suite.class,
})
@RunWith(Suite.class)
public class QuickTestsSuite3 {
	
}
