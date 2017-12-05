package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us9.us9007.US90007ClosePartyTest;
import com.tests.us9.us9007.US9007PlaceHostOrderWithSpecialPriceProductHostTest;

@SuiteClasses({
	US90007ClosePartyTest.class,
	US9007PlaceHostOrderWithSpecialPriceProductHostTest.class,

	
})
@RunWith(Suite.class)
public class US9007Suite {

}
