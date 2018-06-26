package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss34.us34001.US34001CreateCreditMemoOnOrderTestVDV;
import com.tests.uss34.us34001.US34001CreateInvoiceTestVDV;
import com.tests.uss34.us34001.US34001CreateShipmentTestVDV;


@SuiteClasses({
	US3001SFMSuite.class,
	US34001CreateInvoiceTestVDV.class,
	US34001CreateShipmentTestVDV.class,
	US34001CreateCreditMemoOnOrderTestVDV.class,
	//US3001ValidateClosedOrderEmailsTestVDV.class
})
@RunWith(Suite.class)
public class US34001ClosedOrder {

}
 