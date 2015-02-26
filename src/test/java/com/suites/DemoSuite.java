package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us1.US001StyleCoachShoppingTest;
import com.tests.us1.US001UserProfileOrderIdTest;
import com.tests.us1.US001ValidateOrderBackOfficeTest;
import com.tests.us1.US001ValidateOrderEmailTest;
import com.tests.us2.US002CartSegmentationLogicTest;
import com.tests.us3001.US3001Test;
import com.tests.us3002.US3002Test;
import com.tests.us3003.US3003Test;
import com.tests.us3004.US3004Test;
import com.tests.us3005.US3005Test;
import com.tests.us3006.US3006Test;
import com.tests.us3007.US3007Test;
import com.tests.us3008.US3008Test;
import com.tests.us3009.US3009Test;



@SuiteClasses({
	US001StyleCoachShoppingTest.class,
	US001UserProfileOrderIdTest.class,
	US001ValidateOrderBackOfficeTest.class,
	US001ValidateOrderEmailTest.class,
	US002CartSegmentationLogicTest.class,
	US3001Test.class,
	US3002Test.class,
	US3003Test.class,
	US3004Test.class,
	US3005Test.class,
	US3006Test.class,
	US3007Test.class,
	US3008Test.class,
	US3009Test.class,
	
})
@RunWith(Suite.class)
public class DemoSuite {

}
