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

public class ValidData extends Page {
	@SuppressWarnings("static-access")
	@BeforeClass
	public void setData() {
		Provider.file("propworth").sheet("valid");
	}

	@BeforeMethod
	public void setDetails(ITestResult result) {
		Details.bridge(result).description("Calculate property worth with valid data").name("Prop Worth")
				.device(Property.browser()).category("Functional", "Functionality", "Positive", "PropWorth", "List",
						"Select", "Radio", "NestedList");
	}

	@Test(dataProvider = "data", dataProviderClass = Provider.class, priority = 0)
	public void propertyWorthWithValidData(DataChunk chunk) {
		Step steps = new Step(SS, chunk);
		steps.add((data) -> view("/propworth/Agra/"), "Go to propworth page");
		steps.add((data) -> {
			propworthPage.enterLocKeyword(data[0]);
		}, "User enters locality key ^", "loc_key");
		steps.add((data) -> {
			propworthPage.selectLocality(data[0]);
		}, "Selects locality ^", "locality");
		steps.add((data) -> {
			propworthPage.selectPropType(data[0]);
			propworthPage.selectBHKLabel(data[1]);
		}, "Selects property type ^ and bhk label ^", "prop_type", "bhk_label");
		steps.add((data) -> {
			propworthPage.selectSuperArea(data[0]);
			propworthPage.selectFloor(data[1]);
		}, "Selects super area ^ and floor ^", "sup_area", "floor");
		steps.add((data) -> {
			propworthPage.selectParking(data[0], data[1]);
		}, "Selects open parking ^ and covered parking ^", "open_park", "cov_park");
		steps.add((data) -> {
			propworthPage.clickCheckNow();
		}, "User click on Check Now button");
		steps.add((data) -> {
			propworthPage.verifyPropDetails();
		}, "User should be presented property worth");
		steps.exec();
	}
}
