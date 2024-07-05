package stepDefs;

import factory.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ExpertCallStepDefs extends Page {

	@Given("^User is on Advertisement Page$")
	public void user_is_on_advertisement_page() {
		view("/bricks/advertise-with-us");
	}

	@Given("^User enters \"([^\"]*)\" name$")
	public void user_enters_name(String username) {
		expertCallPage.enterUserName(username);
	}

	@And("^Selects \"([^\"]*)\" country code$")
	public void selects_country_code(String ccode) {
		expertCallPage.selectCountryCode(ccode);
	}

	@And("^Enters \"([^\"]*)\" mobile number$")
	public void enters_phone_number(String number) {
		expertCallPage.enterPhoneNumber(number);
	}

	@And("^Enters \"([^\"]*)\" email$")
	public void enters_email(String email) {
		expertCallPage.enterEmail(email);
	}

	@And("^Enters \"([^\"]*)\" city key$")
	public void enters_city_key(String citykey) {
		expertCallPage.enterCityKey(citykey);
	}

	@And("^Selects \"([^\"]*)\" city$")
	public void selects_city(String city) {
		expertCallPage.selectCity(city);
	}

	@And("^Enter \"([^\"]*)\" query$")
	public void enter_query(String query) {
		expertCallPage.enterQuery(query);
	}

	@And("^Clicks on agreenment checkbox$")
	public void clicks_on_agreenment_checkbox() {
		expertCallPage.clickOnAgreementCheckbox();
	}

	@When("^User clicks on Get A Callback button$")
	public void user_clicks_on_get_a_callback_button() {
		expertCallPage.clickOnSubmitBtn();
	}

	@Then("^Thank you message is displayed$")
	public void success_message_is_displayed() {
		expertCallPage.verifyCallBooked();
	}

	// 2
	@Then("^Error message \"([^\"]*)\" is displayed for Username$")
	public void error_message_is_displayed(String errmsg) {
		expertCallPage.verifyError(errmsg);
	}

	// 3
	@Given("^User selects \"([^\"]*)\" country code$")
	public void user_enters_country_code(String ccode) {
		expertCallPage.selectCountryCode(ccode);
	}

	@Then("^Error message \"([^\"]*)\" is displayed for Mobile$")
	public void error_message_is_displayed_for_mobile(String errmsg) {
		expertCallPage.verifyError(errmsg);
	}

	// 4
	@Given("^User enters \"([^\"]*)\" email$")
	public void user_enters_something_email(String email) {
		expertCallPage.enterEmail(email);
	}

	@Then("^Error message \"([^\"]*)\" is displayed for Email$")
	public void error_message_something_is_displayed_for_email(String errmsg) {
		expertCallPage.verifyError(errmsg);
	}

	// 5
	@Then("^Error message \"([^\"]*)\" is displayed for Agree to Terms$")
	public void error_message_something_is_displayed_for_agree_to_terms(String errmsg) {
		expertCallPage.verifyError(errmsg);
	}

}
