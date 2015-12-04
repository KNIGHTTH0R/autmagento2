package com.steps.frontend;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.thucydides.core.annotations.Step;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import com.tools.requirements.AbstractSteps;
import com.tools.utils.PdfUtils;

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
		FileUtils.cleanDirectory(downloadsdirectory);
		reportsPage().downloadProductsOrderedBySku();
	}

	@Step
	public void verifyThatProductHasNotAvailableStatus(String sku) {
		Assert.assertTrue("The product is not in the expected section", getNotAvailableProductsSectionText().contains(sku));
	}

	@Step
	public void verifyThatProductHasNotAvailableForTheMomentStatus(String sku) {
		Assert.assertTrue("The product is not in the expected section", getNotAvailableForTheMomentProductsSectionText().contains(sku));

	}

	private String getNotAvailableProductsSectionText() {

		String pattern1 = "Nicht mehr verfügbare Artikel";
		String basedir = System.getProperty("basedir");
		String downloadsdirectory = basedir + "/resources/downloads/";
		File folder = new File(downloadsdirectory);
		File[] listOfFiles = folder.listFiles();
		String text = PdfUtils.readPdf(downloadsdirectory + listOfFiles[0].getName());

		return StringUtils.substringAfter(text, pattern1);

	}

	private String getNotAvailableForTheMomentProductsSectionText() {

		String pattern1 = "Vorübergehend nicht verfügbar";
		String pattern2 = "Nicht mehr verfügbare Artikel";
		String basedir = System.getProperty("basedir");
		String downloadsdirectory = basedir + "/resources/downloads/";
		File folder = new File(downloadsdirectory);
		File[] listOfFiles = folder.listFiles();
		String text = PdfUtils.readPdf(downloadsdirectory + listOfFiles[0].getName());


		return StringUtils.substringBetween(text, pattern1, pattern2);
	}

}
