package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us6.us6005.US6005ScRegistrationNewCustomerTest;
import com.tests.us6.us6005.US6005VerifyStylistAddressesTest;


@SuiteClasses({
	US6005ScRegistrationNewCustomerTest.class,
	US6005VerifyStylistAddressesTest.class,
	
})
@RunWith(Suite.class)
public class US6005Suite {

}
