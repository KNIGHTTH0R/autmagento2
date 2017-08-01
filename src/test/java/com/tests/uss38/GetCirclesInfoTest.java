package com.tests.uss38;

import static com.jayway.restassured.RestAssured.given;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.FunctionalTest;
import com.steps.external.SocialMedia.SocialMediaSteps;
import com.tests.BaseTest;
import com.tools.constants.SocialMediaConstansts;
import com.tools.data.socialMediaApi.Circles;
import com.tools.persistance.MongoWriter;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class GetCirclesInfoTest extends BaseTest {

	private String facebookGroupId;
	@Steps
	public SocialMediaSteps socialMediaSteps;

	@Before
	public void setUp() throws Exception {

		FunctionalTest.setup();
	}

	@Test
	public void getCirclesInfoTest() {
		Circles[] circles = given().when().get("circles?token=" + SocialMediaConstansts.Token).then().statusCode(200)
				.extract().as(Circles[].class);
	
		socialMediaSteps.validateFacebookGroupIdIsNotEmpty(circles[0].getFacebook_group_id());
		facebookGroupId= circles[0].getFacebook_group_id();
		socialMediaSteps.validateFacebook_nameIsNotEmpty(circles[0].getFacebook_name());
		socialMediaSteps.validateFacebook_privacyIsNotEmpty(circles[0].getFacebook_privacy());

		System.out.println(facebookGroupId);
	}

	@After
	public void saveData() {
		MongoWriter.saveStringValue(facebookGroupId, getClass().getSimpleName() + "GR_ID");

	}

}
