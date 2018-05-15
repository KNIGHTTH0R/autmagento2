package com.steps.frontend;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import com.tools.constants.ContextConstants;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.DateUtils;
import com.tools.utils.PdfUtils;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

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

	@Title("Verify TP info message")
	@Step
	@Screenshots(onlyOnFailures = true)
	public void verifyTpInfoMessage(boolean shouldBePresent) throws ParseException {
		if (shouldBePresent) {
			Assert.assertTrue("The message is not displayed",
					getProductLine(ContextConstants.NOT_AVAILABLE_FOR_THE_MOMENT)
							.contains(ContextConstants.TP_INFO_MESSAGE));
		} else {
			Assert.assertTrue("The message is displayed and it shouldn't",
					!getProductLine(ContextConstants.NOT_AVAILABLE_FOR_THE_MOMENT)
							.contains(ContextConstants.TP_INFO_MESSAGE));
		}

	}

	@Title("Verify product has * next to earliest availability date")
	@Step
	@Screenshots(onlyOnFailures = true)
	public void verifyAsteriscNextToAvDate(ProductDetailedModel model) throws ParseException {
		Assert.assertTrue("The product is not marked correctly",
				getProductLine(model.getSku()).contains(
						DateUtils.parseDate(model.getStockData().getEarliestAvailability(), "yyyy-MM-dd", "dd. MMMM",
								new Locale.Builder().setLanguage(MongoReader.getContext()).build()) + " ***"));
	}

	@Title("Verify product has no * next to earliest availability date")
	@Step
	@Screenshots(onlyOnFailures = true)
	public void verifyNoAsteriscNextToAvDate(ProductDetailedModel model) throws ParseException {
		String line = getProductLine(model.getSku());
		Assert.assertTrue("The product is not marked correctly",
				line.contains(DateUtils.parseDate(model.getStockData().getEarliestAvailability(), "yyyy-MM-dd",
						"dd. MMMM", new Locale.Builder().setLanguage(MongoReader.getContext()).build()))
				&& !line.contains("***"));
	}

	@Title("Verify product has no * next to will be announced status")
	@Step
	@Screenshots(onlyOnFailures = true)
	public void verifyNoAsteriscNextToWillBeAnnouncedStatus(ProductDetailedModel model) throws ParseException {
		String line = getProductLine(model.getSku());
		Assert.assertTrue("The product is not marked correctly",
				line.contains(ContextConstants.WILL_BE_ANNOUNCED) && !line.contains("***"));
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

	@Step
	public void clickOnTeamReports() {
		reportsPage().clickOnTeamReports();
	}

	@Step
	public void clickOnMobileVersionOfAvList() {
		reportsPage().clickOnMobileVersionOfAvList();

	}

	public static void main(String[] args) {

		//	String basedir = System.getProperty("basedir");
		//	String downloadsdirectory = basedir + "/resources/downloads/";
			String downloadsdirectory= "C:/Users/emilianmelian/Downloads/automation/";
			File folder = new File(downloadsdirectory);
			File[] listOfFiles = folder.listFiles();
			String text = PdfUtils.readPdf(downloadsdirectory + listOfFiles[0].getName());
			System.out.println(text);
			
	}
	
}
