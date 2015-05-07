package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss11.us11001.US11001OrderForCustomerAsPartyHostTest;
import com.tests.uss11.us11001.US11001UserProfileOrderIdTest;
import com.tests.uss11.us11001.US11001ValidateOrderBackOfficeTest;
import com.tests.uss11.us11001.US11001ValidateOrderEmailTest;

@SuiteClasses({
	
	US11001OrderForCustomerAsPartyHostTest.class,
	US11001UserProfileOrderIdTest.class,	
	US11001ValidateOrderEmailTest.class,	
	US11001ValidateOrderBackOfficeTest.class,	
})
@RunWith(Suite.class)
public class US11001Suite {

}
