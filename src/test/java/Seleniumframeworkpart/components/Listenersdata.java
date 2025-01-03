package Seleniumframeworkpart.components;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Seleniumframeworkpart.resources.ExtentreporterNG;

public class Listenersdata extends Basetest implements ITestListener{

	ExtentTest test;
	ExtentReports extent=	ExtentreporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	
	@Override
    public void onTestStart(ITestResult result) {  //result store all inforation related to test that will going to execution
		test=extent.createTest(result.getMethod().getMethodName());  //to create and register a new test in the report.
		extentTest.set(test);
	}

    @Override
    public void onTestSuccess(ITestResult result) {
     List<String> re=extent.getReport().getLogs(); 
     System.out.println(re);
}

    @Override
    public void onTestFailure(ITestResult result) {
    	//handling test case failure from  here
    	extentTest.get().fail(result.getThrowable());
        
        //handling screen shot from here
        try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());  //get driver from listners bcz listners have data about test
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        String filePath=null;
      try {
		 filePath=  getScreenShot(result.getMethod().getMethodName(),driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }
    
    @Override
    public void onFinish(ITestContext suite) {
    	extent.flush(); // used to write or push all the test information, logs, and screenshots collected during the test execution to the report file
    }

    
	
}
