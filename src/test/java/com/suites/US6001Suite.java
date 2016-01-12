package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us6.us6001.US6001CancelStarterSetOrderTest;
import com.tests.us6.us6001.US6001CheckStylistActivationStarterkitCanceledTest;
import com.tests.us6.us6001.US6001CheckStylistActivationTest;
import com.tests.us6.us6001.US6001CheckStylistPreferedWebsiteAndLanguage;
import com.tests.us6.us6001.US6001ScRegistrationNewCustomerTest;


@SuiteClasses({
	US6001ScRegistrationNewCustomerTest.class,
	US6001CheckStylistActivationTest.class,
	US6001CheckStylistPreferedWebsiteAndLanguage.class,
	US6001CancelStarterSetOrderTest.class,
	US6001CheckStylistActivationStarterkitCanceledTest.class,
})
@RunWith(Suite.class)
public class US6001Suite {

}
