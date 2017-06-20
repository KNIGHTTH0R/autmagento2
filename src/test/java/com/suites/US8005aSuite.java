package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us8a.US8005a.US8005aCustomerBuyWithSimpleProductTest;
import com.tests.us8a.US8005a.US8005aVerifyKoboSingleArticleAndPomFlagsTest;

@SuiteClasses({
	US8005aCustomerBuyWithSimpleProductTest.class,
	US8005aVerifyKoboSingleArticleAndPomFlagsTest.class,	
	
})
@RunWith(Suite.class)
public class US8005aSuite {

}