package com.parallelSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.suites.US6001Suite;
import com.suites.US6001bSuite;
import com.suites.US6002Suite;
import com.suites.US6002bSuite;
import com.suites.US6003Suite;
import com.tests.CreateProductsTest;
import com.tests.us3.us30012.US30012SfmSpecialPriceProductTest;
import com.tests.us6.us6002.US6002CreateCustomerTest;
import com.tests.us6.us6002b.US6002bCreateCustomerTest;
import com.tests.us6.us6006.US6006ScBuyProductwithSpecialPriceTest;


@SuiteClasses({
	US6002CreateCustomerTest.class,
	US6002bCreateCustomerTest.class,

	US6001Suite.class,
	US6001bSuite.class,
	US6002Suite.class,
	US6002bSuite.class,
	US6003Suite.class,
//	US6006ScBuyProductwithSpecialPriceTest.class,
	
})
@RunWith(Suite.class)
public class Run_StylistRegistrationSuite {

}
