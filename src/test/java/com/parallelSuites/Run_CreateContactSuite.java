package com.parallelSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.suites.US17001ContactSuite;


@SuiteClasses({
	US17001ContactSuite.class,
})
@RunWith(Suite.class)
public class Run_CreateContactSuite {

}
