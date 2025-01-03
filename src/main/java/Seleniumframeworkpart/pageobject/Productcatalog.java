package Seleniumframeworkpart.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Seleniumframeworkpart.reusableorabstract.Abstractcomponent;

public class Productcatalog extends Abstractcomponent{
	
	
WebDriver driver;
	
	public Productcatalog(WebDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); 
	}

	
	@FindBy(css=".mb-3")  //this use for getting all webelement
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")  
	WebElement spinner;
	
	By productsBy=By.cssSelector(".mb-3");     //this use for appearing webelement
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToApply(productsBy);
		return products;
		
	}
	
	public WebElement getProductByName(String productName)
	{
		   WebElement prod=   products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);//here we not apply findelement on driver bcz if we do this then it will search on whole page but we want to search only on limited area like "product" then it will search only for that specific element.
return prod;
	}
	
	
	public void addProductToCart(String productName)
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToApply(toastMessage);
		waitForElementToDisappear(spinner);
		
	}
	
	
}
