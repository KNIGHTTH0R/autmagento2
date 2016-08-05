package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us30010.US30010ActivateFreeShippingRuleTest;
import com.tests.us3.us30010.US30010DeactivatefreeShippingRuleTest;
import com.tests.us3.us30010.US30010SfmFreeShippingTest;

@SuiteClasses({
	US30010ActivateFreeShippingRuleTest.class,
	US30010SfmFreeShippingTest.class,
	US30010DeactivatefreeShippingRuleTest.class,
})
@RunWith(Suite.class)
public class US30010Suite {

}

