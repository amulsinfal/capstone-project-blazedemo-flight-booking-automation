package com.blazedemo.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import com.blazedemo.driver.DriverFactory;

public class ScreenshotUtil {

	public static String captureScreenshot(String name) {
		File screenshotDirectory = new File(System.getProperty("user.dir"), "screenshots");
		if (!screenshotDirectory.exists()) {
			screenshotDirectory.mkdir();
			System.out.println("Screenshot directory created at: " + screenshotDirectory);
		}
		String fileName = "Screenshot_" + name + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date())	+ ".png";
		try {
			File source = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
			File destination = new File(screenshotDirectory, fileName);
			FileHandler.copy(source, destination);
			System.out.println("Screenshot captured.");
			return destination.getAbsolutePath();
		} catch (Exception e) {
			throw new RuntimeException("Failed to save screenshot. Exception occured: " + e.getMessage());
		}
	}
}