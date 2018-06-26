package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7001vdv.US7001CheckCustomerActivationVDV;
import com.tests.us7.us7001vdv.US7001CheckReceivedEmailsTestVDV;
import com.tests.us7.us7001vdv.US7001ConfirmCustomerTestVDV;
import com.tests.us7.us7001vdv.US7001RegularCustRegOnMasterTestVDV;
import com.tests.us7.us7001vdv.US7001ValidateBoutiqueTestVDV;


@SuiteClasses({
	US7001RegularCustRegOnMasterTestVDV.class,
	US7001ConfirmCustomerTestVDV.class,
	US7001CheckCustomerActivationVDV.class,
	US7001ValidateBoutiqueTestVDV.class,
	US7001CheckReceivedEmailsTestVDV.class,
})
@RunWith(Suite.class)
public class US7001CustomerSuite {

}
