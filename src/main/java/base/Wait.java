package base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import provider.Property;

public class Wait {
	WebDriver driver;
	WebDriverWait wait;

	Wait(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(Property.timeout()));
	}

	public WebElement untilVisible(WebElement element) {
		return this.wait.until(ExpectedConditions.visibilityOf(element));
	}

	public List<WebElement> untilVisible(List<WebElement> list) {
		return this.wait.until(ExpectedConditions.visibilityOfAllElements(list));
	}

	public WebElement untilClickable(WebElement element) {
		return this.wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public boolean untilInvisible(WebElement element) {
		return this.wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public Boolean untilTextPresent(WebElement element, String text) {
		return this.wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	public Boolean untilAttributeChange(WebElement element, String attr, String value) {
		return this.wait.until(ExpectedConditions.domAttributeToBe(element, attr, value));
	}

	public Boolean numberOfElementsToBe(List<WebElement> elements, Integer number) {
		return this.wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				if (elements.size() == number)
					return true;
				else
					return false;
			}
		});
	}
}
