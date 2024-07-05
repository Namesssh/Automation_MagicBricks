package util.extent;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import provider.Property;

public class ExtentManager {

	public static final ExtentReports extentReports = new ExtentReports();

	public synchronized static ExtentReports createExtentReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter("./target/testng-reports/extent-report.html");
		try {
			reporter.loadXMLConfig(Property.extentXML());
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("System", Property.get("system"));
		extentReports.setSystemInfo("Author", Property.get("author"));
		extentReports.setSystemInfo("Browser", Property.browser());
		return extentReports;
	}

}
