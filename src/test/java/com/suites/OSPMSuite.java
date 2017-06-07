package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss37.US37001OSPMHappyPathTest;
import com.tests.uss37.US37001OSPMInsufficientPrivilegesLoginTest;
import com.tests.uss37.US37001OSPMPrivilegesRemovedSpontaneouslyTest;
import com.tests.uss37.US37001OSPMRemovePjAppFromFacebookTest;



@SuiteClasses({
	US37001OSPMRemovePjAppFromFacebookTest.class,
	US37001OSPMHappyPathTest.class,
	US37001OSPMRemovePjAppFromFacebookTest.class,
	US37001OSPMInsufficientPrivilegesLoginTest.class,
	US37001OSPMRemovePjAppFromFacebookTest.class,
	US37001OSPMPrivilegesRemovedSpontaneouslyTest.class,
})
@RunWith(Suite.class)
public class OSPMSuite {

}

