package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us9.us9001.US9001PlaceHostOrderWithForthyDiscountsAndJbTest;
import com.tests.us9.us9001.US9001ValidateOrderBackOfficeTest;

@SuiteClasses({
	
	US9001PlaceHostOrderWithForthyDiscountsAndJbTest.class,
//	US9001CheckOrderOnStylecoachProfileTest.class,	
//	US9001ValidateOrderEmailTest.class,	
	US9001ValidateOrderBackOfficeTest.class,	
})
@RunWith(Suite.class)
public class US9001Suite {

}
