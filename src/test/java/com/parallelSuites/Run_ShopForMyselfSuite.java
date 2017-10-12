package com.parallelSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.suites.US30010Suite;
import com.suites.US30011Suite;
import com.suites.US3001Suite;
import com.suites.US3003Suite;
import com.suites.US3004Suite;
import com.suites.US3006Suite;
import com.suites.US3007Suite;
import com.suites.US3009Suite;
import com.suites.US4001Suite;
import com.suites.US4002Suite;
import com.tests.CreateProductsTest;
import com.tests.us3.us30012.US30012SfmSpecialPriceProductTest;


@SuiteClasses({
	//CreateProductsTest update it
	//CreateProductsTest.class,
	
	//shop for myself suite
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
	
})
@RunWith(Suite.class)
public class Run_ShopForMyselfSuite {

}
