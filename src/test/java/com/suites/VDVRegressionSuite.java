package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



@SuiteClasses({ 
	
		US3001SFMSuite.class,
		US8001RegularCustomerSuite.class,
		US11001PlaceCustomerSuite.class,
		US9001PlaceHost.class,
		US6001ScRegistrationSuite.class,
	
})
@RunWith(Suite.class)
public class VDVRegressionSuite {

}
