package StepDef;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import PageObjects.CommonPageObjects;
import PageObjects.FooterSectionObject;
import PageObjects.HeaderSectionObject;
import PageObjects.SignInPageObject;
import core.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefFile {
	
	
	private static final Logger logger = LogManager.getLogger(StepDefFile.class);
	WebDriver driver;

	WebDriverWait wait;
	JavascriptExecutor js;
	Scenario scn;
	String base_url = "https://www.candere.com/  ";
	
	

//============== Declare object reference of pageObjects classes =============================//
	
	CommonPageObjects cmnPageObject;
	FooterSectionObject footerObject;
	SignInPageObject signInObject;
	HeaderSectionObject headerObject;
//====================== Before Hook =========================================================//	
	@Before
	public void setUp(Scenario scn)
	{
		this.scn=scn;

		//Get the desired browser to be invoked
		String browserName= WebDriverFactory.getBrowserName();
		driver=WebDriverFactory.getWebDriverForBrowser(browserName); 
		scn.log("Browser get invoked");

		//Initialize object of page objects classes 
		 cmnPageObject= new CommonPageObjects(driver, scn);
		 footerObject= new FooterSectionObject(driver, scn);
		 signInObject= new SignInPageObject(driver, scn);
		 headerObject= new HeaderSectionObject(driver, scn);
	}

//====================== After Hook =========================================================//
	
	@After(order=2)
	//Capture screenshot if test case get failed
	public void captureScreenshot(Scenario scn)
	{
		if(scn.isFailed())
		{
			TakesScreenshot srnshot= ((TakesScreenshot)driver);
			byte [] data =srnshot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "Name of failed step is: "+ scn.getName());
			scn.log("Attach a screenshot as step get failed");
		}
		else
		{
			scn.log("Test case get passed, no screenshot is captured");
		}
	}
	
	@After(order=1)
	//Quit the browser
	public void tearDown(Scenario scn)
	{
		WebDriverFactory.quitTheBrowser();
		scn.log("Browser is quit");
	}
	
//====================== Background ================================================//
	@Given("User navigate to URL and open the landing page")
	public void user_navigate_to_url_and_open_the_landing_page() {
		WebDriverFactory.navigateToURL(base_url);
	    }

//===================== Scenarios =================================================//

	@When("User is on landing page")
	public void user_is_on_landing_page() {
		logger.info("User is on landing page after navigating to base URL");
	    scn.log("User is on landing page after navigating to base URL");
	}
	@Then("Validate current URL of landing page with expected URL")
	public void validate_current_url_of_landing_page_with_expected_url() {
		 cmnPageObject.validatePageURL();
		 scn.log("Validate current URL is: "+ driver.getCurrentUrl());
	}
	
	@Then("Validate title of landing page with expected title as {string}")
	public void validate_title_of_landing_page_with_expected_title_as(String titleOfPage) {
		cmnPageObject.validatePageTitle(titleOfPage);
		scn.log("Validate page title is: "+ driver.getTitle());
	}
		
	@Then("User see the logo of application")
	public void user_see_the_logo_of_application() {
		cmnPageObject.displayLogo();
		scn.log("Display the application logo on landing page");
	}
	
	@When("fetch the height of logo")
	public void fetch_the_height_of_logo() {
		cmnPageObject.fetchLogoHeight();
	}

	@Then("Height of logo should be {string}")
	public void height_of_logo_should_be(String height) {
		cmnPageObject.logoHeightValid(height);
	}
		
	@When("fetch the width of logo")
	public void fetch_the_width_of_logo() {
		 
		cmnPageObject.fetchLogoWidth();
		
	}

	@Then("Width of logo should be {string}")
	public void width_of_logo_should_be(String width) {
		cmnPageObject.logoWidthValid(width);
	}
	
	@Given("User see SignIn button")
	public void user_see_sign_in_button()
	{
		signInObject.signInBtnValidation();
		
		scn.log("Display the signIn Button");
	}

	@When("User click on SignIn button")
	public void user_click_on_sign_in_button() throws InterruptedException {
		Thread.sleep(3000);
		signInObject.clickOnSignInBtn();
		scn.log("User click on signIn Button");
	}
	@Then("User is on signIn page which have expected page title as {string}")
	public void user_is_on_sign_in_page_which_have_expected_page_title_as(String SignInPageTitle) {
		signInObject.validateSignInPage(SignInPageTitle);
	}

	@Given("User click on twitter link")
	public void user_click_on_twitter_link() throws Exception {
		footerObject.setTwitterLink();
		Thread.sleep(3000);
		footerObject.clickOnTwitterLink();
	}

	@When("navigate to twitter account page")
	public void navigate_to_twitter_account_page() {
		footerObject.twitterAcPage();
	}
	@Then("User opened with twitter url {string}")
	public void user_opened_with_twitter_url(String AcName) {
	    
	footerObject.twitterAclinkValidation(AcName);
	}	
	
	 @Given("User navigated to the home application url")
	    public void user_navigated_to_the_home_application_url() {
	        WebDriverFactory.navigateToURL(base_url);
	        scn.log("Browser navigated to URL: " + base_url);

	        String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
	        headerObject.validatePageTitleMatch(expected);
	    }

//	    


//-----------------------------@CreateAccount--------------------------------------------


@When("User see Your Account and click on  SignUp  from home page")
public void user_see_your_account_and_click_on_sign_up_from_home_page() {
    signInObject.clickOnSignInBtn();
    signInObject.clickOnSignUp();
    
}

@When("User redirected to create account page of the application where title us {string}")
public void user_redirected_to_create_account_page_of_the_application_where_title_us(String SignInPageTitle) {
    signInObject.validateSignInPage(SignInPageTitle);
}

@When("User enters  {string}  and  {string} and {string}")
public void user_enters_and_and(String name, String emailid, String mobileno) {
WebElement nameInputFieldElement = driver.findElement(By.xpath("//input[@id='firstname']"));
nameInputFieldElement.sendKeys(name);

WebElement emailIdInputFieldElement = driver.findElement(By.xpath("//input[@id='popup-email_address']"));
emailIdInputFieldElement.sendKeys(emailid);

WebElement mobilenoInputFieldElement = driver.findElement(By.xpath("//input[@id='phone']"));
mobilenoInputFieldElement.sendKeys(mobileno);

}

@When("User click on Create Account")
public void user_click_on_create_account() throws InterruptedException {
	WebElement createaccountButtonElement = driver.findElement(By.xpath("//span[normalize-space()='create account']"));
	createaccountButtonElement.click();
	
	//Thread.sleep(50000);
}

@Then("User successfully redirected to {string}")
public void user_successfully_redirected_to(String SignInPageTitle) throws Exception {
	Thread.sleep(3000);
	signInObject.validateSignInPage(SignInPageTitle);
	signInObject.validationtakescreenshot(scn);
	
	
	
}


//------------------------- @ProductCategory----------------------------------------------------------------------------

@When("User see the product category")
public void user_see_the_product_category() {
   cmnPageObject.setProdCategory();
}

@Then("Validate product category as per expected product category listed below")
public void validate_product_category_as_per_expected_product_category_listed_below(List<String> prodList1) {
    cmnPageObject.validateProdCategory(prodList1);
    scn.log("Validate the product category with expected datatable");
}

@Then("Size of product category should be {int}")
public void size_of_product_category_should_be(Integer prodCatCount) {
    cmnPageObject.sizeOfProdCategory(prodCatCount);
}

//---------------------------------- @loginPositive @smoke-----------------------------------	

@When("User see Your Account and click on  Log In  from home page")
public void user_see_your_account_and_click_on_log_in_from_home_page() throws Exception {
	signInObject.clickOnSignInBtn();
	Thread.sleep(3000);
	signInObject.clickOnLogIn();
}

@When("User enters {string}")
public void user_enters(String Emailidmobileno) {
	WebElement nameInputFieldElement = driver.findElement(By.xpath("//input[@id='email-login-one']"));
	nameInputFieldElement.sendKeys(Emailidmobileno);

}

@When("User click on continue button")
public void user_click_on_continue_button() throws InterruptedException {
	WebElement continueButtonElement = driver.findElement(By.xpath("//button[@id='send2-login-one']"));
	continueButtonElement.click();
	WebElement a=driver.findElement(By.xpath("//p[normalize-space()='OTP has been sent successfully.']"));  
	 String s = a.getText();
	 scn.log(s);
    System.out.println("Alert text is: " + s); 
    Thread.sleep(10000);
}

@When("User enter otp manually and click on login")
public void user_enter_otp_manually_and_click_on_login() throws InterruptedException {
	Thread.sleep(10000);  
	WebElement loginButtonElement = driver.findElement(By.xpath("//form[@id='customer-popup-login-form-three']//button[@name='send']"));
	loginButtonElement.click();
	scn.log("User enter otp manually");
	
	
}

@Then("User successfully redirected to homepage {string}")
public void user_successfully_redirected_to_homepage(String SignInPageTitle) throws Exception {
  Thread.sleep(3000);
	signInObject.validateSignInPage(SignInPageTitle);
//	signInObject.validationtakescreenshot(scn);
}

//-------------------------- @FooterLinksLists-------------------------------------------------
@When("user scroldown to the botton of the landing page of the application")
public void user_scroldown_to_the_botton_of_the_landing_page_of_the_application() throws InterruptedException {
	WebElement AboutUsElement = driver.findElement(By.xpath("//p[normalize-space()='ABOUT US']"));
     JavascriptExecutor js=((JavascriptExecutor) driver);


	js.executeScript("arguments[0].scrollIntoView(true);", AboutUsElement);

	Thread.sleep(5000);
}
@When("user is able to see {int} main options categories")
public void user_is_able_to_see_main_options_categories(int footerMainCatCount) {

	List<WebElement> footerMainCatListEle = driver.findElements(By.xpath("//div[@class='desktop_footer_link_item']//div[1]//nav[1]//p[1]"));
	Assert.assertEquals(footerMainCatCount, footerMainCatListEle.size());
}

@When("the categories having the option {string}, {string}, {string}, {string},{string} and {string}")
public void the_categories_having_the_option_and(String categoryOneNameExp, String categoryTwoNameExp, String categoryThreeNameExp, String categoryFourNameExp, String categoryFiveNameExp, String categorySixNameExp) {
	List<WebElement> footerMainCatListEle = driver.findElements(By.xpath("//div[@class='desktop_footer_link_item']//div[1]//nav[1]//p[1]"));
	Assert.assertEquals(categoryOneNameExp, footerMainCatListEle.get(0).getText());
	Assert.assertEquals(categoryTwoNameExp, footerMainCatListEle.get(1).getText());
	Assert.assertEquals(categoryThreeNameExp, footerMainCatListEle.get(2).getText());
	Assert.assertEquals(categoryFourNameExp, footerMainCatListEle.get(3).getText());
	Assert.assertEquals(categoryFiveNameExp, footerMainCatListEle.get(4).getText());
	Assert.assertEquals(categorySixNameExp, footerMainCatListEle.get(5).getText());
}




@Then("under ABOUT US category below options are visible")
public void under_about_us_category_below_options_are_visible(List<String> expectedAboutUsOptions) {
	List<WebElement> AboutUsOptionsActElement = driver.findElements(By.xpath("//div[@class='row_group flex_group']//div[1]//a"));

	for (int i = 0; i < expectedAboutUsOptions.size(); i++) {
		if (expectedAboutUsOptions.get(i).equals(AboutUsOptionsActElement.get(i).getText())) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
		
	}
}

@Then("under WHY CANDERE? category below options are visible")
public void under_why_candere_category_below_options_are_visible(List<String> expectedWHYCANDEROptions) {
	List<WebElement> WHYCANDEREOptionsActElement = driver.findElements(By.xpath("//div[@class='row_group flex_group']//div[2]//a"));

	for (int i = 0; i < expectedWHYCANDEROptions.size(); i++) {
		if (expectedWHYCANDEROptions.get(i).equals(WHYCANDEREOptionsActElement.get(i).getText())) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
	}}

@Then("under EXPERIENCE CANDERE category below options are visible")
public void under_experience_candere_category_below_options_are_visible(List<String> expectedEXPERIENCECANDEREOptions) {
	List<WebElement> EXPERIENCECANDEREOptionsActElement = driver.findElements(By.xpath("//div[@class='row_group flex_group']//div[3]//a"));

	for (int i = 0; i < expectedEXPERIENCECANDEREOptions.size(); i++) {
		if (expectedEXPERIENCECANDEREOptions.get(i).equals(EXPERIENCECANDEREOptionsActElement.get(i).getText())) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
	}}

@Then("under JEWELLERY GUIDES category below options are visible")
public void under_jewellery_guides_category_below_options_are_visible(List<String> expectedJEWELLERYGUIDESOptions) {

	List<WebElement> JEWELLERYGUIDESOptionsActElement = driver.findElements(By.xpath("//div[@class='row_group flex_group']//div[4]//a"));

	for (int i = 0; i < expectedJEWELLERYGUIDESOptions.size(); i++) {
		if (expectedJEWELLERYGUIDESOptions.get(i).equals(JEWELLERYGUIDESOptionsActElement.get(i).getText())) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
	}}

@Then("under CONTACT US category below options are visible")
public void under_contact_us_category_below_options_are_visible(List<String> expectedCONTACTUSOptions) {
	List<WebElement> CONTACTUSOptionsActElement = driver.findElements(By.xpath("//div[@class='row_group flex_group']//div[5]//a"));

	for (int i = 0; i < expectedCONTACTUSOptions.size(); i++) {
		if (expectedCONTACTUSOptions.get(i).equals(CONTACTUSOptionsActElement.get(i).getText())) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
	}}


//-------------------------@ProdDesc	--------------------------------------------------------------------

@When("User Search for product on search bar {string}")
public void user_search_for_product_on_search_bar(String prodName) throws Exception {

	cmnPageObject.searchProduct(prodName);
	
	Thread.sleep(4000);

}

@Then("Search Result page is displayed with title contains {string}")
public void search_result_page_is_displayed_with_title_contains(String prodName1) throws Exception {
	
	 cmnPageObject.validateSearchResult(prodName1);
	 Thread.sleep(4000);
}


//-------------------------@addtocart--------------------------------------------------------------------

@When("User mousehover on Earring option")
public void user_mousehover_on_earring_option() {
	 WebElement earring=driver.findElement(By.xpath("//a[@class='parent_'][normalize-space()='Earrings']"));
		Actions act=new Actions (driver);
		act.moveToElement(earring).perform();
		logger.info("Done Mouse hover on 'Earring' from Menu");
	    scn.log("Done Mouse hover on 'Earring' from Menu");
}

@When("User see the various catagory on screen")
public void user_see_the_various_catagory_on_screen(List<String> subcategory) throws InterruptedException {
	
List<WebElement> subCategoryElement = driver.findElements(By.xpath("//*[@id=\"store.links\"]/nav[1]/div/div/div/div/ul/li[3]/div/div/div[1]/div/p"));
//Thread.sleep(3000);
	for (int i = 0; i < subcategory.size(); i++) {
		if (subcategory.get(i).equals(subCategoryElement.get(i).getText())) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
	}}

@When("User select Shop by Material Gold and click on it")
public void user_select_shop_by_material_gold_and_click_on_it() {
	WebElement subMenuOption = driver.findElement(By.xpath("//a[@href='https://www.candere.com/jewellery/gold-earrings.html']")); 
	//Mouse hover menuOption 'DGRP
	subMenuOption.click();
	logger.info("Various types of gold earring is display");
	 scn.log("Various types of gold earring is display");
}
@Then("The product list page is displayed")
public void the_product_list_page_is_displayed() {
	
	String productlistPageTitle= "Shop latest designs of gold earrings online from Candere by Kalyan Jewellers";
	//Latest Designs of Gold Earrings Online - Candere by Kalyan Jewellers
	WebDriverWait wait = new WebDriverWait(driver, 30);
	boolean p =wait.until(ExpectedConditions.titleIs(productlistPageTitle));
	Assert.assertEquals(true, p);
	logger.info("Validate product list page with its title, title is: "+ productlistPageTitle);
	scn.log("navigate to product list page , page title is: "+ productlistPageTitle);
}
@When("User click on any product")
public void user_click_on_any_product() throws InterruptedException {

	List<WebElement> firstProd = driver.findElements(By.xpath("//ol[@class=\"products list items product-items\"]//li//div"));
	// But as this step asks click on any link, we can choose to click on Index 0 of
	// the list
	firstProd.get(0).click();
	 Thread.sleep(3000);
	logger.info("Product description page is display");
	scn.log("User clicked on a product");
}



@Then("Product Description is displayed in new tab")
public void product_description_is_displayed_in_new_tab() {
	Set<String> handles = driver.getWindowHandles(); // get all the open windows
	Iterator<String> it = handles.iterator(); // get the iterator to iterate the elements in set
	String original = it.next();// gives the parent window id
	String prodDescp = it.next();// gives the child window id

	driver.switchTo().window(prodDescp); // switch to product Descp
	String productdescPageTitle= "Anomaa Tushi Kyra Gold Earrings";
  WebDriverWait wait = new WebDriverWait(driver, 30);
	boolean p =wait.until(ExpectedConditions.titleIs(productdescPageTitle));
	Assert.assertEquals(true, p);
	logger.info("Validate product list page with its title, title is: "+ productdescPageTitle);
	scn.log("navigate to product list page , page title is: "+ productdescPageTitle);

}

@When("User enters {string} and click on Check button")
public void user_enters_and_click_on_check_button(String pincode) throws Exception {
	WebElement pin = driver.findElement(By.xpath("//input[@id='eedpincode']"));
	pin.sendKeys(pincode);
	Thread.sleep(3000);
	
	WebElement checkbutton = driver.findElement(By.xpath("//*[@id=\"check-eed\"]"));
	checkbutton.click();
}


@When("User see the Expected Delivery Date")
public void user_see_the_expected_delivery_date() throws Exception {
	WebElement e=driver.findElement(By.xpath("//span[@class='productDeliveryDateMsg edd']"));  
	 String s1 = e.getText();
	 scn.log(s1);
   System.out.println("Alert text is: " + s1); 
   Thread.sleep(5000);

}
@When("User click on add to cart and see the cart page")
public void user_click_on_add_to_cart_and_see_the_cart_page() throws InterruptedException {
	WebElement addtocart = driver.findElement(By.xpath("//button[@id='product-addtocart-button']"));
	addtocart.click();
	Thread.sleep(7000);
}
@When("user click on checkout logIn pop is open")
public void user_click_on_checkout_log_in_pop_is_open() throws InterruptedException {
	WebElement checkout = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[7]/div[2]/div[2]/button"));
	checkout.click();
	Thread.sleep(7000);
}


@Then("User click on continue payment page is display")
public void user_click_on_continue_payment_page_is_display() {
	WebElement continue1 = driver.findElement(By.xpath("//button[@class='action primary']"));
	continue1.click();
	
	
}

@When("Shipping page is open then user click on continue")
public void shipping_page_is_open_then_user_click_on_continue() throws Exception {
	WebElement continue2 = driver.findElement(By.xpath("//*[@id=\"opc-sidebar\"]/div[1]/button"));
	continue2.click();
	Thread.sleep(5000);
}


@When("User see the payment page and click on UPI")
public void user_see_the_payment_page_and_click_on_upi() throws Exception {
	WebElement upi = driver.findElement(By.xpath("//*[@id=\"payment_section_payment_page\"]/div[2]/div/div/div[1]/div[4]"));
	upi.click();
	Thread.sleep(5000);
}
@When("User enter {string} and click on placeorder")
public void user_enter_and_click_on_placeorder(String UPIId) throws Exception {
	WebElement UPIID = driver.findElement(By.xpath("//input[@id='vpa']"));
	UPIID.sendKeys(UPIId);
	Thread.sleep(3000);
}


@Then("User see the cander Justpay page")
public void user_see_the_cander_justpay_page() throws Exception {
	WebElement Placeorder = driver.findElement(By.xpath("//div[@class='payment-method _active']//button[@type='submit']"));
	Placeorder.click();
	Thread.sleep(13000);
	signInObject.validationtakescreenshot(scn);

}

@When("Click on proceed to check out after ACT")
public void click_on_proceed_to_check_out_after_act() throws Exception {
//	WebElement checkoutsign =driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[7]/div[2]/div[2]/button"));
//	//Thread.sleep(2000);
//	checkoutsign.click();
//	Thread.sleep(14000);
	
	
	WebElement checkoutsign =driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[7]/div[2]/div[2]/button"));
	
	wait= new WebDriverWait(driver,50);
	wait.until(ExpectedConditions.elementToBeClickable(checkoutsign));
	checkoutsign.click();
	logger.info("Click on the signIn Button");
	scn.log("Click on the signIn Button");
	
	
}


@When("user click on checkout signup pop is open")
public void user_click_on_checkout_signup_pop_is_open() throws Exception {
	WebElement signup1 =driver.findElement(By.xpath("//*[@id=\"customer-popup-registration\"]/span"));
	signup1.click();
	Thread.sleep(5000);
}



@When("User see login page and new user click on  SignUp  from checkout")
public void user_see_login_page_and_new_user_click_on_sign_up_from_checkout() throws Exception {
	WebElement signup1 =driver.findElement(By.xpath("//*[@id=\"customer-popup-registration\"]/span"));
	//signup1.click();
	
	Thread.sleep(3000);
	//signInObject.clickOnSignUp();
}

@When("Shipping page is open then user fill all details {string}  and  {string} and {string} and {string}  and  click on continue")
public void shipping_page_is_open_then_user_fill_all_details_and_and_and_and_click_on_continue(String lastname, String mobile, String zip, String flat ) throws Exception {
	
	WebElement Lastname =driver.findElement(By.xpath("(//input[@name='lastname'])[1]"));
	Lastname.sendKeys(lastname);
	
	WebElement MobileNumber =driver.findElement(By.xpath("(//input[@name='telephone'])[1]"));
	MobileNumber.sendKeys(mobile);
	
	WebElement ZipPostalCode =driver.findElement(By.xpath("(//input[@name='postcode'])[1]"));
	ZipPostalCode .sendKeys(zip);
	
	Thread.sleep(4000);
	
	WebElement Flat =driver.findElement(By.xpath("(//input[@name='street[0]'])[1]"));
	Flat .sendKeys(flat );

	WebElement cont =driver.findElement(By.xpath("//button[@class='action primary']"));
	cont.click();
	
	Thread.sleep(3000);
	
}



@When("User validate all headerlink are clickable or not")
public void user_validate_all_headerlink_are_clickable_or_not() throws Exception {
	headerObject.ClickOnOrderTracking();
	Thread.sleep(3000);
    headerObject.ClickOnBlog();
    Thread.sleep(3000);
   driver.navigate().back();
   //Thread.sleep(3000);
   headerObject.ClickOnFindExperienceCenter();
   Thread.sleep(3000);
   headerObject.ClickOnContactUs();
   Thread.sleep(3000);
   headerObject.ClickOnSearchButton();
   Thread.sleep(3000);
   headerObject.ClickOnUser();
   Thread.sleep(3000);
   headerObject.ClickOnWishlist();
   Thread.sleep(3000);
   driver.navigate().back();
   headerObject.ClickOnBag();
   Thread.sleep(3000);
   driver.navigate().back(); 
   
}

@Then("Below header Links are displayed")
public void below_header_links_are_displayed(List<String> list) throws Exception {
	for (int i=0;i<list.size();i++) {
		headerObject.validateElementPresentInHeaderSection(list.get(i));	
		
}}


@When("User search for {string}")
public void user_search_for(String prodName) throws Exception {
	cmnPageObject.searchProduct(prodName);
//Thread.sleep(3000);
}

@When("choose to buy the first item")
public void choose_to_buy_the_first_item() throws Exception {
	
	List<WebElement> firstProd = driver.findElements(By.xpath("//*[@id=\"amasty-shopby-product-list\"]/div[2]/ol/li"));
	// But as this step asks click on any link, we can choose to click on Index 0 of
	// the list
	firstProd.get(0).click();
	 Thread.sleep(3000);
	logger.info("Product description page is display");
	scn.log("User clicked on a product");
	
	Set<String> handles = driver.getWindowHandles(); // get all the open windows
	Iterator<String> it = handles.iterator(); // get the iterator to iterate the elements in set
	String original = it.next();// gives the parent window id
	String prodDescp = it.next();// gives the child window id

	driver.switchTo().window(prodDescp); // switch to product Descp
	
	
	


}
@When("Add product to shopping cart")
public void add_product_to_shopping_cart() throws InterruptedException {
	WebElement addtocart = driver.findElement(By.xpath("//button[@id='product-addtocart-button']"));
	addtocart.click();
	Thread.sleep(5000);

}
@When("User clicks on continue shopping")
public void user_clicks_on_continue_shopping() throws Exception {
	WebElement continueshopping = driver.findElement(By.xpath("//span[normalize-space()='Continue Shopping']"));
	JavascriptExecutor js=((JavascriptExecutor) driver);
    js.executeScript("arguments[0].scrollIntoView(true);", continueshopping);
	continueshopping.click();
	Thread.sleep(5000);
}
@When("User clicks on My shopping bag and view shopping bag")
public void user_clicks_on_my_shopping_bag_and_view_shopping_bag() throws Exception {
	WebElement minibag = driver.findElement(By.xpath("//div[@class='link_items minicart-wrapper']"));
	Actions act =new Actions(driver);
	act.moveToElement(minibag).build().perform();
	WebElement viewshoppingbag = driver.findElement(By.xpath("//a[@class='action viewcart']"));
	viewshoppingbag.click();
	//Thread.sleep(3000);
}
@When("Click on proceed to check out")
public void click_on_proceed_to_check_out() throws InterruptedException {
	Thread.sleep(3000);
WebElement checkout1 = driver.findElement(By.xpath("//button[@title='Checkout Securely']"));
checkout1.click();
	
	Thread.sleep(5000);
	
}
@Then("User should be asked to login before checkout")
public void user_should_be_asked_to_login_before_checkout() throws Exception {
Thread.sleep(3000);
String checkOutPageTitle= "Shopping Cart";
	WebDriverWait wait = new WebDriverWait(driver, 50);
	boolean p =wait.until(ExpectedConditions.titleIs(checkOutPageTitle));
	Assert.assertEquals(true, p);
	logger.info("Validate checkout page with its title, title is: "+ checkOutPageTitle);
	scn.log("navigate to checkout page , page title is: "+ checkOutPageTitle);

signInObject.validationtakescreenshot(scn);
}

//==============================wishlist=============


@When("User is logged onto the Candere website as a registered user.")
public void user_is_logged_onto_the_candere_website_as_a_registered_user() throws Exception {
	signInObject.clickOnSignInBtn();
	Thread.sleep(3000);
	signInObject.clickOnLogIn();
}

@When("Choose to add product to wishlist")
public void choose_to_add_product_to_wishlist() throws InterruptedException {
	List<WebElement> wishlistprod = driver.findElements(By.xpath("//a[@title='Add to Wish List']"));
wishlistprod.get(0).click();
Thread.sleep(5000);

	//wishlistprod.get(1).click();
	// Thread.sleep(5000);
	logger.info("Product added to wishlist");
	scn.log("User clicked on a product");
}
@When("Click on wishlist icon")
public void click_on_wishlist_icon() throws Exception {
	 Thread.sleep(5000);
	WebElement wishlisticon= driver.findElement(By.xpath("//a[@class='wishlist-custom-icon customer-login-link']//*[name()='svg']"));
	wishlisticon.click();
	
	 Thread.sleep(5000);
}
@When("Move this item to bag")
public void move_this_item_to_bag() throws Exception {
	WebElement movetobag= driver.findElement(By.xpath("//button[@type='button'][normalize-space()='move to bag']"));
	
	movetobag.click();
	Thread.sleep(5000);
	logger.info("Product added to wishlist");
	scn.log("User clicked on a product");

}


//==========Suggestion=========================



@When("User enters a text in search box")
public void user_enters_a_text_in_search_box() {

	WebElement searchbox= driver.findElement(By.xpath("//input[@id='search']"));
	
	searchbox.sendKeys("solitaire");
	
}

@When("User is navigated to search results")
public void user_is_navigated_to_search_results() {
List<WebElement> searchbox1= driver.findElements(By.xpath("//*[@id=\"klevu_acList\"]/li/a"));
	
	System.out.println(searchbox1.size());
	signInObject.validationtakescreenshot(scn);
}




@Then("User click on any suggestion link")
public void user_click_on_any_suggestion_link() throws InterruptedException {
	List<WebElement> searchListAutoComplete= driver.findElements(By.xpath("//div[@id='klevuAutoCompleteArea']//li//a[1]"));
	  System.out.println("Auto Suggest List ::" + searchListAutoComplete.size());
	Thread.sleep(3000);
	  for (WebElement ele : searchListAutoComplete) {
     String searchListAutoComplete1 = ele.getText();
     System.out.println(searchListAutoComplete1);
		  
	      if (searchListAutoComplete1.equalsIgnoreCase("diamond ring")) {
	          ele.click();
	          
	          break;
	      }}}






@Then("Suggestion Search Result box is displayed")
public void suggestion_search_result_box_is_displayed() throws Exception {
  List<WebElement> searchListAutoComplete= driver.findElements(By.xpath("//div[@id='klevuAutoCompleteArea']//li//a[1]"));
  System.out.println("Auto Suggest List ::" + searchListAutoComplete.size());
  String item = "diamond ring";
  for (WebElement ele : searchListAutoComplete) {
	  //Thread.sleep(3000);
      String searchTexts = ele.getText();
      System.out.println(searchTexts);
      
      if (searchTexts.contains(item)) {
          ele.click();
      }}
	}

@When("User click on ordertracking")
public void user_click_on_ordertracking() throws Exception {
	WebElement orderTracking =driver.findElement(By.xpath("//span[normalize-space()='Order Tracking']"));
	orderTracking.click();
	Thread.sleep(5000);
	headerObject.orderTrackingPage();

}

@When("User enter {string}  and  {string}")
public void user_enter_and(String orderid, String emailaddress) throws InterruptedException {
	WebElement OrderId =driver.findElement(By.xpath("//input[@placeholder='ORDER ID *']"));
	OrderId.sendKeys(orderid);
	Thread.sleep(5000);
	WebElement EmailAddress =driver.findElement(By.xpath("//input[@placeholder='EMAIL ADDRESS *']"));
	EmailAddress.sendKeys(emailaddress);
	
}
@When("User click on request info")
public void user_click_on_request_info() throws Exception {
	WebElement Requestinfo =driver.findElement(By.xpath("//button[normalize-space()='REQUEST INFO']"));
	Requestinfo.click();
	Thread.sleep(5000);
	js= (JavascriptExecutor)driver;
	//js.executeScript("arguments[0].scrollIntoView(true);", orderTrackingElement);
	js.executeScript("window.scrollBy(0,350)", "");
	
	signInObject.validationtakescreenshot(scn);
}


@Then("User opened with order tracking url {string}")
public void user_opened_with_order_tracking_url(String Name) {
	headerObject.orderTrackinglinkValidation(Name);
}

//============================Subscribe now======================

@When("User scroldown to the botton of the landing page of the application")
public void User_scroldown_to_the_botton_of_the_landing_page_of_the_application() throws Exception {
	WebElement offerUpadate = driver.findElement(By.xpath("//div[@class='title_']"));
    JavascriptExecutor js=((JavascriptExecutor) driver);


	js.executeScript("arguments[0].scrollIntoView(true);", offerUpadate);

	Thread.sleep(5000);
}


@When("User enters {string} and {string}")
public void user_enters_and(String emailid , String mobileno) throws Exception {

	WebElement EmailId =driver.findElement(By.xpath("//input[@id='newsletter']"));
	EmailId.sendKeys(emailid);
	Thread.sleep(3000);
	WebElement Mobileno =driver.findElement(By.xpath("//input[@id='mobile']"));
	Mobileno.sendKeys(mobileno);
}


@When("User is click on subscribe now")
public void user_is_click_on_subscribe_now() throws Exception {
	WebElement Subscribenow =driver.findElement(By.xpath("//span[normalize-space()='Subscribe Now']"));
	Subscribenow.click();
	Thread.sleep(5000);


}
@Then("Subscribe now message is displayed")
public void subscribe_now_message_is_displayed() {

	//expected error text
    String exp = "Thank you for your subscription.";
    //identify actual error message
    WebElement m = driver.findElement(By.xpath("//div[@class='newsletter-ajax-error']"));
    String act = m.getText();
    System.out.println(" Message is: "+ act);
    //verify error message with Assertion
    Assert.assertEquals(exp, act);
    logger.info("Validate Subscribe now, message is: "+ act);
scn.log("Navigate to Subscribe now , page message is: "+ act);
}

@When("User mouseover to the Ring of the landing page of the application")
public void user_mouseover_to_the_ring_of_the_landing_page_of_the_application() throws Exception {
WebElement ring=driver.findElement(By.xpath("//a[@class='parent_'][normalize-space()='Rings']"));
Actions act=new Actions(driver);
act.moveToElement(ring).build().perform();
//Thread.sleep(10000);

}

@When("User is able to see {int} sub categories")
public void user_is_able_to_see_sub_categories(int prodsubcatagory) {
	
	
	List<WebElement> prodMainCatListEle1 = driver.findElements(By.xpath("//*[@id=\"store.links\"]/nav/div/div/div/div/ul/li[2]/div/div/div[1]/div/p"));
    Assert.assertEquals(prodsubcatagory,prodMainCatListEle1.size());

}

@When("The categories having the option {string}, {string} and {string}")
public void the_categories_having_the_option_and(String categoryOneNameExp1, String categoryTwoNameExp1, String categoryThreeNameExp1) {
	List<WebElement> prodMainCatListEle1 = driver.findElements(By.xpath("//*[@id=\"store.links\"]/nav/div/div/div/div/ul/li[2]/div/div/div[1]/div/p"));

	Assert.assertEquals(categoryOneNameExp1, prodMainCatListEle1.get(0).getText());
Assert.assertEquals(categoryTwoNameExp1, prodMainCatListEle1.get(1).getText());
Assert.assertEquals(categoryThreeNameExp1, prodMainCatListEle1.get(2).getText());




}

@When("User see view all designs link should be visible")
public void user_see_view_all_designs_link_should_be_visible() throws Exception {
WebElement viewdesigns= driver.findElement(By.xpath("//*[@id=\"store.links\"]/nav/div/div/div/div/ul/li[2]/div/div/div[2]/div/a"));
viewdesigns.isDisplayed();
//Thread.sleep(5000);

}

@Then("Under Shop by Style category below options are visible")
public void under_shop_by_style_category_below_options_are_visible(List<String> Shopbystylelist) throws Exception {
	
	List<WebElement> Shopbystyle = driver.findElements(By.xpath("//*[@id=\"store.links\"]/nav[1]/div/div/div/div/ul/li[2]/div/div/div[1]/div[1]/a"));
	//Thread.sleep(5000);
	for (int i = 0; i <Shopbystylelist.size(); i++) {
		if (Shopbystylelist.get(i).equals(Shopbystyle .get(i).getText())) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
		
		}
	//Thread.sleep(5000);
}
@Then("Under Shop by Material category below options are visible")
public void under_shop_by_material_category_below_options_are_visible(List<String> ShopbyMaterialList) {
	List<WebElement> ShopbyMaterial = driver.findElements(By.xpath("//*[@id=\"store.links\"]/nav/div/div/div/div/ul/li[2]/div/div/div[1]/div[2]//a"));

for(int i=0;i<ShopbyMaterialList.size();i++)
{
	if(ShopbyMaterialList.get(i).equals(ShopbyMaterial .get(i).getText())) {
		Assert.assertTrue(true);
	}
	else {
		Assert.fail();
	}
}

}

@Then("Under Shop for category below options are visible")
public void under_shop_for_category_below_options_are_visible(List<String> ShopforList) {
	List<WebElement> ShopFor = driver.findElements(By.xpath("//*[@id=\"store.links\"]/nav/div/div/div/div/ul/li[2]/div/div/div[1]/div[3]/a"));

	for(int i=0;i<ShopforList.size();i++) {
		if(ShopforList.get(i).equals(ShopFor.get(i).getText())) {
			Assert.assertTrue(true);
		}
		else {
			Assert.fail();
		}
	}
	
}
	
//=====================Offers=========================================

   @When("User mouseover to the Offers of the landing page of the application")
	public void user_mouseover_to_the_offers_of_the_landing_page_of_the_application() throws Exception {
	   WebElement offers=driver.findElement(By.xpath("//a[normalize-space()='Offers']"));
	   Actions act=new Actions(driver);
	   act.moveToElement(offers).build().perform();
	   Thread.sleep(5000);
	}

	   @When("User are able to see all the offer product and click on any offer")
	   public void user_are_able_to_see_all_the_offer_product_and_click_on_any_offer() {
		   List<WebElement> offersprod = driver.findElements(By.xpath("//*[@id=\"store.links\"]/nav/div/div/div/div/ul/li[12]/div/div/div/div/a/img"));
		   signInObject.validationtakescreenshot(scn);
		    offersprod.get(0).click();
			logger.info("Product offer");
		     scn.log("User see all the product offers");
	   }

	@Then("User successfully redirected to product list page {string}")
	public void user_successfully_redirected_to_product_list_page(String PLPPageTitle) {
		wait= new WebDriverWait(driver,20);
		
    boolean xyz = wait.until(ExpectedConditions.titleIs(PLPPageTitle));
        Assert.assertEquals(true, xyz);
    	logger.info("Validate title of offers page, so title is: "+ PLPPageTitle);
    	scn.log("Validate title of offers page, so title is: "+ PLPPageTitle);
	}


	
	
}
	





























