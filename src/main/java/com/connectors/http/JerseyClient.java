package com.connectors.http;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClient {

	public static String sendGet(String url) {
		System.out.println(url);
		String output = "";
		try {

			Client client = Client.create();
			WebResource webResource = client.resource(url);
			ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);
			System.out.println(response.getStatus());
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return output;
	}

	public static void sendPost(String url, String paramsString) {
		try {

			Client client = Client.create();
			WebResource webResource = client.resource(url);
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, paramsString);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
