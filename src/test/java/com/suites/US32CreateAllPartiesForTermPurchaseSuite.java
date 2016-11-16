package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss32.us32001.US32001CreatePartyWithStylistHostTest;
import com.tests.uss32.us32002.US32002CreatePartyWithStylistHostTest;
import com.tests.uss32.us32003.US32003CreatePartyWithStylistHostTest;
import com.tests.uss32.us32005.US32005CreatePartyWithStylistHostTest;
import com.tests.uss32.us32006.US32006CreatePartyWithStylistHostTest;
import com.tests.uss32.us32007.US32007CreatePartyWithStylistHostTest;

@SuiteClasses({
	
	US32001CreatePartyWithStylistHostTest.class,
	US32002CreatePartyWithStylistHostTest.class,
	US32003CreatePartyWithStylistHostTest.class,
	US32005CreatePartyWithStylistHostTest.class,
	US32006CreatePartyWithStylistHostTest.class,
	US32007CreatePartyWithStylistHostTest.class,

	
	
})
@RunWith(Suite.class)
public class US32CreateAllPartiesForTermPurchaseSuite {

}
