package com.pages.backend.orders.details;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;


public class OrderAdressPage extends AbstractPage {

	@FindBy(css = "table.order-tables")
	private WebElement tableContainer;


	

}
