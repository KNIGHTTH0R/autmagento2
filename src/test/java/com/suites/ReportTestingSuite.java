package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses({
	US3002Suite.class,
	US3003Suite.class,

})
@RunWith(Suite.class)
public class ReportTestingSuite {

}
