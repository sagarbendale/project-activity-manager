package sjsu.team21.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropFileUtil {

	public static Properties getProperties() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(
					"src/main/resources/application.properties");
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			// System.out.println(prop.getProperty("name"));
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;

	}
}
