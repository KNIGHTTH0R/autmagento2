package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7007.US7007EmailActivationTest;
import com.tests.us7.us7007.US7007EmailCodeTest;
import com.tests.us7.us7007.US7007WidgetUserRegistrationTest;

@SuiteClasses({

	US7007WidgetUserRegistrationTest.class,
	US7007EmailActivationTest.class,
	US7007EmailCodeTest.class,
})
@RunWith(Suite.class)
public class US7007Suite {

}
