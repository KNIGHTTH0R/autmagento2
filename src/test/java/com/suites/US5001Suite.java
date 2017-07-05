package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us5.us5001.US5001FacebookAsociatedContactDeleteTest;
import com.tests.us5.us5001.US5001FacebookCustomerDeleteTest;
import com.tests.us5.us5001.US5001FacebookRegUserLoggedInFBAppNotInstaledTest;
import com.tests.us5.us5001.US5001LoginWithFBAppInstaledUserNotLoggedInFBTest;

@SuiteClasses({
	US5001FacebookCustomerDeleteTest.class,
	US5001FacebookAsociatedContactDeleteTest.class,
	US5001FacebookRegUserLoggedInFBAppNotInstaledTest.class,
	US5001LoginWithFBAppInstaledUserNotLoggedInFBTest.class,	
})
@RunWith(Suite.class)
public class US5001Suite {
	
}
