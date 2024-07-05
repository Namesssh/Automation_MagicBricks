package testng.propworth;

import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.DataChunk;
import factory.Page;
import provider.Property;
import provider.Provider;
import util.step.Step;

public class InvalidKey extends Page {
	@SuppressWarnings("static-access")
	@BeforeClass
	public void setData() {
		Provider.file("propworth").sheet("invalidKey");
	}

	@BeforeMethod
	public void setDetails(ITestResult result) {
		Details.bridge(result).description("Calculate property worth with invalid locality key")
				.name("Prop Worth Locality Key").device(Property.browser()).category("Negative", "PropWorth", "Unit");
	}

	@Test(dataProvider = "data", dataProviderClass = Provider.class, priority = 2)
	public void propertyWorthInvalidData(DataChunk chunk) {
		Step steps = new Step(SS, chunk);
		steps.add((data) -> {
			view("/propworth/Agra/");
		}, "User is on propworth page");
		steps.add((data) -> {
			propworthPage.enterLocKeyword(data[0]);
		}, "User enters locality key ^", "loc_key");
		steps.add((data) -> {
			propworthPage.clickOnSendButton();
		}, "User clicks on send icon button");
		steps.add((data) -> {
			propworthPage.verifyInvalidLocKeyError(data[0]);
		}, "Invalid key error message ^ is displayed", "err_msg");
		steps.exec();
	}
}
