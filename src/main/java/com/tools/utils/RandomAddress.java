package com.tools.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.tools.constants.ContextConstants;
import com.tools.constants.Separators;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.persistance.MongoReader;
import com.tools.utils.FieldGenerators.Mode;

public class RandomAddress {

	private String fileName = UrlConstants.CONTEXT_PATH + MongoReader.getContext() +  "/address.csv";

	List<String> lines = null;

	public AddressModel getRandomAddressFromFile() {

		AddressModel addressModel = new AddressModel();

		try {
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("File can't be opened.");
		}

		int randomWordIndex = getRandomNumber(1, lines.size());

		String[] splittedText = lines.get(randomWordIndex).split(Separators.COMMA_SEPARATOR);
		String houseNumber = cleanAddress(getFirstIntegerFromString(splittedText[0]));

		addressModel.setStreetNumber(houseNumber);
		addressModel.setStreetAddress(cleanAddress(splittedText[0].replace(" " + houseNumber, "")));
		addressModel.setHomeTown(trimQuotes(splittedText[1]));
		addressModel.setPostCode(trimQuotes(splittedText[2]));
		addressModel.setCountryName(trimQuotes(ContextConstants.COUNTRY_NAME));
		addressModel.setPhoneNumber(FieldGenerators.generateRandomString(10, Mode.NUMERIC));

		PrintUtils.printAddressModel(addressModel);

		return addressModel;

	}

	private int getRandomNumber(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

	private String getFirstIntegerFromString(String string) {
		int i = 0;
		while (!Character.isDigit(string.charAt(i)))
			i++;
		int j = i;
		while (Character.isDigit(string.charAt(j)))
			j++;
		return string.substring(i, j);
	}

	private String cleanAddress(String text) {
		text = text.replace("\"", "");
		text = text.replace(" Str.", "");
		text = text.replace(" Str", "");

		return text;
	}
	private String trimQuotes(String text) {
		text = text.replace("\"", "");
		
		return text;
	}

}
