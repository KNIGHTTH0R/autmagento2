package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7009.US7009CheckCustomerActivation;
import com.tests.us7.us7009.US7009CheckReceivedEmailsTest;
import com.tests.us7.us7009.US7009KoboRegOnNotVoucherOwnerContextTest;
import com.tests.us7.us7009.US7009ValidateCustomerIsAssignedToStylist;


@SuiteClasses({
	US7009KoboRegOnNotVoucherOwnerContextTest.class,
	US7009CheckCustomerActivation.class,
	US7009CheckReceivedEmailsTest.class,
	US7009ValidateCustomerIsAssignedToStylist.class,
})
@RunWith(Suite.class)
public class US7009Suite {

}
