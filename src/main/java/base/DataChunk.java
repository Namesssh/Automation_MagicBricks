package base;

import java.util.ArrayList;

public class DataChunk {
	public DataChunk(String[] keys, String[] values, String[] defs) {
		for (int i = 0; i < keys.length; i++) {
			this.keys.add(keys[i]);
			this.values.add(values[i]);
			this.defs.add(defs[i]);
		}
	}

	public DataChunk(String[] keys, String[] values) {
		for (int i = 0; i < keys.length; i++) {
			this.keys.add(keys[i]);
			this.values.add(values[i]);
			this.defs.add(null);
		}
	}

	private ArrayList<String> values = new ArrayList<String>();
	private ArrayList<String> keys = new ArrayList<String>();
	private ArrayList<String> defs = new ArrayList<String>();
	public int index;

	public void index(int i) {
		index = i;
	}

	public String get(String key) {
		int index = keys.indexOf(key);
		try {
			if (values.get(index) == null)
				return defs.get(index);
			else
				return values.get(index);
		} catch (IndexOutOfBoundsException e) {
			return defs.get(index);
		}
	}
}
