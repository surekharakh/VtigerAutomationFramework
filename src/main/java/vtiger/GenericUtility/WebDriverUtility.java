package vtiger.GenericUtility;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.analysis.function.StepFunction;
import org.apache.poi.ss.formula.ThreeDEval;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument;
/**
 * This class will contain all the generic methods related webdriver actions 

 * @author Garvit16
 *
 */

public class WebDriverUtility {
	
	/**
	 * This method will maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	/**
	 * This method will wait for 20 sec for entire DOM will load
	 * @param driver
	 */
	public void waitForDOMLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	/**
	 * This method will wait for an elemnet to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will wait for an elemnet to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method is a custom wait where it will wait for the particular element
	 * @param element
	 * @throws InterruptedException
	 */
	public void waitAndClickOnElement(WebElement element) throws InterruptedException {
		int count=0;
		while(count<10) {
			try {
				element.click();
			}
			catch (Exception e) {
				Thread.sleep(1000);
				count++;
			}
		}
	}
	
	/**
	 * This method will handle dropdown using select class based on index
	 * @param element
	 * @param index
	 */
	public void handleDropDown(WebElement element,int index) {
		 Select s=new Select(element);
		 s.deselectByIndex(index);
	}
	
	/**
	 * This method will handle dropdown using select class based on visible text
	 * @param element
	 * @param visibleText
	 */
	public void handleDropDown(WebElement element,String visibleText) {
		 Select s=new Select(element);
		 s.selectByVisibleText(visibleText);
	}
	/**
	 * This method will handle dropdown using select class based on value
	 * @param value
	 * @param element
	 */
	public void handleDropDown(String value,WebElement element) {
		 Select s=new Select(element);
		 s.selectByValue(value);
	}
	/**
	 * This method will perform mouse over action on perticular element
	 * @param driver
	 * @param element
	 */
	public void mouseHoverOn(WebDriver driver,WebElement element) {
		Actions c=new Actions(driver);
		c.moveToElement(element).perform();
	}
	/**
	 * This method will perform double click on the page
	 * @param driver
	 */
	public void doubleClick(WebDriver driver) {
		Actions c=new Actions(driver);
		c.doubleClick().perform();
	}
	
	/**
	 * This method will perform double click on perticular element
	 * @param driver
	 * @param element
	 */
	public void doubleClick(WebDriver driver,WebElement element) {
		Actions c=new Actions(driver);
		c.doubleClick(element).perform();
	}
	/**
	 * This method will perform right click on the page
	 * @param driver
	 */
	public void rightClick(WebDriver driver) {
		Actions c=new Actions(driver);
		c.contextClick().perform();
	}
	
	/**
	 * This method will perform right click on perticular element
	 * @param driver
	 * @param element
	 */
	public void rightClick(WebDriver driver,WebElement element) {
		Actions c=new Actions(driver);
		c.contextClick(element).perform();
	}
	
	/**
	 * This method will perform drag and drop to source location to target location
	 * @param driver
	 * @param srcElement
	 * @param targetElement
	 */
	public void dragAndDropOn(WebDriver driver,WebElement srcElement,WebElement targetElement) {
		Actions c=new Actions(driver);
		c.dragAndDrop(srcElement,targetElement).perform();
	}
	/**
	 * This method will press and release the enter key
	 * @throws AWTException
	 */
	public void pressEnter() throws AWTException {
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	/**
	 * This method will acccept the altert popup
	 * @param driver
	 */
	public void acceptAlter(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method will cancel the altert popup
	 * @param driver
	 */
	public void dismissAlter(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method will get the text of altert popup
	 * @param driver
	 */
	public String getTheTextOfAlter(WebDriver driver) {
		String text=driver.switchTo().alert().getText();
		return text;
	}
	/**
	 * This method will handle frame based on index
	 * @param driver
	 * @param index
	 */
	public void handleFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method will handle frame based on name or id
	 * @param driver
	 * @param nameorid
	 */
	public void handleFrame(WebDriver driver,String nameorid) {
		driver.switchTo().frame(nameorid);
	}
	
	/**
	 * This method will handle frame based on frame element
	 * @param driver
	 * @param ele
	 */
	public void handleFrame(WebDriver driver,WebElement ele) {
		driver.switchTo().frame(ele);
	}
	
	/**
	 * This method will switch the control back to immidiate parent
	 * @param driver
	 */
	public void toPrentFrame(WebDriver driver) {
		driver.switchTo().parentFrame();
	}
	/**
	 * This method will come out of all the frames
	 * @param driver
	 */
	public void toDefaultWindow(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method will switch from one window to another based on patial window title
	 * @param driver
	 * @param partialWinTitle
	 */
	public void switchToWindow(WebDriver driver,String partialWinTitle) {
		//Step 1: get all The window ids
		
		Set<String> allWindow = driver.getWindowHandles();
		
		//Step 2: itrate through all the window ids
		
		Iterator<String> itr = allWindow.iterator();
		
		//Step 3: Navigate each window and check the title
	
	while (itr.hasNext()) {
		//capture the individual window id
		String winId=itr.next();
		String currentTitle=driver.switchTo().window(winId).getTitle();
		if(currentTitle.contains(partialWinTitle)) {
			
			break;
		}
	}
	}
	
	public String takeScreenshot(WebDriver driver,String scrshotname) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String path=".\\screenshots\\"+scrshotname+".png";
		File dest=new File(path);
		FileUtils.copyFile(src ,dest);
		return dest.getAbsolutePath();//used for reporting
	}
	
	/**
	 * This method will scroll down for 500 units
	 * @param driver
	 */
	public void scrollAction(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("Window.scrollBy(0,500)","");
	}
	
	
	public void scrollAction(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",element);
		int y=element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")", element);
	}
	

}
