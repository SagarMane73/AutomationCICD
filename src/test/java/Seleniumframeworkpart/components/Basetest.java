package Seleniumframeworkpart.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Seleniumframeworkpart.pageobject.Landingpage;

public class Basetest {
	//previously we design pom here we design common data for test and here initializ driver for tests
	public WebDriver driver;
	public Landingpage landingpage;
	
	public WebDriver initializerDriver() throws IOException
	{
		Properties prop=new Properties();//parse file and extract all global related properties like key value pair.
		FileInputStream  fis=new FileInputStream("D:\\Selenium_Java_RahulTut\\Seleniumframeworkdesign\\src\\main\\java\\Seleniumframeworkpart\\resources\\Globaldata.properties");
		prop.load(fis); //bcz it takes path in file stream format 
		
		String browserName=	System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("broswer");  //this is using for maven cmd terminal run when pass diff type browsers from cmd, -Dbrowser means it takes variable from cmd in the form of "System.getProperty("browser")"  and prop means globaldata.properties file data take if we not pass any data from cmd, -Dbrowser use passing runtime parameters and -P means defining diff profiles like purchase.xml,errorvalidation.xml, testng.xml
//		String browserName=prop.getProperty("broswer");
		
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Admin\\Downloads\\chromedriver-win64\\chromedriver.exe");
			 driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			//firefox driver
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//edge driver
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException
	{
		String jsonContent= FileUtils.readFileToString(new File(filepath));
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		
		return data;
	}
	
	
	//for taking screenshot when test case failed.
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException //getting driver from test bcz driver has no life
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir") + "//reports//" + testCaseName +".png" ); //where screenshot will store so this is path
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";  //return path for screenshot
		
		
	}
	@BeforeMethod(alwaysRun = true)
	public Landingpage launchApplication() throws IOException
	{
		driver=initializerDriver();
		 landingpage=new Landingpage(driver);
		landingpage.goTo();
		return landingpage;
		
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.close();
	}
	
	
	

}
