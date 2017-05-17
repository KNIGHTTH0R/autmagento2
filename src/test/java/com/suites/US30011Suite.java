package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us30011.US30011DeactivateDiscountOnCartRuleTest;
import com.tests.us3.us30011.US30011SfmSpecialCaseTest;

@SuiteClasses({
	US30011SfmSpecialCaseTest.class,
	US30011DeactivateDiscountOnCartRuleTest.class,
	
})
@RunWith(Suite.class)
public class US30011Suite {

}

