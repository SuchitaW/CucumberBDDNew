package PageObjects;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.Scenario;

public class SignInPageObject {

	
	private static final Logger logger= LogManager.getLogger(SignInPageObject.class);
	WebDriver driver;
	WebDriverWait wait;
	Scenario scn;
	
//============= Locators for WebElements for SignIn activity =========================================//

	private By signInBtn= By.xpath("//div[@class='link_items actions-custom customer-links']");
	private By logIn= By.id("customer-popup-sign-in");
	private By signUp= By.xpath("//div[@class='sign-in customer-login-link u_l_ico']//a[@id='customer-popup-registration']");

//============= Constructor ========================================================================//
	public SignInPageObject(WebDriver driver,Scenario scn)
	{
		this.driver= driver;
		this.scn=scn;
	}
	
//============ 1. Method to validate SignIn Button =================================================//
    public void signInBtnValidation()
    {
    	WebElement signIn =driver.findElement(signInBtn);
    	Assert.assertEquals(true, signIn.isDisplayed());
    	logger.info("Validate the signIn Button");
    	scn.log("Validate the signIn Button");
    }
    
//============ 2. Method to click on SignIn Button =================================================//


    
    public void clickOnSignInBtn()
    {
    	
    	WebElement signIn =driver.findElement(signInBtn);
    	
    	wait= new WebDriverWait(driver,50);
    	wait.until(ExpectedConditions.elementToBeClickable(signIn));
    	signIn.click();
    	logger.info("Click on the signIn Button");
    	scn.log("Click on the signIn Button");
    	
    }
    
    
//============ 3. Method to validate SignIn page title =============================================//
    public void validateSignInPage(String SignInPageTitle)
    {
    	wait= new WebDriverWait(driver,20);
    	boolean xyz = wait.until(ExpectedConditions.titleIs(SignInPageTitle));
        Assert.assertEquals(true, xyz);
    	logger.info("Validate title of signIn page, so title is: "+ SignInPageTitle);
    	scn.log("Validate title of signIn page, so title is: "+ SignInPageTitle);
    }

	public void clickOnSignUp() {
     WebElement signup =driver.findElement(signUp);
    	
    	wait= new WebDriverWait(driver,50);
    	wait.until(ExpectedConditions.elementToBeClickable(signup));
    	signup.click();
    	logger.info("Click on the signUp Button");
    	scn.log("Click on the signUp Button");
		
	}
	
	public void validationtakescreenshot(Scenario scn) {
		
		TakesScreenshot srnshot= ((TakesScreenshot)driver);
		byte [] data =srnshot.getScreenshotAs(OutputType.BYTES);
		scn.attach(data, "image/png", "Name of passed step is: "+ scn.getName());
		scn.log("Attach a screenshot as step get passed");	
	}
	
	public void clickOnLogIn() {
	     WebElement login =driver.findElement(logIn);
	    	
	    	wait= new WebDriverWait(driver,50);
	    	wait.until(ExpectedConditions.elementToBeClickable(login));
	    	login.click();
	    	logger.info("Click on the logIn Button");
	    	scn.log("Click on the logIn Button");
			
		}
	
}
    	
//==================================================================================================//
	
	
	

