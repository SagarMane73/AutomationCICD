package Seleniumframeworkpart.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Seleniumframeworkpart.reusableorabstract.Abstractcomponent;

public class Confirmationpage extends Abstractcomponent{

	WebDriver driver;
	public Confirmationpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	public String getverifyconfirmationMessage()
	{
		return confirmationMessage.getText();
	}
	
}
