package provider;

import java.util.ArrayList;

import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import base.DataChunk;
import xl.Reader;

public class Provider {

	private ArrayList<DataChunk> data = new ArrayList<DataChunk>();

	private void populateData(String file, String sheet, String key) {
		Reader xl = new Reader(file, sheet);
		int recordSize = key == null ? xl.maxRows() : xl.columns().at(key).size();
		for (int i = 1; i < recordSize; i++) {
			String[] chunk = xl.rows().at(i).append(null, xl.maxCols()).toArray();
			DataChunk c = new DataChunk(xl.headers(), chunk, xl.columns().defs());
			c.index(i);
			this.data.add(c);
		}
	}

	@DataProvider(name = "data")
	public DataChunk[] data(ITestContext context) {
		String file = null;
		String key = null;
		String sheet = null;
		if (context.getAttribute("dataFile") != null)
			file = context.getAttribute("dataFile").toString();
		if (context.getAttribute("dataKey") != null)
			key = context.getAttribute("dataKey").toString();
		if (context.getAttribute("dataSheet") != null)
			sheet = context.getAttribute("dataSheet").toString();
		populateData(file, sheet, key);
		return data.toArray(new DataChunk[data.size()]);
	}

	public static Provider file(String value) {
		Reporter.getCurrentTestResult().getTestContext().setAttribute("dataFile", value);
		return new Provider();
	}

	public static Provider sheet(String value) {
		Reporter.getCurrentTestResult().getTestContext().setAttribute("dataSheet", value);
		return new Provider();
	}

	public static Provider key(String value) {
		Reporter.getCurrentTestResult().getTestContext().setAttribute("dataKey", value);
		return new Provider();
	}

}
