package Seleniumframeworkpart.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Seleniumframeworkpart.reusableorabstract.Abstractcomponent;

public class Landingpage extends Abstractcomponent{
	
	
	WebDriver driver;
	
	public Landingpage(WebDriver driver)
	{ super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); //initializes the elements (annotated with @FindBy)
	}
	
//	webelement and locators:
//	webelement:It's part of the Page Object Model (POM).Suitable for static elements in the Page Object Model.
//	locators:This approach is used when you don't want to tie the locator directly to a WebElement.Used for dynamic elements or when you need to perform custom actions like waiting for conditions.
	
	@FindBy(id="userEmail")   //internally it will work like driver.findElements(By.id(""));
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut'")
	WebElement errorMessage;
	
	public void loginApplication(String email,String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		//return new Productcatalog(driver);
	}
	
	

	
	public  void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	public String getErrorMessage()
	{  waitForWebElementToApply(errorMessage);
		return errorMessage.getText();
		
	}
}
