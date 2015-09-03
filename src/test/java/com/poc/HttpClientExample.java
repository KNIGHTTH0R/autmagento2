package com.poc;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.tools.data.geolocation.CoordinatesModel;
import com.tools.geolocation.GoogleResponse;
import com.tools.geolocation.Result;

public class HttpClientExample {

	public static void main(String[] args) throws Exception {

		JerseyClient.sendGet("");

	}

	public String sendGet(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response1 = httpclient.execute(httpGet);
		String unparsedResponse;

		try {
			System.out.println(response1.getStatusLine());
			HttpEntity entity = response1.getEntity();
			unparsedResponse = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
		} finally {
			response1.close();
		}
		return unparsedResponse;

	}

	public void setAuthentication(String host, int port, String username, String password) {
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(host, port), new UsernamePasswordCredentials(username, password));
//		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
	}

	public static CoordinatesModel getLatAndLongFromAddress(String url) throws IOException {

		CoordinatesModel coordinatesModel = new CoordinatesModel();

		// String unparsedResponse = new HttpClientExample().sendGet(url);
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

}