package com.steps.external;

import java.util.List;

import com.pages.external.TempMail;
import com.pages.external.YopmailPage;
import com.tools.data.CalcDetailsModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.BasicProductModel;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.DateUtils;

import net.thucydides.core.annotations.Step;

public class EmailClientSteps extends AbstractSteps {

//	 MailinatorPage mailPage;
	 YopmailPage mailPage;
//	 FakeMailGenerator mailPage;
//	 MailDrop mailPage;
//	 Dispostable mailPage;
//   Mailnesia mailPage;
//	 TempMail mailPage;
	private static final long serialVersionUID = 1L;

	@Step
	public String confirmAccount(String email, String title) {

		mailPage.openEmail(email, title);
		String confirmationLink=mailPage.getConfirmationLink();
		String link=confirmationLink.substring(confirmationLink.indexOf("https://aut."), confirmationLink.indexOf("distributed="));
		navigate(link+"distributed=");
	//	navigate(mailPage.getConfirmationLink());

		return DateUtils.getCurrentDate("MM/dd/YYYY");

	}
	
	@Step
	public void openEmail(String email, String title){
		mailPage.openEmail(email, title);
		
	}
	
	
	//need implemantation on yopmail actual: temp mail
/*	@Step
	public void checkReceivedOriginalDocuments(String originalDoc){
		mailPage.checkReceivedOriginalDocuments(originalDoc);

	}*/

	@Step
	public String registerFromLink(String email, String title) {

		mailPage.openEmail(email, title);
		navigate(mailPage.getRegisterLink());

		return DateUtils.getCurrentDate("MM/dd/YYYY");

	}

	@Step
	public String grabConfirmationLinkFromEmail(String email, String title) {

		mailPage.openEmail(email, title);
		return mailPage.getConfirmationLink();
	}

	@Step
	public String grabEmailCoupon(String email, String title) {

		mailPage.openEmail(email, title);

		return mailPage.grabCouponCode();
	}

	@Step
	public void validateThatEmailIsReceived(String email, String title) {

		mailPage.openEmail(email, title);

	}
	
	//need implemantation on yopmail actual: temp mai
/*	@Step
	public void validateThatOrderEmailIsReceived(String email, String title,String order) {

		mailPage.openEmailAndCheck(email, title, order);

	}*/

	@Step
	public void confirmPartyInvitation(String email, String title) {
		mailPage.openEmail(email, title);
		//emilian
		mailPage.clickPartyConfirmationLink();

		
		
	}



	public List<OrderItemModel> grabbedProductsList() {
		return mailPage.grabbedProductsList();

	}

	public OrderTotalsModel grabbedOrderTotals() {
		return mailPage.grabbedOrderTotals();

	}

}
