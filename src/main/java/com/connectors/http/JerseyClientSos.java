package com.connectors.http;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;


public class JerseyClientSos {

	
	
	public static String sendGet(String url,String email,String password){
		String output = "";
		try {

			Client client = Client.create();
			//client.addFilter(new HTTPBasicAuthFilter("pippajean", "Minerilor62!"));
			client.addFilter(new HTTPBasicAuthFilter(email, password));
			WebResource webResource = client.resource(url);
			ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);

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
/*
	public static void sendPost(String jsonInString) {
		try {

			Client client = Client.create();
			client.addFilter(new HTTPBasicAuthFilter("pippajean", "Minerilor62!"));
			WebResource webResource = client.resource("https://apidev.salesonspeed.de/contacts/?requestedUserId=586f530766eeed5a1110c5a7");
			
			
		//	String jsonInput="{\"prename\": \"Incercare\",\"lastname\": \"Reusita\",\"phones\": [{ \"type\": \"mobile\",\"number\": \"0123456789\"}, { \"type\": \"home\",  \"number\": \"9876543210\"  }]}";
			
			
			//application/x-www-form-urlencoded
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonInString);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	*/
	public static void sendPost(String jsonInString) {
		try {

			Client client = Client.create();
			client.addFilter(new HTTPBasicAuthFilter("pippajean", "Minerilor62!"));
			WebResource webResource = client.resource("https://apidev.salesonspeed.de/contacts/?requestedUserId=586f530766eeed5a1110c5a7");
			
			
		//	String jsonInput="{\"prename\": \"Incercare\",\"lastname\": \"Reusita\",\"phones\": [{ \"type\": \"mobile\",\"number\": \"0123456789\"}, { \"type\": \"home\",  \"number\": \"9876543210\"  }]}";
			
			
			//application/x-www-form-urlencoded
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonInString);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	public static void sendPut() {
		try {

			Client client = Client.create();
			client.addFilter(new HTTPBasicAuthFilter("pippajean", "Minerilor62!"));
			WebResource webResource = client.resource("https://apidev.salesonspeed.de/contacts/5874e16566eeed5a1110c611?requestedUserId=586f530766eeed5a1110c5a7");
			
			String jsonInput="{\"prename\": \"Incercare2\",\"lastname\": \"ReusitaPut\"}";
			
			//application/x-www-form-urlencoded
			ClientResponse response = webResource.type("application/json").put(ClientResponse.class, jsonInput);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println("sasa"+output);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	
	public static void sendDelete() {
		try {

			Client client = Client.create();
			client.addFilter(new HTTPBasicAuthFilter("pippajean", "Minerilor62!"));
			WebResource webResource = client.resource("https://apidev.salesonspeed.de/contacts/5874e16566eeed5a1110c611?requestedUserId=586f530766eeed5a1110c5a7");
		
			ClientResponse response = webResource.type("application/json").delete(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	
	
	public static String sendGetValidation(String url,String email,String password){
		String output = "";
		try {
			Client client = Client.create();
			//client.addFilter(new HTTPBasicAuthFilter("pippajean", "Minerilor62!"));
			client.addFilter(new HTTPBasicAuthFilter(email, password));
			WebResource webResource = client.resource(url);
			ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);

			if (response.getStatus() == 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			output = response.getEntity(String.class);
		//	System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}
		
		return output;
	
	}
	public static void main(String[] args) {
		//	sendGet("https://apidev.salesonspeed.de/contacts?requestedUserId=586f530766eeed5a1110c5a7");
		//sendPost();
		//sendPut();
		//	sendDelete();
		
	}
}
