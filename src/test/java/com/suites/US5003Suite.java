package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us5.us5003.US5003FacebookAsociatedContactDeleteTest;
import com.tests.us5.us5003.US5003FacebookCustomerDeleteTest;
import com.tests.us5.us5003.US5003FacebookRegUserLoggedInFBAppInstaledTest;
import com.tests.us5.us5003.US5003LoginWithFBAppNotInstaledUserNotLoggedInFBTest;
import com.tests.us5.us5003.US5003RemovePippaAppFromFacebookTest;

@SuiteClasses({
	//use data from US5002 suite
	US5003FacebookCustomerDeleteTest.class,
	US5003FacebookAsociatedContactDeleteTest.class,
	US5003FacebookRegUserLoggedInFBAppInstaledTest.class,
	US5003RemovePippaAppFromFacebookTest.class,
	US5003LoginWithFBAppNotInstaledUserNotLoggedInFBTest.class,
	
	
})
@RunWith(Suite.class)
public class US5003Suite {
	
}
