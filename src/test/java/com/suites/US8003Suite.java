package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.poc.ActivateBuyGet1ForRegular;
import com.poc.DeactivateBuy3Get1ForRegular;
import com.tests.us8.us8003.US8003CustomerBuyWithForthyDiscountsJbAndBuy3Get1Test;
import com.tests.us8.us8003.US8003UserProfileOrderIdTest;
import com.tests.us8.us8003.US8003ValidateOrderBackOfficeTest;
import com.tests.us8.us8003.US8003ValidateOrderEmailTest;

@SuiteClasses({
	ActivateBuyGet1ForRegular.class,
	US8003CustomerBuyWithForthyDiscountsJbAndBuy3Get1Test.class,
	US8003UserProfileOrderIdTest.class,	
	US8003ValidateOrderEmailTest.class,	
	US8003ValidateOrderBackOfficeTest.class,
	DeactivateBuy3Get1ForRegular.class,
	
})
@RunWith(Suite.class)
public class US8003Suite {

}
