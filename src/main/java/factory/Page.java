package factory;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.Base;

public class Page extends Base {
	protected static NavigationPage navigation;
	protected static PropWorthPage propworthPage;
	protected static ExpertCallPage expertCallPage;
	protected static AdvertisementPage advertisementPage;
	protected static PostProperty postPropertyPage;

	protected static void build() {
		initDriver();
		initUtils();
		navigation = new NavigationPage(Driver);
		propworthPage = new PropWorthPage(Driver);
		expertCallPage = new ExpertCallPage(Driver);
		advertisementPage = new AdvertisementPage(Driver);
		postPropertyPage = new PostProperty(Driver);
	}

	@BeforeMethod
	public void setupDriver() {
		build();
	}

	@AfterMethod
	public void clearField() {
		tearDown();
	}

}
