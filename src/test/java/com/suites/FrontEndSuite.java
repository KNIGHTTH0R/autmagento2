package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.poc.ActivateRule;
import com.poc.DeactivateRule;
import com.tests.us1.US001StyleCoachShoppingTest;
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
import com.tests.us4001.US4001Test;
import com.tests.us4002.US4002Test;




@SuiteClasses({
	DeactivateRule.class,
	US001StyleCoachShoppingTest.class,
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
	
	
	//3+1 rule
	ActivateRule.class,
	US4001Test.class,
	US4002Test.class,
	DeactivateRule.class,
})
@RunWith(Suite.class)
public class FrontEndSuite {

}
