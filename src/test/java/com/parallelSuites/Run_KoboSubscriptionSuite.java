package com.parallelSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.suites.US11001Suite;
import com.suites.US11002Suite;
import com.suites.US11004Suite;
import com.suites.US11005Suite;
import com.suites.US11007Suite;
import com.suites.US11008Suite;
import com.suites.US70010Suite;
import com.suites.US70011Suite;
import com.suites.US70012Suite;
import com.suites.US7001Suite;
import com.suites.US7001bSuite;
import com.suites.US7002Suite;
import com.suites.US7009Suite;
import com.suites.US7CreateRegularCustomersSuite;
import com.suites.US8001Suite;
import com.suites.US8002Suite;
import com.suites.US8003Suite;
import com.suites.US8004Suite;
import com.suites.US8005Suite;
import com.suites.US8006Suite;
import com.suites.US8007Suite;
import com.suites.US8008Suite;
import com.tests.CreateProductsTest;
import com.tests.us3.us30012.US30012SfmSpecialPriceProductTest;
import com.tests.us6.us6006.US6006ScBuyProductwithSpecialPriceTest;
import com.tests.uss11.us11011.US110011PlaceCustomerOrderProductWithSpecialPriceTest;
import com.tests.uss12.uss12001.US12001ApplyCreditMemoOnKoboSubscriptionOrderTest;
import com.tests.uss12.uss12001.US12001ApplyCreditMemoOnKoboUpgradeOrderTest;
import com.tests.uss12.uss12001.US12001CancelCreditMemoForKoboSubscriptionTest;
import com.tests.uss12.uss12001.US12001CancelCreditMemoForKoboUpgradeTest;
import com.tests.uss12.uss12001.US12001CancelKoboInitialSubscriptionTest;
import com.tests.uss12.uss12001.US12001ChechUsesPerCouponAfterSubscriptionCancelCmTest;
import com.tests.uss12.uss12001.US12001ChechUsesPerCouponAfterSubscriptionCmTest;
import com.tests.uss12.uss12001.US12001ChechUsesPerCouponAfterSubscriptionUpgardeCMTest;
import com.tests.uss12.uss12001.US12001ChechUsesPerCouponAfterSubscriptionUpgradeCancelCMTest;
import com.tests.uss12.uss12001.US12001ChechUsesPerCouponAfterSubscriptionUpgradeTest;
import com.tests.uss12.uss12001.US12001ConfirmCustomerTest;
import com.tests.uss12.uss12001.US12001CustomerBuyWithContactBoosterTest;
import com.tests.uss12.uss12001.US12001InitialKoboSubscriptionTest;
import com.tests.uss12.uss12001.US12001KoboSubscriptionTest;
import com.tests.uss12.uss12001.US12001KoboSubscriptionUpgradeTest;
import com.tests.uss12.uss12001.US12001MarkAsPaidInitialKoboOrderTest;
import com.tests.uss12.uss12001.US12001MarkAsPaidKoboOrderTest;
import com.tests.uss12.uss12001.US12001RegularCustomerRegistrationTest;
import com.tests.uss12.uss12001.US12001StyleCoachRegistrationTest;
import com.tests.uss12.uss12001.US12001VerifyStylistKoboStatusAfterCancelCmOnSubscriptionTest;
import com.tests.uss12.uss12001.US12001VerifyStylistKoboStatusAfterCmOnSubscriptionTest;
import com.tests.uss12.uss12001.US12001VerifyStylistKoboStatusAfterSubscriptionTest;
import com.tests.uss12.uss12001.US12001VoucherRunOutNotificationEmailTest;


@SuiteClasses({
	US12001StyleCoachRegistrationTest.class,
	US12001RegularCustomerRegistrationTest.class,
	US12001InitialKoboSubscriptionTest.class,
	US12001CancelKoboInitialSubscriptionTest.class,
	US12001KoboSubscriptionTest.class,
	US12001MarkAsPaidKoboOrderTest.class,
	US12001MarkAsPaidInitialKoboOrderTest.class,
//	US12001KoboSubscriptionOrderEmailTest.class, //not expected mail received
	US12001VerifyStylistKoboStatusAfterSubscriptionTest.class,
	US12001KoboSubscriptionUpgradeTest.class,
//	US12001KoboSubscriptionUpgradeOrderEmailTest.class,//not expected mail received 
	US12001ChechUsesPerCouponAfterSubscriptionUpgradeTest.class,
	US12001ApplyCreditMemoOnKoboSubscriptionOrderTest.class,
	US12001VerifyStylistKoboStatusAfterCmOnSubscriptionTest.class,
	US12001ChechUsesPerCouponAfterSubscriptionCmTest.class,
	US12001CancelCreditMemoForKoboSubscriptionTest.class,
	US12001VerifyStylistKoboStatusAfterCancelCmOnSubscriptionTest.class,
	US12001ChechUsesPerCouponAfterSubscriptionCancelCmTest.class,
	US12001ApplyCreditMemoOnKoboUpgradeOrderTest.class,
	US12001ChechUsesPerCouponAfterSubscriptionUpgardeCMTest.class,
	US12001CancelCreditMemoForKoboUpgradeTest.class,
	US12001ChechUsesPerCouponAfterSubscriptionUpgradeCancelCMTest.class,
	//moved up - US12001RegularCustomerRegistrationTest.class,
	US12001ConfirmCustomerTest.class,
	US12001CustomerBuyWithContactBoosterTest.class,
	US12001VoucherRunOutNotificationEmailTest.class,
})
@RunWith(Suite.class)
public class Run_KoboSubscriptionSuite {

}
