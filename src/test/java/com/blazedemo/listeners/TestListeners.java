package com.blazedemo.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.blazedemo.report.ExtentReportFactory;
import com.blazedemo.report.ExtentReportManager;
import com.blazedemo.utils.ScreenshotUtil;

public class TestListeners implements ITestListener {
	public static ExtentReports report;
	private String methodName = null;
	private String className = null;

	@Override
	public void onTestStart(ITestResult result) {
		className = result.getTestClass().getRealClass().getName();
		methodName = result.getMethod().getMethodName();
		ExtentTest test = report.createTest(className + " :: " + methodName);
		System.out.println("Execution of '" + methodName + "' test started.");
		ExtentReportFactory.setExtentTest(test);
		ExtentReportFactory.getExtentTest().log(Status.INFO, "Execution of '" +methodName+ "' test started.");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		methodName = result.getMethod().getMethodName();
		System.out.println("Execution of '" + methodName + "' test passed.");
		Markup markup = MarkupHelper.createLabel("Test case \"" +methodName+ "\" execution passed.",
				ExtentColor.GREEN);
		ExtentReportFactory.getExtentTest().log(Status.PASS, markup);
		ExtentReportFactory.removeExtentTest();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		methodName = result.getMethod().getMethodName();
		String screenshotPath = ScreenshotUtil.captureScreenshot(methodName);
		System.out.println("Execution of '" + methodName + "' test failed.");
		System.out.println("Screenshot saved: " + screenshotPath);
		System.out.println("Test failure reason: " + result.getThrowable());
		Markup markup = MarkupHelper.createLabel("Test case \"" + methodName + "\" execution failed.",
		ExtentColor.RED);
		ExtentReportFactory.getExtentTest().log(Status.FAIL, "Screenshot Captured.",
				MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		ExtentReportFactory.getExtentTest().log(Status.FAIL, markup);
		ExtentReportFactory.getExtentTest().log(Status.INFO, "Screenshot saved: " + screenshotPath);
		ExtentReportFactory.getExtentTest().log(Status.FAIL, "Test failure reason: "+result.getThrowable());
		ExtentReportFactory.removeExtentTest();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		methodName = result.getMethod().getMethodName();
		System.out.println("Execution of '" + methodName + "' test skipped.");
		System.out.println("Test skip reason: " + result.getThrowable());
		Markup markup = MarkupHelper.createLabel("Test case \"" + methodName + "\" execution skipped.",
				ExtentColor.ORANGE);
		ExtentReportFactory.getExtentTest().log(Status.SKIP, markup);
		ExtentReportFactory.getExtentTest().log(Status.SKIP, "Test skip reason: " + result.getThrowable());
		ExtentReportFactory.removeExtentTest();
	}

	@Override
	public void onStart(ITestContext context) {
		report = ExtentReportManager.setupExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
		System.out.println("ExtentReport generated: "+ExtentReportManager.extentReportFile.getAbsolutePath());
	}
}