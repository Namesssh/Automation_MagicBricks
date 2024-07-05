package util.listener;

import static util.extent.ExtentTestManager.getTest;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.Base;
import base.DataChunk;
import provider.Property;
import util.extent.ExtentManager;
import util.extent.ExtentTestManager;

public class TestListener extends Base implements ITestListener {
	private static int getTestIterationCount(ITestResult iTestResult) {
		int count = 0;
		Object[] params = iTestResult.getParameters();
		for (Object object : params) {
			if (object instanceof DataChunk) {
				DataChunk chunk = (DataChunk) object;
				count = chunk.index;
			}
		}
		return count;
	}

	private static String getTestName(ITestResult iTestResult) {
		Object name = iTestResult.getAttribute("name");
		String method = iTestResult.getMethod().getConstructorOrMethod().getName();
		String suffix = " #" + getTestIterationCount(iTestResult);
		if (name == null)
			return iTestResult.getMethod().getConstructorOrMethod().getName();
		else {
			if (method.toLowerCase().contains("invalid")) {
				return name + " [Invalid Data] " + " [Iteration" + suffix + "] ";
			} else if (method.toLowerCase().contains("valid")) {
				return name + " [Valid Data] " + " [Iteration" + suffix + "] ";
			} else {
				return name + " [Iteration" + suffix + "] ";
			}
		}
	}

	private static String getTestDescription(ITestResult iTestResult) {
		Object description = iTestResult.getAttribute("description");
		if (description == null)
			return "Test";
		else
			return description.toString();
	}

	@Override
	public void onStart(ITestContext iTestContext) {
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		ExtentManager.extentReports.flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		ExtentTestManager.startTest(getTestName(iTestResult), getTestDescription(iTestResult));
		ExtentTest test = ExtentTestManager.getTest();
		test.assignAuthor(Property.get("author"));
		test.assignDevice((String) iTestResult.getAttribute("device"));
		test.assignCategory((String[]) iTestResult.getAttribute("category"));
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		getTest().log(Status.PASS, "Successfully " + getTestDescription(iTestResult).toLowerCase());
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		getTest().log(Status.FAIL, "Couldn't " + getTestDescription(iTestResult).toLowerCase(), SS.ngStep());
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		getTest().log(Status.PASS, "Skipped " + getTestDescription(iTestResult).toLowerCase());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	}
}
