package Vitiger.Contacts.Tests;

import java.io.IOException;

//End-TO-End scenario (Create contact with organization) 
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

public class CreateContactWithOrgTest {
	
	public static void main(String[] args) throws IOException {
		 WebDriver driver=null;
//Step1:Create object of all the utilities
		JavaUtility jUtil=new JavaUtility();
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		
//Step2:read all the data required for the test script
		String BROWSER = pUtil.readDataFromFile("browser");
		String URL = pUtil.readDataFromFile("url");
		String USERNAME = pUtil.readDataFromFile("username");
		String PASSWORD = pUtil.readDataFromFile("password");
		
		String ORGNAME = eUtil.readDataFromExcel("Contact", 4, 3)+jUtil.RandomNumber();//MESCOE
		String LASTNAME = eUtil.readDataFromExcel("Contact", 4, 2);//JADHAV
		
//Step 3:launch the browser
		 if(BROWSER.equalsIgnoreCase("chrome"))
        {
       	           //if browser and driver version is not same so at that time we get 
//selenium webdriverexception. so we have to again download driver executable file from google to avoid this, we want to autodownload driver executable file
//there is a seperate tool like webdriver manager. so dnld it from maven repo. type webdriver manager
         
        	driver = new ChromeDriver();
        	System.out.println("--- chrome browser launched ----");
        }
        else if(BROWSER.equalsIgnoreCase("Firefox"))
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

//Step4:login to app
       driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

//Step5:navigate to organizations link
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Accounts&action=index\"]")).click();
		
//Step6:click on create organization look up image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
//Step7:create organization with mandatory fields and save
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
//Step8:Validate for organization
		  String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();//here we validate that we are coming to that page or not
		
		  System.out.println(orgHeader);
		  if(orgHeader.contains(ORGNAME))
		  {
			  System.out.println("org created");
		  }
		  else
		  {
			  System.out.println("org not created");
		  }
//Step9:Navigate to contact link
		  
			  driver.findElement(By.xpath("//a[@href=\"index.php?module=Contacts&action=index\"]")).click();
		  

		
//Sttep 10:click on create contact lookup image
		  driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
//Step 11: create contact with organization and save
		  driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		  driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@alt='Select']")).click();
		  //by clicking on lookup of org one child window opens,so we move from parent to child
		  wUtil.switchToWindow(driver, "Accounts"); //here we take child win partial text
		  driver.findElement(By.name("search_text")).sendKeys(ORGNAME);//we inspect serach box and pass that ORGNAME REF VAR
		  //means in that our MESCOE value is there.and we search for that value
		  driver.findElement(By.name("search")).click();// for searching we click on search button
		  driver.findElement(By.linkText(ORGNAME)).click();// we get our org here so to click we pass the var
		  
		  //after clicking organization that we created we go to parent window from child window
		  wUtil.switchToWindow(driver, "Contacts");
		  
		  //save
		  driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			

		  
//Step 12:validate for contacts
		  String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText(); 
		  System.out.println(contactHeader);
		  if(contactHeader.contains(LASTNAME))
		  {
			  System.out.println("contact created--pass");
		  }
		  else
		  {
			  System.out.println("contact not created--Fail");
		  }
//step 13: logout
		  Actions actions=new Actions(driver);
			WebElement ele1=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			wUtil.waitForElementToBeClickable(driver, ele1);
			wUtil.mouseHoverOn(driver, ele1);
			driver.findElement(By.linkText("Sign Out")).click();
			System.out.println("sign out successful");
	
	}
	

}
