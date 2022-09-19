package vtiger.Organization.Tests;
//Organization Electronics Dropdown Using Utilities  
import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import vtiger.GenericUtility.ExcelFileUtility;
import vtiger.GenericUtility.JavaUtility;
import vtiger.GenericUtility.PropertyFileUtility;
import vtiger.GenericUtility.WebDriverUtility;

public class OrganizationElectronicsDropdownUsingUtilities {
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver=null;
		
//Step1:Create Object of all the utilities
		JavaUtility jUtility=new JavaUtility();
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
        WebDriverUtility wUtil=new WebDriverUtility();
        

        //step2:Read all the necessary data
        String Browser = pUtil.readDataFromFile("browser");
         String URL = pUtil.readDataFromFile("url");
         String USERNAME = pUtil.readDataFromFile("username");
         String PASSWORD = pUtil.readDataFromFile("password");
         
         String ORGNAME = eUtil.readDataFromExcel("Contact", 1, 2)+jUtility.RandomNumber();
         
         String Industry = eUtil.readDataFromExcel("Contact", 1, 4);
         System.out.println(Industry);
         
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

//Step 5: Navigate to organizations link
		
		driver.findElement(By.linkText("Organizations")).click();
		   
//Step 6: click on create organization lookup image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
				
//Step 7: Create organization with mandatory fields
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		WebElement element = driver.findElement(By.name("industry"));
		wUtil.handleDropDown(Industry, element);
//step 8:save
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	    Thread.sleep(2000);
//Step 9:Logout
	    
	    Actions actions=new Actions(driver);
		WebElement ele1=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.waitForElementToBeVisible(driver, ele1);
		wUtil.mouseHoverOn(driver, ele1);
		driver.findElement(By.linkText("Sign Out")).click();
		
		
	}

}
