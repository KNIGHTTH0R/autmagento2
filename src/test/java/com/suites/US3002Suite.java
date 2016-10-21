package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3002.US3002BuyProductsForTheFirstTimeTest;
import com.tests.us3.us3002.US3002SfmScenario1For001DifferenceTest;

@SuiteClasses({
	US3002BuyProductsForTheFirstTimeTest.class,
	US3002SfmScenario1For001DifferenceTest.class,
	
})
@RunWith(Suite.class)
public class US3002Suite {

}
