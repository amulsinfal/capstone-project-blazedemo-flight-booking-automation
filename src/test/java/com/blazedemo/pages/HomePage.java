package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;
import com.blazedemo.report.ExtentReportFactory;
import com.blazedemo.utils.WaitUtil;

public class HomePage {
	private WebDriver driver;
	private By departureCity = By.name("fromPort");
	private By destinationCity = By.name("toPort");
	private By findFlightButton = By.xpath("//input[@type='submit']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPageDisplayed() {
		try {
			WaitUtil.waitForElementToBeVisible(driver, findFlightButton);
			System.out.println("Home page is displayed.");
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Home page is displayed.");
			return driver.getTitle().contains("BlazeDemo");
		} catch (Exception e) {
			ExtentReportFactory.getExtentTest().log(Status.FAIL, "Home page is not displayed: " 
			+ e.getMessage());
			throw new RuntimeException("Home page is not displayed: " + e.getMessage());
		}
	}

	public String getPageTitle() {
		try {
			WaitUtil.waitForElementToBeVisible(driver, findFlightButton);
			String title = driver.getTitle();
			System.out.println("Home page Title: " + title);
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Home page Title: " + title);
			return title;
		} catch (Exception e) {
			ExtentReportFactory.getExtentTest().log(Status.FAIL,
			"Could not get Home page title. Exception occured: " + e.getMessage());
			throw new RuntimeException("Could not get Home page title. Exception occured: " 
			+ e.getMessage());
		}
	}

	public boolean isDepartureCityDropdownVisible() {
		try {
			WaitUtil.waitForElementToBeVisible(driver, departureCity);
			boolean isDisplayed = driver.findElement(departureCity).isDisplayed();
			System.out.println("Departure city dropdown displayed on Home page: " + isDisplayed);
			ExtentReportFactory.getExtentTest().log(Status.INFO,
			"Departure city dropdown displayed on Home page: " + isDisplayed);
			return isDisplayed;
		} catch (Exception e) {
			System.out.println("Departure city dropdown not displayed on Home page. Exception occured: " 
			+ e.getMessage());
			ExtentReportFactory.getExtentTest().log(Status.FAIL,
			"Departure city dropdown not displayed on Home page. Exception occured: " + e.getMessage());
			return false;
		}
	}

	public boolean isDestinationCityDropdownVisible() {
		try {
			WaitUtil.waitForElementToBeVisible(driver, destinationCity);
			boolean isDisplayed = driver.findElement(destinationCity).isDisplayed();
			System.out.println("Destination city dropdown displayed  on Home page: " + isDisplayed);
			ExtentReportFactory.getExtentTest().log(Status.INFO,
			"Destination city dropdown displayed on Home page: " + isDisplayed);
			return isDisplayed;
		} catch (Exception e) {
			System.out.println(
			"Destination city dropdown not displayed  on Home page. Exception occured: " + e.getMessage());
			ExtentReportFactory.getExtentTest().log(Status.FAIL,
			"Destination city dropdown not displayed on Home page. Exception occured: " + e.getMessage());
			return false;
		}
	}

	public ReservePage searchFlights(String fromCity, String toCity) {
		try {
			WaitUtil.waitForElementToBeVisible(driver, departureCity);
			Select selectFromCity = new Select(driver.findElement(departureCity));
			selectFromCity.selectByVisibleText(fromCity);
			System.out.println("Departure city selected  on Home page: " 
			+ selectFromCity.getFirstSelectedOption().getText());
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Departure city selected on Home page: " 
			+ selectFromCity.getFirstSelectedOption().getText());
			Select selectToCity = new Select(driver.findElement(destinationCity));
			selectToCity.selectByVisibleText(toCity);
			System.out.println("Destination city selected on Home page: " 
			+ selectToCity.getFirstSelectedOption().getText());
			ExtentReportFactory.getExtentTest().log(Status.INFO,"Destination city selected on Home page: "
			+ selectToCity.getFirstSelectedOption().getText());
			driver.findElement(findFlightButton).click();
			ExtentReportFactory.getExtentTest().log(Status.INFO,"Find Flights button on Home page clicked.");
			System.out.println("Find Flights button on Home page clicked.");
			return new ReservePage(driver);
		} catch (Exception e) {
			ExtentReportFactory.getExtentTest().log(Status.FAIL,
			"Failed to complete flight search on Home page due to: " + e.getMessage());
			throw new RuntimeException("Failed to complete flight search on Home page due to: "
			+ e.getMessage());
		}
	}
}
