package stepDefs;

import factory.Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NavigationStepDefs extends Page {

	@Given("^User is on home page$")
	public void user_is_on_home_page() {

	}

	@When("^User hover \"([^\"]*)\" navigation link and clicks \"([^\"]*)\" dropdown link$")
	public void user_hover_navigation_link_and_clicks_dropdown_link(String navlink, String drodownlink) {
		navigation.to(navlink, drodownlink);
	}

	@Then("^A webpage for \"([^\"]*)\" is displayed$")
	public void a_webpage_is_displayed(String dropdownlink) {
		navigation.verifyWebPageDisplayed();
	}
}
