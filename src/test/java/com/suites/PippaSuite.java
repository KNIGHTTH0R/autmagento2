package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CheckCustomerActivationTest;
import com.tests.CreateCustomerTest;
import com.tests.GrabStylistPropertiesTest;
import com.tests.ValidateStylistTest;

@SuiteClasses({
		GrabStylistPropertiesTest.class,
		CreateCustomerTest.class,
		CheckCustomerActivationTest.class,
		ValidateStylistTest.class,
})
@RunWith(Suite.class)
public class PippaSuite {

}
