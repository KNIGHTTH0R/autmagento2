package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses({
	
	US17001Suite.class,
	US17002Suite.class,
	US17003Suite.class,
	
})
@RunWith(Suite.class)
public class MassReassignSuite {

}
