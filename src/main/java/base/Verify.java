package base;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Verify {
	WebDriver driver;

	public Verify(WebDriver driver) {
		this.driver = driver;
	}

	public void textEqual(WebElement actual, String expected, boolean caseSensitive) {
		if (caseSensitive)
			Assert.assertEquals(actual.getText(), expected);
		else {
			Assert.assertEquals(actual.getText().toLowerCase(), expected.toLowerCase());
		}
	}

	public void textEqual(WebElement actual, String expected) {
		textEqual(actual, expected, true);
	}

	public void textEqual(WebElement actual, WebElement expected, boolean caseSensitive) {
		if (caseSensitive)
			Assert.assertEquals(actual.getText(), expected.getText());
		else {
			Assert.assertEquals(actual.getText().toLowerCase(), expected.getText().toLowerCase());
		}
	}

	public void textEqual(WebElement actual, WebElement expected) {
		textEqual(actual, expected, true);
	}

	public void isPresent(List<WebElement> elements) {
		if (elements.size() == 0) {
			Assert.assertFalse(false);
		} else {
			Assert.assertTrue(true);
		}
	}

	public void isDisplayed(WebElement element) {
		Assert.assertTrue(element.isDisplayed());
	}

	public void sizeEqual(List<WebElement> elements, int count) {
		Assert.assertTrue(elements.size() == count);
	}

	public void attrEqual(WebElement element, String name, String expected) {
		String actual = element.getAttribute(name);
		Assert.assertEquals(actual, expected);
	}
}
