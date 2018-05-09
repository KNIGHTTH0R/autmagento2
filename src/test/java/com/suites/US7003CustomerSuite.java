package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7003vdv.US7003CheckCustomerActivationVDV;
import com.tests.us7.us7003vdv.US7003CheckReceivedEmailsTestVDV;
import com.tests.us7.us7003vdv.US7003ConfirmCustomerTestVDV;
import com.tests.us7.us7003vdv.US7003RegularCustDykscSearchByPlzTestVDV;
import com.tests.us7.us7003vdv.US7003ValidateCustomerIsAssignedToStylistVDV;

@SuiteClasses({

	US7003RegularCustDykscSearchByPlzTestVDV.class,
	US7003ConfirmCustomerTestVDV.class,
	US7003CheckCustomerActivationVDV.class,
	US7003ValidateCustomerIsAssignedToStylistVDV.class,
	US7003CheckReceivedEmailsTestVDV.class,
})
@RunWith(Suite.class)
public class US7003CustomerSuite {

}
