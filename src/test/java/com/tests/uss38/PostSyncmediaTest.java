package com.tests.uss38;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.connectors.http.FunctionalTest;
import com.sun.jna.platform.mac.Carbon;
import com.tools.data.socialMediaApiCommnets.Data;
import com.tools.data.socialMediaApiCommnets.PostsCommentsModel;

public class PostSyncmediaTest extends FunctionalTest {

	Map<String, String> comment = new HashMap<>();

	public String token = "eyJpdiI6IkVWcHdOaCtzbmtmVGhpb3J4TGRoOVE9PSIsInZhbHVlIjoiMTBFM2dkMVkxdzVFSE1MQ2htZFR6NDJHZjF1bDJvcjExRmxvdXZoa3k1MFRMejEzTnhSeWtqS3B6XC81R3o0T1psUDRENWhseFU4REFMamZjXC9rWG5Nbk1vRFc1ZnUxeHVVZzM3aWpLMndsU2RyMW5BVUZXRzIzZ1crMGIremtBTHE1QTFPdUFcL21YbkgwY0pPV2IwRzhJQVRGUWwyaWdoVVhqc2hyYWJYZFk4OUp2alN3c25JelliN3RpMnRVWkdJQVZneGVsc0lhWXNsUGE4cytucFRkN1lnTlNVVWxUZnIwMnZZa2c1SDNQbnBoQlFkNFdaK2g5RUtjZng0N0duZyIsIm1hYyI6ImZkODAzNmZmYWJlMjcwMmNhODBmZWIyNTVhMjQwY2YyZTRiNjcyZDgyYTMxMTBiZTUxNzc3NTdjYTdlYzU5MGMifQ==";
	public String circleId = "1831408183552808";

	@Test
	public void postSyncmedia() {
		comment.put("token", token);
		comment.put("post_ids", "1831408183552808_1831446426882317");
		comment.put("uri", "http://staging-labs.tools.pippajean.com/social-media-klout-score/hooks/api-social-media/event");


		given().contentType("application/json").body(comment).when().post("syncmedia").then().statusCode(200);
	}
}
