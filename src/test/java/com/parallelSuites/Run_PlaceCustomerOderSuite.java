package com.parallelSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss11.us11011.US110011PlaceCustomerOrderProductWithSpecialPriceTest;


@SuiteClasses({
	//create specific products
	//create specific parties
	
	//CreateSpecificProducts
/*	US11001Suite.class,
	US11002Suite.class,
	//should be verified 
	//US11003VerifyProductsInPlaceACustomerOrderModal.class,
	US11004Suite.class,
	US11005Suite.class,*/
	
//	US11006Suite.class, -> deprecated
	/*US11007Suite.class,
	US11008Suite.class*/
	US110011PlaceCustomerOrderProductWithSpecialPriceTest.class,
})
@RunWith(Suite.class)
public class Run_PlaceCustomerOderSuite {

}
