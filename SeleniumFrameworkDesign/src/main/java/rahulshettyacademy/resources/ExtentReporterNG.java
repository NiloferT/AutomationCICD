package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		
		//gets the path to the project up till extent report class
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		
		//ExtentSparkReporter is a helper class that helps with some configurations
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		//this is the main class to which ExtentSparkReporter will attach its object
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Nilo");
		return extent;
	}

}
