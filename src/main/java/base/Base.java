package base;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import provider.Property;
import util.MouseEvent;

public class Base {
	public static WebDriver Driver;
	public static Screenshot SS;
	public static Scroller Scroll;
	public static Switcher Switch;
	public static Wait Wait;
	public static TestDetails Details;
	public static Logger Log;
	public static Verify Verify;
	public static MouseEvent Mouse;

	private static void initMouseEvent() {
		Mouse = new MouseEvent(Driver);
	}

	private static void initLogger() {
		Log = new Logger();
	}

	private static void initTestDetails() {
		Details = new TestDetails();
	}

	private static void initScreenshot() {
		SS = new Screenshot(Driver);
	}

	private static void initScroller() {
		Scroll = new Scroller(Driver);
	}

	private static void initSwitcher() {
		Switch = new Switcher(Driver);
	}

	private static void initWait() {
		Wait = new Wait(Driver);
	}

	private static void initVerify() {
		Verify = new Verify(Driver);
	}

	protected static void initDriver() {
		String browser = Property.browser();
		switch (browser) {
		case "Chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			WebDriverManager.chromedriver().setup();
			Driver = new ChromeDriver(options);
			break;
		case "Edge":
			WebDriverManager.edgedriver().setup();
			Driver = new EdgeDriver();
			break;
		case "Safari":
			WebDriverManager.safaridriver().setup();
			Driver = new SafariDriver();
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			Driver = new FirefoxDriver();
			break;
		default:
			WebDriverManager.chromedriver().setup();
			Driver = new ChromeDriver();
			break;
		}
		Driver.manage().window().maximize();
	}

	protected static void initUtils() {
		initTestDetails();
		initScreenshot();
		initScroller();
		initSwitcher();
		initWait();
		initTestDetails();
		initLogger();
		initVerify();
		initMouseEvent();
	}

	protected static void tearDown() {
		Driver.quit();
	}

	protected static void view(String path) {
		if (online()) {
			Driver.get(Property.url() + path);
		} else {
			Driver.quit();
			System.out
					.println("[Error] System is not connected to internet or bad URL is passed to create connection.");
		}
	}

	public static void click(WebElement element) {
		WebElement elem = Wait.untilClickable(element);
		try {
			elem.click();
		} catch (ElementClickInterceptedException e) {
			Actions action = new Actions(Driver);
			action.moveToElement(elem).click().perform();
		}
	}

	public static boolean isAlert() {
		try {
			Switch.alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public static boolean online() {
		try {
			URL url = new URL(Property.url());
			URLConnection connection = url.openConnection();
			connection.connect();
			return true;
		} catch (MalformedURLException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	public static WebElement getElementWithText(List<WebElement> elements, String text, boolean contains) {

		WebElement result = null;
		try {
			for (WebElement propertyType : elements) {
				String elemText = propertyType.getText();
				boolean assertion = contains ? elemText.contains(text) : elemText.equals(text);
				if (assertion) {
					result = propertyType;
					break;
				}
			}
			if (result == null)
				throw new NoSuchElementException("Element with \"" + text + "\" is not present within the group");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static WebElement getElementWithText(List<WebElement> elements, String text) {
		return getElementWithText(elements, text, false);
	}
}
