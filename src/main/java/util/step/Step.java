package util.step;

import java.util.ArrayList;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import base.DataChunk;
import base.Screenshot;
import util.extent.ExtentTestManager;

public class Step {
	private ArrayList<StepBuilder> steps = new ArrayList<StepBuilder>();
	private ArrayList<String> descs = new ArrayList<String>();
	private ArrayList<String[]> data = new ArrayList<String[]>();
	private ExtentTest test;
	private Screenshot screenshot;
	private DataChunk chunk;

	public Step(Screenshot screenshot, DataChunk chunk) {
		test = ExtentTestManager.getTest();
		this.screenshot = screenshot;
		this.chunk = chunk;
	}

	public void add(StepBuilder builder, String description, String... data) {
		steps.add(builder);
		descs.add(description);
		String[] temp = new String[data.length];
		for (int i = 0; i < data.length; i++) {
			temp[i] = chunk.get(data[i]);
		}
		this.data.add(temp);
	}

	private String createDesc(String desc, String... data) {
		int occur = 0;
		for (char ch : desc.toCharArray()) {
			if (Character.toString(ch).equals("^")) {
				desc = desc.replaceFirst("\\^", data[occur]);
				occur++;
			}
		}
		return desc;
	}

	public void exec() {
		boolean error = false;
		for (int i = 0; i < steps.size(); i++) {
			String[] data = this.data.get(i);

			String desc = createDesc(descs.get(i), data);
			StepBuilder step = steps.get(i);
			try {
				if (error) {
					test.skip(desc);
					System.out.println("[Skip] " + desc);
					continue;
				}
				step.execute(data);
			} catch (Exception e) {
				error = true;
				test.fail(desc + " [" + e.getClass().getSimpleName() + "]", screenshot.ngStep());
				System.out.println("[Fail] " + desc + " [" + e.getClass().getSimpleName() + "]");
				e.printStackTrace();
				continue;
			}
			test.pass(desc);
			System.out.println("[Pass] " + desc);
		}
		Assert.assertFalse(error);
	}
}
