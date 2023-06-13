package PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.WebDriverFactory;
import io.cucumber.java.Scenario;

public class FooterSectionObject {
	
	
	private static final Logger logger= LogManager.getLogger(FooterSectionObject.class);
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Scenario scn;
	
//============= Locators for Footer section WebElements=====================================//

	private By twitterLink= By.xpath("//img[@alt='twitter_color_1']");
	private By twitterAcLink= By.xpath("//link[@href='https://twitter.com/CandereByKalyan']");

//============= Expected Results ==========================================================//

	String twitterPageTitle= "Candere By Kalyan Jewellers (@CandereByKalyan) / Twitter";


//============= Constructor ===============================================================//
	public FooterSectionObject(WebDriver driver,Scenario scn)
	{
		this.driver= driver;
		this.scn=scn;
	}
	
//============ 1. Method to set twitter link =================================================//
    public void setTwitterLink()
    {
    	WebElement twitterElement =driver.findElement(twitterLink);
    	
    	//Scroll till twitter link element available on screen using Javascript executor
		js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", twitterElement);
		Assert.assertEquals(true, twitterElement.isDisplayed());
    	logger.info("Validate the twitter link");
    }
    
//============ 2. Method to click the twitter link ===========================================//
    public void clickOnTwitterLink()
    {
    	WebElement twitterElement =driver.findElement(twitterLink);
    	twitterElement.click();
    	logger.info("Click the twitter link");
    	scn.log("Click the twitter link");
    }
    
//============ 3. Method to validate twitter account page ===================================//
    public void twitterAcPage()
    {

    	wait= new WebDriverWait(driver, 20);
    	boolean p =wait.until(ExpectedConditions.titleIs(twitterPageTitle));
    	Assert.assertEquals(true, p);
    	logger.info("Validate twitter account page with its title, title is: "+ twitterPageTitle);
    	scn.log("navigate to twitter account page, page title is: "+ twitterPageTitle);
    }


    public void twitterAclinkValidation(String AcName)
    {
    	WebElement twitterAcLinkElement =driver.findElement(twitterAcLink);
    	
    	Assert.assertEquals(AcName, twitterAcLinkElement.getAttribute("href"));
    	logger.info("Validate twitter url is: "+twitterAcLinkElement.getAttribute("href"));
  scn.log("Validate twitter url  is: "+twitterAcLinkElement.getAttribute("href"));
    }
//=======================================================================================================================//
    

}
        


