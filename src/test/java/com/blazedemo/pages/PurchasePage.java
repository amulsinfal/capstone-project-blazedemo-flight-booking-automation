package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.Status;
import com.blazedemo.report.ExtentReportFactory;
import com.blazedemo.utils.WaitUtil;

public class PurchasePage {
	private WebDriver driver;
	private By txtName = By.id("inputName");
	private By txtAddress = By.id("address");
	private By txtCity = By.id("city");
	private By txtState = By.id("state");
	private By txtZipCode = By.id("zipCode");
	private By txtCardType = By.id("cardType");
	private By txtCreditCardNumber = By.id("creditCardNumber");
	private By txtCreditCardMonth = By.id("creditCardMonth");
	private By txtCreditCardYear = By.id("creditCardYear");
	private By txtNameOnCard = By.id("nameOnCard");
	private By chkRememberMe = By.id("rememberMe");
	private By btnPurchaseFlight = By.xpath("//input[@value='Purchase Flight']");

	public PurchasePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPageDisplayed() {
		try {
			WaitUtil.waitForElementToBeVisible(driver, btnPurchaseFlight);
			System.out.println("Purchase page is displayed.");
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Purchase page is displayed.");
			return driver.getTitle().contains("BlazeDemo Purchase");
		} catch (Exception e) {
			ExtentReportFactory.getExtentTest().log(Status.FAIL, "Purchase page is displayed.");
			throw new RuntimeException("Purchase page is not displayed. Exception occured: " 
			+ e.getMessage());
		}
	}

	public String getPageTitle() {
		try {
			WaitUtil.waitForElementToBeVisible(driver, btnPurchaseFlight);
			String title = driver.getTitle();
			System.out.println("Purchase page Title: " + title);
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Purchase page Title: " + title);
			return title;
		} catch (Exception e) {
			ExtentReportFactory.getExtentTest().log(Status.FAIL,
			"Could not get Purchase page title. Exception occured: " + e.getMessage());
			throw new RuntimeException("Could not get Purchase page title. Exception occured: " 
			+ e.getMessage());
		}
	}

	public ConfirmationPage bookFlight(String name, String address, String city, String state, String code,
			String cardType, String ccNumber, String ccMonth, String ccYear, String nameOnCard, 
			String rememberMe) {

		try {
			WaitUtil.waitForElementToBeClickable(driver, btnPurchaseFlight);
			WebElement inputName = driver.findElement(txtName);
			inputName.clear();
			inputName.sendKeys(name);
			System.out.println("Name entered: " + name);
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Name entered: " + name);
			WebElement inputAddress = driver.findElement(txtAddress);
			inputAddress.clear();
			inputAddress.sendKeys(address);
			System.out.println("Address entered: " + address);
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Address entered: " + address);
			WebElement inputCity = driver.findElement(txtCity);
			inputCity.clear();
			inputCity.sendKeys(city);
			System.out.println("City entered: " + city);
			ExtentReportFactory.getExtentTest().log(Status.INFO, "City entered: " + city);
			WebElement inputState = driver.findElement(txtState);
			inputState.clear();
			inputState.sendKeys(state);
			System.out.println("State entered: " + state);
			ExtentReportFactory.getExtentTest().log(Status.INFO, "State entered: " + state);
			WebElement inputZip = driver.findElement(txtZipCode);
			inputZip.clear();
			inputZip.sendKeys(code);
			System.out.println("Zipcode entered: " + code);
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Zipcode entered: " + code);
			Select selectCardType = new Select(driver.findElement(txtCardType));
			selectCardType.selectByVisibleText(cardType);
			System.out.println("Card type selected: " + selectCardType.getFirstSelectedOption().getText());
			ExtentReportFactory.getExtentTest().log(Status.INFO,
					"Card type selected: " + selectCardType.getFirstSelectedOption().getText());
			WebElement inputCCNumber = driver.findElement(txtCreditCardNumber);
			inputCCNumber.clear();
			inputCCNumber.sendKeys(ccNumber);
			System.out.println("Credit Card Number entered: " + ccNumber);
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Credit Card Number entered: " + ccNumber);
			WebElement inputCCMonth = driver.findElement(txtCreditCardMonth);
			inputCCMonth.clear();
			inputCCMonth.sendKeys(ccMonth);
			System.out.println("Credit Card Month entered: " + ccMonth);
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Credit Card Month entered: " + ccMonth);
			WebElement inputCCYear = driver.findElement(txtCreditCardYear);
			inputCCYear.clear();
			inputCCYear.sendKeys(ccYear);
			System.out.println("Credit Card Year entered: " + ccYear);
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Credit Card Year entered: " + ccYear);
			WebElement inputCardName = driver.findElement(txtNameOnCard);
			inputCardName.clear();
			inputCardName.sendKeys(nameOnCard);
			System.out.println("Name On Card entered: " + nameOnCard);
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Name On Card entered: " + nameOnCard);
			WebElement checkboxRememberMe = driver.findElement(chkRememberMe);
			if (rememberMe.equalsIgnoreCase("Yes")) {
				if (!checkboxRememberMe.isSelected()) {
					checkboxRememberMe.click();
				}
				System.out.println("Remember Me checkbox is checked.");
				ExtentReportFactory.getExtentTest().log(Status.INFO, "Remember Me checkbox is checked.");
			} else if (rememberMe.equalsIgnoreCase("No")) {
				if (checkboxRememberMe.isSelected()) {
					checkboxRememberMe.click();
				}
				System.out.println("Remember Me checkbox is un-checked.");
				ExtentReportFactory.getExtentTest().log(Status.INFO,"Remember Me checkbox is un-checked.");
			}

			driver.findElement(btnPurchaseFlight).click();
			System.out.println("Purchase flight clicked.");
			ExtentReportFactory.getExtentTest().log(Status.INFO, "Purchase flight clicked.");
			return new ConfirmationPage(driver);

		} catch (Exception e) {
			ExtentReportFactory.getExtentTest().log(Status.FAIL,
			"Could not complete flight booking. Exception occured: " + e.getMessage());
			throw new RuntimeException("Could not complete flight booking. Exception occured: " 
			+ e.getMessage());
		}

	}
}
