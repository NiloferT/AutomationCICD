package rahulshettyacademy.ExtentReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportDemo {
	
	ExtentReports extent;
	
@BeforeTest
public void config() {
	
	//gets the path to the project up till extent report class
	String path = System.getProperty("user.dir") + "\\reports\\index.html";
	
	//ExtentSparkReporter is a helper class that helps with some configurations
	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	reporter.config().setReportName("Web Automation Results");
	reporter.config().setDocumentTitle("Test Results");
	
	//this is the main class to which ExtentSparkReporter will attach its object
	extent = new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "Nilo");
	
}
	
@Test

public void initialDemo() {
	
	//start monitoring 
	ExtentTest test = extent.createTest("Initial Demo");
	
	WebDriver driver = new ChromeDriver();
	driver.get("https://rahulshettyacademy.com");
	System.out.println(driver.getTitle());
	
	driver.close();
	
	//failed test report
	test.fail("Results do not match");
	
	//notify that test is done and stop monitoring and generate a report
	extent.flush();
}
	

}