package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss19.us19002.US19002UnregisteredConnectWithMeOnContextTest;
import com.tests.uss19.us19002.US19002VerifyContactReceivedEmailTest;
import com.tests.uss19.us19002.US19002VerifyThatContactsWhereAddedToStylecoachTest;

@SuiteClasses({
	US19002UnregisteredConnectWithMeOnContextTest.class,
	US19002VerifyThatContactsWhereAddedToStylecoachTest.class,
	US19002VerifyContactReceivedEmailTest.class,
})
@RunWith(Suite.class)
public class US19002Suite {

}
