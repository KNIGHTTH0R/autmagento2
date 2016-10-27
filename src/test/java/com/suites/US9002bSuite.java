package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us9.us9002b.US9002bPlaceHostOrderAndShipToSylecoachTest;
import com.tests.uss10.us10002b.US10002bClosePartyTest;

@SuiteClasses({
	
	US10002bClosePartyTest.class,
	US9002bPlaceHostOrderAndShipToSylecoachTest.class,
	
})
@RunWith(Suite.class)
public class US9002bSuite {

}
