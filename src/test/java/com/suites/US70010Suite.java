package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.uss70010.US70010CheckCustomerActivation;
import com.tests.us7.uss70010.US70010CheckReceivedEmailsTest;
import com.tests.us7.uss70010.US70010KoboRegOnVoucherOwnerContextTest1;
import com.tests.us7.uss70010.US70010PlacePomOrderTest;
import com.tests.us7.uss70010.US70010ValidateCustomerIsAssignedToStylist;


@SuiteClasses({
	//US70010KoboRegOnVoucherOwnerContextTest.class,
//	US70010KoboRegOnVoucherOwnerContextTest1.class,
	US70010PlacePomOrderTest.class,
	US70010CheckCustomerActivation.class,
	US70010ValidateCustomerIsAssignedToStylist.class,
	US70010CheckReceivedEmailsTest.class,
})
@RunWith(Suite.class)
public class US70010Suite {

}
