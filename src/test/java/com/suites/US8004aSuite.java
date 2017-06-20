package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us8a.US8004a.US8004aCustomerBuySinglePomWithoutKoboVoucherTest;
import com.tests.us8a.US8004a.US8004aVerifyKoboSingleArticleAndPomFlagsTest;

@SuiteClasses({
	US8004aCustomerBuySinglePomWithoutKoboVoucherTest.class,
	US8004aVerifyKoboSingleArticleAndPomFlagsTest.class,	
	
})
@RunWith(Suite.class)
public class US8004aSuite {

}