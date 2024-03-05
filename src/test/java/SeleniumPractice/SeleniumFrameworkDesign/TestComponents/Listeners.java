package SeleniumPractice.SeleniumFrameworkDesign.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SeleniumPractice.SeleniumFrameworkDesign.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent= ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	public void onTestStart(ITestResult result) {
	    // not implemented
		test= extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);  //assigns unique thread id
	  }

	 
	public void onTestSuccess(ITestResult result) {
	    // not implemented
		
		extentTest.get().log(Status.PASS, "Test Passed");
	  }

	  
	public void onTestFailure(ITestResult result) {
	    // not implemented
		//Screenshot
		//test.log(Status.FAIL, "Test Failed");
		extentTest.get().fail(result.getThrowable());  //it will print the error message in the report
		try {
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		String filePath = null;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	  }

	  
	public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }

	 
	public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }

	 
	public void onStart(ITestContext context) {
	    // not implemented
	  }

	 
	public void onFinish(ITestContext context) {
	    // not implemented
		extent.flush();
	  }
	
	

}
