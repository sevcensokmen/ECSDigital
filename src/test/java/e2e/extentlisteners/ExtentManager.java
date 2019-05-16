package e2e.extentlisteners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import e2e.utilities.DriverManager;


public class ExtentManager {

    private static ExtentReports extent;
    private static Date d = new Date();
    private static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

    static String lastCapturedScreenName;

    private static int lastCapturedScreenIndex = 0;

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {

            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reports/" + fileName);

            htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
            htmlReporter.config().setChartVisibilityOnOpen(true);
            htmlReporter.config().setTheme(Theme.STANDARD);
            htmlReporter.config().setDocumentTitle(fileName);
            htmlReporter.config().setEncoding("utf-8");
            htmlReporter.config().setReportName(fileName);

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("Automation Tester", "Rahul Arora");
            extent.setSystemInfo("Organization", "Way2Automation");
            extent.setSystemInfo("Build no", "W2A-1234");
        }
        return extent;
    }

    public static void captureScreenshot() {
        lastCapturedScreenIndex = lastCapturedScreenIndex + 1;
        File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

        Date d = new Date();
        lastCapturedScreenName = d.toString().replace(":", "_").replace(" ", "_") + "_" + lastCapturedScreenIndex + ".jpg";

        try {
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/reports/" + lastCapturedScreenName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

