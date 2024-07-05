package stepDefs;

import factory.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PostPropertyStepDefs extends Page {
	@Given("^User is on Post Property Launcher page$")
	public void user_is_on_property_posting_launcher_page() {
		navigation.to("Sell", "Post Property");
		String launcherTitle = "Post Free Property Ads | Rent & Sell Property Online";
		Switch.tab(() -> Driver.getTitle().trim().equals(launcherTitle));
	}

	@When("^User selects \"([^\"]*)\" user type$")
	public void user_selects_user_type(String usertype) {
		postPropertyPage.selectUserType(usertype);
	}

	@And("^Selects \"([^\"]*)\" intention$")
	public void selects_intention(String intent) {
		postPropertyPage.selectUserIntent(intent);
	}

	@And("^Enters \"([^\"]*)\" country code and \"([^\"]*)\" mobile number$")
	public void enters_country_code_and_mobile_number(String ccode, String mobile) {
		postPropertyPage.enterMobileNumber(ccode, mobile);
	}

	@And("^Clicks on Start Now button$")
	public void user_clicks_on_start_now_button() {
		postPropertyPage.clickOnStartNow();
	}

	@Then("^Post Property form is displayed$")
	public void property_posting_page_is_displyed() {
		postPropertyPage.verifyPostPropertyForm();
	}

	@Given("^User selects Owner user type$")
	public void user_selects_owner_user_type() {
		postPropertyPage.selectOwnerUserType();
	}

	@When("^User enters name \"([^\"]*)\"$")
	public void user_enters_name(String name) {
		postPropertyPage.enterUserName(name);
	}

	@And("^Enters email \"([^\"]*)\"$")
	public void enters_email(String email) {
		postPropertyPage.enterUserEmail(email);
	}

	@And("^Click on Login & Post Property button$")
	public void click_on_login_post_property_button() {
		postPropertyPage.clickOnLoginAndPostBtn();
	}

	@Then("^Country code \"([^\"]*)\" and mobile number \"([^\"]*)\" is pre-filled$")
	public void mobile_number_and_country_code_is_prefilled(String code, String number) {
		postPropertyPage.verifyPrefilledMobile(code, number);
	}

	@And("^\"([^\"]*)\" errors are displayed for \"([^\"]*)\"$")
	public void errors_isare_displayed_for_personal_details(String errcount, String section) {
		postPropertyPage.verifyErrors(section, Integer.parseInt(errcount));
	}

	@Given("^User selects \"([^\"]*)\" property intent$")
	public void user_selects_property_intent(String propintent) {
		postPropertyPage.selectPropertyIntent(propintent);
	}

	@When("^Selects \"([^\"]*)\" property type$")
	public void selects_property_type(String proptype) {
		postPropertyPage.selectPropertyType(proptype);
	}

	@When("^User selects \"([^\"]*)\" bedrooms$")
	public void user_selects_bedrooms(String bed) {
		postPropertyPage.selectBedroom(bed);
	}

	@And("^Selects \"([^\"]*)\" balconies$")
	public void selects_balconies(String balc) {
		postPropertyPage.selectBalcony(balc);
	}

	@And("^Selects \"([^\"]*)\" floor number$")
	public void selects_floor_number(String floornum) {
		postPropertyPage.selectFloor(floornum);
	}

	@And("^Selects \"([^\"]*)\" floors$")
	public void selects_floors(String floors) {
		postPropertyPage.selectTotalFloors(floors);
	}

	@And("^Selects \"([^\"]*)\" furnished$")
	public void selects_furnished(String furn) {
		postPropertyPage.selectFurnished(furn);
	}

	@And("^Selects \"([^\"]*)\" bathroom$")
	public void selects_bathroom(String bath) {
		postPropertyPage.selectBathroom(bath);
	}

	@And("^Selects \"([^\"]*)\" image upload tab$")
	public void selects_image_upload_tab(String tab) {
		postPropertyPage.selectImageUploadTab(tab);
	}

	@When("^User uploads \"([^\"]*)\" image \"([^\"]*)\" times$")
	public void user_uploads_image_times(String img, String count) {
		postPropertyPage.uploadImages(img, Integer.parseInt(count));
	}

	@Then("^\"([^\"]*)\" images are uploaded in \"([^\"]*)\" category$")
	public void images_are_uploaded_in_category(String count, String catg) {
		postPropertyPage.verifyImageUploaded(catg, Integer.parseInt(count));
	}

	@When("^Selects \"([^\"]*)\" possession status$")
	public void selects__possession_status(String status) {
		postPropertyPage.selectPossessionStatus(status);
	}

	@And("^Selects availability month \"([^\"]*)\" and year \"([^\"]*)\" or \"([^\"]*)\" construction age$")
	public void selects_availability_month__and_year_(String availm, String availy, String age) {
		if (age.isEmpty()) {
			postPropertyPage.selectAvailMonth(availm);
			postPropertyPage.selectAvailYear(availy);
		} else {
			postPropertyPage.selectConstructionAge(age);
		}
	}

	@Given("^Terms & Conditions checkbox is checked by default$")
	public void terms_conditions_checkbox_is_checked_by_default() {
		postPropertyPage.verifyTermsCheckboxChecked();
	}

	@When("^User unchecks Terms & Conditions checkbox$")
	public void user_unchecks_terms_conditions_checkbox() {
		postPropertyPage.uncheckTermCheckbox();
	}

	@Then("^Terms & Conditions error is displayed$")
	public void terms_conditions_error_is_displayed() {
		postPropertyPage.verifyTermsError();
	}

	@And("^Selects \"([^\"]*)\" property intent$")
	public void selects_property_intent(String intent) {
		postPropertyPage.selectPropertyIntent(intent);
	}

	@And("^Selects \"([^\"]*)\" bedrooms$")
	public void selects_bedrooms(String bed) {
		postPropertyPage.selectBedroom(bed);
	}

	@And("^Unchecks exclusive and whatsapp checkbox$")
	public void unchecks_exclusive_and_whatsapp_checkbox() {
		postPropertyPage.unselectExclusiveAndWhatsapp();
	}

	@And("^Uploads \"([^\"]*)\" image \"([^\"]*)\" times$")
	public void uploads_image_times(String img, String count) {
		postPropertyPage.uploadImages(img, Integer.parseInt(count));
	}

	@Then("^OTP verification form is displayed$")
	public void otp_verification_form_is_displayed() {
		postPropertyPage.verifyOTPisDisplayed();
	}
}
