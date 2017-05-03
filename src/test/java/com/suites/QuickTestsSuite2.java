package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7008.US7008CheckReceivedEmailsTest;
import com.tests.us7.us7008.US7008KoboRegOnMasterNotPrefCountryTest1;
import com.tests.us7.us7009.US7009KoboRegOnNotVoucherOwnerContextTest1;
import com.tests.us7.uss70010.US70010CheckReceivedEmailsTest;
import com.tests.us7.uss70010.US70010KoboRegOnVoucherOwnerContextTest1;
import com.tests.us7.uss70011.US70011KoboCampaignRegistrationOnMasterTest1;
import com.tests.us7.uss70012.US70012KoboCampaignRegistrationUnderContextTest1;

@SuiteClasses({
//	CreateProductsTest.class,
//	RegularCartSuite.class,
	
	
	US7008KoboRegOnMasterNotPrefCountryTest1.class,
//	US7009KoboRegOnNotVoucherOwnerContextTest.class,
	US7009KoboRegOnNotVoucherOwnerContextTest1.class,
	US70010KoboRegOnVoucherOwnerContextTest1.class,
//	US70010KoboRegOnVoucherOwnerContextTest.class,
	US70011KoboCampaignRegistrationOnMasterTest1.class,
	US70012KoboCampaignRegistrationUnderContextTest1.class,
	
	US7008Suite.class,
	US7009Suite.class,
	US70010Suite.class,
	US70011Suite.class,
	US70012Suite.class,
	
	
	US7008CheckReceivedEmailsTest.class,
	US70010CheckReceivedEmailsTest.class,
	
})
@RunWith(Suite.class)
public class QuickTestsSuite2 {

}
