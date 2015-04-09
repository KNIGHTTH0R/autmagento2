package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.us10005.US10005CreateFolowUpPartyForActivePartyTest;
import com.tests.uss10.us10005.US10005VerifyFollowUpPartyCreationEmailTest;
import com.tests.uss10.us10005.US10005VerifyHostPartyCreationEmailTest;

@SuiteClasses({
	US10005VerifyHostPartyCreationEmailTest.class,
	US10005CreateFolowUpPartyForActivePartyTest.class,
	US10005VerifyFollowUpPartyCreationEmailTest.class,	
	
})
@RunWith(Suite.class)
public class US10005Suite {

}
