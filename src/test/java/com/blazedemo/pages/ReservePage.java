package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.Status;
import com.blazedemo.report.ExtentReportFactory;
import com.blazedemo.utils.WaitUtil;

public class ReservePage {
	private WebDriver driver;
	private By chooseFlight = By.xpath("(//input[@value='Choose This Flight'])");

	public ReservePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPageDisplayed() {
		try {
			WaitUtil.waitForElementToBeVisible(driver, chooseFlight);
			System.out.println("Reserve page is displayed.");
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Reserve page is displayed.");
			return driver.getTitle().contains("BlazeDemo - reserve");
		} catch (Exception e) {
			ExtentReportFactory.getExtentTest().log(Status.FAIL, "Reserve page is not displayed: " 
			+ e.getMessage());
			throw new RuntimeException("Reserve page is not displayed: " + e.getMessage());
		}
	}

	public String getPageTitle() {
		try {
			WaitUtil.waitForElementToBeVisible(driver, chooseFlight);
			String title = driver.getTitle();
			System.out.println("Reserve page Title: " + title);
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Reserve page Title: " + title);
			return title;
		} catch (Exception e) {
			ExtentReportFactory.getExtentTest().log(Status.FAIL,
			"Could not get Reserve page title. Exception occured: " + e.getMessage());
			throw new RuntimeException("Could not get Reserve page title. Exception occured: " 
			+ e.getMessage());
		}
	}

	public boolean isFlightNumberPresent(String flightNumber) {
		try {
			if (WaitUtil.waitForElementsToBeVisible(driver, chooseFlight).isEmpty()) {
				System.out.println("No flights found on the Reserve page.");
				ExtentReportFactory.getExtentTest().log(Status.INFO,"No flights found on the Reserve page.");
				return false;
			}
			int size = driver.findElements(By.xpath("//input[@value='Choose This Flight']")).size();
			for (int i = 1; i <= size; i++) {
				String displayedFlightNumber = driver.findElement(By.xpath(
				"//table/tbody/tr[" + i + "]/td/input[@value='Choose This Flight']/ancestor::tr/td[2]"))
				.getText();
				if (displayedFlightNumber.equalsIgnoreCase(flightNumber)) {
					System.out.println("Flight number found on Reserve page: " + displayedFlightNumber);
					ExtentReportFactory.getExtentTest().log(Status.INFO,
					"Flight number found on Reserve page: " + displayedFlightNumber);
					return true;
				}
			}
			System.out.println("Flight number " + flightNumber + " not found on the Reserve page.");
			ExtentReportFactory.getExtentTest().log(Status.INFO,"Flight number " + flightNumber 
			+ " not found on the Reserve page.");
			return false;
		} catch (Exception e) {
			System.out.println("Could not get the flight number on the Reserve page. Exeception occured: " 
		+ e.getMessage());
			ExtentReportFactory.getExtentTest().log(Status.INFO,
			"Could not get the flight number on the Reserve page. Exeception occured: " + e.getMessage());
			return false;
		}
	}

	public PurchasePage chooseFlightNumber(String flightNumber) {
		try {
			if (WaitUtil.waitForElementsToBeVisible(driver, chooseFlight).isEmpty()) {
				System.out.println("No flights found on the Reserve page.");
				ExtentReportFactory.getExtentTest().log(Status.FAIL,"No flights found on the Reserve page.");
				throw new RuntimeException("No flights found on the Reserve page.");
			}
			int size = WaitUtil.waitForElementsToBeVisible(driver, chooseFlight).size();
			for (int i = 1; i <= size; i++) {
				String displayedFlightNumber = driver.findElement(By.xpath(
				"//table/tbody/tr[" + i + "]/td/input[@value='Choose This Flight']/ancestor::tr/td[2]"))
				.getText();
				if (displayedFlightNumber.equalsIgnoreCase(flightNumber)) {
					driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td/input[@value="
							+ "'Choose This Flight']")).click();
					System.out.println("Flight Number " + displayedFlightNumber + 
					" clicked on the Reserve page.");
					ExtentReportFactory.getExtentTest().log(Status.INFO,
					"Flight Number " + displayedFlightNumber + " clicked on the Reserve page.");
					return new PurchasePage(driver);
				}
			}
			System.out.println("Flight Number " + flightNumber + " not present in the table "
					+ "on the Reserve page.");
			ExtentReportFactory.getExtentTest().log(Status.INFO,
			"Flight Number " + flightNumber + " not present in the table on the Reserve page.");
			ExtentReportFactory.getExtentTest().log(Status.FAIL,
			"Flight number not present in the table on the Reserve page.");
			throw new RuntimeException("Flight number not present in the table on the Reserve page.");
		} catch (Exception e) {
			ExtentReportFactory.getExtentTest().log(Status.FAIL,
			"Could not get the flight number on the Reserve page. Exeception occured: " + e.getMessage());
			throw new RuntimeException(
			"Could not get the flight number on the Reserve page. Exeception occured: " + e.getMessage());
		}
	}
}