package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3001.US3001ValidateOrderEmailTest;
import com.tests.us3.us3003.US3003ValidateOrderEmailTest;
import com.tests.us3.us3004.US3004ValidateOrderEmailTest;
import com.tests.us3.us3007.US3007ValidateOrderEmailTest;
import com.tests.us3.us3009.US3009ValidateOrderEmailTest;
import com.tests.us4.us4001.US4001ValidateOrderEmailTest;
import com.tests.us4.us4002.US4002ValidateOrderEmailTest;
import com.tests.us8.us8001.US8001ValidateOrderEmailTest;
import com.tests.us8.us8002.US8002ValidateOrderEmailTest;
import com.tests.us8.us8003.US8003ValidateOrderEmailTest;
import com.tests.us8.us8004.US8004ValidateOrderEmailTest;
import com.tests.us8.us8005.US8005ValidateOrderEmailTest;
import com.tests.us8.us8006.US8006ValidateOrderEmailTest;
import com.tests.us9.us9001.US9001ValidateOrderEmailTest;
import com.tests.us9.us9002.US9002ValidateOrderEmailTest;
import com.tests.uss11.us11001.US11001ValidateOrderEmailTest;
import com.tests.uss11.us11002.US11002ValidateOrderEmailTest;
import com.tests.uss11.us11005.US11005ValidateOrderEmailTest;
import com.tests.uss16.us16001.US16001ValidateOrderEmailTest;

@SuiteClasses({
	US3001ValidateOrderEmailTest.class,
	US3003ValidateOrderEmailTest.class,
	US3004ValidateOrderEmailTest.class,
	US3007ValidateOrderEmailTest.class,
	US3009ValidateOrderEmailTest.class,
	
	US4001ValidateOrderEmailTest.class,
	US4002ValidateOrderEmailTest.class,
	
	US8001ValidateOrderEmailTest.class,	
	US8002ValidateOrderEmailTest.class,
	US8003ValidateOrderEmailTest.class,	
	US8004ValidateOrderEmailTest.class,	
	US8005ValidateOrderEmailTest.class,
	US8006ValidateOrderEmailTest.class,
	
	US11001ValidateOrderEmailTest.class,
	US11002ValidateOrderEmailTest.class,
	US11005ValidateOrderEmailTest.class,
	
	US9001ValidateOrderEmailTest.class,
	US9002ValidateOrderEmailTest.class,
	
	US16001ValidateOrderEmailTest.class,
	

})
@RunWith(Suite.class)
public class VerifyOrderEmailsSuite {

}
