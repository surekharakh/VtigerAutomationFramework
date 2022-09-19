package Vitiger.Contacts.Tests;
//CreateContact using all the utilities.
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import vtiger.GenericUtility.ExcelFileUtility;
import vtiger.GenericUtility.JavaUtility;
import vtiger.GenericUtility.PropertyFileUtility;
import vtiger.GenericUtility.WebDriverUtility;

public class CreateContactTest1 {
public static void main(String[] args) throws IOException {
		
		WebDriver driver=null;
				
				//Step 1: Create object of all the utilities
				
		        JavaUtility jUtil=new JavaUtility();
		        PropertyFileUtility pUtil=new  PropertyFileUtility();
		        ExcelFileUtility eUtil=new ExcelFileUtility();
		        WebDriverUtility wUtil=new WebDriverUtility();
		        
		        //step2:Read all the necessary data
		        String Browser = pUtil.readDataFromFile("browser");
		         String URL = pUtil.readDataFromFile("url");
		         String USERNAME = pUtil.readDataFromFile("username");
		         String PASSWORD = pUtil.readDataFromFile("password");
		         
		         String LASTNAME = eUtil.readDataFromExcel("Contact", 1, 2)+jUtil.RandomNumber();
		         
		         String LEADSOURCE = eUtil.readDataFromExcel("Contact", 1, 3);
		         System.out.println(LEADSOURCE);
		         
		       //Step 3: launch the browser -- run time polyporphism
		         if(Browser.equalsIgnoreCase("chrome"))
		         {
		        	           //if browser and driver version is not same so at that time we get 
		//selenium webdriverexception. so we have to again download driver executable file from google to avoid this, we want to autodownload driver executable file
		//there is a seperate tool like webdriver manager. so dnld it from maven repo. type webdriver manager
		          
		         	driver = new ChromeDriver();
		         	System.out.println("--- chrome browser launched ----");
		         }
		         else if(Browser.equalsIgnoreCase("Firefox"))
		         {
		         	driver = new FirefoxDriver();
		         	System.out.println("---- Firefox browser launched----");
		         }
		         else
		         {
		         	System.out.println("-invalid browser name-");
		         	driver = new ChromeDriver();
		         	System.out.println("--- chrome browser launched ----");
		         }
		         
		        wUtil.maximizeWindow(driver);
		        wUtil.waitForDOMLoad(driver);
		        driver.get(URL);
		        
		       // step4: login to app
		        
		        driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
		      //step5:navigate to contacts
				driver.findElement(By.linkText("Contacts")).click();
		     
				//step 6:click on create contact lookup image
				driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
				
				//Step 7:Create contact with mandatory fields
				driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
				WebElement element = driver.findElement(By.name("leadsource"));
				wUtil.handleDropDown(LEADSOURCE, element);
				
				//step 8:save
			    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

				//step9:Logout
			    Actions actions=new Actions(driver);
				WebElement elem=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				wUtil.waitForElementToBeVisible(driver, elem);
				wUtil.mouseHoverOn(driver, elem);
				driver.findElement(By.linkText("Sign Out")).click();


		       
		      	}
}
