package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.poc.ActivateBuyGet1ForHost;
import com.poc.DeactivateBuy3Get1ForHost;
import com.tests.us9.us9002.US9002PartyHostBuyWithForthyDiscountsJbAndBuy3Get1Test;
import com.tests.us9.us9002.US9002UserProfileOrderIdTest;
import com.tests.us9.us9002.US9002ValidateOrderBackOfficeTest;
import com.tests.us9.us9002.US9002ValidateOrderEmailTest;

@SuiteClasses({
	ActivateBuyGet1ForHost.class,
	US9002PartyHostBuyWithForthyDiscountsJbAndBuy3Get1Test.class,
	US9002UserProfileOrderIdTest.class,	
	US9002ValidateOrderEmailTest.class,	
	US9002ValidateOrderBackOfficeTest.class,
	DeactivateBuy3Get1ForHost.class,
	
})
@RunWith(Suite.class)
public class US10004Suite {

}
