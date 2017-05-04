package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7001.US7001RegularCustRegOnMasterTest;
import com.tests.us7.us7001b.US7001bRegularCustRegOnNotPrefCountryTest;
import com.tests.us7.us7002.US7002RegularCustRegistrationOnContextTest;
import com.tests.us7.us7004.US7004RegularUserRegistrationLandingPageTest;
import com.tests.us7.us7004b.US7004bRegCustRegLandingPageNotPrefCountryTest;
import com.tests.us7.us7008.US7008KoboRegOnMasterNotPrefCountryTest1;
import com.tests.us7.us7009.US7009KoboRegOnNotVoucherOwnerContextTest1;
import com.tests.us7.uss70010.US70010KoboRegOnVoucherOwnerContextTest1;
import com.tests.us7.uss70011.US70011KoboCampaignRegistrationOnMasterTest1;
import com.tests.us7.uss70012.US70012KoboCampaignRegistrationUnderContextTest1;


@SuiteClasses({
	US7001RegularCustRegOnMasterTest.class,
	US7001bRegularCustRegOnNotPrefCountryTest.class,
	US7002RegularCustRegistrationOnContextTest.class,
//	US7004RegularUserRegistrationLandingPageTest.class,
//	US7004bRegCustRegLandingPageNotPrefCountryTest.class,
//	US7005RegularKnownUserRegistrationLandingPageTest.class,
//	US7006bLandingPageRegSelectedScNotPrefCountryTest.class,
//	US7006LandingPageRegistrationSelectedScTest.class,
//	US7007WidgetUserRegistrationTest.class,
	
	
	US7008KoboRegOnMasterNotPrefCountryTest1.class,
//	US7009KoboRegOnNotVoucherOwnerContextTest.class,
	US7009KoboRegOnNotVoucherOwnerContextTest1.class,
	US70010KoboRegOnVoucherOwnerContextTest1.class,
//	US70010KoboRegOnVoucherOwnerContextTest.class,
	US70011KoboCampaignRegistrationOnMasterTest1.class,
	US70012KoboCampaignRegistrationUnderContextTest1.class,
})
@RunWith(Suite.class)
public class US7CreateRegularCustomersSuite {

}
