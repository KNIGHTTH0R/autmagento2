package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us1.US001StyleCoachShoppingTest;
import com.tests.us1.US001CheckOrderOnStylecoachProfileTest;
import com.tests.us1.US001ValidateOrderBackOfficeTest;
import com.tests.us1.US001ValidateOrderEmailTest;

@SuiteClasses({
	US001StyleCoachShoppingTest.class,
	US001CheckOrderOnStylecoachProfileTest.class,
	US001ValidateOrderEmailTest.class,
	US001ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US001Suite {

}
