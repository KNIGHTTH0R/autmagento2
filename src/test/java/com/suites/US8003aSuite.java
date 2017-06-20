package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us8a.us8003a.US8003aCustomerBuyTwoSimplePomWithCoboTest;
import com.tests.us8a.us8003a.US8003aVerifyKoboSingleArticleAndPomFlagsTest;

@SuiteClasses({
	US8003aCustomerBuyTwoSimplePomWithCoboTest.class,
	US8003aVerifyKoboSingleArticleAndPomFlagsTest.class,	
	
})
@RunWith(Suite.class)
public class US8003aSuite {

}