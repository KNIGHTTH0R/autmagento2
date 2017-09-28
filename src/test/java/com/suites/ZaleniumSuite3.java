package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsTest;
import com.tests.uss10.us10001.US10001CreatePartyWithCustomerHostTest;
import com.tests.uss10.us10001b.US10001bCreatePartyWithStylistHostTest;
import com.tests.uss10.us10002b.US10002bCreatePartyWithCustomerHostTest;

@SuiteClasses({
	CreateProductsTest.class,
	//shop for myself suite
	US3001Suite.class,
	 
})
@RunWith(Suite.class)
public class ZaleniumSuite3 {

}
