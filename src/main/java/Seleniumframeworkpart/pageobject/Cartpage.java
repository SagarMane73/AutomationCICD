package Seleniumframeworkpart.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Seleniumframeworkpart.reusableorabstract.Abstractcomponent;

public class Cartpage extends Abstractcomponent{

	WebDriver driver;
	public Cartpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	return match;
	}
	
	public void goToCheckout()
	{
		checkoutEle.click();
	}

}
