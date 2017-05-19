package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us8.us8003.US8003ActivateBuyGet1Test;
import com.tests.us8.us8003.US8003CheckOrderOnCustomerProfileTest;
import com.tests.us8.us8003.US8003CustomerBuyWithForthyDiscountsJbAndBuy3Get1Test;
import com.tests.us8.us8003.US8003DeactivateBuy3Get1Test;
import com.tests.us8.us8003.US8003ValidateOrderBackOfficeTest;
import com.tests.us8.us8003.US8003ValidateOrderEmailTest;
import com.tests.us8.us8003.US8003ValidateOrderInStylistsCustomerOrderReportTest;

@SuiteClasses({
	US8003ActivateBuyGet1Test.class,
	US8003CustomerBuyWithForthyDiscountsJbAndBuy3Get1Test.class,
	
//	US8003ValidateOrderEmailTest.class,	
	US8003ValidateOrderBackOfficeTest.class,
	US8003ValidateOrderInStylistsCustomerOrderReportTest.class,
	US8003DeactivateBuy3Get1Test.class,
	US8003CheckOrderOnCustomerProfileTest.class,	
})
@RunWith(Suite.class)
public class US8003Suite {

}
