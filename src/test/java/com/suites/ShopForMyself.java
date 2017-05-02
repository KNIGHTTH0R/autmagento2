package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us30012.US30012SfmSpecialPriceProductTest;


@SuiteClasses({
	//CreateProductsTest.class,
	US3001Suite.class,
	US3003Suite.class,
	US3004Suite.class,
	US3005Suite.class,
	US3006Suite.class,
	US3007Suite.class,
	US3009Suite.class,
	US30010Suite.class,
//	US30011Suite.class,
//	US30012SfmSpecialPriceProductTest.class,
	US4001Suite.class,
	US4002Suite.class,
	
})
@RunWith(Suite.class)
public class ShopForMyself {

}
