package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us6.us6001.US6001ScRegistrationNewCustomerTest;
import com.tests.us7.us7001b.US7001bRegularCustRegOnNotPrefCountryTest;

@SuiteClasses({
	US7001bRegularCustRegOnNotPrefCountryTest.class,
})
@RunWith(Suite.class)
public class QuickTestsSuite1 {

}
