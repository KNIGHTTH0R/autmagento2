package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

public class FooterSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void clickRegistrierungLink() {
		footerPage().clickRegistrierungLink();
	}

	@Step
	public void clickStarterSetLink() {
		footerPage().clickStarterSetLink();
	}

	@Step
	public void clickTrainingLink() {
		footerPage().clickTrainingLink();
	}

	@Step
	public void clickErfolgsgeschichtenLink() {
		footerPage().clickErfolgsgeschichtenLink();
	}

	@Step
	public void clickTraumkarriereStyleCoachLink() {
		footerPage().clickTraumkarriereStyleCoachLink();
	}

	@StepGroup
	public void subscribeToNewsletter(CustomerFormModel model) {

		navigate(MongoReader.getBaseURL());
		footerPage().inputNewsletterEmail(model.getEmailName());
		footerPage().submitNesletter();
	}

	// @StepGroup
	// public void navigateToRegisterFormFromStarterSetLink() {
	// footerPage().clickStarterSetLink();
	// stylistCampaignPage().clickJetztStartenButton();
	// starterSetPage().clickOnJetztStyleCoachWerdenButton();
	// }

	@StepGroup
	public void navigateToRegisterFormFromStarterSetLink() {
		navigate(MongoReader.getBaseURL());
		footerPage().clickStarterSetLink();
		stylistCampaignPage().clickStartersetOrderButton();
	}

	// @StepGroup
	// public void navigateToRegisterFormFromTrainingLink() {
	// footerPage().clickTrainingLink();
	// stylistCampaignPage().clickJetztStartenButton();
	// starterSetPage().clickOnJetztStyleCoachWerdenButton();
	// }

	@StepGroup
	public void navigateToRegisterFormFromTrainingLink() {
		footerPage().clickTrainingLink();
		stylistCampaignPage().clickStartersetOrderButton();
	}

	// @StepGroup
	// public void navigateToRegisterFormFromIncentivereisenLink() {
	// footerPage().clickIncentivereisenLink();
	// stylistCampaignPage().clickStarteJetztButton();
	// starterSetPage().clickOnJetztStyleCoachWerdenButton();
	// }

	// @StepGroup
	// public void navigateToRegisterFormFromErfolgsgeschichtenLink() {
	// footerPage().clickErfolgsgeschichtenLink();
	// stylistCampaignPage().clickStarteJetztButton();
	// starterSetPage().clickOnJetztStyleCoachWerdenButton();
	// }

	@StepGroup
	public void navigateToRegisterFormFromErfolgsgeschichtenLink() {
		footerPage().clickErfolgsgeschichtenLink();
		stylistCampaignPage().clickStartersetOrderButton();
	}

	// @StepGroup
	// public void navigateToRegisterFormFromTraumkarriereStyleCoachLink() {
	// footerPage().clickTraumkarriereStyleCoachLink();
	// stylistCampaignPage().clickStarteJetztButton();
	// starterSetPage().clickOnJetztStyleCoachWerdenButton();
	// }
	//
	@StepGroup
	public void navigateToRegisterFormFromTraumkarriereStyleCoachLink() {
		getDriver().get(MongoReader.getBaseURL());
		footerPage().clickTraumkarriereStyleCoachLink();
		stylistCampaignPage().clickStartersetOrderButton();
	}

	@StepGroup
	public void navigateToRegisterFormFromRegistrierungLink() {
		getDriver().get(MongoReader.getBaseURL());
		footerPage().clickRegistrierungLink();

	}

	@StepGroup
	public void verifyThatFooterWebsiteIsCorrect(String website) {
		footerPage().verifyThatFooterWebsiteIsCorrect(website);

	}

	@StepGroup
	public void selectWebsiteFromFooter(String language) {
		footerPage().selectWebsiteFromFooter(language);

	}

}
