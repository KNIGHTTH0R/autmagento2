package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsTest;
import com.tests.us6.us6001.US6001ScRegistrationNewCustomerTest;
import com.tests.us7.us7001b.US7001bRegularCustRegOnNotPrefCountryTest;
import com.tests.us7.us7008.US7008CheckReceivedEmailsTest;
import com.tests.us7.uss70010.US70010CheckReceivedEmailsTest;

@SuiteClasses({
//	CreateProductsTest.class,
//	ShopForMyself.class, 
	//CreateProductsTest.class,
	US7CreateRegularCustomersSuite.class,
	
	US7001Suite.class,
	US7001bSuite.class,
	US7002Suite.class,
//	US7004Suite.class,
//	US7004bSuite.class,
	US7008Suite.class,
	US7009Suite.class,
	US70010Suite.class,
	US70011Suite.class,
	US70012Suite.class,
	
	//ce 
	VerifyOrderEmailsSuite.class
})
@RunWith(Suite.class)
public class QuickTestsSuite1 {

	
}
