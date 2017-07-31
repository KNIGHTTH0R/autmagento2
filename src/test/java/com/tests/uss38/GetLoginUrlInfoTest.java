package com.tests.uss38;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;

import com.connectors.http.FunctionalTest;
import com.tools.data.socialMediaApi.Data;
import com.tools.data.socialMediaApi.LiveStreamsModel;

public class GetLoginUrlInfoTest extends FunctionalTest {
	
	@Test
	public void basicPingTest() {
		given().when().get("login-url?uri=dsds").then().statusCode(200).extract();
	}
}