package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7002vdv.US7002CheckCustomerActivationVDV;
import com.tests.us7.us7002vdv.US7002CheckReceivedEmailsTestVDV;
import com.tests.us7.us7002vdv.US7002ConfirmCustomerTestVDV;
import com.tests.us7.us7002vdv.US7002RegularCustRegistrationOnContextTestVDV;
import com.tests.us7.us7002vdv.US7002ValidateCustomerIsAssignedToStylistVDV;

@SuiteClasses({

	US7002RegularCustRegistrationOnContextTestVDV.class,
	US7002ConfirmCustomerTestVDV.class,
	US7002CheckCustomerActivationVDV.class,
	US7002ValidateCustomerIsAssignedToStylistVDV.class,
	US7002CheckReceivedEmailsTestVDV.class,
	
})
@RunWith(Suite.class)
public class US7002CreateCustomerSuite {

}
