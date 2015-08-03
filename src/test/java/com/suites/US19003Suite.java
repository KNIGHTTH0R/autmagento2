package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss19.us19003.US19003UnregisteredConnectWithMeOnMasterSearchByNameTest;
import com.tests.uss19.us19003.US19003VerifyContactReceivedEmailTest;
import com.tests.uss19.us19003.US19003VerifyThatContactsWhereAddedToStylecoachTest;

@SuiteClasses({
	US19003UnregisteredConnectWithMeOnMasterSearchByNameTest.class,
	US19003VerifyThatContactsWhereAddedToStylecoachTest.class,
	US19003VerifyContactReceivedEmailTest.class,
})
@RunWith(Suite.class)
public class US19003Suite {

}
