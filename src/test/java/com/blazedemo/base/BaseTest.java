package com.blazedemo.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.blazedemo.driver.BrowserFactory;
import com.blazedemo.driver.DriverFactory;
import com.blazedemo.utils.ConfigReader;

public class BaseTest {

	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	public void launchBrowser(@Optional("") String browser) {
		if (browser == null || browser.trim().isEmpty()) {
			browser = ConfigReader.getValue("browser");
		}
		BrowserFactory.initializeBrowser(browser.trim());
		String url = ConfigReader.getValue("url").trim();
		DriverFactory.getDriver().get(url);
		System.out.println("Navigating to url: " + url);
	}	

	@AfterMethod(alwaysRun = true)
	public void terminateBrowser() {
		DriverFactory.getDriver().quit();
		System.out.println("Brower quit successfully.\n");
		DriverFactory.removeDriver();
	}
}