package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us5.US0005FacebookCustomerDeleteTest;
import com.tests.us5.US0005FacebookLoginTest;

@SuiteClasses({
	US0005FacebookLoginTest.class,
	US0005FacebookCustomerDeleteTest.class,
})
@RunWith(Suite.class)
public class US5000Suite {

}
