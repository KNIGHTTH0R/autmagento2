package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss11.us11001.US11001PartyHostBuysForCustomerWithVoucherTest;

@SuiteClasses({
	US11001PartyHostBuysForCustomerWithVoucherTest.class,
//	US11001CheckOrderOnStylecoachProfileTest.class,	
//	US11001ValidateOrderEmailTest.class,	
})
@RunWith(Suite.class)
public class US11001Suite {

}
