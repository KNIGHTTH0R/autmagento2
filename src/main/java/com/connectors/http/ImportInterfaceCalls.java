package com.connectors.http;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ImportInterfaceCalls {

	public static void importOrderInNav(String url, String orderId) {

		System.setProperty("http.proxyHost", "localhost");
		System.setProperty("http.proxyPort", "8080");

		try {
			Client client = Client.create();
			WebResource webResource = client.resource(url);
			webResource.setProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			webResource.setProperty("Accept-Encoding", "gzip, deflate");
			webResource.setProperty("Content-Type", "application/");
			webResource.setProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
			webResource
					.setProperty(
							"Authorization",
							"Negotiate TlRMTVNTUAADAAAAGAAYAHQAAABIAUgBjAAAAAAAAABYAAAAEAAQAFgAAAAMAAwAaAAAABAAEADUAQAAFYKI4gYBsR0AAAAPRoPk1mVjd79tQNQ+SiGbEU4AYQB2AGkAcwBpAG8AbgBFAFYATwAwADIANgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAns315L3KkdDxpNCO+Au+ZAQEAAAAAAABl3harm0fRAbkjeT0pgUM1AAAAAAIAHgBPAFcATgBFAFIATwBSAC0ATwBWAEQASAAxAEoARQABAB4ATwBXAE4ARQBSAE8AUgAtAE8AVgBEAEgAMQBKAEUABAAeAE8AVwBOAEUAUgBPAFIALQBPAFYARABIADEASgBFAAMAHgBPAFcATgBFAFIATwBSAC0ATwBWAEQASAAxAEoARQAHAAgAZd4Wq5tH0QEGAAQAAgAAAAgAMAAwAAAAAAAAAAEAAAAAIAAAB1FSrDodQ818QD6iTyeTGJb6Viv3tX8yQrVYN/3hahYKABAAAAAAAAAAAAAAAAAAAAAAAAkAKABIAFQAVABQAC8AMQA0ADgALgAyADUAMQAuADEANwA4AC4AMgAwADcAAAAAAAAAAAAAAAAAoCEKGQYKxJkf9HE27Gh5eQ==");
			ClientResponse response = webResource.type("application/x-www-form-urlencoded").post(ClientResponse.class, composeParamsString(orderId));

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}
		

		System.setProperty("http.proxyHost", "null");
		System.setProperty("http.proxyPort", "null");
	}

	public static String composeParamsString(String orderId) {

		// return
		// "__RequestVerificationToken=r34aqAgltwCNGO9-IustNI4LCjV-CWEvORYXoQ466JzEO-2rKLfALZiCYgA0x6yqmWJfG0P9jy_q40H_v_gTFwjvIOsrSiq68lK0hO7ZH_nrVa5vrNiGj5u5mBC3e4Kg0&AuftragNo="
		// + orderId +
		// "&Command=Automation+Auftrag+Importieren&Betriebsergebniss=" +
		// orderId + "+erfolgreich+importiert+%21";
		return "__RequestVerificationToken=QiGkETM3hpv_wduPdAFwjzrs5DkmUBb_WZ8INRIyfFssCQmBtA821XA0VAmV2OTZnOHCtwhosV-oxoPiazaDo_RLdAO-QyMoZ8v2bQqcBiWkrld7pWX-gwhRXNNJaav60&AuftragNo="
				+ orderId + "&Command=Automation+Auftrag+Importieren&Betriebsergebniss=";

	}

	public static void main(String[] args) {

		ImportInterfaceCalls.importOrderInNav("http://148.251.178.207/PjOrderImport/Home/ImportierenAut", "staging-int00012061");

	}

}
