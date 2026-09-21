package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.Status;
import com.blazedemo.report.ExtentReportFactory;
import com.blazedemo.utils.WaitUtil;

public class ConfirmationPage {
	private WebDriver driver;
	private By lblThankYouMessage = By.xpath("//div[@class='container hero-unit']/h1");

	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPageDisplayed() {
		try {
			WaitUtil.waitForElementToBeVisible(driver, lblThankYouMessage);
			System.out.println("Confirmation page is displayed.");
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Confirmation page is displayed.");
			return driver.getTitle().contains("BlazeDemo Confirmation");
		} catch (Exception e) {
			ExtentReportFactory.getExtentTest().log(Status.FAIL,
			"Confirmation page is not displayed: " + e.getMessage());
			throw new RuntimeException("Confirmation page is not displayed: " + e.getMessage());
		}
	}

	public String getPageTitle() {
		try {
			WaitUtil.waitForElementToBeVisible(driver, lblThankYouMessage);
			String title = driver.getTitle();
			System.out.println("Confirmation page Title: " + title);
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Confirmation page Title: " + title);
			return title;
		} catch (Exception e) {
			ExtentReportFactory.getExtentTest().log(Status.FAIL,
			"Could not get Confirmation page title. Exception occured: " + e.getMessage());
			throw new RuntimeException("Could not get Confirmation page title. Exception occured: " 
			+ e.getMessage());
		}
	}

	public boolean isMessageDisplayed() {
		try {
			WaitUtil.waitForElementToBeVisible(driver, lblThankYouMessage);
			System.out.println("Thank You message is displayed.");
			ExtentReportFactory.getExtentTest().log(Status.INFO,
			"Thank You message is displayed on Confirmation page.");
			return true;
		} catch (Exception e) {
			ExtentReportFactory.getExtentTest().log(Status.FAIL,
			"Thank You message was not displayed on Confirmation page. " + e.getMessage());
			System.out.println("Thank You message was not displayed on Confirmation page. " 
			+ e.getMessage());
			return false;
		}
	}

	public String getMessage() {
		try {
			WaitUtil.waitForElementToBeVisible(driver, lblThankYouMessage);
			String text = driver.findElement(lblThankYouMessage).getText();
			System.out.println("Message Text displayed: " + text);
			ExtentReportFactory.getExtentTest().log(Status.INFO,
			"Message Text displayed on Confirmation page: " + text);
			return text;
		} catch (Exception e) {
			ExtentReportFactory.getExtentTest().log(Status.FAIL,
			"Could not get message text from the Confirmation page. Exception occured: " + e.getMessage());
			throw new RuntimeException(
			"Could not get message text from the Confirmation page. Exception occured: " + e.getMessage());
		}
	}
}