package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.us10001.US10001CreatePartyWithStylistHostTest;
import com.tests.uss10.us10002.US10002CreatePartyWithCustomerHostTest;
import com.tests.uss10.us10002b.US10002bCreatePartyWithCustomerHostTest;
import com.tests.uss10.us10003.US10003CheckInviteEmailAndRegistratiionLinkTest;
import com.tests.uss10.us10003.US10003CreatePartyWithNewContactHostTest;
import com.tests.uss10.us10003.US10003EditPartyAndVerifyNotAllowedCountriesTest;
import com.tests.uss10.us10003.US10003VerifyHostPartyCreationEmailTest;
import com.tests.uss10.us10004.US10004CreatePartyWithStylistHostTest;
import com.tests.uss10.us10004.US10004UpdateAndDeletePartyTest;
import com.tests.uss10.us10005.US10005CreatePartyWithCustomerHostTest;
import com.tests.uss10.us10006.US10006ChechEmailAndAcceptInvitationTest;
import com.tests.uss10.us10006.US10006CreatePartyWithStylistHostTest;
import com.tests.uss10.uss10007.US10007CreatePartyWithCustomerHostTest;
import com.tests.uss10.uss10008.US10008CreatePartyWithNewContactHostTest;
import com.tests.uss11.us11001.US11001CreatePartyWithStylistHostTest;
import com.tests.uss11.us11002.US11002CreatePartyWithCustomerHostTest;
import com.tests.uss11.us11004.US11004CreatePartyWithStylistHostTest;
import com.tests.uss11.us11005.US11005CreatePartyWithStylistHostTest;
import com.tests.uss24.US24001CreatePartyWithNewContactPlzValidationTest;
import com.tests.uss25.US25001CreatePartyWithNewContactTest;

@SuiteClasses({
	US10001CreatePartyWithStylistHostTest.class,
	US10002CreatePartyWithCustomerHostTest.class,
	US10002bCreatePartyWithCustomerHostTest.class,
	US10003CreatePartyWithNewContactHostTest.class,	
	US10003EditPartyAndVerifyNotAllowedCountriesTest.class,
	US10003VerifyHostPartyCreationEmailTest.class,
	US10003CheckInviteEmailAndRegistratiionLinkTest.class,
	US10004CreatePartyWithStylistHostTest.class,
	US10004UpdateAndDeletePartyTest.class,
	US10005CreatePartyWithCustomerHostTest.class,
	US10006CreatePartyWithStylistHostTest.class,
	US10006ChechEmailAndAcceptInvitationTest.class,
	US10007CreatePartyWithCustomerHostTest.class,
	US10008CreatePartyWithNewContactHostTest.class,
	US11001CreatePartyWithStylistHostTest.class,
	US11002CreatePartyWithCustomerHostTest.class,
	US11004CreatePartyWithStylistHostTest.class,
	US11005CreatePartyWithStylistHostTest.class,
	US24001CreatePartyWithNewContactPlzValidationTest.class,
	US25001CreatePartyWithNewContactTest.class,
	
})
@RunWith(Suite.class)
public class US10CreateAllPartiesSuite {

}
