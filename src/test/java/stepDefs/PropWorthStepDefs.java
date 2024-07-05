package stepDefs;

import factory.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PropWorthStepDefs extends Page {

	@Given("^User is on propworth page$")
	public void user_is_on_propworth_page() {
		view("/propworth/Agra/");
	}

	@Given("^User enters locality key \"([^\"]*)\"$")
	public void user_enters_locality_key(String key) {
		propworthPage.enterLocKeyword(key);
	}

	@And("^Selects locality \"([^\"]*)\"")
	public void selects_locality(String locality) {
		propworthPage.selectLocality(locality);
	}

	@And("^Selects propert type \"([^\"]*)\" and bhk label \"([^\"]*)\"$")
	public void selects_propert_type_and_bhk_label_(String proptype, String bhklabel) {
		propworthPage.selectPropType(proptype);
		propworthPage.selectBHKLabel(bhklabel);
	}

	@And("^Selects super area \"([^\"]*)\" and floor \"([^\"]*)\"$")
	public void selects_super_area_and_floor(String suparea, String floor) {
		propworthPage.selectSuperArea(suparea);
		propworthPage.selectFloor(floor);
	}

	@And("^Selects open parking \"([^\"]*)\" and covered parking \"([^\"]*)\"$")
	public void selects_open_parking_and_covered_parking(String openpark, String covpark) {
		propworthPage.selectParking(openpark, covpark);
	}

	@When("^User click on Check Now button$")
	public void user_click_on_check_now_button() {
		propworthPage.clickCheckNow();
	}

	@Then("^User property worth is displayed$")
	public void user_should_be_presented_with_result() {
		propworthPage.verifyPropDetails();
	}

	@When("^User selects locality \"([^\"]*)\"$")
	public void user_selects_locality(String locality) {
		propworthPage.selectLocality(locality);
	}

	@Then("^Unavailability error message \"([^\"]*)\" is displayed$")
	public void user_error_message_is_displayed(String errmsg) {
		propworthPage.verifyUnavailableLocError(errmsg);
	}

	@When("^User clicks on send icon button$")
	public void user_clicks_on_send_icon_button() {
		propworthPage.clickOnSendButton();
	}

	@Then("^Invalid key error message \"([^\"]*)\" is displayed$")
	public void invalid_error_key_error_message_something_is_displayed(String errmsg) {
		propworthPage.verifyInvalidLocKeyError(errmsg);
	}

	@Then("^Area error message \"([^\"]*)\" is displayed$")
	public void area_error_message_something_is_displayed(String errmsg) {
		propworthPage.verifyInvalidAreaError(errmsg);
	}

}
