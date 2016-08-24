package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3005.US3005BuyProductsForTheFirstTimeTest;
import com.tests.us3.us3005.US3005SfmScenario2For001DifferenceTest;
import com.tests.us3.us3005.US3005ValidateOrderBackOfficeTest;

@SuiteClasses({
	US3005BuyProductsForTheFirstTimeTest.class,
	US3005SfmScenario2For001DifferenceTest.class,
	US3005ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US3005Suite {

}
