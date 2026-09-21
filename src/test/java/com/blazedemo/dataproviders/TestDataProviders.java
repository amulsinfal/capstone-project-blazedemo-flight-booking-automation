package com.blazedemo.dataproviders;

import org.testng.annotations.DataProvider;

public class TestDataProviders {

	@DataProvider(name = "PageTitleData")
	public Object[][] getPageTitleData() {
		return new Object[][] { { "BlazeDemo" } };
	}

	@DataProvider(name = "FindFlightsData")
	public Object[][] getFindFlightsData() {
		return new Object[][] { { "Paris", "Buenos Aires", "234" }, };
	}

	@DataProvider(name = "FlightBookingData")
	public Object[][] getFlightBookingData() {
		return new Object[][] { { "Boston","New York","12","Rick Tas","End Street","Indi","Indiana","52112",
		"Visa","1234567890123456","6","2030","Rick Tas","No","Thank you for your purchase today!"} };
	}

	@DataProvider(name = "MultipleBookingData", parallel = true)
	public Object[][] getMultipleBookingData() {
		return new Object[][] { { "Paris","New York","43","John Doe","Avenue Street","Nerwak","New Jersy",
		"41118", "Visa","1234567890123456","1","2027","John Doe","Yes","Thank you for your purchase today!"},
		{ "Boston","Berlin","234","David Jes","Main Street","NY","NY","51123","American Express",
		"4567890123455678","3","2027","David Jes","No","Thank you for your purchase today!" } };
	}

	@DataProvider(name = "BlankCreditCardData")
	public Object[][] getBlankCreditCardData() {
		return new Object[][] { { "Paris","Buenos Aires","12","Rick Tig","167 End Street","Indianapolis",
		"Indiana","52112","Visa","","6","2030","Rick Tig","No" } };
	}

	@DataProvider(name = "InvalidCreditCardData")
	public Object[][] getInvalidCreditCardData() {
		return new Object[][] { { "Paris","Buenos Aires","12","Rick Tig","167 End Street","Indianapolis",
		"Indiana","52112","Visa","!@#$%^7890)(*&65$#23$%","6","2030","Rick Tig","No"} };
	}

	@DataProvider(name = "DuplicateCityData")
	public Object[][] getDuplicateCityData() {
		return new Object[][] { { "Boston", "Boston" }, };
	}
}