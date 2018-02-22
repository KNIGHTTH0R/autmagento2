package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



@SuiteClasses({ 
	
	
		
		US3001_SFM.class,
		US8001_RegularCustomer.class,
		US11001_PlaceCustomer.class,
		US9001_PlaceHost.class,
		US6001_ScRegistration.class,
	

})
@RunWith(Suite.class)
public class VDV_RegressionSuite {

}
