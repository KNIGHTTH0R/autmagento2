package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3006.US3006ActivateDiscountOnCartRuleTest;
import com.tests.us3.us3006.US3006CheckOrderOnStylecoachProfileTest;
import com.tests.us3.us3006.US3006DeactivateDiscountOnCartRuleTest;
import com.tests.us3.us3006.US3006SfmValidVatSmbBillingShippingDeTest;
import com.tests.us3.us3006.US3006ValidateOrderBackOfficeTest;
import com.tests.us3.us3006.US3006ValidateOrderEmailTest;

@SuiteClasses({
	US3006ActivateDiscountOnCartRuleTest.class,
	US3006SfmValidVatSmbBillingShippingDeTest.class,
	US3006CheckOrderOnStylecoachProfileTest.class,
	US3006ValidateOrderEmailTest.class,
	US3006ValidateOrderBackOfficeTest.class,
	US3006DeactivateDiscountOnCartRuleTest.class,
})
@RunWith(Suite.class)
public class US3006Suite {

}
