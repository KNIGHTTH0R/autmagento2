package com.tests.uss38;

import org.junit.Test;

import com.connectors.http.FunctionalTest;
import com.tools.CustomVerification;
import com.tools.data.socialMediaApi.MeModel;
import com.tools.data.socialMediaApiCommnets.Data;
import com.tools.data.socialMediaApiCommnets.PostsCommentsModel;

import static com.jayway.restassured.RestAssured.given;

public class GetPostsComments extends FunctionalTest {
	public String token = "eyJpdiI6IkVWcHdOaCtzbmtmVGhpb3J4TGRoOVE9PSIsInZhbHVlIjoiMTBFM2dkMVkxdzVFSE1MQ2htZFR6NDJHZjF1bDJvcjExRmxvdXZoa3k1MFRMejEzTnhSeWtqS3B6XC81R3o0T1psUDRENWhseFU4REFMamZjXC9rWG5Nbk1vRFc1ZnUxeHVVZzM3aWpLMndsU2RyMW5BVUZXRzIzZ1crMGIremtBTHE1QTFPdUFcL21YbkgwY0pPV2IwRzhJQVRGUWwyaWdoVVhqc2hyYWJYZFk4OUp2alN3c25JelliN3RpMnRVWkdJQVZneGVsc0lhWXNsUGE4cytucFRkN1lnTlNVVWxUZnIwMnZZa2c1SDNQbnBoQlFkNFdaK2g5RUtjZng0N0duZyIsIm1hYyI6ImZkODAzNmZmYWJlMjcwMmNhODBmZWIyNTVhMjQwY2YyZTRiNjcyZDgyYTMxMTBiZTUxNzc3NTdjYTdlYzU5MGMifQ==";
	public String circleId = "1831408183552808";
	public String messageId = "1837234809636812";
	public String messageValue = "QeqZ40hzVytest";

	@Test
	public void circleCommentsStatusPing() {
		/// OOOK
		PostsCommentsModel status = given().when()
				.get("posts/1831408183552808_1831446426882317/comments?token=" + token).then().statusCode(200).extract()
				.as(PostsCommentsModel.class);
		Data[] data = status.getData();
	
		for (Data d : data) {
			boolean found = true;
			if (d.getId() == "1837234809636812") {
				found = true;
				CustomVerification.verifyTrue("The comment does not displayed expected message",
						d.getMessage().contentEquals(messageValue));
			}
			CustomVerification.verifyTrue("The comment with id: " + messageId + "was not found", found);

		}

		// System.out.println(status.getStatus());

		// http://staging-labs.api-social-media.pippajean.com/facebook/circle/1831408183552808/live-status/1831408183552808_1831468853546741?token=eyJpdiI6IkVWcHdOaCtzbmtmVGhpb3J4TGRoOVE9PSIsInZhbHVlIjoiMTBFM2dkMVkxdzVFSE1MQ2htZFR6NDJHZjF1bDJvcjExRmxvdXZoa3k1MFRMejEzTnhSeWtqS3B6XC81R3o0T1psUDRENWhseFU4REFMamZjXC9rWG5Nbk1vRFc1ZnUxeHVVZzM3aWpLMndsU2RyMW5BVUZXRzIzZ1crMGIremtBTHE1QTFPdUFcL21YbkgwY0pPV2IwRzhJQVRGUWwyaWdoVVhqc2hyYWJYZFk4OUp2alN3c25JelliN3RpMnRVWkdJQVZneGVsc0lhWXNsUGE4cytucFRkN1lnTlNVVWxUZnIwMnZZa2c1SDNQbnBoQlFkNFdaK2g5RUtjZng0N0duZyIsIm1hYyI6ImZkODAzNmZmYWJlMjcwMmNhODBmZWIyNTVhMjQwY2YyZTRiNjcyZDgyYTMxMTBiZTUxNzc3NTdjYTdlYzU5MGMifQ==
	}
}
