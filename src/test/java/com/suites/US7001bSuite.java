package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7001b.US7001bCheckCustomerActivation;
import com.tests.us7.us7001b.US7001bCheckReceivedEmailsTest;
import com.tests.us7.us7001b.US7001bConfirmCustomerTest;
import com.tests.us7.us7001b.US7001bRegularCustRegOnNotPrefCountryTest;
import com.tests.us7.us7001b.US7001bValidateCustomerIsAssignedToStylist;


@SuiteClasses({
//	US7001bRegularCustRegOnNotPrefCountryTest.class,
	US7001bConfirmCustomerTest.class,
	US7001bCheckCustomerActivation.class,	
	US7001bValidateCustomerIsAssignedToStylist.class,
	//US7001bCheckReceivedEmailsTest.class,
})
@RunWith(Suite.class)
public class US7001bSuite {

}
