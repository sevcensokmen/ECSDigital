package e2e.utilities;

import java.io.File;
import java.io.IOException;

public class Constant {

	public static String PATH;

	static {
		try {
			PATH = new File(".").getCanonicalPath();
		} catch (IOException e) {
			// Should not be here at all
		}
	}
	public final static String CONFIG_PROPERTIES_DIRECTORY = PATH 
			+ "/src/test/resources/properties/Config.properties";

	public final static String GECKO_DRIVER_DIRECTORY = PATH
			+ "/src/test/resources/executables/geckodriver";

	public final static String CHROME_DRIVER_DIRECTORY = PATH
			+ "/src/test/resources/executables/chromedriver";
	
	public final static String GECKO_DRIVER_DIRECTORY_WINDOWS = PATH 
			 + "//src//test//resources//executables//geckodriver.exe";

	public final static String CHROME_DRIVER_DIRECTORY_WINDOWS = PATH
			 + "//src//test//resources//executables//chromedriver.exe";
	
	public final static String LOG4J_CONFIG_DIRECTORY = PATH 
			+ "/src/test/resources/properties/log4j.properties"; 

	public final static String EXTENT_REPORT_FILE = PATH 
			+ "/src/test/resources/extent-config.xml";

	public final static String EXTENT_REPORT_CONFIG_DIRECTORY = PATH 
			+ "/src/test/resources/extentconfig/ReportsConfig.xml"; 
	
	public final static String EXTENT_REPORT_CONFIG= PATH 
			+ "/target/Extent_Reports/"; 
	

}