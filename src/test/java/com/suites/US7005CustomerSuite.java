package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7005vdv.US7005CheckCustomerActivationTestVDV;
import com.tests.us7.us7005vdv.US7005CheckReceivedEmailsTestVDV;
import com.tests.us7.us7005vdv.US7005ConfirmCustomerTestVDV;
import com.tests.us7.us7005vdv.US7005RegCustRegUnderSpecificBEStylistTestVDV;
import com.tests.us7.us7005vdv.US7005ValidateCustomerIsAssignedToStylistVDV;

@SuiteClasses({
	US7005RegCustRegUnderSpecificBEStylistTestVDV.class,
	US7005ConfirmCustomerTestVDV.class,
	US7005CheckCustomerActivationTestVDV.class,
	US7005ValidateCustomerIsAssignedToStylistVDV.class,
	US7005CheckReceivedEmailsTestVDV.class,
})
@RunWith(Suite.class)
public class US7005CustomerSuite {

}
