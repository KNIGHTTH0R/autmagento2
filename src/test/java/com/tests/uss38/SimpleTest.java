package com.tests.uss38;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.FunctionalTest;
import com.tools.data.socialMediaApi.Circles;
import com.tools.data.socialMediaApi.CommentsModel;
import com.tools.data.socialMediaApi.LiveStatusModel;
import com.tools.data.socialMediaApi.LiveStreamsModel;
import com.tools.data.socialMediaApi.LoginUrlModel;
import com.tools.data.socialMediaApi.MeModel;
import com.tools.data.socialMediaApiCommnets.Data;
import com.tools.data.socialMediaApiCommnets.PostsCommentsModel;

import net.serenitybdd.junit.runners.SerenityRunner;

import static com.jayway.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class SimpleTest extends FunctionalTest {
	public String token = "eyJpdiI6IkVWcHdOaCtzbmtmVGhpb3J4TGRoOVE9PSIsInZhbHVlIjoiMTBFM2dkMVkxdzVFSE1MQ2htZFR6NDJHZjF1bDJvcjExRmxvdXZoa3k1MFRMejEzTnhSeWtqS3B6XC81R3o0T1psUDRENWhseFU4REFMamZjXC9rWG5Nbk1vRFc1ZnUxeHVVZzM3aWpLMndsU2RyMW5BVUZXRzIzZ1crMGIremtBTHE1QTFPdUFcL21YbkgwY0pPV2IwRzhJQVRGUWwyaWdoVVhqc2hyYWJYZFk4OUp2alN3c25JelliN3RpMnRVWkdJQVZneGVsc0lhWXNsUGE4cytucFRkN1lnTlNVVWxUZnIwMnZZa2c1SDNQbnBoQlFkNFdaK2g5RUtjZng0N0duZyIsIm1hYyI6ImZkODAzNmZmYWJlMjcwMmNhODBmZWIyNTVhMjQwY2YyZTRiNjcyZDgyYTMxMTBiZTUxNzc3NTdjYTdlYzU5MGMifQ==";
	public String circleId="1831408183552808";
	
	//live-streams/1831408183552808
	/*@Test
	public void basicPingTest() {
		given().when().get("login-url?uri=dsds").then().statusCode(200).extract();
	}*/

	/*@Test
	public void containsPingTest() {
		LoginUrlModel url = given().when().get("login-url?uri=dsds").then().statusCode(200).extract()
				.as(LoginUrlModel.class);
		assertThat(url.getData().getUrl()).contains("Ddsds");

	}*/

	/*@Test
	public void mePingTest() {
		MeModel me = given().when().get("me?token="+token).then().statusCode(200).extract()
				.as(MeModel.class);
		
		System.out.println(me.getName());
	//	assertThat(url.getData().getUrl()).contains("Ddsds");
	}*/

	/*@Test
	public void circlePingTest(){
		Circles[] group = given().when().get("circles?token="+token).then().statusCode(200).extract()
				.as(Circles[].class);
		
		for (Circles circles : group) {
			System.out.println(circles.getFacebook_group_id());
		}
		//or:
		System.out.println(group[0].getFacebook_group_id());    ////1831408183552808
	}*/
	
	@Test
	public void circleLiveSteamsPing(){
		///not ok 
		LiveStreamsModel livest = given().when().get("live-streams/1831408183552808?token="+token).then().statusCode(200).extract()
				.as(LiveStreamsModel.class);
		
		//Data[] data=livest.getData();
		
		
//		for (Data d : data) {
//			System.out.println(d);
//			
		
	}
	
	
	/*@Test
	public void circleLiveStatusPing(){
		///ok 
		LiveStatusModel status = given().when().get("circle/1831408183552808/live-status/1831408183552808_1831468853546741?token="+token).then().statusCode(200).extract()
				.as(LiveStatusModel.class);
		System.out.println(status.getStatus());
		
		//  http://staging-labs.api-social-media.pippajean.com/facebook/circle/1831408183552808/live-status/1831408183552808_1831468853546741?token=eyJpdiI6IkVWcHdOaCtzbmtmVGhpb3J4TGRoOVE9PSIsInZhbHVlIjoiMTBFM2dkMVkxdzVFSE1MQ2htZFR6NDJHZjF1bDJvcjExRmxvdXZoa3k1MFRMejEzTnhSeWtqS3B6XC81R3o0T1psUDRENWhseFU4REFMamZjXC9rWG5Nbk1vRFc1ZnUxeHVVZzM3aWpLMndsU2RyMW5BVUZXRzIzZ1crMGIremtBTHE1QTFPdUFcL21YbkgwY0pPV2IwRzhJQVRGUWwyaWdoVVhqc2hyYWJYZFk4OUp2alN3c25JelliN3RpMnRVWkdJQVZneGVsc0lhWXNsUGE4cytucFRkN1lnTlNVVWxUZnIwMnZZa2c1SDNQbnBoQlFkNFdaK2g5RUtjZng0N0duZyIsIm1hYyI6ImZkODAzNmZmYWJlMjcwMmNhODBmZWIyNTVhMjQwY2YyZTRiNjcyZDgyYTMxMTBiZTUxNzc3NTdjYTdlYzU5MGMifQ==  
	}*/
	
	/*@Test
	public void circleCommentsStatusPing(){
		/// OOOK 
		PostsCommentsModel status = given().when().get("posts/1831408183552808_1831446426882317/comments?token="+token).then().statusCode(200).extract()
				.as(PostsCommentsModel.class);
		Data[] data=status.getData();
		
			
		for (Data d : data) {
			System.out.println(d);
			
		}
		
	//	System.out.println(status.getStatus());
		
		//  http://staging-labs.api-social-media.pippajean.com/facebook/circle/1831408183552808/live-status/1831408183552808_1831468853546741?token=eyJpdiI6IkVWcHdOaCtzbmtmVGhpb3J4TGRoOVE9PSIsInZhbHVlIjoiMTBFM2dkMVkxdzVFSE1MQ2htZFR6NDJHZjF1bDJvcjExRmxvdXZoa3k1MFRMejEzTnhSeWtqS3B6XC81R3o0T1psUDRENWhseFU4REFMamZjXC9rWG5Nbk1vRFc1ZnUxeHVVZzM3aWpLMndsU2RyMW5BVUZXRzIzZ1crMGIremtBTHE1QTFPdUFcL21YbkgwY0pPV2IwRzhJQVRGUWwyaWdoVVhqc2hyYWJYZFk4OUp2alN3c25JelliN3RpMnRVWkdJQVZneGVsc0lhWXNsUGE4cytucFRkN1lnTlNVVWxUZnIwMnZZa2c1SDNQbnBoQlFkNFdaK2g5RUtjZng0N0duZyIsIm1hYyI6ImZkODAzNmZmYWJlMjcwMmNhODBmZWIyNTVhMjQwY2YyZTRiNjcyZDgyYTMxMTBiZTUxNzc3NTdjYTdlYzU5MGMifQ==  
	}*/
	
}
