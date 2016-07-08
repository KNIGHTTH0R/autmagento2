package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss31003.US31003ActivateManualCronTest;
import com.tests.uss31003.US31003PartyHostBuysForCustomerTpTest;
import com.tests.uss31003.US31003ValidateCancelEmailForCustomerTest;
import com.tests.uss31003.US31003ValidateCancelEmailForStylistTest;
import com.tests.uss31003.US31003ValidateCanceledAndReleasedByAdminOrdersInTpGridTest;
import com.tests.uss31003.US31003ValidatePostponeEmailForCustomerTest;
import com.tests.uss31003.US31003ValidatePostponeEmailForStylistTest;
import com.tests.uss31003.US31003ValidatePostponedByAdminOrdersInTpGridTest;
import com.tests.uss31003.US31003ValidateReleaseEmailForStylistTest;

@SuiteClasses({
	US31003ActivateManualCronTest.class,
	US31003PartyHostBuysForCustomerTpTest.class,
	US31003ValidatePostponedByAdminOrdersInTpGridTest.class,
	US31003ValidatePostponeEmailForCustomerTest.class,
	US31003ValidatePostponeEmailForStylistTest.class,
	US31003ValidateCanceledAndReleasedByAdminOrdersInTpGridTest.class,
	US31003ValidateCancelEmailForCustomerTest.class,
	US31003ValidateCancelEmailForStylistTest.class,
	US31003ValidateReleaseEmailForStylistTest.class//inca nu merge pentru ca nu se face automat payment complete orderul
	
	
})
@RunWith(Suite.class)
public class US31003Suite {

}
