package com.tests.uss38;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.FunctionalTest;
import com.tools.constants.SocialMediaConstansts;

import net.serenitybdd.junit.runners.SerenityRunner;

@RunWith(SerenityRunner.class)
public class PostKloutTest extends FunctionalTest {
	Map<String, String> comment = new HashMap<>();

	@Test
	public void postKloutTest() {
		comment.put("token", SocialMediaConstansts.Token);
		comment.put("uri",
				"http://staging-labs.tools.pippajean.com/social-media-klout-score/hooks/api-social-media/event");
		comment.put("since", "2017-07-26");

		given().contentType("application/json").body(comment).when().post("klout").then().statusCode(200);
	}
}
