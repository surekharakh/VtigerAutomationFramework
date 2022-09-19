package vtiger.practice;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrganisationCreation {

	public static void main(String[] args) {
		//Step1 launch the browser
		
		WebDriver driver= new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("http://localhost:8888/");
		
		//Step 2 login to app
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		
		driver.findElement(By.name("user_password")).sendKeys("admin");
		
		driver.findElement(By.id("submitButton")).click();
		
		//3 click on organisation
		
		driver.findElement(By.linkText("Organizations")).click();
		
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		driver.findElement(By.name("accountname")).sendKeys("qsp");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
        driver.findElement(By.xpath("//img[@style='padding: 0px;padding-left:5px']")).click();
        
        driver.findElement(By.linkText("Sign Out")).click();
	}

}
