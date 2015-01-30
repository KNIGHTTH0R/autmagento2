package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.US003CartSegmentationWithVatTest;

@SuiteClasses({
	US003CartSegmentationWithVatTest.class,
})
@RunWith(Suite.class)
public class US003Suite {

}
