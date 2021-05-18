package utils;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import helpers.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Sandeep
 * 
 */

public class Base {

    public ExtentReports extent;

    public static ExtentTest scenarioDef;

    public static ExtentTest features;

    public static String reportLocation = "target\\report\\";
    
    public ConfigManager configManager = new ConfigManager();
    
    public String applicationURL = configManager.getProperty("baseURL");
    public void openBaseURL() {
    	driver.get(applicationURL);
    }
    
    //initializing the property file reading
    public static Properties CONFIG=null;
    public static WebDriver driver=null;
    public void initialize() throws IOException{
        if(driver == null){

            //Initialize the webdriver
            if(configManager.getProperty("browser").equals("chrome")){
            	WebDriverManager.chromedriver().setup();
            	ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
            } 
            else if (configManager.getProperty("browser").equals("firefox")){
            	WebDriverManager.iedriver().setup();
                driver = new FirefoxDriver();

            }else if(configManager.getProperty("browser").equals("edge")){
            	WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }

            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
    }
    
    public void tearDown() {
    	driver.quit();
    }


    public void mouseHover(WebElement element) {
    	Actions actions = new Actions(driver);
    	actions.moveToElement(element).perform();
    	
    }
    
    public void waitForElement(final WebElement element, long timeoutInSeconds) {
    	
    	try {
			ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver input) {
					
					return element.isDisplayed();
				}
			};
			Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).ignoring(StaleElementReferenceException.class);
			wait.until(expectation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
