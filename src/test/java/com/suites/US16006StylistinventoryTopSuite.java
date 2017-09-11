package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss16.us16003DataPreparation.US16003SetUpNewBorrowFunctionalityTest;
import com.tests.uss16.us16003DataPreparation.US16003StyleCoachRegistrationTest;
import com.tests.uss16.us16004a.US16004aDefaultTopAndCustomPackageConfigTest;
import com.tests.uss16.us16004a.US16004aPlaceBarrowOrderDefaultTopAndCustomPackageTest;
import com.tests.uss16.us16004a.US16004aPlaceBarrowOrderDefaultTopAndTopPackageTest;
import com.tests.uss39.StylistInventoryBorrowTopPackageTest;
import com.tests.uss39.StylistInventoryReturnTopPackageTest;

@SuiteClasses({
	//data preparation
	
	US16003StyleCoachRegistrationTest.class,
	US16003SetUpNewBorrowFunctionalityTest.class,
	
	//stylist in TOP; xxxx package
	US16004aPlaceBarrowOrderDefaultTopAndTopPackageTest.class,
	
	//stylist inventory borrow process 
	StylistInventoryBorrowTopPackageTest.class,
	
	//stylist inventory return  process 
	StylistInventoryReturnTopPackageTest.class,
	
	US16003StyleCoachRegistrationTest.class,
	US16004aDefaultTopAndCustomPackageConfigTest.class,
	US16004aPlaceBarrowOrderDefaultTopAndCustomPackageTest.class,

	
})

@RunWith(Suite.class)
public class US16006StylistinventoryTopSuite {

}

