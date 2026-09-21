package com.blazedemo.driver;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.blazedemo.utils.ConfigReader;

public class BrowserFactory {
	public static void initializeBrowser(String browser) {
		WebDriver driver = null;
		
		switch (browser.toLowerCase().trim()) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito");
			driver = new ChromeDriver(chromeOptions);
			System.out.println("Launching Chrome browser.");
			break;
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--private");
			driver = new FirefoxDriver(firefoxOptions);
			System.out.println("Launching Firefox browser.");
			break;
		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--inprivate");
			driver = new EdgeDriver(edgeOptions);
			System.out.println("Launching Edge browser.");
			break;
		default:
			throw new RuntimeException("Browser name is incorrect. Test execution stopped.");
		}
		driver.manage().window().maximize();
		String implicitWait = ConfigReader.getValue("implicitWait");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(implicitWait)));
		System.out.println("Implicit wait time set for " + implicitWait + " seconds.");
		DriverFactory.setDriver(driver);
	}
}