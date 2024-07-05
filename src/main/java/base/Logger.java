package base;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Media;

import util.extent.ExtentTestManager;

public class Logger {
	private ExtentTest initTest() {
		return ExtentTestManager.getTest();
	}

	public void info(String msg, Media media) {
		ExtentTest test = initTest();
		test.info(msg, media);
	}

	public void info(String msg) {
		info(msg, null);
	}
}
