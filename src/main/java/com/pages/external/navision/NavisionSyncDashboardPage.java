package com.pages.external.navision;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tools.requirements.AbstractPage;

public class NavisionSyncDashboardPage extends AbstractPage {

	@FindBy(css = ".ms-nav-band-caption a[title='Lines']")
	private WebElement linesOption;

	@FindBy(css = ".ms-nav-ctxmenu-item a[title='Filters']")
	private WebElement filtersOption;

	public void clickLinesLink() {
		element(linesOption).waitUntilVisible();
		linesOption.click();
	}

	public void clickFilterLink() {
		element(filtersOption).waitUntilVisible();
		filtersOption.click();
	}

	public void clickPJOrderList(String tableName) {
		List<WebElement> tableList = getDriver().findElements(By.cssSelector(".ms-nav-scrollable tbody tr"));
		boolean found = false;
		for (WebElement tableItem : tableList) {
			if (tableItem.findElement(By.cssSelector("td:nth-child(1)")).getText().contentEquals(tableName)) {
				found = true;
				tableItem.click();

			}
		}
		Assert.assertTrue("The table was not found", found);
	}

	public void insertAuthentificationCredentials() {
		// WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		// Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		// alert.authenticateUsing(new UserAndPassword("Tinxit",
		// "NAV-MAG12#$"));

		// setup web driver
		// System.setProperty("webdriver.chrome.driver", "path to your
		// chromedriver.exe");
		// driver = new ChromeDriver();

		// create new thread for interaction with windows authentication window
		(new Thread(new LoginWindow())).start();

		// open your url. this will prompt you for windows authentication
		getDriver().get("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=43%3bh%2fqqAAJ7%2f1AAUABKAF8ATQBJAFgARQBEAF8ATQBBAE4AVQBBAEw%3d&mode=Edit&page=11205257&i=301&IsDlg=1");

		// add test scripts below ...
	//	getDriver().findElement(By.linkText("Home")).click();

	}

	public class LoginWindow implements Runnable {

		@Override
		public void run() {
			try {
				login();
			} catch (Exception ex) {
				System.out.println("Error in Login Thread: " + ex.getMessage());
			}
		}

		public void login() throws Exception {

			// wait - increase this wait period if required
			Thread.sleep(5000);

			// create robot for keyboard operations
			Robot rb = new Robot();

			// Enter user name by ctrl-v
			StringSelection username = new StringSelection("Tinxit");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(username, null);
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_CONTROL);

			// tab to password entry field
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(2000);

			// Enter password by ctrl-v
			StringSelection pwd = new StringSelection("NAV-MAG12#$");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pwd, null);
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_CONTROL);

			// press enter
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);

			// wait
			Thread.sleep(5000);
		}
	}
}
