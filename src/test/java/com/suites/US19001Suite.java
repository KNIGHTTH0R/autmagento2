package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss19.us19001.US19001UnregisteredConnectWithMeOnMasterSearchByPlzTest;
import com.tests.uss19.us19001.US19001VerifyThatContactsWhereAddedToStylecoachTest;

@SuiteClasses({
	US19001UnregisteredConnectWithMeOnMasterSearchByPlzTest.class,
//	US19001VerifyContactReceivedEmailTest.class,
	US19001VerifyThatContactsWhereAddedToStylecoachTest.class,
})
@RunWith(Suite.class)
public class US19001Suite {

}
