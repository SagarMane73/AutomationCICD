package Seleniumframeworkpart.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Seleniumframeworkpart.components.Basetest;
import Seleniumframeworkpart.pageobject.Cartpage;
import Seleniumframeworkpart.pageobject.Checkoutpage;
import Seleniumframeworkpart.pageobject.Confirmationpage;
import Seleniumframeworkpart.pageobject.Landingpage;
import Seleniumframeworkpart.pageobject.Orderpage;
import Seleniumframeworkpart.pageobject.Productcatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalonetest extends Basetest{
         //new comment added
	@Test(dataProvider ="getData",groups = {"purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
//		WebDriverManager.chromedriver().setup();
		
//		LOCATOR:A strategy to identify elements on a webpage. Purpose: Specifies how to find an element using attributes like ID, name, class, etc.
//		WEBELEMENT:Represents the actual element found on the webpage. Purpose: Allows interaction with the web element, such as clicking, sending keys, or retrieving text.
		/** code replace for test, Here this code replace with Basetest class initializerDriver() method
		 System.setProperty("webdriver.chrome.driver",
		
				"C:\\Users\\Admin\\Downloads\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		**/
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  //wait for page loading
		/**  IMP:below line replcae with goTo() method
		 driver.get("https://rahulshettyacademy.com/client"); **/
		
		/** code replcae for test here below code replace with launchApplication() method in Basetest class
		 Landingpage landingpage=new Landingpage(driver);
		landingpage.goTo();   **/
	
		landingpage.loginApplication(input.get("email"), input.get("password"));
		
		 /**  IMP:below code replaced with above lines 
		driver.findElement(By.id("userEmail")).sendKeys("sagarmne7777@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sagar@123");
		driver.findElement(By.id("login")).click(); **/
		
		Productcatalog productCatalog=new Productcatalog(driver);
		List<WebElement> products=productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("productname"));
		productCatalog.goToVartPage();
		
		Cartpage cartPage=new Cartpage(driver);
		Boolean match=cartPage.VerifyProductDisplay(input.get("productname"));
		Assert.assertTrue(match);
		cartPage.goToCheckout();
		
		Checkoutpage checkoutPage=new Checkoutpage(driver);
		checkoutPage.selectCountry("india");
		checkoutPage.submitOrder();
		
		Confirmationpage confirmationPage=new Confirmationpage(driver);
		String confirmMessage=confirmationPage.getverifyconfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	/**	IMP:below code replace with above code 
	    
	    //get all elements into list
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5)); //wait to load the elements on page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));   

	   List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));  //here get common class and get all elemnets into list
	   **/
	   
	  /**  IMP:this  code replace with above code
	   WebElement prod=   products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);//here we not apply findelement on driver bcz if we do this then it will search on whole page but we want to search only on limited area like "product" then it will search only for that specific element.
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); **/
	   
	/**  IMP:below code replcaed with above code
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));  **/
		
		/** IMP: below code replcae with above cart class object
		 WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.findElement(By.cssSelector("[routerlink*='cart'")).click();
		

		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase("ZARA COAT 3"));
	    Assert.assertTrue(match);
	    driver.findElement(By.cssSelector(".totalRow button")).click();  **/
	    
	/**IMP:below code replaced with above checkout and confirmation class 
	 * 	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
	    Actions a=new Actions(driver);
	    a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	    driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();//contains is method use to locate elements with partially matching attributes,
	    driver.findElement(By.cssSelector(".action__submit")).click();
	  String confirmMessage=  driver.findElement(By.cssSelector(".hero-primary")).getText();
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER.")); **/
	    
	    
		
		
/**The Page Object Model (POM) is a design pattern in Selenium testing used to organize and manage the structure of test code effectively.
 *  Here's why it is used:
Key Benefits of POM:
Code Reusability:

You can reuse the same page objects across multiple tests, reducing duplication.
Improved Readability and Maintenance:

All locators and methods for interacting with a page are stored in one place, making the code easier to understand and update.
Separation of Concerns:

The test logic (what to test) is separated from the page interaction logic (how to interact with elements).
Scalability:

Makes it easier to scale up your test suite as the application grows.

How It Works:
Page Classes: Each web page is represented by a class.
Locators: Defined using annotations like @FindBy or traditional By locators.
Methods: Encapsulate user actions (e.g., click, type) for the page.

Why Use It?
If your application UI changes, you only need to update the locators in the respective page class, not in all test cases. This ensures easier maintenance and minimizes the risk of test failures due to UI changes.
**/
	}
	
//	@Test(dependsOnMethods = {"submitOrder"})
//	public void orderHistoryTest()
//	{	 
//	Productcatalog productcatalog=	landingpage.loginApplication("sagarmne7777@gmail.com", "Sagar@123");
//Orderpage orderPage=	productcatalog.goToOrderPage();
//Assert.assertTrue(orderPage.VerifyOrderDisplay("ADIDAS ORIGINAL"));
//	
//	
//	}
	
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//return new Object[][] {{"sagarmne7777@gmail.com","Sagar@123","ADIDAS ORIGINAL"},{"sagarmne7777@gmail.com","Sagar@123","ADIDAS ORIGINAL"}};
	
	
//	OR bcz suppose large amount of data is presnet that time use hashmap 
//		HashMap<String,String> hm=new HashMap<String,String>();
//		hm.put("email", "sagarmne7777@gmail.com");
//		hm.put("password", "Sagar@123");
//		hm.put("productname", "ADIDAS ORIGINAL");
//		
//		HashMap<String,String> hm2=new HashMap<String,String>();
//		hm2.put("email", "anshika@gmail.com");
//		hm2.put("password", "Iamking@000");
//		hm2.put("productname", "ADIDAS ORIGINAL");
		
		
//		OR
		
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Seleniumframeworkpart//data//Purchaseorder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}

}
