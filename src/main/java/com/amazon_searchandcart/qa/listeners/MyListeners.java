package com.amazon_searchandcart.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.amazon_searchandcart.qa.utils.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

public class MyListeners implements ITestListener {
	ExtentReports extentReports;
	ExtentTest extentTest;
	
	
	
	
	
	
	
	
	
	@Override
	public void onStart(ITestContext context) {
		
		
		 extentReports=ExtentReporter.generateExtentReport();
		
	}
	
	
	
	
	
	

	
	

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName=result.getName();
		
		extentTest=extentReports.createTest(testName);
		
		
		WebDriver driver = null;

		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		 if (driver != null) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\Screenshots\\"+testName+".png");
		try {
			Files.copy(src, trg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 

		extentTest.addScreenCaptureFromPath(trg.getAbsolutePath());
		extentTest.log(Status.INFO, testName+" got Successfully Executed ");
		
		
		
		 }
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName=result.getName();
		
		
		
		WebDriver driver = null;

		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		 if (driver != null) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\Screenshots\\"+testName+".png");
		try {
			Files.copy(src, trg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 

		extentTest.addScreenCaptureFromPath(trg.getAbsolutePath());
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,result.getName()+" got failed");

		
	}
	}
	
	
	
	
	

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName=result.getName();
		

		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+" got skipped");

	}

	
	
	
	@Override
	public void onFinish(ITestContext context) {
        
		
		
		
		
		extentReports.flush();

		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	

}
