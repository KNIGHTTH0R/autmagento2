package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.poc.ActivateRule;
import com.poc.DeactivateRule;
import com.tests.us4001.US4001Test;
import com.tests.us4001.US4001UserProfileOrderIdTest;
import com.tests.us4001.US4001ValidateOrderBackOfficeTest;
import com.tests.us4001.US4001ValidateOrderEmailTest;

@SuiteClasses({
	ActivateRule.class,
	US4001Test.class,
	US4001UserProfileOrderIdTest.class,
	US4001ValidateOrderEmailTest.class,
	US4001ValidateOrderBackOfficeTest.class,
	DeactivateRule.class,
})
@RunWith(Suite.class)
public class US4001Suite {

}
