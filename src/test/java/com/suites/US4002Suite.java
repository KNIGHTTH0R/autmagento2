package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.poc.ActivateRule;
import com.poc.DeactivateRule;
import com.tests.us4.us4002.US4002ShopForMyselfWithBuy3GetOneTest;
import com.tests.us4.us4002.US4002UserProfileOrderIdTest;
import com.tests.us4.us4002.US4002ValidateOrderBackOfficeTest;
import com.tests.us4.us4002.US4002ValidateOrderEmailTest;

@SuiteClasses({
	ActivateRule.class,
	US4002ShopForMyselfWithBuy3GetOneTest.class,
	US4002UserProfileOrderIdTest.class,
	US4002ValidateOrderEmailTest.class,
	US4002ValidateOrderBackOfficeTest.class,
	DeactivateRule.class,
})
@RunWith(Suite.class)
public class US4002Suite {

}
