package com.blazedemo.report;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.blazedemo.utils.ConfigReader;

public class ExtentReportManager {
	public static ExtentReports extentReport;
	public static File extentReportFile;
	
	public static ExtentReports setupExtentReport() {
		File reportsDirectory = new File(System.getProperty("user.dir"), "reports");
		
		if (!reportsDirectory.exists()) {
			reportsDirectory.mkdir();
			System.out.println("Screenshot directory created at: " + reportsDirectory);
		}
		
		String fileName = "ExecutionReport_ " + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) +
				".html";
		extentReportFile = new File(reportsDirectory, fileName);

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		sparkReporter.config().setReportName("Blazedemo Automation Execution Report");
		sparkReporter.config().setDocumentTitle("Blazedemo testcase execution report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");

		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Browser under test", ConfigReader.getValue("browser"));
		extentReport.setSystemInfo("App. under test", ConfigReader.getValue("url"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		
		return extentReport;
	}
}
