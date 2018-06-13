package com.parallelSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.suites.US3001SFMSuite;
import com.suites.US3002SFMSuite;
import com.suites.US3003SFMSuite;


@SuiteClasses({
	US3001SFMSuite.class,
	US3002SFMSuite.class,
	US3003SFMSuite.class,
	
})
@RunWith(Suite.class)
public class Run_ShopForMyselfSuite {
	
}
