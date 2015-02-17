package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us1.US001StyleCoachShoppingTest;
import com.tests.us1.US001UserProfileOrderIdTest;
import com.tests.us1.US001ValidateOrderBackOfficeTest;
import com.tests.us1.US001ValidateOrderEmailTest;
import com.tests.us2.US002CartSegmentationLogicTest;
import com.tests.us3001.US3001CartSegmentationWithVatTest;
import com.tests.us3002.US3002CartSegmentationWithVatBillingTest;



@SuiteClasses({
	US001StyleCoachShoppingTest.class,
	US001UserProfileOrderIdTest.class,
	US001ValidateOrderBackOfficeTest.class,
	US001ValidateOrderEmailTest.class,
	US002CartSegmentationLogicTest.class,
	US3001CartSegmentationWithVatTest.class,
	US3002CartSegmentationWithVatBillingTest.class,
})
@RunWith(Suite.class)
public class DemoSuite {

}
