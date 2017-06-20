package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us8a.us8001a.US8001aCustomerBuyWithContactBoosterTest;
import com.tests.us8a.us8001a.US8001aVerifyKoboSingleArticleAndPomFlagsTest;

@SuiteClasses({
	US8001aCustomerBuyWithContactBoosterTest.class,
	US8001aVerifyKoboSingleArticleAndPomFlagsTest.class,	
	
})
@RunWith(Suite.class)
public class US8001aSuite {

}