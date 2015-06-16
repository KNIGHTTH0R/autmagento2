package com.connectors.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import com.tools.env.constants.JenkinsConstants;

//TODO deprecated - try to change this

@SuppressWarnings("deprecation")
public class ApacheHttpHelper {

	public static boolean validateResponseStatusCode = true;

	private static DefaultHttpClient httpClient = new DefaultHttpClient();
	private Credentials credentials = new UsernamePasswordCredentials("voicu.vlad", "alpha123");

	public ApacheHttpHelper() {
		super();
		httpClient.getCredentialsProvider().setCredentials(AuthScope.ANY, credentials);
	}

	public ApacheHttpHelper(String userName, String password) {
		super();
		httpClient.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
	}

	// set credentials
	public void setUserNameAndPassword(String userName, String password) {
		credentials = new UsernamePasswordCredentials(userName, password);
	}

	// HTTP GET request
	public static String sendGet(String url) throws Exception {
		HttpGet request = new HttpGet(url);

		System.out.println("Sending GET request to: " + url);
		request.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials(JenkinsConstants.USERNAME, JenkinsConstants.PASSWORD), "UTF-8", false));

		// add request header
		request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.124 Safari/537.36");
		request.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		request.addHeader("Accept-Language", "en-US,en;q=0.8");
		request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
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

	public static void main(String args[]) {
		try {
			ApacheHttpHelper.sendGet("http://jenkins.pippajean.com/job/RUN_MAIL_SCRIPT/build?token=XDWRmbcL3U1mRlCjOBrF");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
