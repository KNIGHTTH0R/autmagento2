package com.parallelSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.suites.US11001Suite;
import com.suites.US11002Suite;
import com.suites.US11004Suite;
import com.suites.US11005Suite;
import com.suites.US11007Suite;
import com.suites.US11008Suite;
import com.suites.US70010Suite;
import com.suites.US70011Suite;
import com.suites.US70012Suite;
import com.suites.US7001Suite;
import com.suites.US7001bSuite;
import com.suites.US7002Suite;
import com.suites.US7009Suite;
import com.suites.US7CreateRegularCustomersSuite;
import com.suites.US8001Suite;
import com.suites.US8002Suite;
import com.suites.US8003Suite;
import com.suites.US8004Suite;
import com.suites.US8005Suite;
import com.suites.US8006Suite;
import com.suites.US8007Suite;
import com.suites.US8008Suite;
import com.tests.CreateProductsTest;
import com.tests.us3.us30012.US30012SfmSpecialPriceProductTest;
import com.tests.us6.us6006.US6006ScBuyProductwithSpecialPriceTest;
import com.tests.uss11.us11011.US110011PlaceCustomerOrderProductWithSpecialPriceTest;


@SuiteClasses({
	//create specific products
	//create specific parties
	
	//CreateSpecificProducts
	US11001Suite.class,
	US11002Suite.class,
	//should be verified 
	//US11003VerifyProductsInPlaceACustomerOrderModal.class,
	US11004Suite.class,
	US11005Suite.class,
	
//	US11006Suite.class, -> deprecated
	/*US11007Suite.class,
	US11008Suite.class*/
	US110011PlaceCustomerOrderProductWithSpecialPriceTest.class,
})
@RunWith(Suite.class)
public class Run_PlaceCustomerOderSuite {

}
