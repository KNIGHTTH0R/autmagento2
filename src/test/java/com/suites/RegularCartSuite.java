package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsTest;
import com.tests.us8.us80011.US80011CustomerOrderProductWithSpecialPriceTest;

@SuiteClasses({
	CreateProductsTest.class,
	US8001Suite.class,
	US8002Suite.class,
	US8003Suite.class,
	US8004Suite.class,
	US8005Suite.class,
	US8006Suite.class,
	US8007Suite.class,
	US8008Suite.class,
	
	//US80011CustomerOrderProductWithSpecialPriceTest.class,

})
@RunWith(Suite.class)
public class RegularCartSuite {

}
