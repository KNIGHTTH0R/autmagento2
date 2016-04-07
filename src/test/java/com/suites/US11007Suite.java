package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss11.us11007.US11007PartyHostBuysForCustomerTpTest;
import com.tests.uss11.us11007.US11007ValidateOrdersBackEndTest;

@SuiteClasses({
	US11007PartyHostBuysForCustomerTpTest.class,
	US11007ValidateOrdersBackEndTest.class,	
})
@RunWith(Suite.class)
public class US11007Suite {

}
