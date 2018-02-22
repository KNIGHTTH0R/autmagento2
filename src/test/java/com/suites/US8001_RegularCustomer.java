package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.VdVCreateProductsTestRegularCart;
import com.tests.us6.us6001.US6001CancelStarterSetOrderTestVDV;
import com.tests.us6.us6001.US6001CheckAssociatedContactCreationTestVDV;
import com.tests.us6.us6001.US6001CheckStylistActivationStarterkitCanceledTestVDV;
import com.tests.us6.us6001.US6001CheckStylistActivationTestVDV;
import com.tests.us6.us6001.US6001ScRegistrationNewCustomerTestVDV;
import com.tests.us6.us6001.US6001ValidateStarterSetOrderInBackendTestVDV;
import com.tests.us8.us8001vdv.US8001CustomerBuyProductsWithNoBonnusTestVDV;
import com.tests.us8.us8001vdv.US8001ValidateOrderEmailTestVDV;

@SuiteClasses({
	VdVCreateProductsTestRegularCart.class,
	US8001CustomerBuyProductsWithNoBonnusTestVDV.class,
	US8001ValidateOrderEmailTestVDV.class,
	 
})
@RunWith(Suite.class)
public class US8001_RegularCustomer {

}
