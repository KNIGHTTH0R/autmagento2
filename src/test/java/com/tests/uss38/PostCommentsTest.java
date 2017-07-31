package com.tests.uss38;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.Test;

import com.connectors.http.FunctionalTest;
import com.tools.CustomVerification;
import com.tools.data.socialMediaApiCommnets.CommentResponse;
import com.tools.utils.FieldGenerators;
import com.tools.utils.FieldGenerators.Mode;

public class PostCommentsTest extends FunctionalTest {
	Map<String, String> comment = new HashMap<>();

	public String token = "eyJpdiI6IkVWcHdOaCtzbmtmVGhpb3J4TGRoOVE9PSIsInZhbHVlIjoiMTBFM2dkMVkxdzVFSE1MQ2htZFR6NDJHZjF1bDJvcjExRmxvdXZoa3k1MFRMejEzTnhSeWtqS3B6XC81R3o0T1psUDRENWhseFU4REFMamZjXC9rWG5Nbk1vRFc1ZnUxeHVVZzM3aWpLMndsU2RyMW5BVUZXRzIzZ1crMGIremtBTHE1QTFPdUFcL21YbkgwY0pPV2IwRzhJQVRGUWwyaWdoVVhqc2hyYWJYZFk4OUp2alN3c25JelliN3RpMnRVWkdJQVZneGVsc0lhWXNsUGE4cytucFRkN1lnTlNVVWxUZnIwMnZZa2c1SDNQbnBoQlFkNFdaK2g5RUtjZng0N0duZyIsIm1hYyI6ImZkODAzNmZmYWJlMjcwMmNhODBmZWIyNTVhMjQwY2YyZTRiNjcyZDgyYTMxMTBiZTUxNzc3NTdjYTdlYzU5MGMifQ==";
	public String circleId = "1831408183552808";
	public String messageValue=FieldGenerators.generateRandomString(10, Mode.ALPHANUMERIC)+"test";
	
	@Test
		public void circleCommentsStatusPing(){
			comment.put("message", messageValue);
			
			CommentResponse postComm=given().contentType("application/json").body(comment).when().post("posts/1831408183552808_1831446426882317/comment?token="+token).then().statusCode(200).extract().as(CommentResponse.class);
			System.out.println(postComm);
			CustomVerification.verifyTrue("The repoonse id is null", postComm.getId()!=null);
		//	assertThat(postComm.getId()!=null);
			System.out.println(messageValue);
			
			
			
	}
}
