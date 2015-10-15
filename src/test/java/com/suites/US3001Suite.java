package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3001.US3001SfmValidVatNoSmbBillingShippingNotDeTest;
import com.tests.us3.us3001.US3001CheckOrderOnStylecoachProfileTest;
import com.tests.us3.us3001.US3001ValidateOrderBackOfficeTest;
import com.tests.us3.us3001.US3001ValidateOrderEmailTest;


@SuiteClasses({
	US3001SfmValidVatNoSmbBillingShippingNotDeTest.class,
	US3001CheckOrderOnStylecoachProfileTest.class,
	US3001ValidateOrderEmailTest.class,
	US3001ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US3001Suite {

}
