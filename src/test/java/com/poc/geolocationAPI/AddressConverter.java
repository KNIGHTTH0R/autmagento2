package com.poc.geolocationAPI;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.tools.data.geolocation.CoordinatesModel;
import com.tools.data.soap.DBStylistModel;

public class AddressConverter {

	private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";

	// Here the fullAddress String is in format like
	// "address(street name + house number or viceversa),city,state,zipcode".

	public GoogleResponse convertToLatLong(String fullAddress) throws IOException {

		URL url = new URL(URL + "?address=" + URLEncoder.encode(fullAddress, "UTF-8"));
		// Open the Connection
		URLConnection conn = url.openConnection();

		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		GoogleResponse response = (GoogleResponse) mapper.readValue(in, GoogleResponse.class);
		in.close();
		return response;

	}

	// TODO pass as parameter an AddressModel here -maybe
	public static CoordinatesModel getLattitudeAndLongitudeFromAddress(String address) throws IOException {

		CoordinatesModel coordinatesModel = new CoordinatesModel();
		GoogleResponse res = new AddressConverter().convertToLatLong(address);
		if (res.getStatus().equals("OK")) {

			for (Result result : res.getResults()) {

				coordinatesModel.setLattitude((result.getGeometry().getLocation().getLat()));
				coordinatesModel.setLongitude((result.getGeometry().getLocation().getLng()));

			}
		} else {
			System.out.println(res.getStatus());
		}
		return coordinatesModel;
	}

//	public static List<DBStylistModel> calculateLatAndLongForStylist(List<DBStylistModel> model) throws IOException {
//
//		for (DBStylistModel dbStylistModel : model) {
//			GoogleResponse res = new AddressConverter().convertToLatLong(dbStylistModel.getStreet() + " "+  dbStylistModel.getHouseNumber() + "," + dbStylistModel.getPostCode());
//			System.out.println(dbStylistModel.getStreet() + " "+  dbStylistModel.getHouseNumber() + "," + dbStylistModel.getPostCode());
//			if (res.getStatus().equals("OK")) {
//
//				for (Result result : res.getResults()) {
//
//					dbStylistModel.setLattitude((result.getGeometry().getLocation().getLat()));
//					dbStylistModel.setLongitude((result.getGeometry().getLocation().getLng()));
//				}
//			} else {
//				System.out.println(res.getStatus());
//			}
//		}
//
//		return model;
//	}

}
