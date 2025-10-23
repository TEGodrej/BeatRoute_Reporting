package GenericUtility;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driverutility  {
	
	public WebDriver driver;
	public Driverutility(WebDriver driver) {
		this.driver = driver;
		
	}
	
	

	public static WebDriverWait wait;
	public void WaitToClick(int time, WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(time));
	    element = wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public void WaitTovisibility(int time,WebElement element) {
		wait=new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public void implicitlyWait(int time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false);",element);
	}
	
	public void scrollToCenter(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].scrollIntoView({block: 'top', inline: 'nearest'});", element);
		js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);


	}
	
	public void jumpToTopOfPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");

	}
	
	public void smoothScrollToTop() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll({top: 0, behavior: 'smooth'});");

	}
	
	public void scrollSpecificElementIntoTopView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'start', inline: 'nearest'});", element);

	}
	
	public void forceScrollPage(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, arguments[0].getBoundingClientRect().top + window.scrollY);", element);

	}
	
	public void scrollToElement(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void threadWait(int time) {
		try {
			Thread.sleep(Duration.ofSeconds(time));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void SelectByVisibleText(WebElement elem , String text) {
		Select select= new Select(elem);
		select.selectByVisibleText(text);
	}
	
	public void explicitWaitUntilClickable(int time, WebElement webelement) {
		WebDriverWait wait;
		wait=new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(webelement));
	}
	
	public void explicitWaitByLocator(int time , By locator) {
		WebDriverWait wait;
		wait=new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void explicitWaitForVisibility(int time , WebElement element) {
		WebDriverWait wait;
		wait=new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void ClickActionOnWebelement(WebElement element) {
		Actions action =new Actions(driver);
		action.moveToElement(element).click().perform();
	}
	
	public void doubleClickActionOnWebelement(WebElement element) {
		Actions action =new Actions(driver);
		action.moveToElement(element).doubleClick().perform();
	}
	
	public void moveToElementByAction(WebElement element) {
		Actions action= new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	public void actionClick(WebElement element) {
		Actions action = new Actions(driver);
		action.click(element).click().perform();
	}
	
	public void contextClick(WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element);
	}
	
	public void forceClik(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",element );
	}
	
	public void allowPopUp() {
	    try {
	        // Switch to the browser alert popup
	        Alert alert = driver.switchTo().alert();

	        // Accept the popup (click "OK")
	        alert.accept();

	        System.out.println("Browser popup accepted successfully.");
	    } catch (NoAlertPresentException e) {
	        System.out.println("No browser popup is present.");
	    }
	}
	
	public void allowPopUpjs() {
//	    JavascriptExecutor js = (JavascriptExecutor) driver;
//	    js.executeScript("window.confirm = function(msg){ return true; };");
		
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.default_content_setting_values.notifications", 1);
		options.setExperimentalOption("prefs", prefs);
	}
	
	public void robotClick(int x, int y) throws AWTException {
		Robot robot = new Robot();
		robot.mouseMove(x, y); // screen coordinates
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

	}
	
	public void scrollByAction(WebElement element) {
		Actions action = new Actions(driver);
		action.scrollToElement(element);
	}
	
	

}
