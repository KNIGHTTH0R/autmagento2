package com.tests.uss38;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.FunctionalTest;
import com.steps.external.SocialMedia.SocialMediaSteps;
import com.tests.BaseTest;
import com.tools.constants.SocialMediaConstansts;
import com.tools.data.socialMediaApi.MeModel;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class GetMeInfoTest extends BaseTest {
	MeModel meInfo = new MeModel();

	@Steps
	public SocialMediaSteps socialMediaSteps;

	@Before
	public void setUp() throws Exception {

		FunctionalTest.setup();
	}

	@Test
	public void getMeInfoTest() throws Exception {

		meInfo = given().when().get("me?token=" + SocialMediaConstansts.Token).then().statusCode(200).extract()
				.as(MeModel.class);

		socialMediaSteps.validateName(meInfo.getName(), SocialMediaConstansts.Name);
		socialMediaSteps.validateFriendName(meInfo.getFriends().getData()[0].getName(),
				SocialMediaConstansts.FriendName);

	}

}
