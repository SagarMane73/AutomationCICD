package Seleniumframeworkpart.Tests;

import java.io.IOException;

import org.testng.annotations.Test;

import Seleniumframeworkpart.components.Basetest;
import Seleniumframeworkpart.components.IRetryclass;
import Seleniumframeworkpart.pageobject.Productcatalog;
import junit.framework.Assert;

public class Errorvalidation extends Basetest{
	
	
	
	
	@Test(groups = {"Errorhandling"}, retryAnalyzer=IRetryclass.class)
	public void loginErrorValidation() throws InterruptedException, IOException {
		
	landingpage.loginApplication("sagarmne7777@3gmail.com", "Sagar@123");
	Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	
	
	}
	
	@Test
	public void Demo()
	{
		System.out.println("for console checking data:test error handling ");
	}

}
