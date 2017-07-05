package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us5.us5004.US5004FacebookAsociatedContactDeleteTest;
import com.tests.us5.us5004.US5004FacebookCustomerDeleteTest;
import com.tests.us5.us5004.US5004FacebookRegUserFBLoggedOutAppNotInstaledTest;
import com.tests.us5.us5004.US5004LoginWithFBAppNotInstaledUserLoggedInFBTest;
import com.tests.us5.us5004.US5004RemovePippaAppFromFacebookTest;

@SuiteClasses({
	US5004FacebookCustomerDeleteTest.class,
	US5004FacebookAsociatedContactDeleteTest.class,
	US5004RemovePippaAppFromFacebookTest.class,
	US5004FacebookRegUserFBLoggedOutAppNotInstaledTest.class,
	US5004RemovePippaAppFromFacebookTest.class,
	US5004LoginWithFBAppNotInstaledUserLoggedInFBTest.class,
})
@RunWith(Suite.class)
public class US5004Suite {
	
}
