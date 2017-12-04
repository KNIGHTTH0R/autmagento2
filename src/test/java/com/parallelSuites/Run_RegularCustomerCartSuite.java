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


@SuiteClasses({
	//CreateSpecificProducts
	US8001Suite.class,
	US8002Suite.class,
	US8003Suite.class,	
	US8004Suite.class,
	US8005Suite.class,
//	US8006Suite.class,
	US8007Suite.class,
	US8008Suite.class,
})
@RunWith(Suite.class)
public class Run_RegularCustomerCartSuite {

}
