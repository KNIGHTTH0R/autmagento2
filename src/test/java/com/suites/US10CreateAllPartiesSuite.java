package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.us10001.US10001CreatePartyWithStylistHostTest;
import com.tests.uss10.us10002.US10002CreatePartyWithCustomerHostTest;
import com.tests.uss10.us10003.US10003CreatePartyWithNewContactHostTest;
import com.tests.uss10.us10004.US10004CreatePartyWithStylistHostTest;
import com.tests.uss10.us10005.US10005CreateFolowUpPartyForActivePartyTest;
import com.tests.uss10.us10006.US10006CreatePartyWithStylistHostTest;

@SuiteClasses({
	US10001CreatePartyWithStylistHostTest.class,
	US10002CreatePartyWithCustomerHostTest.class,
	US10003CreatePartyWithNewContactHostTest.class,	
	US10004CreatePartyWithStylistHostTest.class,	
	US10005CreateFolowUpPartyForActivePartyTest.class,
	US10006CreatePartyWithStylistHostTest.class,
	
})
@RunWith(Suite.class)
public class US10CreateAllPartiesSuite {

}
