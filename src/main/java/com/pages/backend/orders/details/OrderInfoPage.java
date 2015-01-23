package com.pages.backend.orders.details;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;
import com.tools.data.backend.OrderInfoModel;


public class OrderInfoPage extends AbstractPage {

	
	
	@FindBy(css = "table.order-tables > div > :nth-child(2)")
	private WebElement infoContainer;
	
	

	public OrderInfoModel grabOrderInfo(){
		
		element(infoContainer).waitUntilVisible();
		
		System.out.println(infoContainer.getText());
		
		
		
		
		OrderInfoModel orderInfo =  new OrderInfoModel();
//		private String orderDate;
//		private String orderStatus;
//		private String aquiredBy;
//		private String orderIP;  table.form-list"
		
		return orderInfo;
	}
	

}
