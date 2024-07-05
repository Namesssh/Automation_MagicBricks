package factory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class AdvertisementPage extends Base {
	public AdvertisementPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBys({ @FindBy(xpath = "//ul[@id='userTypeList']//li") })
	private List<WebElement> userTypes;

	@FindBys({ @FindBy(xpath = "//li[@id='sellId']//parent::ul//li") })
	private List<WebElement> lookingTo;

	@FindBys({
			@FindBy(xpath = "//div[@id='propertyTypeLabel']//following-sibling::div//li[not(contains(@style,'display: none;'))]") })
	private List<WebElement> propertyType;

	@FindBy(xpath = "//input[@value='Show Me Packages']")
	private WebElement showPkgBtn;

	@FindBys({ @FindBy(xpath = "//div[contains(@class,'pckg-grid up')]") })
	private List<WebElement> packages;

	@FindBy(linkText = "Add to My Orders")
	private WebElement addToCartBtn;

	@FindBy(id = "totalCurrentCartItems")
	private WebElement cartItemCounter;

	@FindBy(id = "_packagename")
	private WebElement packageName;

	public void selectUserType(String type) {
		click(getElementWithText(userTypes, type));
	}

	public void selectLookingTo(String type) {
		click(getElementWithText(lookingTo, type));
	}

	public void selectPropertyType(String type) {
		click(getElementWithText(propertyType, type));
	}

	public void clickOnShowPkgBtn() {
		click(showPkgBtn);
	}

	public void verifyPkgsDisplayed() {
		Verify.isPresent(packages);
	}

	public void selectPackage(String pkg) {
		WebElement initButton = Driver
				.findElement(By.xpath("//div[@data-tab='" + pkg.toLowerCase() + "']//a[@class='butn']"));
		WebElement button = Wait.untilVisible(initButton);
		click(button);
	}

	public void clickOnAddToCartBtn() {
		click(addToCartBtn);
	}

	public void verifyCartItemCount(String count) {
		Verify.textEqual(cartItemCounter, count);
	}

	public void verifyPackageName(String pkg) {
		Verify.textEqual(packageName, pkg, false);
	}
}
