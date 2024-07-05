package testng.expertcall;

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
		Provider.file("expertcall").sheet("valid");
	}

	@BeforeMethod
	public void setDetails(ITestResult result) {
		Details.bridge(result).description("Book an expert call with valid data").name("Book Expert Call")
				.device(Property.browser()).category("Functional", "Functionality", "Positive", "ExpertCall", "Select",
						"Radio", "Input", "Textarea");
	}

	@Test(dataProvider = "data", dataProviderClass = Provider.class)
	public void expertCallWithValidData(DataChunk chunk) {
		Step steps = new Step(SS, chunk);
		steps.add((data) -> {
			view("/bricks/advertise-with-us");
		}, "User is on Advertisement Page");
		steps.add((data) -> {
			expertCallPage.enterUserName(data[0]);
		}, "User enters ^ name", "user_name");
		steps.add((data) -> {
			expertCallPage.selectCountryCode(data[0]);
		}, "Selects ^ country code", "c_code");
		steps.add((data) -> {
			expertCallPage.enterPhoneNumber(data[0]);
		}, "Enters ^ mobile number", "mob_num");
		steps.add((data) -> {
			expertCallPage.enterEmail(data[0]);
		}, "Enters ^ email", "email");
		steps.add((data) -> {
			expertCallPage.enterCityKey(data[0]);
		}, "Enters ^ city key", "city_key");
		steps.add((data) -> {
			expertCallPage.selectCity(data[0]);
		}, "Selects ^ city", "city");
		steps.add((data) -> {
			expertCallPage.enterQuery(data[0]);
		}, "Enter ^ query", "query");
		steps.add((data) -> {
			expertCallPage.clickOnAgreementCheckbox();
		}, "Clicks on agreenment checkbox");
		steps.add((data) -> {
			expertCallPage.clickOnSubmitBtn();
		}, "User clicks on Get A Callback button");
		steps.add((data) -> {
			expertCallPage.verifyCallBooked();
		}, "Thank you message is displayed");
		steps.exec();
	}
}
