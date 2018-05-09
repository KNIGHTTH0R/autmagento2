package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.uss10009.US10009AddManuallyGuestsToPartyFromHostPartyPageTest;
import com.tests.uss10.uss10009.US10009CheckAssociatedContactCreationTest;
import com.tests.uss10.uss10009.US10009CreatePartyWithCustomerHostTestVDV;
import com.tests.uss10.uss10009.US10009GuestPageFromEmailCompleteCustomerRegTest;


@SuiteClasses({
	US10009CreatePartyWithCustomerHostTestVDV.class,
	US10009AddManuallyGuestsToPartyFromHostPartyPageTest.class,
	US10009GuestPageFromEmailCompleteCustomerRegTest.class,
	US10009CheckAssociatedContactCreationTest.class,

})
@RunWith(Suite.class)
public class US10009PartySuite {

}
