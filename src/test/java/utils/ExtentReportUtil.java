package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
/**
 * @author Suresh
 * 
 */


public class ExtentReportUtil extends Base {

    String fileName = reportLocation + "extent-report.html";


    public void ExtentReport() {
        extent = new ExtentReports();

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Selenium BDD Test Report");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName("Selenium BDD Test Report");

        extent.attachReporter(sparkReporter);

    }

    public void ExtentReportScreenshot() throws IOException {

        File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(scr.toPath(), new File(reportLocation + "screenshot.png").toPath());
        scenarioDef.fail("details").addScreenCaptureFromPath(reportLocation + "screenshot.png");
    }


    public void FlushReport(){
        extent.flush();
    }




}
