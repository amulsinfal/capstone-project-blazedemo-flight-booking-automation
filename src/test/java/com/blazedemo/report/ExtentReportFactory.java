package com.blazedemo.report;

import com.aventstack.extentreports.ExtentTest;

public class ExtentReportFactory {
	
	public static final ThreadLocal<ExtentTest> tlTest = new ThreadLocal<ExtentTest>();

	public static ExtentTest getExtentTest() {
		return tlTest.get();
	}
	
	public static void setExtentTest(ExtentTest extentTest) {
		tlTest.set(extentTest);
	}
	
	public static void removeExtentTest() {
		tlTest.remove();
	}

}
