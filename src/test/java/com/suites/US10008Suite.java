package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.uss10008.US10008CloseVirginPartyAnfVerifyCommissionBonusesTest;
import com.tests.uss10.uss10008.US10008OrderForCustomerAsPartyHostTest;

@SuiteClasses({	
	US10008OrderForCustomerAsPartyHostTest.class,
	US10008CloseVirginPartyAnfVerifyCommissionBonusesTest.class,
	
})
@RunWith(Suite.class)
public class US10008Suite {

}
