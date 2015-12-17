package com.connectors.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tools.data.navision.SyncInfoModel;
import com.tools.data.soap.CategoryModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.data.soap.StockDataModel;
import com.tools.env.constants.Separators;
import com.tools.persistance.MongoReader;
import com.tools.utils.FieldGenerators;
import com.tools.utils.FieldGenerators.Mode;
import com.tools.utils.FormatterUtils;

/**
 * @author mihaibarta
 *
 */

public class MagentoCategoriesCalls {

	public static CategoryModel createCategoryModel() {
		CategoryModel result = new CategoryModel();
		result.setParentId("52");
		List<String> categoriesFilters = new ArrayList<String>();
		categoriesFilters.add("name");
		result.setAvailableSortBy(categoriesFilters);
		result.setIsActive("1");
		result.setDefaultSortBy("name");
		result.setIncludeInMenu("1");
		result.setUrlKey("dfdffsfd");
		return result;
	}

	public static String createApiCategory(CategoryModel category, String parentId) {

		String resultID = null;
		try {
			SOAPMessage response = HttpSoapConnector.soapCreateCategory(category, parentId);
			resultID = extractResult(response);
			System.out.println("resultID: " + resultID);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultID;
	}

	private static String extractResult(SOAPMessage response) throws SOAPException, IOException {
		return response.getSOAPBody().getElementsByTagName("result").item(0).getFirstChild().getNodeValue();
	}

}
