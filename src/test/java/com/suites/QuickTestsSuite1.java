package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsTest;
import com.tests.us3.us30012.US30012SfmSpecialPriceProductTest;


@SuiteClasses({
	CreateProductsTest.class,
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
//	
//	US7001RegularCustRegOnMasterTest.class,
//	US7001ConfirmCustomerTest.class,
//	US7001bSuite.class,
////	US7002Suite.class,
////	US7004Suite.class,
////	//US7004bSuite.class,
//	
//	
////	US7008Suite.class,
//	US7009Suite.class,
//	US70010Suite.class,
//	US70011Suite.class,
//	US70012Suite.class,
	
})
@RunWith(Suite.class)
public class QuickTestsSuite1 {

	
}
