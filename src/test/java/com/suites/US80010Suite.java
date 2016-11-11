package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us8.us80010.US80010CustomerOrderSpecialCaseTest;
import com.tests.us8.us80010.US80010DeactivateDiscountOnCartRuleTest;

@SuiteClasses({
	US80010CustomerOrderSpecialCaseTest.class,
	US80010DeactivateDiscountOnCartRuleTest.class,	
	
})
@RunWith(Suite.class)
public class US80010Suite {

}
