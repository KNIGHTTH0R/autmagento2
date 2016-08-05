package com.pages.external.unbounce;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.constants.ContextConstants;
import com.tools.data.frontend.DykscSeachModel;
import com.tools.requirements.AbstractPage;

public class UnbounceDykscPage extends AbstractPage {

	@FindBy(id = "by_geoip")
	private WebElement searchByPlzOption;

	@FindBy(css = "search_postcode")
	private WebElement searchPlzField;

	@FindBy(id = "search_countryId")
	private WebElement selectACountryDropdown;

	@FindBy(css = "button[name='search_by_geoip_submit']")
	private WebElement searchByPlzButton;

	@FindBy(id = "by_sc_name")
	private WebElement searchByStylistNameOption;

	@FindBy(id = "search_firstname")
	private WebElement searchFirstName;

	@FindBy(id = "search_lastname")
	private WebElement searchLastName;

	@FindBy(css = "button[name='search_by_name_submit']")
	private WebElement searchByStylistNameButton;

	@FindBy(id = "kostenlos-anmelden")
	private WebElement submitButton;

	@FindBy(id = "sc_name_result")
	private WebElement styleCoachNameResult;

	@FindBy(css = "ul#stylist-list li:nth-child(1) div button")
	private WebElement firstStylistContainer;
	
	@FindBy(id = "kostenlos-anmelden")
	private WebElement displayedScs;

	public void selectFirstStylistFromList() {
		element(firstStylistContainer).waitUntilVisible();
		firstStylistContainer.click();
	}

	public void selectSearchByPlzOption() {
		element(searchByPlzOption).waitUntilVisible();
		searchByPlzOption.click();
	}

	public void enterPLZ(String plz) {
		element(searchPlzField).waitUntilVisible();
		element(searchPlzField).clear();
		element(searchPlzField).sendKeys(plz);
		;
	}

	public void selectCountryOption(String country) {
		element(selectACountryDropdown).waitUntilVisible();
		element(selectACountryDropdown).selectByVisibleText(country);
	}

	public void clickSearchStylistByPlz() {
		element(searchByPlzButton).waitUntilVisible();
		element(searchByPlzButton).click();

	}

	public void selectSearchByNameOption() {
		element(searchByStylistNameOption).waitUntilVisible();
		searchByStylistNameOption.click();
	}

	public void enterFirstName(String firstName) {
		element(searchFirstName).waitUntilVisible();
		element(searchFirstName).clear();
		element(searchFirstName).sendKeys(firstName);
		;
	}

	public void enterLasttName(String lastName) {
		element(searchLastName).waitUntilVisible();
		element(searchLastName).clear();
		element(searchLastName).sendKeys(lastName);
		;
	}

	public void clickSearchStylistByName() {
		element(searchByStylistNameButton).waitUntilVisible();
		element(searchByStylistNameButton).click();

	}

	public boolean isStylecoachFound() {
		element(styleCoachNameResult).waitUntilVisible();
		return !styleCoachNameResult.getText().contains(ContextConstants.NO_SC_FOUND_BY_GEOIP);

	}

	public List<DykscSeachModel> getFoundStylecoachesData() {

		List<DykscSeachModel> resultList = new ArrayList<DykscSeachModel>();

		List<WebElement> foundStylecoaches = getDriver().findElements(By.cssSelector("ul#stylist-list li"));

		for (WebElement stylecoach : foundStylecoaches) {

			DykscSeachModel model = new DykscSeachModel();

			model.setId(stylecoach.getAttribute("rel"));
			model.setName(stylecoach.findElement(By.cssSelector("span.sc-name")).getText());
			resultList.add(model);

		}

		return resultList;

	}

}
