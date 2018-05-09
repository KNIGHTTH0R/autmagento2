package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7004vdv.US7004CheckCustomerActivationTestVDV;
import com.tests.us7.us7004vdv.US7004CheckReceivedEmailsTestVDV;
import com.tests.us7.us7004vdv.US7004ConfirmCustomerTestVDV;
import com.tests.us7.us7004vdv.US7004RegularCustDykscSearchByNameTestVDV;
import com.tests.us7.us7004vdv.US7004ValidateCustomerIsAssignedToStylistVDV;

@SuiteClasses({

	US7004RegularCustDykscSearchByNameTestVDV.class,
	US7004ConfirmCustomerTestVDV.class,
	US7004CheckCustomerActivationTestVDV.class,
	US7004ValidateCustomerIsAssignedToStylistVDV.class,
	US7004CheckReceivedEmailsTestVDV.class,
})
@RunWith(Suite.class)
public class US7004CustomerSuite {

}
