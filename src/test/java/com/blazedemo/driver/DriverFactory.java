package com.blazedemo.driver;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
	private static final ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return tDriver.get();
	}

	public static void setDriver(WebDriver driver) {
		tDriver.set(driver);
	}

	public static void removeDriver() {
		tDriver.remove();
	}
}
