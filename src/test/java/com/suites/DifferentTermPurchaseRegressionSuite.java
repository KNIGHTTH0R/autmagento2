package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsTest;
import com.tests.uss15.us15004.US15004OrderZzzProductsForCustomerTest;

@SuiteClasses({
		CreateProductsTest.class,
		US10007SuitePartOne.class,
		//Place customer order 
		US15004OrderZzzProductsForCustomerTest.class,
		
		//Place Host Order
		US9001Suite.class,
		US9002Suite.class,
		US9002bSuite.class,
		

})
@RunWith(Suite.class)
public class DifferentTermPurchaseRegressionSuite {
	
}
