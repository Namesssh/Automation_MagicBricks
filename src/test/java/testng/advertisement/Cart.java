package testng.advertisement;

import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.DataChunk;
import factory.Page;
import provider.Property;
import provider.Provider;
import util.step.Step;

public class Cart extends Page {
	@SuppressWarnings("static-access")
	@BeforeClass
	public void setData() {
		Provider.file("advertisement").sheet("cart");
	}

	@BeforeMethod
	public void setDetails(ITestResult result) {
		Details.bridge(result).description("Advertise your property on magicbricks").name("Property Advertisement")
				.device(Property.browser())
				.category("Functional", "Positive", "Advertisement", "Radio", "List", "MultiWindow");
	}

	@Test(dataProvider = "data", dataProviderClass = Provider.class)
	public void advertisementAsIndividual(DataChunk chunk) {
		Step steps = new Step(SS, chunk);
		steps.add((data) -> {
			view("/bricks/advertise-with-us");
		}, "User is on Advertisement Page");
		steps.add((data) -> {
			advertisementPage.selectUserType(data[0]);
		}, "User selects I am a ^", "i_am");
		steps.add((data) -> {
			advertisementPage.selectLookingTo(data[0]);
		}, "User selects I am looking to ^", "looking_to");
		steps.add((data) -> {
			advertisementPage.selectPropertyType(data[0]);
		}, "Selects My Property is ^", "prop_type");
		steps.add((data) -> {
			advertisementPage.clickOnShowPkgBtn();
		}, "Clicks on Show Me Packages button");
		steps.add((data) -> {
			advertisementPage.selectPackage(data[0]);
		}, "Selects ^ package", "pkg");
		steps.add((data) -> {
			advertisementPage.clickOnAddToCartBtn();
		}, "Click on Add to My Orders button");
		steps.add((data) -> {
			advertisementPage.verifyPackageName(data[0]);
		}, "^ details are displayed", "pkg");
		steps.add((data) -> {
			advertisementPage.verifyCartItemCount(data[0]);
		}, "Cart item count is ^", "cart_count");
		steps.exec();
	}
}
