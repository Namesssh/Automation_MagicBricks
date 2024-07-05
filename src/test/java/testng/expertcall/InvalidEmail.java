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

public class InvalidEmail extends Page {
	@SuppressWarnings("static-access")
	@BeforeClass
	public void setData() {
		Provider.file("expertcall").sheet("invalidEmail");
	}

	@BeforeMethod
	public void setDetails(ITestResult result) {
		Details.bridge(result).description("Book an expert call with invalid email").name("Book Expert Call Email")
				.device(Property.browser()).category("Negative", "ExpertCall", "Unit", "Input");
	}

	@Test(dataProvider = "data", dataProviderClass = Provider.class)
	public void expertCallWithInvalidData(DataChunk chunk) {
		Step steps = new Step(SS, chunk);
		steps.add((data) -> {
			view("/bricks/advertise-with-us");
		}, "User is on Advertisement Page");
		steps.add((data) -> {
			expertCallPage.enterUserName(data[0]);
		}, "User enters ^ email", "email");
		steps.add((data) -> {
			expertCallPage.clickOnSubmitBtn();
		}, "User clicks on Get A Callback button");
		steps.add((data) -> {
			expertCallPage.verifyCallBooked();
		}, "Error message ^ is displayed for Email", "err_msg");
		steps.exec();
	}
}
