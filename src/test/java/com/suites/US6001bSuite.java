package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us6.us6001b.US6001bCheckStylistActivationTest;
import com.tests.us6.us6001b.US6001bCheckStylistPreferedWebsiteAndLanguage;
import com.tests.us6.us6001b.US6001bScRegistrationNewCustForbiddenCountryTest;


@SuiteClasses({
	US6001bScRegistrationNewCustForbiddenCountryTest.class,
	US6001bCheckStylistActivationTest.class,
	US6001bCheckStylistPreferedWebsiteAndLanguage.class,
})
@RunWith(Suite.class)
public class US6001bSuite {

}
