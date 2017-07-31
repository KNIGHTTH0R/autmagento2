package com.tests.uss38;

import com.connectors.http.FunctionalTest;
import com.tools.data.socialMediaApi.Circles;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;

public class GetCirclesInfoTest extends FunctionalTest{
	public String token = "eyJpdiI6IkVWcHdOaCtzbmtmVGhpb3J4TGRoOVE9PSIsInZhbHVlIjoiMTBFM2dkMVkxdzVFSE1MQ2htZFR6NDJHZjF1bDJvcjExRmxvdXZoa3k1MFRMejEzTnhSeWtqS3B6XC81R3o0T1psUDRENWhseFU4REFMamZjXC9rWG5Nbk1vRFc1ZnUxeHVVZzM3aWpLMndsU2RyMW5BVUZXRzIzZ1crMGIremtBTHE1QTFPdUFcL21YbkgwY0pPV2IwRzhJQVRGUWwyaWdoVVhqc2hyYWJYZFk4OUp2alN3c25JelliN3RpMnRVWkdJQVZneGVsc0lhWXNsUGE4cytucFRkN1lnTlNVVWxUZnIwMnZZa2c1SDNQbnBoQlFkNFdaK2g5RUtjZng0N0duZyIsIm1hYyI6ImZkODAzNmZmYWJlMjcwMmNhODBmZWIyNTVhMjQwY2YyZTRiNjcyZDgyYTMxMTBiZTUxNzc3NTdjYTdlYzU5MGMifQ==";
	public String circleId="1831408183552808";
	
	
	@Test
	public void circlePingTest(){
		Circles[] group = given().when().get("circles?token="+token).then().statusCode(200).extract()
				.as(Circles[].class);
		
		for (Circles circles : group) {
			System.out.println(circles.getFacebook_group_id());
		}
		//or:
		//System.out.println(group[0].getFacebook_group_id());    ////1831408183552808
	}
}
