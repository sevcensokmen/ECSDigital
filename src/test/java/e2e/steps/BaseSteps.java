package e2e.steps;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import e2e.pageobjects.ECSDigitalTestPage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import e2e.extentlisteners.ExtentTestManager;
import e2e.utilities.Constant;
import e2e.utilities.DriverFactory;
import e2e.utilities.DriverManager;


public class BaseSteps {

    private WebDriver driver;
    private Properties Config = new Properties();
    private FileInputStream fis;
    public Logger log = Logger.getLogger(BaseSteps.class);
    public boolean grid = true;


    private WebDriverWait waitDriver;
    public static ECSDigitalTestPage ecsDigitalTestPage;


    public void setUpFramework() {

        configureLogging();


        DriverFactory.setConfigPropertyFilePath(Constant.CONFIG_PROPERTIES_DIRECTORY);
        try {
            fis = new FileInputStream(DriverFactory.getConfigPropertyFilePath());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            Config.load(fis);
            log.info("Config properties file loaded");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (Config.getProperty("os.name").equalsIgnoreCase("mac")) {

            DriverFactory.setChromeDriverExePath(Constant.CHROME_DRIVER_DIRECTORY);
            DriverFactory.setGeckoDriverExePath(Constant.GECKO_DRIVER_DIRECTORY);

        } else {


            DriverFactory.setChromeDriverExePath(Constant.CHROME_DRIVER_DIRECTORY_WINDOWS);
            DriverFactory.setGeckoDriverExePath(Constant.GECKO_DRIVER_DIRECTORY_WINDOWS);

        }


        DriverFactory.setGridPath(Config.getProperty("grid.path"));
        ecsDigitalTestPage = PageFactory.initElements(driver, ECSDigitalTestPage.class);
    }


    public void logInfo(String message) {

        ExtentTestManager.testReport.get().info(message);
    }

    public void configureLogging() {
        String log4jConfigFile = Constant.LOG4J_CONFIG_DIRECTORY;
        PropertyConfigurator.configure(log4jConfigFile);
    }


    public void openBrowser(String browser) {

        grid = Boolean.parseBoolean(Config.getProperty("grid"));

        DriverFactory.setRemote(grid);

        if (DriverFactory.isRemote()) {
            DesiredCapabilities cap = null;

            if (browser.equals("firefox")) {

                cap = DesiredCapabilities.firefox();
                cap.setBrowserName("firefox");
                cap.setPlatform(Platform.ANY);

            } else if (browser.equals("chrome")) {

                cap = DesiredCapabilities.chrome();
                cap.setBrowserName("chrome");
                cap.setPlatform(Platform.ANY);
            } else if (browser.equals("ie")) {

                cap = DesiredCapabilities.internetExplorer();
                cap.setBrowserName("iexplore");
                cap.setPlatform(Platform.WIN10);
            }

            try {
                driver = new RemoteWebDriver(new URL(DriverFactory.getGridPath()), cap);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (browser.equals("chrome")) {
            System.out.println("Launching : " + browser);
            System.setProperty("webdriver.chrome.driver",
                    DriverFactory.getChromeDriverExePath());
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            System.out.println("Launching : " + browser);
            System.setProperty("webdriver.gecko.driver",
                    DriverFactory.getGeckoDriverExePath());
            driver = new FirefoxDriver();

        }

        DriverManager.setWebDriver(driver);
        log.info("Driver Initialized !!!");
//		DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        waitDriver = new WebDriverWait(DriverManager.getDriver(), 3000);

    }

    public void quit() {

        DriverManager.getDriver().quit();
        log.info("Test Execution Completed !!!");
    }


    public WebDriverWait getWaitDriver(){
        return waitDriver;
    }


}
