package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3001.US3001BuyProductsForTheFirstTimeTest;

@SuiteClasses({
	US3001BuyProductsForTheFirstTimeTest.class,
})
@RunWith(Suite.class)
public class QuickTestsSuite3 {
	
}
