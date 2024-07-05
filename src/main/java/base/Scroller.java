package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Scroller {

	Scroller(WebDriver driver) {
		this.js = (JavascriptExecutor) driver;
	}

	private JavascriptExecutor js;

	public void up(int px) {
		js.executeScript("window.scrollBy(0, -" + px + ")");
	}

	public void down(int px) {
		js.executeScript("window.scrollBy(0, " + px + ")");
	}

	public void left(int px) {
		js.executeScript("window.scrollBy(" + px + ",0)");
	}

	public void right(int px) {
		js.executeScript("window.scrollBy(-" + px + ",0)");
	}

	public WebElement intoView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView();", element);
		return element;
	}

}
