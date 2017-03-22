package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7001.US7001RegularCustRegOnMasterTest;

@SuiteClasses({
	US7001RegularCustRegOnMasterTest.class,
})
@RunWith(Suite.class)
public class QuickTestsSuite2 {

}
