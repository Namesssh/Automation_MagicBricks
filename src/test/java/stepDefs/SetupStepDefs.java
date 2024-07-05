package stepDefs;

import java.util.Collection;

import factory.Page;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class SetupStepDefs extends Page {
	private String noTearDownTag = "@NoTearDown";
	private boolean noTearDown = false;

	private boolean findTag(Scenario scenario, String tag) {
		boolean value = false;
		Collection<String> tags = scenario.getSourceTagNames();
		for (String t : tags) {
			if (t.equals(tag)) {
				value = true;
			}
		}
		return value;
	}

	private void setToDefault() {
		noTearDown = false;
	}

	@Before
	public void before(Scenario scenario) {
		if (findTag(scenario, noTearDownTag)) {
			if (Driver.toString().contains("(null)")) {
				build();
			}
			noTearDown = true;
		} else {
			build();
		}
	}

	@After
	public void after(Scenario scenario) {
		if (!noTearDown) {
			tearDown();
		}
		setToDefault();
	}

	@BeforeAll
	public static void before_all() {
	}

	@AfterAll
	public static void after_all() {
		if (!Driver.toString().equals("(null)"))
			tearDown();
	}

	@AfterStep
	public void takeScreenshot(Scenario scenario) {
		SS.scenarioStep(scenario);
	}
}
