package com.tests.us7.us7003;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.requirements.Application;

@WithTag(name = "US7003", type = "fontend")
@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class US7003RegularUserRegistrationThankYouPageTest extends BaseTest{
	
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps 
	public CustomVerification customVerifications;
	
	
}
