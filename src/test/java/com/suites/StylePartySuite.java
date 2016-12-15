package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss11.us11003.US11003VerifyProductsInPlaceACustomerOrderModal;
import com.tests.uss11.us11011.US110011PlaceCustomerOrderProductWithSpecialPriceTest;


@SuiteClasses({
//	US11001Suite.class,
//	US11002Suite.class,
//	US11003VerifyProductsInPlaceACustomerOrderModal.class,
//	US11004Suite.class,
//	US11005Suite.class,
//	US11006Suite.class,
//	US11007Suite.class,
//	US11008Suite.class,
//	US110011PlaceCustomerOrderProductWithSpecialPriceTest.class,
	US10007SuitePartOne.class,
	US10008Suite.class,
	US9001Suite.class,
	US9002Suite.class,
	US9002bSuite.class,
	US9004Suite.class,
	US9005Suite.class,
	US9006Suite.class,
	US9007Suite.class,
})
@RunWith(Suite.class)
public class StylePartySuite {

}
