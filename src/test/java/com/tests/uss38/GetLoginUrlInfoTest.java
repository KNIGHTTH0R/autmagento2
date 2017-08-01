package com.tests.uss38;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.FunctionalTest;
import com.steps.external.SocialMedia.SocialMediaSteps;
import com.tests.BaseTest;
import com.tools.data.socialMediaApi.LoginUrlModel;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
@RunWith(SerenityRunner.class)
public class GetLoginUrlInfoTest extends BaseTest {
	private String testUri="testUri";
	@Steps
	public SocialMediaSteps socialMediaSteps;

	@Before
	public void setUp() throws Exception {

		FunctionalTest.setup();
	}
	@Test
	public void getLoginUrlInfoTest() {
		LoginUrlModel urlLogin= given().when().get("login-url?uri="+testUri).then().statusCode(200).extract().as(LoginUrlModel.class);
		
		socialMediaSteps.validateURI(urlLogin.getData().getUrl(),testUri);
	}
}