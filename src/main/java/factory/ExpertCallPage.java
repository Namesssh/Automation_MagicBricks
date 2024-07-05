package factory;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.Base;

public class ExpertCallPage extends Base {

	public ExpertCallPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userName")
	private WebElement userNameField;

	@FindBy(id = "myListingValsR")
	private WebElement countryCodeField;

	@FindBy(id = "phoneNumber")
	private WebElement phoneNumberField;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailField;

	@FindBy(xpath = "//input[@name='keyword']")
	private WebElement cityKeyField;

	@FindBy(id = "keyword_suggest")
	private WebElement citySuggestionList;

	@FindBys({ @FindBy(css = "#keyword_suggest div") })
	private List<WebElement> cityTiles;

	@FindBy(id = "query")
	private WebElement queryField;

	@FindBy(id = "chk-TNC")
	private WebElement agreementCheckbox;

	@FindBy(id = "callBackSubmit")
	private WebElement submitButton;

	@FindBy(id = "thankReq")
	private WebElement confirmationBlock;

	@FindBys({ @FindBy(xpath = "//div[@id='thankReq']//p[@class='thxHeading']") })
	private List<WebElement> thankYouMsg;

	@FindBys({ @FindBy(xpath = "//div[@class='errorMsgBlock']") })
	private List<WebElement> fieldErrors;

	public void enterUserName(String name) {
		userNameField.sendKeys(name);
	}

	public void selectCountryCode(String code) {
		Select cc = new Select(countryCodeField); // Country code select tag
		cc.selectByVisibleText(code);
	}

	public void enterPhoneNumber(String number) {
		phoneNumberField.sendKeys(number);
	}

	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}

	public void enterCityKey(String key) {
		cityKeyField.sendKeys(key);
	}

	public void selectCity(String city) {
		Wait.untilVisible(citySuggestionList);
		click(getElementWithText(cityTiles, city));
	}

	public void enterQuery(String query) {
		queryField.sendKeys(query);
	}

	public void clickOnAgreementCheckbox() {
		agreementCheckbox.sendKeys(Keys.SPACE);
	}

	public void clickOnSubmitBtn() {
		submitButton.sendKeys(Keys.ENTER);
	}

	public void verifyCallBooked() {
		Verify.isPresent(thankYouMsg);
	}

	public void verifyError(String error) {
		WebElement element = getElementWithText(fieldErrors, error);
		Verify.isDisplayed(element);
	}
}
