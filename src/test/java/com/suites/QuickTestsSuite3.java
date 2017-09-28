package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsTest;
import com.tests.us6.us6006.US6006ScBuyProductwithSpecialPriceTest;
import com.tests.uss11.us11003.US11003VerifyProductsInPlaceACustomerOrderModal;
import com.tests.uss11.us11011.US110011PlaceCustomerOrderProductWithSpecialPriceTest;

@SuiteClasses({
		CreateProductsTest.class,
		US10CreateAllPartiesSuite.class,
		//Place customer order 
		US11001Suite.class,
		US11002Suite.class,
		//should be verified 
		//US11003VerifyProductsInPlaceACustomerOrderModal.class,
		US11004Suite.class,
		US11005Suite.class,
		
		
//		US11006Suite.class, -> deprecated
//		US11007Suite.class,
//		US11008Suite.class,
//		US110011PlaceCustomerOrderProductWithSpecialPriceTest.class,
//		
//		//Party
//		US10007SuitePartOne.class,
})
@RunWith(Suite.class)
public class QuickTestsSuite3 {
	
}
