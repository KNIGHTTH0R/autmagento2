package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss16.us16001.US16001StyleCoachBorrowCartTest;
import com.tests.uss16.us16001.US16001UserProfileOrderIdTest;
import com.tests.uss16.us16001.US16001ValidateOrderBackOfficeTest;
import com.tests.uss16.us16001.US16001ValidateOrderEmailTest;

@SuiteClasses({
	US16001StyleCoachBorrowCartTest.class,
	US16001UserProfileOrderIdTest.class,	
	US16001ValidateOrderEmailTest.class,	
	US16001ValidateOrderBackOfficeTest.class,	
})
@RunWith(Suite.class)
public class US16001Suite {

}
