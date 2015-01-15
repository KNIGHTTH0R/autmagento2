package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us0.CheckCustomerActivationTest;
import com.tests.us0.CreateCustomerTest;
import com.tests.us0.GrabStylistPropertiesTest;
import com.tests.us0.ValidateStylistTest;

@SuiteClasses({
		GrabStylistPropertiesTest.class,
		CreateCustomerTest.class,
		CheckCustomerActivationTest.class,
		ValidateStylistTest.class,
})
@RunWith(Suite.class)
public class PippaSuite {

}
