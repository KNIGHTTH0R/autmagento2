package com.parallelSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.suites.US70010Suite;
import com.suites.US70011Suite;
import com.suites.US70012Suite;
import com.suites.US7001Suite;
import com.suites.US7001bSuite;
import com.suites.US7002Suite;
import com.suites.US7009Suite;
import com.suites.US7CreateRegularCustomersSuite;
import com.tests.CreateProductsTest;
import com.tests.us3.us30012.US30012SfmSpecialPriceProductTest;
import com.tests.us6.us6006.US6006ScBuyProductwithSpecialPriceTest;


@SuiteClasses({
	US7CreateRegularCustomersSuite.class,
	US7001Suite.class,
	US7001bSuite.class,
	US7002Suite.class,
	
//	US7008Suite.class,
	US7009Suite.class,
	US70010Suite.class,
	US70011Suite.class,
	US70012Suite.class,
	
})
@RunWith(Suite.class)
public class Run_RegularCustomerRegistrationSuite {

}
