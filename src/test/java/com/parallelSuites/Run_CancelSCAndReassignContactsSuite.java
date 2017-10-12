package com.parallelSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.suites.US17001Suite;
import com.suites.US17002Suite;
import com.suites.US17003Suite;


@SuiteClasses({
	US17001Suite.class,
	US17002Suite.class,
	US17003Suite.class,
})
@RunWith(Suite.class)
public class Run_CancelSCAndReassignContactsSuite {

}
