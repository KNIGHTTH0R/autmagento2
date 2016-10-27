package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us8.us8007.US8007CustomerBuyWithTpTest;
import com.tests.us8.us8007.US8007ValidateRegularOrderBackEndTest;

@SuiteClasses({
	US8007CustomerBuyWithTpTest.class,
	US8007ValidateRegularOrderBackEndTest.class,	
	
})
@RunWith(Suite.class)
public class US8007Suite {

}
