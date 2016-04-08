package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss28.us28001.US28001CheckContactInBackendTest;
import com.tests.uss28.us28001.US28001UnbounceRegistrationTest;

@SuiteClasses({
	US28001UnbounceRegistrationTest.class,
	US28001CheckContactInBackendTest.class,
})
@RunWith(Suite.class)
public class US28001Suite {

}
