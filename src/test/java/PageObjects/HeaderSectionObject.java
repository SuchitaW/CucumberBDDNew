package PageObjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class HeaderSectionObject {
	
	private static final Logger logger= LogManager.getLogger(HeaderSectionObject.class);
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Scenario scn;
	
	//============= Locators for Header section WebElements=====================================
		private By orderTrackingLink= By.xpath("//span[normalize-space()='Order Tracking']");
		private By orderTrackingAcLink= By.xpath("//a[@href=\"https://www.candere.com/ordertracking/ordertracking/index\"]");
		private By orderid= By.xpath("//input[@placeholder='ORDER ID *']");
		private By emailaddress= By.xpath("//input[@placeholder='EMAIL ADDRESS *']");
		private By RequestInfo= By.xpath("//button[normalize-space()='REQUEST INFO']");
	    private By search_text_box = By.xpath("//div[@id='search_control']");;
	    private By search_button = By.xpath("//input[@id='search']");
	
         private By nav_link_logo =  By.xpath("//img[@title='Candere']");
	private By nav_link_ordertracking =  By.xpath("//span[normalize-space()='Order Tracking']");
	private By nav_link_findexperiencecenter =  By.xpath("//span[normalize-space()='Find Experience Centre']");
	private By nav_link_contactUs =  By.xpath("//div[@class='item']//a[normalize-space()='Contact Us']");
	private By nav_link_blog =  By.xpath("//a[normalize-space()='Blog']");
	private By nav_link_user =  By.xpath("//div[@class='actions-custom']//img[@alt='user']");
	
	private By nav_link_wishlist =  By.xpath("//a[@class='wishlist-custom-icon customer-login-link']//*[name()='svg']");
	private By nav_link_bag =  By.xpath("//div[@class='link_items minicart-wrapper']");
	
	 private By product_link_list = By.xpath("//div[@class='header_top_menu']//li");
	 
	 
	//============= Expected Results ==========================================================//
	        String orderTrackingPageTitle= "Track Your Order";
			String ContactUsPageTitle= "Contact Us | Candere by Kalyan Jewellers";
			String BlogPageTitle="Blog";
			String BagPageTitle="Shopping Cart";
			String FindExperienceCenterPageTitle= "Experience Centre - Infiniti Mall, Malad - Candere By Kalyan Jewellers";
			
	
	//============= Constructor ===============================================================//
		public HeaderSectionObject (WebDriver driver,Scenario scn)
		{
			this.driver= driver;
			this.scn=scn;
		}
		
		public void SetSearchTextBox(String text) {
			WebDriverWait webDriverWait = new WebDriverWait(driver,20);
			WebElement elementSearchBox = webDriverWait.until(ExpectedConditions.elementToBeClickable(search_text_box));
			elementSearchBox.clear();
			elementSearchBox.sendKeys(text);
			logger.info("Value entered in search box: " + text);}

		public void ClickOnSearchButton() {
			driver.findElement(search_button).click();
			logger.info("Clicked on Search Button");
			
			
		}

		public void validateCandereLogo() {
			boolean b = driver.findElement(nav_link_logo).isDisplayed();
			Assert.assertEquals("Navigation link logo",true, b);
		}
		
		
		public void ClickOnOrderTracking() {
			driver.findElement(nav_link_ordertracking).click();
			logger.info("Clicked on Order Tracking");
			
			String actPageTitle= driver.getTitle();
	    	Assert.assertEquals(orderTrackingPageTitle, actPageTitle);
	        logger.info("Validate title of page, title is:" + actPageTitle);
			
		}
			
		public void ClickOnFindExperienceCenter() {
			driver.findElement(nav_link_findexperiencecenter).click();
			logger.info("Clicked on Find Experience Center");
			
			String actPageTitle= driver.getTitle();
	    	Assert.assertEquals(FindExperienceCenterPageTitle, actPageTitle);
	        logger.info("Validate title of page, title is:" + actPageTitle);
			
		}
			
		public void ClickOnContactUs() {
			driver.findElement(nav_link_contactUs).click();
			logger.info("Clicked on Contact Us");
			
			String actPageTitle= driver.getTitle();
	    	Assert.assertEquals(ContactUsPageTitle, actPageTitle);
	        logger.info("Validate title of page, title is:" + actPageTitle);
		}
			
		public void ClickOnBlog() {
			driver.findElement(nav_link_blog).click();
			logger.info("Clicked on Blog");
			
			String actPageTitle= driver.getTitle();
	    	Assert.assertEquals(BlogPageTitle, actPageTitle);
	        logger.info("Validate title of page, title is:" + actPageTitle);
		}
			
		public void ClickOnUser() {
			driver.findElement(nav_link_user).click();
			logger.info("Clicked on My Account");
		}
		public void ClickOnWishlist() {
			driver.findElement(nav_link_wishlist).click();
			logger.info("Clicked on Wishlist");
		}
		
		public void ClickOnBag() {
			driver.findElement(nav_link_bag).click();
			logger.info("Clicked on Shopping Bag");
			
			String actPageTitle= driver.getTitle();
	    	Assert.assertEquals(BagPageTitle, actPageTitle);
	        logger.info("Validate title of page, title is:" + actPageTitle);
		}
		
		public void validateElementPresentInHeaderSection(String text) throws Exception {
			boolean b=true;

			switch(text.toLowerCase().trim()) {

			case "Order Tracking":
				b = driver.findElement(nav_link_ordertracking).isDisplayed();
				break;
			case "Candere logo":
				b = driver.findElement(nav_link_logo).isDisplayed();
				break;
			case "Find Experience Center":
				b = driver.findElement(nav_link_findexperiencecenter).isDisplayed();
				break;
			case "Contact Us":
				b = driver.findElement(nav_link_contactUs).isDisplayed();
				break;
			case "Blog":
				b = driver.findElement(nav_link_blog).isDisplayed();
				break;
			case "User":
				b = driver.findElement(nav_link_user).isDisplayed();
				break;
			case "	":
				b = driver.findElement(nav_link_wishlist).isDisplayed();
				break;
				
			case "Bag":
				b = driver.findElement(nav_link_bag).isDisplayed();
				break;
			case "search text box":
				b = driver.findElement(search_text_box).isDisplayed();
				break;
			default:
				//logger.fatal("Header Link Description is  present in the case. Please add link description first.");
				//throw new Exception("Header Link Description is not present in the case. Please add link description first.");
			}

			if (b) {
				logger.info("Header Link is displayed: " + text);
				Assert.assertEquals("Header Link displayed",true, b);
			}else {
				logger.fatal("Header Link is not displayed: " + text);
				Assert.fail("Header Link is not displayed: " + text);
			}

		}

		public void validatePageTitleMatch(String expectedTitle) {
			
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			Boolean b = wait.until(ExpectedConditions.titleContains(expectedTitle));
			Assert.assertEquals("Title Validation",true, b);
			logger.info("Page title matched: " + expectedTitle );

		}

		 public String ClickOnTheProductLink(int productIndex){
		        //listOfProducts will have all the links displayed in the search box
		        List<WebElement> listOfProducts = driver.findElements(product_link_list);
		        logger.info("Number of products searched: " + listOfProducts.size());

		        //Link on the  link with argument productIndex
		        listOfProducts.get(productIndex).click();
		        logger.info("Clicked on the Link in the List with index: " + productIndex +
		                ". Link Text: " + listOfProducts.get(productIndex).getText());

		        //return the text of the clicked link if further validation is required.
		        return listOfProducts.get(productIndex).getText();

		    }
			
		
//==========================ordertracking=============
		
		
		public void setOrderTrackingLink()
	    {
	    	WebElement orderTrackingElement =driver.findElement(orderTrackingAcLink);
	    	orderTrackingElement.click();
	    	wait= new WebDriverWait(driver, 20);
 Assert.assertEquals(true, orderTrackingElement.isDisplayed());
	    	logger.info("Validate the order Tracking link");
	    }
		public void orderTrackingPage()
	    {

	    	wait= new WebDriverWait(driver, 20);
	    	boolean p =wait.until(ExpectedConditions.titleIs(orderTrackingPageTitle));
	    	Assert.assertEquals(true, p);
	    	logger.info("Validate order tracking page with its title, title is: "+ orderTrackingPageTitle);
	    	scn.log("navigate to order tracking page, page title is: "+ orderTrackingPageTitle);
	    }


	    public void orderTrackinglinkValidation(String Name)
	    {
	    	WebElement orderTrackingAcLinkElement =driver.findElement(orderTrackingAcLink);
	    	
	    	Assert.assertEquals(Name, orderTrackingAcLinkElement.getAttribute("href"));
	    	logger.info("Validate orderTracking url is: "+orderTrackingAcLinkElement.getAttribute("href"));
	  scn.log("Validate orderTracking  url  is: "+orderTrackingAcLinkElement.getAttribute("href"));
	    
	    }


}


