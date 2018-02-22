package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us6.us6001.US6001CancelStarterSetOrderTestVDV;
import com.tests.us6.us6001.US6001CheckAssociatedContactCreationTestVDV;
import com.tests.us6.us6001.US6001CheckStylistActivationStarterkitCanceledTestVDV;
import com.tests.us6.us6001.US6001CheckStylistActivationTestVDV;
import com.tests.us6.us6001.US6001ScRegistrationNewCustomerTestVDV;
import com.tests.us6.us6001.US6001ValidateStarterSetOrderInBackendTestVDV;

@SuiteClasses({
	US6001ScRegistrationNewCustomerTestVDV.class,
	US6001CheckStylistActivationTestVDV.class,
	US6001CancelStarterSetOrderTestVDV.class,
	US6001CheckStylistActivationStarterkitCanceledTestVDV.class,
	US6001CheckAssociatedContactCreationTestVDV.class,
	US6001ValidateStarterSetOrderInBackendTestVDV.class
	 
})
@RunWith(Suite.class)
public class US6001_ScRegistration {

}
