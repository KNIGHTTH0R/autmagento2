package com.pages.backend.orders.details;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.AbstractPage;
import com.tools.data.OrderItemModel;

public class OrderAdressPage extends AbstractPage {

	@FindBy(css = "table.order-tables")
	private WebElement tableContainer;


	

}
