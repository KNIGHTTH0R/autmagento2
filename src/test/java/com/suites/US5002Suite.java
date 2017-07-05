package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us5.us5002.US5002FacebookAsociatedContactDeleteTest;
import com.tests.us5.us5002.US5002FacebookCustomerDeleteTest;
import com.tests.us5.us5002.US5002FacebookRegUserFBLoggedOutAppInstaledTest;
import com.tests.us5.us5002.US5002LoginWithFBAppInstaledUserLoggedInFBTest;

@SuiteClasses({
	//use data from US5001 suite
	US5002FacebookCustomerDeleteTest.class,
	US5002FacebookAsociatedContactDeleteTest.class,
	US5002FacebookRegUserFBLoggedOutAppInstaledTest.class,
	US5002LoginWithFBAppInstaledUserLoggedInFBTest.class,
	
	
	
	
})
@RunWith(Suite.class)
public class US5002Suite {
	
}
