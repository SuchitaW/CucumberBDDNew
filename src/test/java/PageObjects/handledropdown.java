package PageObjects;

import java.awt.Desktop.Action;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class handledropdown {
	
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.candere.com/jewellery/gold-rings.html");
		
		driver.manage().window().maximize();
		
	WebElement designType=driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[5]"));
//		Actions act =new Actions(driver);
//		act.moveToElement(designType).build().perform();
	designType.click();
		
		
		//selectChoiceValues(driver, "choice1");
	
	selectChoiceValues(driver, "all");
		
		selectChoiceValues(driver, "choice1","choice2 ","choice4 ");
		//SelectchoiceValue - 1 choice
		//SelectchoiceValue -4 choice
		//SelectchoiceValue -all choice
		
		
		//ol[@class='items am-filter-items-attr_product_design_type  -am-multiselect']//li[@class='item filter_customcheckbox__']
	
		//List<WebElement> productdesignType=driver.findElements(By.xpath("//ol[@class='items am-filter-items-attr_product_design_type  -am-multiselect']//li[@class='item filter_customcheckbox__']"));
	
	    // System.out.println(productdesignType.size());
	
	}
	
	
	public static void selectChoiceValues(WebDriver driver,String... value) throws InterruptedException
	
	{
		List<WebElement> choiceList=driver.findElements(By.xpath("//*[@id=\"narrow-by-list\"]/div[5]/dd/form/ol//li"));
		
		System.out.println(choiceList.size());
		
			for(int i=0;i<choiceList.size();i++) {
				
           System.out.println(choiceList.get(i).getText());
		   if(choiceList.get(i).getText().contains("Ethnic"))
          {

           choiceList.get(i).click();

                break;
           }}}}
		
//          choiceList.get(i).click();
//		
//		}
//		if(!value[0].equalsIgnoreCase("all"))
//		{
//		for(WebElement item:choiceList)
//			{
//			String text=item.getText();{
//									for(String val:value)
//						{
//							if(text.equals(val))
//							{
//							item.click();
//							break;
//						}
//						}
//			}
//		}
//
//	else {
//		for(WebElement item:choiceList)
//		{
//			item.click();
//			
//		}
//		Thread.sleep(5000);
//		}
//	}
//	



