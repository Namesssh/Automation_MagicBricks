package provider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Property {
	private static final String path = "src/test/resources/configs/config.properties";

	private static RuntimeException execption(String key) {
		return new RuntimeException(key + " not specified in the \"config.properties\" file");
	}

	private static Properties reader() {
		BufferedReader reader;
		Properties props;
		try {
			reader = new BufferedReader(new FileReader(path));
			props = new Properties();
			try {
				props.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("config.properties not found at " + path);
		}

		return props;
	}

	public static String browser() {
		String value = reader().getProperty("browser");
		if (value != null)
			return value;
		else
			throw execption("browser");
	}

	public static String url() {
		String value = reader().getProperty("url");
		if (value != null)
			return value;
		else
			throw execption("url");
	}

	public static int timeout() {
		String value = reader().getProperty("timeout");
		if (value != null)
			return Integer.parseInt(value);
		else
			throw execption("timeout");
	}

	public static String extentXML() {
		String value = reader().getProperty("extentXML");
		if (value != null)
			return value;
		else
			throw execption("extentXML");
	}

	public static String get(String key) {
		String value = reader().getProperty(key);
		if (value != null)
			return value;
		else
			throw execption(key);
	}

}
