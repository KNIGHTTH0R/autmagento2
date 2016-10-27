package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss19.us19004.US19004RegisteredConnectWithMeOnContextTest;

@SuiteClasses({
	US19004RegisteredConnectWithMeOnContextTest.class,
//	US19004VerifyContactReceivedEmailTest.class,
//	US19004VerifyThatContactsWhereAddedToStylecoachTest.class,

})
@RunWith(Suite.class)
public class US19004Suite {

}
