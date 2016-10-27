package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss13.us13007.US13007ConfirmCustomerTest;
import com.tests.uss13.us13007.US13007DykscSearchByNameTest;
import com.tests.uss13.us13007.US13007ValidateCustomerIsAssignedToStylist;
import com.tests.uss13.us13007.US13007ValidateStylistPropertiesTest;

@SuiteClasses({
	US13007DykscSearchByNameTest.class,
	US13007ConfirmCustomerTest.class,
	US13007ValidateCustomerIsAssignedToStylist.class,
	US13007ValidateStylistPropertiesTest.class,
})
@RunWith(Suite.class)
public class US13007Suite {

}
