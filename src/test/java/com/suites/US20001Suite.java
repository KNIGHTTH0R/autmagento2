package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss20.us20001.US20001EditStylistTest;
import com.tests.uss20.us20001.US20001StyleCoachRegistrationTest;
import com.tests.uss20.us20001.US20001StylistActivationTest;
import com.tests.uss20.us20001.US20001VerifyNewCreatedStylistDetailsInCommissionTest;

@SuiteClasses({
	US20001StyleCoachRegistrationTest.class,
	US20001StylistActivationTest.class,
	US20001VerifyNewCreatedStylistDetailsInCommissionTest.class,
	US20001EditStylistTest.class,
	US20001VerifyNewCreatedStylistDetailsInCommissionTest.class,
})
@RunWith(Suite.class)
public class US20001Suite {

}
