package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss11.us11005.US11005PartyHostBuysForCustomerWithVoucherTest;
import com.tests.uss11.us11005.US11005ValidateOrderBackOfficeTest;

@SuiteClasses({
	US11005PartyHostBuysForCustomerWithVoucherTest.class,
//	US11005CheckOrderOnStylecoachProfileTest.class,
//	US11005ValidateOrderEmailTest.class,
	US11005ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US11005Suite {

}
