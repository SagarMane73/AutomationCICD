package Seleniumframeworkpart.reusableorabstract;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Seleniumframeworkpart.pageobject.Cartpage;
import Seleniumframeworkpart.pageobject.Orderpage;

public class Abstractcomponent {

	WebDriver driver;
	
	public Abstractcomponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this); 
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToApply(By findBy) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30)); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForWebElementToApply(WebElement findBy) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30)); 
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
	}
	
	
	
	public void  waitForElementToDisappear(WebElement ele)
	{  	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30)); 
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public Cartpage goToVartPage()
	{
		cartHeader.click();
		Cartpage cartPage=new Cartpage(driver);
		return cartPage;
	}
	
	public Orderpage goToOrderPage()
	{
		orderHeader.click();
		Orderpage orderPage=new Orderpage(driver);
		return orderPage;
	}
}
