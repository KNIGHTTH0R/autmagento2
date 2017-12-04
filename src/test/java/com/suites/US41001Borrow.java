package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss41.us41001_Borrow.US41001BorrowCheckOriginalDocumentsInNavTabTest;
import com.tests.uss41.us41001_Borrow.US41001BorrowPayAndImportOrderInNavisionTest;
import com.tests.uss41.us41001_Borrow.US41001BorrowPostOrderInNavisionTest;
import com.tests.uss41.us41001_Borrow.US41001StyleCoachBorrowsProductsTest;
import com.tests.uss41.us41001_Borrow.us41001BorrowOrderDetails.US41001BorrowCheckShipmentEmailTest;
import com.tests.uss41.us41001_Borrow.us41001BorrowOrderDetails.US41001RegularCreateInvoiceAndShipmentTest;

@SuiteClasses({
	US41001StyleCoachBorrowsProductsTest.class,
	US41001BorrowPayAndImportOrderInNavisionTest.class,
	US41001BorrowPostOrderInNavisionTest.class,
	US41001BorrowCheckOriginalDocumentsInNavTabTest.class,
	US41001RegularCreateInvoiceAndShipmentTest.class,
	US41001BorrowCheckShipmentEmailTest.class,
//	US41001BorrowCheckReceivedEmailsTest.class,
	
})
@RunWith(Suite.class)
public class US41001Borrow {

}
