package steps;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import utils.Base;

public class SearchAndSelectSteps extends Base {
	 public HomePage homePage;
	 
	 @Before
	    public void setup() throws IOException {
	        initialize();
	        homePage = PageFactory.initElements(driver, HomePage.class);

	    }

	 String pattern = "EEEEE, MMMMM dd, yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String tomorrowDate = simpleDateFormat.format(new Date(System.currentTimeMillis()+24*60*60*1000));
		String yesterday = simpleDateFormat.format(new Date(System.currentTimeMillis()-24*60*60*1000));
		String returnDate = simpleDateFormat.format(new Date(System.currentTimeMillis()+7*24*60*60*1000));

		private boolean previousDateStatus;
	@Given("I navigate to JetBlue website") 
    public void i_navigate_to_JetBlue_website() throws Throwable {
        driver.get("https://www.jetblue.com/");
        try {
        	if(homePage.cookiesFrame.isDisplayed()) {
            	driver.switchTo().frame(1);
                waitForElement(homePage.cookiesModel, 5);
                if(homePage.acceptAllCookiesCTA.isDisplayed()) {
                	homePage.acceptAllCookiesCTA.click();
                }        
                driver.switchTo().defaultContent();
            }
        } catch (Exception e) {
		}
    }
	
	@When("I enter From city as {string}")
	public void i_enter_from_city_as(String string) {
		homePage.fromCity.clear();
	    homePage.fromCity.sendKeys(string);
	    homePage.fromCity.sendKeys(Keys.TAB);
	}



	@When("I click on Search flights")
	public void i_click_on_search_flights() {
		 homePage.toCity.sendKeys(Keys.TAB);
		 homePage.departDate.sendKeys(Keys.TAB);
		 homePage.returnDate.sendKeys(Keys.TAB);
	     homePage.searchButton.click();
	}
	@Then("I should get an error message {string}")
	public void i_should_get_an_error_message(String string) {
		homePage.toCity.sendKeys(Keys.TAB);	
		 homePage.departDate.sendKeys(Keys.TAB);
		 homePage.returnDate.sendKeys(Keys.TAB);
		 assertEquals(string, homePage.cityErrorMessage.getText());
	}

	@When("I enter To city as {string}")
	public void i_enter_to_city_as(String string) {
		homePage.toCity.clear();
		homePage.toCity.sendKeys(string);
		homePage.toCity.sendKeys(Keys.ENTER);
	}


	@When("I select Depart date")
	public void i_select_depart_date() {
	    driver.findElement(By.xpath("//button[@aria-label='"+tomorrowDate+"']")).click();
	}
	
	@When("I select Return date")
	public void i_select_return_date() {
	    driver.findElement(By.xpath("//button[@aria-label='"+returnDate+"']")).click();
	}
	
	@When("I try to select Return Date prior to Depart date")
	public void i_try_to_select_return_date_prior_to_depart_date() {
		previousDateStatus = driver.findElement(By.xpath("//button[@aria-label='"+yesterday+"']")).isEnabled();
	}
	
	@Then("Return Date prior to Depart date should be disabled")
	public void return_date_prior_to_depart_date_should_be_disabled() {
		assertFalse(previousDateStatus);
	}
	
	@Then("I should land on flights selection page")
	public void i_should_land_on_flights_selection_page() {
		System.out.println(driver.getTitle());
		 assertEquals(driver.getTitle(), "Flight Selection");

	}
	
    @When("I select lowest fare for departure")
    public void i_select_lowest_fare_for_departure() throws ParseException {
    	
    	String min;
	     double m=0,r=0;
		 
	       //No. of Columns
	        List<WebElement>  col = driver.findElements(By.xpath(".//*[@id='leftcontainer']/table/thead/tr/th"));
	        System.out.println("Total No of columns are : " +col.size());
	        //No.of rows
	        List<WebElement>  rows = driver.findElements(By.xpath (".//*[@id='leftcontainer']/table/tbody/tr/td[1]"));
	        System.out.println("Total No of rows are : " + rows.size());
	        for (int i =1;i<rows.size();i++)
	        {    
	            min= driver.findElement(By.xpath("//table/tbody/tr[" + (i+1)+ "]/td[4]")).getText();
	            NumberFormat f =NumberFormat.getNumberInstance(); 
	            Number num = f.parse(min);
	            min = num.toString();
	            m = Double.parseDouble(min);
	            if(m<r)
	             {    
	                r=m;
	             }
	        }
	        System.out.println("lowest fare is : "+ r);
    	
    }
    @When("I select lowest fare for return")
    public void i_select_lowest_fare_for_return() throws ParseException {
    	String min;
	     double m=0,r=0;
		 
	       //No. of Columns
	        List<WebElement>  col = driver.findElements(By.xpath(".//*[@id='leftcontainer']/table/thead/tr/th"));
	        System.out.println("Total No of columns are : " +col.size());
	        //No.of rows
	        List<WebElement>  rows = driver.findElements(By.xpath (".//*[@id='leftcontainer']/table/tbody/tr/td[1]"));
	        System.out.println("Total No of rows are : " + rows.size());
	        for (int i =1;i<rows.size();i++)
	        {    
	            min= driver.findElement(By.xpath("//table/tbody/tr[" + (i+1)+ "]/td[4]")).getText();
	            NumberFormat f =NumberFormat.getNumberInstance(); 
	            Number num = f.parse(min);
	            min = num.toString();
	            m = Double.parseDouble(min);
	            if(m<r)
	             {    
	                r=m;
	             }
	        }
	        System.out.println("lowest fare is : "+ r);
   	
    	
    }
    @When("I click on continue")
    public void i_click_on_continue() {
    	homePage.continueButton.click();
    	
    }
    @Then("I should naviate to travelers info page")
    public void i_should_naviate_to_travelers_info_page() {
		 assertEquals(driver.getTitle(), "Traveler Information");

    }
}
