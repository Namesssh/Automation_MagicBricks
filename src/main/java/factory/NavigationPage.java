package factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.Base;

public class NavigationPage extends Base {
	Actions action;

	public NavigationPage(WebDriver driver) {
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	public void to(String item, String subItem) {
		view("/");
		WebElement link = Driver
				.findElement(By.xpath("//li[@class='js-menu-container']//a[text()='" + item + "']//parent::li"));
		WebElement sublinkFinder = Driver.findElement(By.xpath("//li[@class='js-menu-container']//a[text()='" + item
				+ "']//following-sibling::div//a[contains(text(),'" + subItem + "')]//parent::li"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		action.moveToElement(link).perform();
		WebElement sublink = Wait.untilVisible(sublinkFinder);
		action.moveToElement(sublink).perform();
		action.click().perform();
	}

	public void hoverMainLink(String item) {
		WebElement link = Driver
				.findElement(By.xpath("//li[@class='js-menu-container']//a[text()='" + item + "']//parent::li"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		action.moveToElement(link).perform();
	}

	public void verifyWebPageDisplayed() {
		Assert.assertEquals(Switch.tabCount(), 2);
	}
}
