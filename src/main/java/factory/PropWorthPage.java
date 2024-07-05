package factory;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import base.Base;

//Page Factory or Page Object
public class PropWorthPage extends Base {
	PropWorth propWorth;

	public PropWorthPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		propWorth = new PropWorth();
	}

	@FindBy(id = "keywordLoc")
	WebElement locSearchBox;

	@FindBys({ @FindBy(xpath = "//div[@id='keyword_suggest_Loc']//child::div") })
	List<WebElement> locSuggestions;

	@FindBys({ @FindBy(xpath = "//ul[@class='selectPropType']//child::li") })
	List<WebElement> propertyTypes;

	@FindBys({ @FindBy(xpath = "//div[@id='bhkDivTwo']//ul//li") })
	List<WebElement> bhkLabels;

	@FindBy(id = "superAreaInput")
	WebElement superAreaInput;

	@FindBy(id = "superAreaVal")
	WebElement superAreaUnitButton;

	@FindBys({ @FindBy(xpath = "//select[@id='superArea']//following-sibling::div//li") })
	List<WebElement> superAreaUnits;

	@FindBy(id = "selectFloorValTwo")
	WebElement floorButton;

	@FindBys({ @FindBy(xpath = "//select[@id='selectFloorTwo']//following-sibling::div//li") })
	List<WebElement> floors;

	@FindBy(id = "parkingDiv")
	WebElement parking;

	@FindBy(id = "parkingOpenVal")
	WebElement openParkingButton;

	@FindBys({ @FindBy(xpath = "//select[@id='parkingOpen']//following-sibling::div//li") })
	List<WebElement> openParkingVals;

	@FindBy(id = "parkingCoveredVal")
	WebElement covParkingButton;

	@FindBys({ @FindBy(xpath = "//select[@id='parkingCovered']//following-sibling::div//li") })
	List<WebElement> covParkingVals;

	@FindBy(css = "#checkNowDiv > .checkNowBtn")
	WebElement checkNowButton;

	@FindBy(className = "searchProjInfo")
	WebElement propDetails;

	@FindBy(id = "noPropType")
	WebElement noPropertyError;

	@FindBy(className = "locSocietySearchBtn")
	WebElement sendButton;

	@FindBy(id = "noKeyword")
	WebElement invalidKeyError;

	@FindBy(id = "inputareaError")
	WebElement areaError;

	public void enterLocKeyword(String key) {
		WebElement box = Wait.untilVisible(locSearchBox);
		box.sendKeys(key);
	}

	public void selectLocality(String locality) {
		Wait.untilVisible(locSuggestions);
		getElementWithText(locSuggestions, locality).click();
		propWorth.locality(locality);
	}

	public void selectPropType(String type) {
		getElementWithText(propertyTypes, type).click();
		propWorth.propType(type);
	}

	public void selectBHKLabel(String label) {
		String lb = label + " BHK";
		getElementWithText(bhkLabels, lb).click();
		propWorth.bhkLabel(lb);
	}

	public void selectSuperArea(String suparea) {
		if (!suparea.isEmpty()) {
			String[] area = suparea.split(" ");
			Wait.untilVisible(superAreaInput).click();
			superAreaInput.sendKeys(area[0]);
			superAreaUnitButton.click();
			getElementWithText(superAreaUnits, area[1]).click();
			propWorth.superArea(area[0], area[1]);
		}
	}

	public void selectFloor(String floor) {
		if (!floor.isEmpty()) {
			Wait.untilVisible(floorButton).click();
			getElementWithText(floors, floor).click();
		}
	}

	public void selectParking(String open, String covered) {
		Wait.untilVisible(parking);
		if (!open.isEmpty()) {
			openParkingButton.click();
			getElementWithText(openParkingVals, open).click();
		}
		if (!covered.isEmpty()) {
			covParkingButton.click();
			getElementWithText(covParkingVals, covered).click();
		}
	}

	public void clickCheckNow() {
		Scroll.intoView(checkNowButton).sendKeys(Keys.ENTER);
	}

	public void verifyPropDetails() {
		Verify.textEqual(propDetails, propWorth.result());
	}

	public void verifyUnavailableLocError(String err) {
		Verify.textEqual(noPropertyError, err);
	}

	public void clickOnSendButton() {
		click(sendButton);
	}

	public void verifyInvalidLocKeyError(String err) {
		Verify.textEqual(invalidKeyError, err);
	}

	public void verifyInvalidAreaError(String err) {
		Verify.textEqual(areaError, err);
	}
}

class PropWorth {
	String locality;
	String propType;
	String bhkLabel;
	String superArea;
	String floor;
	String openPark;
	String covPark;

	public PropWorth locality(String locality) {
		this.locality = locality;
		return this;
	}

	public PropWorth propType(String type) {
		this.propType = type;
		return this;
	}

	public PropWorth bhkLabel(String label) {
		this.bhkLabel = label;
		return this;
	}

	public PropWorth superArea(String area, String unit) {
		String nunit = unit.replace("sq", "Sq-");
		this.superArea = area + " " + nunit;
		return this;
	}

	public PropWorth floor(String floor) {
		this.floor = floor;
		return this;
	}

	public PropWorth parking(String parking) {
		String[] park = parking.split(" ");
		this.openPark = park[0];
		this.covPark = park[1];
		return this;
	}

	public String result() {
		String result = propType + " " + bhkLabel + ", " + superArea + ", in " + locality;
		return result;
	}

}
