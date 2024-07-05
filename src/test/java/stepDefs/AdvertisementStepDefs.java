package stepDefs;

import factory.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdvertisementStepDefs extends Page {

	@Given("^User selects I am a \"([^\"]*)\"$")
	public void user_selects_i_am_a(String iam) {
		advertisementPage.selectUserType(iam);
	}

	@When("^User selects I am looking to \"([^\"]*)\"$")
	public void user_selects_i_am_looking_to(String lookingto) {
		advertisementPage.selectLookingTo(lookingto);
	}

	@And("^Selects My Property is \"([^\"]*)\"$")
	public void selects_my_property_is(String proptype) {
		advertisementPage.selectPropertyType(proptype);
	}

	@And("^Clicks on Show Me Packages button$")
	public void clicks_on_show_me_packages_button() {
		advertisementPage.clickOnShowPkgBtn();
	}

	@Then("^Advertisement packages are displayed$")
	public void advertisement_packages_are_displayed() {
		advertisementPage.verifyPkgsDisplayed();
	}

	// 2
	@When("^User selects dealing in \"([^\"]*)\"$")
	public void user_selects_dealing_in(String dealingin) {
		advertisementPage.selectPropertyType(dealingin);
	}

	// 3
	@And("^Selects \"([^\"]*)\" package$")
	public void selects_package(String pkg) {
		advertisementPage.selectPackage(pkg);
	}

	@And("^Click on Add to My Orders button$")
	public void click_on_add_to_my_orders_button() {
		advertisementPage.clickOnAddToCartBtn();
	}

	@Then("^\"([^\"]*)\" details are displayed$")
	public void package_details_are_displayed(String pkg) {
		advertisementPage.verifyPackageName(pkg);
	}

	@And("^Cart item count is \"([^\"]*)\"$")
	public void cart_item_count_is_something(String cartcount) {
		advertisementPage.verifyCartItemCount(cartcount);
	}

}
