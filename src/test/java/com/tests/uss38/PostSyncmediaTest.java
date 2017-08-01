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
public class PostSyncmediaTest extends FunctionalTest {

	Map<String, String> comment = new HashMap<>();

	@Test
	public void postSyncmedia() {
		comment.put("token", SocialMediaConstansts.Token);
		comment.put("post_ids", SocialMediaConstansts.Post_id);
		comment.put("uri",
				"http://staging-labs.tools.pippajean.com/social-media-klout-score/hooks/api-social-media/event");

		given().contentType("application/json").body(comment).when().post("syncmedia").then().statusCode(200);
	}
}
