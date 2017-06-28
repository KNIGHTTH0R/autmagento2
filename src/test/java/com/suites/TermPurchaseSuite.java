package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsTest;
import com.tests.CreateProductsWithTP;
import com.tests.uss10.us10001b.US10001bCreatePartyWithStylistHostTest;
import com.tests.uss10.uss10007.US10007CreatePartyWithStylistHostTest;
import com.tests.uss10.uss10008.US10008CreatePartyWithExistingContactHostTest;
import com.tests.uss11.us11008.US10009CreatePartyWithStylistHostTest;


@SuiteClasses({
	CreateProductsTest.class,
	CreateProductsWithTP.class,
	
	US10001bCreatePartyWithStylistHostTest.class,
	US10009CreatePartyWithStylistHostTest.class,
	US10007CreatePartyWithStylistHostTest.class,
	US10009CreatePartyWithStylistHostTest.class,
	
	US32001Suite.class,
	US32002Suite.class,
	US32003Suite.class,
	
	US8007Suite.class,
	US8008Suite.class,
	US9004Suite.class,
	US9005Suite.class,
	
	US11007Suite.class,
	
	US11008Suite.class,
	//US110010Suite.class,
	
})

@RunWith(Suite.class)
public class TermPurchaseSuite {
	
}