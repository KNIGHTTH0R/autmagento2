package com.steps.frontend;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import com.tools.data.soap.ProductDetailedModel;
import com.tools.env.constants.ContextConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.DateUtils;
import com.tools.utils.PdfUtils;

import net.thucydides.core.annotations.Step;

/**
 * @author mihai
 *
 */
public class ReportsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	/**
	 * the download directory is configured in pom to be "resources/downloads";
	 * we will clean that directory each time before another download
	 *
	 */
	@Step
	public void downloadProductsOrderedBySku() throws IOException {

		String basedir = System.getProperty("basedir");
		File downloadsdirectory = new File(basedir + "/resources/downloads");
		FileUtils.forceMkdir(downloadsdirectory);
		FileUtils.cleanDirectory(downloadsdirectory);
		reportsPage().downloadProductsOrderedBySku();
	}

	@Step
	public void clickOnIpReports() {
		reportsPage().clickOnIpReports();
	}

	@Step
	public void verifyThatProductHasNotAvailableStatus(String sku) {
		Assert.assertTrue("The product is not in the expected section",
				getNotAvailableProductsSectionText().contains(sku));
	}

	@Step
	public void verifyThatProductHasNotAvailableForTheMomentStatus(String sku) {
		Assert.assertTrue("The product is not in the expected section",
				getNotAvailableForTheMomentProductsSectionText().contains(sku));

	}

	@Step
	public void verifyThatProductHasAsteriscNextToAvDate(ProductDetailedModel model) throws ParseException {
		Assert.assertTrue("The product is not marked correctly",
				getProductLine(model.getSku()).contains(
						DateUtils.parseDate(model.getStockData().getEarliestAvailability(), "yyyy-MM-dd", "dd. MMMM",
								new Locale.Builder().setLanguage(MongoReader.getContext()).build()) + " *"));
	}

	private String getNotAvailableProductsSectionText() {

		String basedir = System.getProperty("basedir");
		String downloadsdirectory = basedir + "/resources/downloads/";
		File folder = new File(downloadsdirectory);
		File[] listOfFiles = folder.listFiles();
		String text = PdfUtils.readPdf(downloadsdirectory + listOfFiles[0].getName());

		return StringUtils.substringAfter(text, ContextConstants.NOT_AVAILABLE);

	}

	private String getNotAvailableForTheMomentProductsSectionText() {

		String basedir = System.getProperty("basedir");
		String downloadsdirectory = basedir + "/resources/downloads/";
		File folder = new File(downloadsdirectory);
		File[] listOfFiles = folder.listFiles();
		String text = PdfUtils.readPdf(downloadsdirectory + listOfFiles[0].getName());
		
		return StringUtils.substringBetween(text, ContextConstants.NOT_AVAILABLE_FOR_THE_MOMENT,
				ContextConstants.NOT_AVAILABLE);
	}

	public String getProductLine(String sku) {
		String[] lines = getNotAvailableForTheMomentProductsSectionText().split("\r\n");
		System.out.println("lines" + lines.length);
		String searchedLine = "";
		for (String line : lines) {
			if (line.contains(sku)) {
				searchedLine = line;
				System.out.println(searchedLine);
				break;
			}
		}
		return searchedLine;
	}

}
