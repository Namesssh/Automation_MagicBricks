package base;

import org.testng.ITestResult;

public class TestDetails {
	private ITestResult testResult;

	public TestDetails bridge(ITestResult result) {
		testResult = result;
		return this;
	}

	public TestDetails name(String value) {
		testResult.setAttribute("name", value);
		return this;
	}

	public TestDetails description(String value) {
		testResult.setAttribute("description", value);
		return this;
	}

	public TestDetails device(String value) {
		testResult.setAttribute("device", value);
		return this;
	}

	public TestDetails category(String... value) {

		testResult.setAttribute("category", value);
		return this;
	}
}
