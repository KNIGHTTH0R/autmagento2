package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.US003CartSegmentationWithVatTest;
import com.tests.us3.US003ValidateOrderBackOfficeTest;
import com.tests.us3.US003ValidateOrderEmailTest;

@SuiteClasses({
	US003CartSegmentationWithVatTest.class,
	US003ValidateOrderEmailTest.class,
	US003ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US003Suite {

}
