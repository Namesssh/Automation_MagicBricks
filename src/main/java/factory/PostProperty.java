package factory;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.Base;

public class PostProperty extends Base {
	public PostProperty(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "ownerISD")
	private WebElement countryCode;

	@FindBy(id = "ownerMobile")
	private WebElement mobileNumber;

	@FindBy(xpath = "//button[normalize-space()='Start Now']")
	private WebElement startNowBtn;

	@FindBys(@FindBy(id = "postPropertyForm"))
	private List<WebElement> postPropertyForm;

	@FindBy(id = "iamO")
	private WebElement ownerUserType;

	@FindBy(id = "name")
	private WebElement userName;

	@FindBy(id = "isd1")
	private WebElement countryCode2;

	@FindBy(id = "mobileNo")
	private WebElement mobileNumber2;

	@FindBy(id = "email")
	private WebElement userEmail;

	@FindBy(id = "buttonLogin")
	private WebElement submitButton;

	@FindBy(id = "propertyType")
	private WebElement propertyType;

	@FindBys(@FindBy(xpath = ""))
	private List<WebElement> a;

	@FindBys(@FindBy(css = "#bedroomsDiv li:not(:last-child)"))
	private List<WebElement> bedroom;

	@FindBys(@FindBy(css = "#bedroomsDiv li:last-child ol"))
	private List<WebElement> bedroomSub;

	@FindBys(@FindBy(css = "#balconiesDiv li:not(:last-child)"))
	private List<WebElement> balconies;

	@FindBys(@FindBy(css = "#balconiesDiv li:last-child ol"))
	private List<WebElement> balconiesSub;

	@FindBys(@FindBy(css = "#FloorNoDiv li:not(:last-child)"))
	private List<WebElement> floor;

	@FindBys(@FindBy(css = "#FloorNoDiv li:last-child ol"))
	private List<WebElement> floorSub;

	@FindBys(@FindBy(css = "#totalFloor li:not(:last-child)"))
	private List<WebElement> totalFloor;

	@FindBys(@FindBy(css = "#totalFloor li:last-child ol"))
	private List<WebElement> totalFloorSub;

	@FindBys(@FindBy(css = "#bathroomsDiv li:not(:last-child)"))
	private List<WebElement> bathroom;

	@FindBys(@FindBy(css = "#bathroomsDiv li:last-child ol"))
	private List<WebElement> bathroomSub;

	@FindBys(@FindBy(css = "#furnishedDiv li"))
	private List<WebElement> furnished;

	@FindBy(id = "fileupload")
	private WebElement imageField;

	@FindBys(@FindBy(css = ".uploadImgTab a"))
	private List<WebElement> imageUploadTabs;

	@FindBy(xpath = "//div[@id='exteriorViewImage_Id']//div[@id='allData']//li")
	private WebElement exteriorImages;

	@FindBys(@FindBy(xpath = "//label[contains(@for,'possessionStatus')]"))
	private List<WebElement> possessionStatus;

	@FindBy(id = "availFromMonthVal")
	private WebElement availMonthBtn;

	@FindBy(id = "availFromYearVal")
	private WebElement availYearBtn;

	@FindBys(@FindBy(css = "#availFromMonth + div.unitBlockAreaDropDown li"))
	private List<WebElement> availMonthSelect;

	@FindBys(@FindBy(css = "#availFromYear ~ div.unitBlockAreaDropDown li"))
	private List<WebElement> availYearSelect;

	@FindBy(id = "ageofconsVal")
	private WebElement constructionAgeBtn;

	@FindBys(@FindBy(css = "#ageofcons ~ div.unitBlockAreaDropDown li"))
	private List<WebElement> constructionAgeSelect;

	@FindBy(id = "termsAndConditions")
	private WebElement termsCheckbox;

	@FindBy(id = "termsAndConditionsError")
	private WebElement termsErr;

	@FindBys(@FindBy(xpath = "//*[@id='termsAndConditions']//parent::span[contains(@class,'checked')]"))
	private List<WebElement> termsAssertion;

	@FindBy(id = "isexclusive")
	private WebElement exclusiveCheckbox;

	@FindBy(id = "whatsappopt")
	private WebElement whatsappCheckbox;

	@FindBy(id = "listingFrame")
	private WebElement verifyOTPIframe;

	@FindBy(id = "verifyOtpDiv")
	private WebElement verifyOtpDiv;

	private List<WebElement> getSectionErrors(String section) {
		String xpath = "//*[@class='secHeading' and contains(.,'" + section
				+ "')]//following-sibling::div[contains(@class,'formEle')]//*[@class='formErr' and contains(@style,'display: block;')]";

		List<WebElement> elements = Driver.findElements(By.xpath(xpath));

		return elements;
	}

	public void selectUserType(String type) {
		WebElement element = Driver.findElement(By.xpath("//label[@for='" + type + "']"));
		Mouse.click(element);
	}

	public void selectUserIntent(String intent) {
		WebElement element = Driver.findElement(By.xpath("//label[@for='" + intent + "']"));
		Mouse.click(element);
	}

	public void enterMobileNumber(String code, String number) {
		Select select = new Select(countryCode);
		select.selectByVisibleText(code);
		mobileNumber.sendKeys(number);
	}

	public void clickOnStartNow() {
		click(startNowBtn);
	}

	public void verifyPostPropertyForm() {
		Verify.isPresent(postPropertyForm);
	}

	public void selectOwnerUserType() {
		Mouse.click(ownerUserType);
	}

	public void enterUserName(String name) {
		userName.sendKeys(name);
	}

	public void enterUserEmail(String email) {
		userEmail.sendKeys(email);
	}

	public void clickOnLoginAndPostBtn() {
		click(submitButton);
	}

	public void verifyPrefilledMobile(String code, String number) {
		Select select = new Select(countryCode2);
		Verify.textEqual(select.getFirstSelectedOption(), code);
		Assert.assertEquals(mobileNumber2.getAttribute("value"), number);
	}

	public void verifyErrors(String section, int count) {
		List<WebElement> errs = getSectionErrors(section);
		Verify.sizeEqual(errs, count);
	}

	public void selectPropertyIntent(String intent) {
		String xpath = "//input[@id='propertyFor" + intent.charAt(0) + "']";
		WebElement element = Driver.findElement(By.xpath(xpath));
		Mouse.click(element);
	}

	public void selectPropertyType(String type) {
		Select select = new Select(propertyType);
		select.selectByVisibleText(type);
	}

	public void selectBedroom(String value) {
		if (!value.isEmpty()) {
			Wait.untilVisible(bedroom);
			if (Integer.parseInt(value) >= 5) {
				bedroom.get(4).click();
				Wait.untilVisible(bedroomSub);
				click(getElementWithText(bedroomSub, value));
			} else {
				click(getElementWithText(bedroom, value));
			}
		}
	}

	public void selectBalcony(String value) {
		if (!value.isEmpty()) {
			Wait.untilVisible(balconies);
			if (Integer.parseInt(value) >= 4) {
				balconies.get(4).click();
				Wait.untilVisible(balconiesSub);
				click(getElementWithText(balconiesSub, value));
			} else {
				click(getElementWithText(balconies, value));
			}
		}
	}

	public void selectFloor(String value) {
		if (!value.isEmpty()) {
			Wait.untilVisible(floor);
			try {
				if (Integer.parseInt(value) >= 6) {
					bedroom.get(8).click();
					Wait.untilVisible(floorSub);
					click(getElementWithText(floorSub, value));
				} else {
					click(getElementWithText(floor, value));
				}
			} catch (NumberFormatException e) {
				click(getElementWithText(floor, value));
			}
		}

	}

	public void selectTotalFloors(String value) {
		if (!value.isEmpty()) {
			Wait.untilVisible(totalFloor);
			if (Integer.parseInt(value) >= 14) {
				totalFloor.get(13).click();
				Wait.untilVisible(totalFloorSub);
				click(getElementWithText(totalFloorSub, value));
			} else {
				click(getElementWithText(totalFloor, value));
			}
		}
	}

	public void selectFurnished(String value) {
		if (!value.isEmpty()) {
			Wait.untilVisible(furnished);
			click(getElementWithText(furnished, value));
		}
	}

	public void selectBathroom(String value) {
		if (!value.isEmpty()) {
			if (Integer.parseInt(value) >= 4) {
				bathroom.get(4).click();
				Wait.untilVisible(bathroomSub);
				click(getElementWithText(bathroomSub, value));
			} else {
				click(getElementWithText(bathroom, value));
			}
		}
	}

	public void selectImageUploadTab(String tab) {
		WebElement element = Driver.findElement(By.xpath("//*[@id='" + tab + "ViewImage_count']//parent::a"));
		click(element);
	}

	public void uploadImages(String name, int count) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String path = "C:\\Users\\Ankit Mishra\\eclipse-workspace\\magicbricks\\src\\test\\resources\\images\\" + name;
		for (int i = 0; i < count; i++) {
			imageField.sendKeys(path);
		}
	}

	public void verifyImageUploaded(String category, int count) {
		By container = By.id(category.toLowerCase() + "ViewImage_count");
		String xpath = "//div[@id='" + category.toLowerCase() + "ViewImage_Id']//div[@id='allData']//li";
		WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.textToBe(container, "(" + count + ")"));
		List<WebElement> images = Driver.findElements(By.xpath(xpath));
		Assert.assertEquals(images.size(), count);
	}

	public void selectPossessionStatus(String status) {
		click(getElementWithText(possessionStatus, status));
	}

	public void selectAvailMonth(String month) {
		click(availMonthBtn);
		Wait.untilVisible(availMonthSelect);
		click(getElementWithText(availMonthSelect, month));
	}

	public void selectAvailYear(String year) {
		click(availYearBtn);
		Wait.untilVisible(availYearSelect);
		click(getElementWithText(availYearSelect, year));
	}

	public void selectConstructionAge(String age) {
		click(constructionAgeBtn);
		Wait.untilVisible(constructionAgeSelect);
		click(getElementWithText(constructionAgeSelect, age));
	}

	public void verifyTermsCheckboxChecked() {
		Verify.sizeEqual(termsAssertion, 1);
	}

	public void uncheckTermCheckbox() {
		Mouse.click(termsCheckbox);
	}

	public void verifyTermsError() {
		Verify.isDisplayed(termsErr);
	}

	public void unselectExclusiveAndWhatsapp() {
		Mouse.click(exclusiveCheckbox);
		Mouse.click(whatsappCheckbox);
	}

	public void verifyOTPisDisplayed() {
		Wait.untilVisible(verifyOTPIframe);
		Switch.frame(verifyOTPIframe);
	}
}
