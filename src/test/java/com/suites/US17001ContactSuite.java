package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss17.us17001.US17001AddNewContactToStyleCoachTest;
import com.tests.uss17.us17001.US17001AddNewContactToStyleCoachUsing100erTest;

@SuiteClasses({
	US17001AddNewContactToStyleCoachTest.class, // add first contact to SC list
	US17001AddNewContactToStyleCoachUsing100erTest.class,
})
@RunWith(Suite.class)
public class US17001ContactSuite {

}
