package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.US10001ClosePartyTest;
import com.tests.uss10.US10001CreatePartyTest;

@SuiteClasses({
	US10001CreatePartyTest.class,
	US10001ClosePartyTest.class,

	
})
@RunWith(Suite.class)
public class US10001Suite {

}
