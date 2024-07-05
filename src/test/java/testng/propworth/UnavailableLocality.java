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

public class UnavailableLocality extends Page {
	@SuppressWarnings("static-access")
	@BeforeClass
	public void setData() {
		Provider.file("propworth").sheet("unavailableLocality");
	}

	@BeforeMethod
	public void setDetails(ITestResult result) {
		Details.bridge(result).description("Calculate property with unavailable locality")
				.name("Prop Worth Unavailable Locality").device(Property.browser())
				.category("PropWorth", "Negative", "Unit", "Input");
	}

	@Test(dataProvider = "data", dataProviderClass = Provider.class, priority = 1)
	public void propertyWorthValidData(DataChunk chunk) {
		Step steps = new Step(SS, chunk);
		steps.add((data) -> {
			view("/propworth/Agra/");
		}, "User is on propworth page");
		steps.add((data) -> {
			propworthPage.enterLocKeyword(data[0]);
		}, "User enters locality key ^", "loc_key");
		steps.add((data) -> {
			propworthPage.selectLocality(data[0]);
		}, "User selects locality ^", "locality");
		steps.add((data) -> {
			propworthPage.verifyUnavailableLocError(data[0]);
		}, "User error message ^ is displayed", "err_msg");
		steps.exec();
	}
}
