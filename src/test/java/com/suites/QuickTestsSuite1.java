package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us30012.US30012SfmSpecialPriceProductTest;
import com.tests.us7.us7001.US7001ConfirmCustomerTest;
import com.tests.us7.us7001.US7001RegularCustRegOnMasterTest;


@SuiteClasses({
	US10CreateAllPartiesSuite.class,
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
