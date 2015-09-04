package com.connectors.http;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.tools.commision.CommisionResponse;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.env.variables.Credentials;
import com.tools.env.variables.UrlConstants;
import com.tools.geolocation.GoogleResponse;
import com.tools.geolocation.Result;
import com.tools.utils.DateUtils;
import com.tools.utils.MD5;

public class ComissionRestCalls {

	public static void main(String[] args) throws Exception {

		ComissionRestCalls.getPartyPerformanceInfo("13941");

	}

	public static String composeAuthenticationSuffix() throws Exception {

		String hash = MD5.getMd5(Credentials.API_KEY + Credentials.API_SECRET + DateUtils.getTimestamp() + "30");
		String suffix = "?api_sig=" + hash + "&api_key=" + Credentials.API_KEY + "&api_ts=" + DateUtils.getTimestamp() + "&api_ttl=30";

		return suffix;
	}

	public static CoordinatesModel getLatAndLongFromAddress(String url) throws IOException {

		CoordinatesModel coordinatesModel = new CoordinatesModel();

		String unparsedResponse = JerseyClient.sendGet(url);
		ObjectMapper mapper = new ObjectMapper();
		GoogleResponse res = (GoogleResponse) mapper.readValue(unparsedResponse, GoogleResponse.class);

		if (res.getStatus().equals("OK")) {

			for (Result result : res.getResults()) {

				coordinatesModel.setLattitude((result.getGeometry().getLocation().getLat()));
				coordinatesModel.setLongitude((result.getGeometry().getLocation().getLng()));
				System.out.println(coordinatesModel.getLattitude());
				System.out.println(coordinatesModel.getLongitude());
			}
		} else {
			System.out.println(res.getStatus());
		}

		return coordinatesModel;
	}

	public static String getStylistInfo(String stylistId) throws Exception {

		String unparsedResponse = JerseyClient.sendGet(UrlConstants.COMMISION_WEB_BASE + UrlConstants.COMMISION_STYLIST_SUFFIX + stylistId + composeAuthenticationSuffix());

		ObjectMapper mapper = new ObjectMapper();
		CommisionResponse res = (CommisionResponse) mapper.readValue(unparsedResponse, CommisionResponse.class);
		if (res.getStatus().equals("ok")) {

			String name = res.getBody().getName();
			System.out.println(name);
		} else {
			System.out.println(res.getStatus());
		}

		return unparsedResponse;
	}
	
	public static String getPartyPerformanceInfo(String stylistId) throws Exception {
		
		String unparsedResponse = JerseyClient.sendGet(UrlConstants.COMMISION_WEB_BASE + UrlConstants.COMMISION_PARTY_SUFFIX + stylistId + composeAuthenticationSuffix());
		
//		ObjectMapper mapper = new ObjectMapper();
//		CommisionResponse res = (CommisionResponse) mapper.readValue(unparsedResponse, CommisionResponse.class);
//		if (res.getStatus().equals("ok")) {
//			
//			String name = res.getBody().getName();
//			System.out.println(name);
//		} else {
//			System.out.println(res.getStatus());
//		}
		
		return unparsedResponse;
	}
}