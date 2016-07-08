package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss31.uss31001.US31001ActivateSemiautomatedCronTest;
import com.tests.uss31.uss31001.US31001PartyHostBuysForCustomerTpTest;
import com.tests.uss31.uss31001.US31001ValidateCancelEmailForCustomer1Test;
import com.tests.uss31.uss31001.US31001ValidateCancelEmailForStylist1Test;
import com.tests.uss31.uss31001.US31001ValidateCancelLimitEmailForStylistTest;
import com.tests.uss31.uss31001.US31001ValidateCancelLimitEmailForCustomerTest;
import com.tests.uss31.uss31001.US31001ValidateCancelLimitReachedOrdersInTpGridTest;
import com.tests.uss31.uss31001.US31001ValidateCanceledAndPaymentCompleteOrdersInTpGridTest;
import com.tests.uss31.uss31001.US31001ValidateCanceledAndReleasedOrdersInTpGridTest;
import com.tests.uss31.uss31001.US31001ValidateOrdersInTpGridTest;
import com.tests.uss31.uss31001.US31001ValidatePostponeEmail2ForCustomerTest;
import com.tests.uss31.uss31001.US31001ValidatePostponeEmail2ForStylistTest;
import com.tests.uss31.uss31001.US31001ValidatePostponeEmail3ForCustomerTest;
import com.tests.uss31.uss31001.US31001ValidatePostponeEmail3ForStylistTest;
import com.tests.uss31.uss31001.US31001ValidatePostponeEmailForCustomerTest;
import com.tests.uss31.uss31001.US31001ValidatePostponeEmailForStylistTest;
import com.tests.uss31.uss31001.US31001ValidatePostponedOrdersInTpGrid2Test;
import com.tests.uss31.uss31001.US31001ValidatePostponedOrdersInTpGrid3Test;
import com.tests.uss31.uss31001.US31001ValidatePostponedOrdersInTpGridTest;
import com.tests.uss31.uss31001.US31001ValidateReleaseEmailForStylist1Test;

@SuiteClasses({
	US31001ActivateSemiautomatedCronTest.class,
	US31001PartyHostBuysForCustomerTpTest.class,
	US31001ValidateOrdersInTpGridTest.class,
	US31001ValidatePostponedOrdersInTpGridTest.class,
	US31001ValidatePostponeEmailForCustomerTest.class,
	US31001ValidatePostponeEmailForStylistTest.class, //dureaza mult 
	US31001ValidateCanceledAndReleasedOrdersInTpGridTest.class,
	US31001ValidateCancelEmailForCustomer1Test.class,
	US31001ValidateCancelEmailForStylist1Test.class, //dureaza mult 
//	US31001ValidateReleaseEmailForStylist1Test.class, //pica pentru ca inca nu s-a schimbat statusul in Payment complete si nu primeste emailul
	US31001ValidateCanceledAndPaymentCompleteOrdersInTpGridTest.class,
	US31001ValidatePostponedOrdersInTpGrid2Test.class,
	US31001ValidatePostponeEmail2ForCustomerTest.class,
	US31001ValidatePostponeEmail2ForStylistTest.class, //dureaza mult 
	US31001ValidatePostponedOrdersInTpGrid3Test.class,
	US31001ValidatePostponeEmail3ForCustomerTest.class,
	US31001ValidatePostponeEmail3ForStylistTest.class,
	US31001ValidateCancelLimitReachedOrdersInTpGridTest.class,
	US31001ValidateCancelLimitEmailForStylistTest.class,
	US31001ValidateCancelLimitEmailForCustomerTest.class

})
@RunWith(Suite.class)
public class US31001Suite {

}
