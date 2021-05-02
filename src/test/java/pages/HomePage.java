package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Suresh
 * 
 */
public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

    @FindBy(xpath = "//iframe[@title='TrustArc Cookie Consent Manager']")
    public WebElement cookiesFrame;
    
    @FindBy(className = "mainContent")
    public WebElement cookiesModel;
    
    @FindBy(xpath = "//a[@class='call' and @role='button']")
    public WebElement acceptAllCookiesCTA;
    
    @FindBy(xpath = "//label[contains(text(),'From') or contains(text(),'Desde')]/following-sibling::input")
    public WebElement fromCity;
    
    @FindBy(xpath = "//label[contains(text(),'To')  or contains(text(),'A')]/following-sibling::input")
    public WebElement toCity;
    
    @FindBy(id = "jb-date-picker-input-id-1")
    public WebElement departDate;
    
    @FindBy(id = "jb-date-picker-input-id-2")
    public WebElement returnDate;

    @FindBy(xpath = "//jb-booker-air-submit/button")
    public WebElement searchButton;
    
    @FindBy(xpath = "//dot-city-selector-v2//*[@name='error']/following-sibling::p")
    public WebElement cityErrorMessage;
    
    @FindBy(xpath = "//*[@data-qaid='continueToNextWarningMessage']")
    public WebElement continueToNextWarningMessage;

    @FindBy(xpath = "//*[@data-qaid='continueButton']")
	public WebElement continueButton;
  
}
