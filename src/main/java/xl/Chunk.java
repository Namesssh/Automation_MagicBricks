package xl;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chunk {
	protected Chunk(ArrayList<String> values) {
		this.values = values;
	}

	protected Chunk() {
	}

	private ArrayList<String> values = new ArrayList<String>();
	public String def;

	protected void add(String value) {
		values.add(value);
	}

	protected void add(int index, String value) {
		values.add(index, value);
	}

	protected void trimEnd() {
		for (int i = values.size() - 1; i >= 0; i--) {
			if (values.get(i) == null)
				values.remove(i);
			else
				break;
		}
	}

	private String lastNonNull() {
		String value = null;
		for (int i = values.size() - 1; i >= 0; i--) {
			if (values.get(i) != null) {
				value = values.get(i);
				break;
			}
		}

		return value;
	}

	protected void removeArgs() {
		String regex = "\\[default+=+([A-Za-z0-9!@#$%^&*()_+\\-=~`<>?:{}|,./;\\ ]+)\\]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(values.get(0) == null ? "" : values.get(0));
		if (matcher.find()) {
			String val = matcher.group(1);
			if (val.contentEquals("_"))
				def = "";
			else
				def = val;
		}

		else
			def = lastNonNull();

		String newKey = values.get(0).split("\\[")[0];
		values.set(0, newKey);
	}

	protected void removeArgsInvert() {
		String regex = "\\[default+=+([A-Za-z0-9!@#$%^&*()_+\\-=~`<>?:{}|,./;\\ ]+)\\]";
		Pattern pattern = Pattern.compile(regex);
		for (int i = 0; i < values.size(); i++) {
			Matcher matcher = pattern.matcher(values.get(i));
			if (matcher.find()) {
				String val = matcher.group(1);
				if (val.contentEquals("_"))
					def = "";
				else
					def = val;
			} else
				def = lastNonNull();

			String newKey = values.get(i).split("\\[")[0];
			values.set(i, newKey);
		}
	}

	public String at(int index) {
		String v = values.get(index);
		return index == 0 ? v.split("\\[")[0] : v;
	}

	public int size() {
		return values.size();
	}

	public Chunk append(Object value, int len) {
		int upperBound = len - values.size();
		for (int i = 0; i < upperBound; i++) {
			values.add((String) value);
		}
		return this;
	}

	public Chunk replace(Object value, Object target) {
		values.replaceAll((v) -> v == value ? (String) target : v);
		return this;
	}

	public String[] toArray() {
		return values.toArray(new String[values.size()]);
	}
}
