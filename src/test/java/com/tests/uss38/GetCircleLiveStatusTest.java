package com.tests.uss38;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.FunctionalTest;
import com.steps.external.SocialMedia.SocialMediaSteps;
import com.tests.BaseTest;
import com.tools.constants.SocialMediaConstansts;
import com.tools.data.socialMediaApi.Data;
import com.tools.data.socialMediaApi.LiveStatusModel;
import com.tools.data.socialMediaApi.LiveStreamsModel;
import com.tools.persistance.MongoReader;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
@RunWith(SerenityRunner.class)
public class GetCircleLiveStatusTest extends BaseTest{
	public String circleId,livestreamId;
	@Steps
	public SocialMediaSteps socialMediaSteps;

	@Before
	public void setUp() throws Exception {

		FunctionalTest.setup();
		circleId=MongoReader.grabStringValue("GetCirclesInfoTest" + "GR_ID").get(0);
		livestreamId=MongoReader.grabStringValue("GetLiveStreamsTest" + "video_id").get(0);
	}
	
	@Test
	public void circleLiveStatusPing(){
		///ok 
		LiveStatusModel status = given().when().get("circle/"+circleId+"/live-status/"+livestreamId+"?token="+SocialMediaConstansts.Token).then().statusCode(200).extract()
				.as(LiveStatusModel.class);

		
		LiveStreamsModel livest = given().when().get("live-streams/"+circleId+"?token="+SocialMediaConstansts.Token).then().statusCode(200).extract()
				.as(LiveStreamsModel.class);
		Data[] data=livest.getData();
		
		socialMediaSteps.validateStatus(status.getStatus(),data[0].getTarget().getStatus());
		socialMediaSteps.validateVideoId(status.getObject_id(),data[0].getTarget().getVideo().getId());
		socialMediaSteps.validateStartTime(status.getStart_time(),data[0].getTarget().getBroadcast_start_time());
		socialMediaSteps.validateDuration(status.getDuration(),data[0].getProperties()[0].getText());

		
	}
}
