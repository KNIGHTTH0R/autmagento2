package com.tests.uss38;

import static com.jayway.restassured.RestAssured.given;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.FunctionalTest;
import com.steps.external.SocialMedia.SocialMediaSteps;
import com.tests.BaseTest;
import com.tools.constants.SocialMediaConstansts;
import com.tools.data.socialMediaApi.Data;
import com.tools.data.socialMediaApi.LiveStreamsModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
@RunWith(SerenityRunner.class)
public class GetLiveStreamsTest extends BaseTest {
	public String circleId,livestreamId;
	@Steps
	public SocialMediaSteps socialMediaSteps;

	@Before
	public void setUp() throws Exception {
		FunctionalTest.setup();
		circleId=MongoReader.grabStringValue("GetCirclesInfoTest" + "GR_ID").get(0);
	}
	
	@Test
	public void LiveStreamsTest(){
		///not ok 
		LiveStreamsModel livest = given().when().get("live-streams/"+circleId+"?token="+SocialMediaConstansts.Token).then().statusCode(200).extract()
				.as(LiveStreamsModel.class);
		
		Data[] data=livest.getData();
		
		socialMediaSteps.validateIdIsNotEmpty(data[0].getId());
		livestreamId=data[0].getId();
		socialMediaSteps.validateCreatedTimeIsNotEmpty(data[0].getCreated_time());
		socialMediaSteps.validateName(data[0].getFrom().getName(), SocialMediaConstansts.Name);
		
	}
	@After
	public void saveData() {
		MongoWriter.saveStringValue(livestreamId,getClass().getSimpleName()+"video_id");
		
	}
	
}
