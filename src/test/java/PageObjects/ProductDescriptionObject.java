package PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class ProductDescriptionObject {
	private static final Logger logger= LogManager.getLogger(CommonPageObjects.class);
	WebDriver driver;
	Scenario scn;
	WebDriverWait wait;
	//============= Locators for WebElements on Landing page============================================//
	
	  private By elementSearchBoxElement = By.xpath("//input[@id='search']");
	    private By searchButtonElement = By.xpath("//button[@title='Search']");

	private By logoImage= By.xpath("//img[@title='Candere']");
	private By prodCatgory= By.xpath("//div[@class='header_top_menu']//li");
	
	
	
	
	

}
