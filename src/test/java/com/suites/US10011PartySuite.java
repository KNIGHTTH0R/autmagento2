package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.uss10011.US10011AccessGuestPageFromPartyDetailsAndDeclinInvitatinTest;
import com.tests.uss10.uss10011.US10011AddManuallyGuestsToPartyFromHostPartyPageTest;
import com.tests.uss10.uss10011.US10011CreatePartyWithNewContactTest;
import com.tests.uss10.uss10011.US10011VerifyGuestListOnPartyDetailsAndHostPageTest;


@SuiteClasses({
	US10011CreatePartyWithNewContactTest.class,
	US10011AccessGuestPageFromPartyDetailsAndDeclinInvitatinTest.class,
	US10011AddManuallyGuestsToPartyFromHostPartyPageTest.class,
	US10011VerifyGuestListOnPartyDetailsAndHostPageTest.class,

})
@RunWith(Suite.class)
public class US10011PartySuite {

}
 