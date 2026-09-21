package com.blazedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.blazedemo.base.BaseTest;
import com.blazedemo.driver.DriverFactory;
import com.blazedemo.pages.ConfirmationPage;
import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.dataproviders.TestDataProviders;

public class FlightBookingTest extends BaseTest {
	
	/*
	 * TC01 Verify the homepage loads and dropdowns visible. Home page should be displayed with correct homepage 
	 * and dropdown should be visible.
	 */
	@Test(priority = 1, groups = "Smoke", description = "TC01 Verify the homepage loads and dropdowns visible.",
			dataProvider = "PageTitleData", dataProviderClass = TestDataProviders.class)
	public void verifyHomePageTitle(String expectedTitle) {
		HomePage homePage = new HomePage(DriverFactory.getDriver());
		Assert.assertTrue(homePage.getPageTitle().contains(expectedTitle), "Home page title mismatch.");
        Assert.assertTrue(homePage.isDepartureCityDropdownVisible(), "Departure city dropdown is missing.");
        Assert.assertTrue(homePage.isDestinationCityDropdownVisible(), "Destination city dropdown is missing.");
	}

	/*
	 * TC02 Search flights with valid cities. Valid flight details should be displayed for the selected cities. 
	 * Verifying that flight number is displayed.
	 */
	@Test(priority = 2, groups = "Functional", description = "TC02 Search flights with valid cities.", 
			dataProvider = "FindFlightsData", dataProviderClass = TestDataProviders.class)
	public void testToFindFlights(String fromCity, String toCity, String flightNumber) {
		boolean actualResult = new HomePage(DriverFactory.getDriver())
			.searchFlights(fromCity, toCity)
			.isFlightNumberPresent(flightNumber);
		Assert.assertTrue(actualResult, "Flight number not found on the reserve page.");
	}

	/*
	 * TC03 Complete a flight booking The booking should be successfully with all the valid booking details. 
	 * Verifying that confirmation page is displayed.
	 */
	@Test(priority = 3, groups = "Functional", description = "TC03 Complete a flight booking", 
			dataProvider = "FlightBookingData", dataProviderClass = TestDataProviders.class)
	public void testToCompleteFlightBooking(String fromCity, String toCity, String flightNumber, String name,
			String address, String city, String state, String code, String cardType, String ccNumber, String ccMonth,
			String ccYear, String nameOnCard, String rememberMe, String expectedMessage) {
		ConfirmationPage confirmationPage = new HomePage(DriverFactory.getDriver())
			.searchFlights(fromCity, toCity)
			.chooseFlightNumber(flightNumber)
			.bookFlight(name, address, city, state, code, cardType, ccNumber, ccMonth, ccYear, nameOnCard, rememberMe);
		Assert.assertTrue(confirmationPage.isPageDisplayed(), "Confirmation page failed to load.");
		Assert.assertTrue(confirmationPage.isMessageDisplayed(), "Message is not displayed.");
		Assert.assertEquals(confirmationPage.getMessage(), expectedMessage, "Incorrect confirmation message displayed");
	}

	/*
	 * TC04 Multiple bookings with different data sets The booking should be successfully with all the valid booking 
	 * details for different data sets. Verifying that confirmation page is displayed with the valid message.
	 */
	@Test(priority = 4, groups = "Data-driven", description = "TC04 Multiple bookings with different data sets", 
			dataProvider = "MultipleBookingData", dataProviderClass = TestDataProviders.class)
	public void testToPerformMultipleBooking(String fromCity, String toCity, String flightNumber, String name,
			String address, String city, String state, String code, String cardType, String ccNumber, String ccMonth,
			String ccYear, String nameOnCard, String rememberMe, String expectedMessage) {
		ConfirmationPage confirmationPage = new HomePage(DriverFactory.getDriver())
			.searchFlights(fromCity, toCity)
			.chooseFlightNumber(flightNumber)
			.bookFlight(name, address, city, state, code, cardType, ccNumber, ccMonth, ccYear, nameOnCard, rememberMe);
		Assert.assertTrue(confirmationPage.isPageDisplayed(), "Confirmation page failed to load.");
		Assert.assertTrue(confirmationPage.isMessageDisplayed(), "Message is not displayed.");
		Assert.assertEquals(confirmationPage.getMessage(), expectedMessage, "Incorrect confirmation message displayed");		
	}

	/*
	 * TC05 Blank credit card The confirmation page should not be displayed because Credit card number is blank. 
	 * Verifying that Purchase page is displayed and not navigated to next page.
	 */
	@Test(priority = 5, groups = "Negative", description = "TC05 Blank credit card" , 
			dataProvider = "BlankCreditCardData", dataProviderClass = TestDataProviders.class)
	public void testToCheckBlankCreditCard(String fromCity, String toCity, String flightNumber, String name,
			String address, String city, String state, String code, String cardType, String ccNumber, String ccMonth,
			String ccYear, String nameOnCard, String rememberMe) {
		PurchasePage purchasePage = new HomePage(DriverFactory.getDriver())
			.searchFlights(fromCity, toCity)
			.chooseFlightNumber(flightNumber);
		purchasePage.bookFlight(name, address, city, state, code, cardType, ccNumber, ccMonth, ccYear, nameOnCard,
			rememberMe);
		Assert.assertTrue(purchasePage.isPageDisplayed(), "Purchase page is not displayed.");
	}

	/*
	 * TC06 Invalid credit card characters The confirmation page should not be displayed because Credit card number 
	 * contains invalid characters. Verifying that Purchase page is displayed and not navigated to next page.
	 */
	@Test(priority = 6, groups = "Negative", description = "TC06 Invalid credit card characters",
			dataProvider = "InvalidCreditCardData", dataProviderClass = TestDataProviders.class)
	public void testToCheckInvalidCreditCardCharacters(String fromCity, String toCity, String flightNumber, String name,
			String address, String city, String state, String code, String cardType, String ccNumber, String ccMonth,
			String ccYear, String nameOnCard, String rememberMe) {
		PurchasePage purchasePage = new HomePage(DriverFactory.getDriver())
			.searchFlights(fromCity, toCity)
			.chooseFlightNumber(flightNumber);
		purchasePage.bookFlight(name, address, city, state, code, cardType, ccNumber, ccMonth, ccYear, nameOnCard, 
			rememberMe);
		Assert.assertTrue(purchasePage.isPageDisplayed(), "Purchase page is not displayed.");
	}

	/*
	 * TC07 Same departure and destination city The home page should be displayed because departure and destination 
	 * city cannot be same. Verifying that home page is displayed.
	 */
	@Test(priority = 7, groups = "Negative", description = "TC07 Same departure and destination city",
			dataProvider = "DuplicateCityData", dataProviderClass = TestDataProviders.class)
	public void testToCheckSameDepartureAndDestinationCity(String fromCity, String toCity) {
		HomePage homePage = new HomePage(DriverFactory.getDriver());
		homePage.searchFlights(fromCity, toCity);
		Assert.assertTrue(homePage.isPageDisplayed(), "Home page is not displayed.");
	}
}