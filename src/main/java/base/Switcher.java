package base;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Switcher {
	Switcher(WebDriver driver) {
		this.driver = driver;
	}

	private WebDriver driver;

	public void tab(WindowFinder finder) {
		String oWindow = driver.getWindowHandle();
		boolean isFound = false;
		for (String window : driver.getWindowHandles()) {
			driver.switchTo().window(window);
			if (finder.find()) {
				isFound = !isFound;
				break;
			}
		}
		if (!isFound) {
			driver.switchTo().window(oWindow);
		}
	}

	public void window(WindowFinder finder) {
		String oWindow = driver.getWindowHandle();
		boolean isFound = false;
		for (String window : driver.getWindowHandles()) {
			driver.switchTo().window(window);
			if (finder.find()) {
				isFound = !isFound;
				break;
			}
		}
		if (!isFound) {
			driver.switchTo().window(oWindow);
		}
	}

	public Alert alert(int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public Alert alert() {
		return driver.switchTo().alert();
	}

	public WebDriver frame(String idOrName) {
		return driver.switchTo().frame(idOrName);
	}

	public WebDriver frame(WebElement frameElement) {
		return driver.switchTo().frame(frameElement);
	}

	public WebDriver frame(int index) {
		return driver.switchTo().frame(index);
	}

	public WebDriver page() {
		return driver.switchTo().defaultContent();
	}

	public int tabCount() {
		return driver.getWindowHandles().size();
	}
}
