package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us6.us6002b.US6002bCheckStylistActivationTest;
import com.tests.us6.us6002b.US6002bCheckStylistPreferedWebsiteAndLanguage;
import com.tests.us6.us6002b.US6002bCreateCustomerTest;
import com.tests.us6.us6002b.US6002bScRegExistingCustForbiddenCountryTest;


@SuiteClasses({
	US6002bCreateCustomerTest.class,
	US6002bScRegExistingCustForbiddenCountryTest.class,
	US6002bCheckStylistActivationTest.class,
	US6002bCheckStylistPreferedWebsiteAndLanguage.class,
})
@RunWith(Suite.class)
public class US6002bSuite {
	

}
//UARyYzly