package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsTest;

@SuiteClasses({
	CreateProductsTest.class,
	RegularCartSuite.class,
})
@RunWith(Suite.class)
public class QuickTestsSuite2 {

}
