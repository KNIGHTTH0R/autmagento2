package com.connectors.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import com.tools.constants.HttpConstants;

//TODO deprecated - try to change this

@SuppressWarnings("deprecation")
public class ApacheHttpHelper {

	public static boolean validateResponseStatusCode = true;

	private static DefaultHttpClient httpClient = new DefaultHttpClient();

	// HTTP GET request
	public static String sendGet(String url, String username, String pass) throws Exception {
		HttpGet request = new HttpGet(url);
		System.out.println("Sending GET request to: " + url);
		request.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials(username, pass), "UTF-8", false));

		// add request header
		request.addHeader("User-Agent", HttpConstants.USER_AGENT);
		request.addHeader("Accept-Encoding", HttpConstants.ACCEPT_ENCODING);
		request.addHeader("Accept-Language", HttpConstants.ACCEPT_LANGUAGE);
		request.setHeader("Accept", HttpConstants.ACCEPT);
		String response = executeRequest(request);
		return response;

	}

	// execute http request
	private static String executeRequest(HttpUriRequest request) throws ClientProtocolException, IOException {
		HttpResponse response = httpClient.execute(request);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ " + response.toString());
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println("Request result is ---------------------------: " + result.toString());
		if (validateResponseStatusCode) {
			if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() >= 400) {
				System.out.println("Execute request got BAD RESPONSE: " + result.toString());
				throw new IOException("Got bad response, error code = " + response.getStatusLine().getStatusCode());
			}
		}

		return result.toString();
	}

}
