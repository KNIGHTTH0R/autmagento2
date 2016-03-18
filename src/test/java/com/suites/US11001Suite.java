package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss11.us11001.US11001PartyHostBuysForCustomerWithVoucherTest;
import com.tests.uss11.us11001.US11001ValidateOrderBackOfficeTest;

@SuiteClasses({
	US11001PartyHostBuysForCustomerWithVoucherTest.class,
//	US11001CheckOrderOnStylecoachProfileTest.class,	
//	US11001ValidateOrderEmailTest.class,	
	US11001ValidateOrderBackOfficeTest.class,	
})
@RunWith(Suite.class)
public class US11001Suite {

}
