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
public class PostSyncTest extends FunctionalTest {

	Map<String, String> comment = new HashMap<>();

	public String circleId = "1831408183552808";

	@Test
	public void postSync() {
		comment.put("token", SocialMediaConstansts.Token);
		comment.put("social_circle_ids", circleId);

		given().contentType("application/json").body(comment).when().post("sync").then().statusCode(200);
	}
}
