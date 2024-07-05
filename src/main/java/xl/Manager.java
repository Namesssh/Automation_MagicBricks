package xl;

import java.util.ArrayList;

public class Manager {
	Manager(ArrayList<Chunk> values) {
		this.values = values;
	}

	private ArrayList<Chunk> values = new ArrayList<Chunk>();

	public Chunk[] toArray() {
		return values.toArray(new Chunk[values.size()]);
	}

	public String at(int index, int cell) {
		return values.get(index).at(cell);
	}

	public Chunk at(int index) {
		return values.get(index);
	}

	public Chunk at(String key) {
		int index = -1;
		try {
			for (int i = 0; i < values.size(); i++) {
				if (at(i, 0) != null) {
					if (at(i, 0).matches(key))
						index = i;
				}
			}
			if (index == -1)
				throw new Exception("No data available with \"" + key + "\" key");
			else
				return at(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] defs() {
		String[] d = new String[values.size()];
		for (int i = 0; i < values.size(); i++) {
			d[i] = at(i).def;
		}
		return d;
	}

	public int size() {
		return values.size();
	}
}
