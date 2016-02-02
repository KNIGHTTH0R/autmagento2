package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss11.us11005.US11005CheckOrderOnStylecoachProfileTest;
import com.tests.uss11.us11005.US11005CreatePartyWithStylistHostTest;
import com.tests.uss11.us11005.US11005PartyHostBuysForCustomerWithVoucherTest;
import com.tests.uss11.us11005.US11005ValidateOrderBackOfficeTest;
import com.tests.uss11.us11005.US11005ValidateOrderEmailTest;

@SuiteClasses({
	US11005CreatePartyWithStylistHostTest.class,
	US11005PartyHostBuysForCustomerWithVoucherTest.class,
	US11005CheckOrderOnStylecoachProfileTest.class,	
	US11005ValidateOrderEmailTest.class,	
	US11005ValidateOrderBackOfficeTest.class,	
})
@RunWith(Suite.class)
public class US11005Suite {

}
