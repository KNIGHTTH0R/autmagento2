package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us6.us6002.US6002CheckStylistActivationTest;
import com.tests.us6.us6002.US6002CheckStylistPreferedWebsiteAndLanguage;
import com.tests.us6.us6002.US6002CreateCustomerTest;
import com.tests.us6.us6002.US6002StyleCoachRegistrationTest;


@SuiteClasses({
	US6002CreateCustomerTest.class,
	US6002StyleCoachRegistrationTest.class,
	US6002CheckStylistActivationTest.class,
	US6002CheckStylistPreferedWebsiteAndLanguage.class,
})
@RunWith(Suite.class)
public class US6002Suite {

}
