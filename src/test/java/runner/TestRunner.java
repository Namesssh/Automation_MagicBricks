package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/features" }, glue = { "stepDefs" }, plugin = { "pretty",
		"json:target/cucumber-reports/cucumber-report.json",
		"html:target/cucumber-reports/cucumber-report.html", }, monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {
}
