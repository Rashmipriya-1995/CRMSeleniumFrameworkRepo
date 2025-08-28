package com.crm.generic.listenerUtility;

import java.time.LocalDateTime;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.baseclasstest.BaseClass;

public class ListenerUtility implements ITestListener, ISuiteListener {

	public static ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) { // before executing every test case it will execute
		test = report.createTest(result.getMethod().getMethodName());// it will insert testcase in extent report
		test.log(Status.INFO, result.getMethod().getMethodName() + "---Executed---");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("------" + result.getMethod().getMethodName() + "---End----");
		test.log(Status.PASS, result.getMethod().getMethodName() + "---Completed---");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test.log(Status.FAIL, "Test Script is failed");

		TakesScreenshot ts = (TakesScreenshot) BaseClass.driver;
		String filepath = ts.getScreenshotAs(OutputType.BASE64);

		String time = LocalDateTime.now().toString().replace(":", "_");
		test.addScreenCaptureFromBase64String(filepath, testName + time); // we can get screenshot for each step

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		String time = new Date().toString().replace("", "_").replace(":", "_");

		spark = new ExtentSparkReporter("./AdvanceReport/ExtentReport_" + time + ".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("Browser", "Chrome");

	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
		report.flush();

	}

}
