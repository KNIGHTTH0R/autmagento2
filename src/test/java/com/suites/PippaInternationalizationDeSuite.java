package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsTest;
import com.tests.us3.us30012.US30012SfmSpecialPriceProductTest;
import com.tests.us6.us6006.US6006ScBuyProductwithSpecialPriceTest;
import com.tests.us8.us80011.US80011CustomerOrderProductWithSpecialPriceTest;
import com.tests.uss11.us11003.US11003VerifyProductsInPlaceACustomerOrderModal;
import com.tests.uss11.us11011.US110011PlaceCustomerOrderProductWithSpecialPriceTest;

@SuiteClasses({
	
	CreateProductsTest.class,
	
	US10CreateAllPartiesSuite.class,

	US3001Suite.class,
	US3003Suite.class,
	US3004Suite.class,
	US3006Suite.class,
	US3007Suite.class,
	US3009Suite.class,
	US30010Suite.class,
	US30011Suite.class,
	US30012SfmSpecialPriceProductTest.class,
	
	US4001Suite.class,
	US4002Suite.class,
	
	US6001Suite.class,
	US6001bSuite.class,
	US6002Suite.class,
	US6002bSuite.class,
	US6003Suite.class,
	US6006ScBuyProductwithSpecialPriceTest.class,
	
	US7001Suite.class,
	US7001bSuite.class,
	US7002Suite.class,
	US7004Suite.class,
	US7004bSuite.class,
	US7008Suite.class,
	US7009Suite.class,
	US70010Suite.class,
	US70011Suite.class,
	US70012Suite.class,
	
//	US10006Suite.class,
	
	US8001Suite.class,
	US8002Suite.class,
	US8003Suite.class,	
	US8004Suite.class,
	US8005Suite.class,
	US8006Suite.class,
	US8007Suite.class,
	US8008Suite.class,
	US80011CustomerOrderProductWithSpecialPriceTest.class,
	
	US11001Suite.class,
	US11002Suite.class,
	US11003VerifyProductsInPlaceACustomerOrderModal.class,
	US11004Suite.class,
	US11005Suite.class,
	US11006Suite.class,
	US11007Suite.class,
	US11008Suite.class,
	US110011PlaceCustomerOrderProductWithSpecialPriceTest.class,
	
	US10007SuitePartOne.class,
	US10008Suite.class,
	//US9008Suite 
	US9001Suite.class,
	US9002Suite.class,
	US9002bSuite.class,
	US9004Suite.class,
	US9005Suite.class,
	US9006Suite.class,
	US9007Suite.class,
	
	
	US15004Suite.class,
	
	US12001Suite.class,
	
//	US13001Suite.class,
//	US13002Suite.class,
//	US13003Suite.class,
//	US13004Suite.class,
//	US13005Suite.class,
//	US13006Suite.class,
//	US13007Suite.class,
	
	US16001Suite.class,
	US16002Suite.class,
	
	US17001Suite.class,
	US17002Suite.class,
	US17003Suite.class,
	
//	US23001PartOneSuite.class,
	US20001Suite.class,
//	US21001Suite.class,
//	US22001Suite.class,
	
	US10007SuitePartTwo.class,

//	US23001PartTwoSuite.class,
	
//	US26001Suite.class,
//	US23001PartThreeSuite.class,
	
	NotificationsSuite.class,
	
})
@RunWith(Suite.class)
public class PippaInternationalizationDeSuite {

}
