package ObjectRepository;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage_AF {
	
	WebDriver driver;
	public LoginPage_AF(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Please enter your email']")
	private WebElement userNameTextField;
	
	@FindBy(xpath = "//input[@value='PROCEED']")
	private WebElement proceedButton;
	
	@FindBy(xpath = "//input[@placeholder='Enter your password']")
	private WebElement passwordTextField;
	
	@FindBy(xpath = "//input[@value='Log In']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//img[@src='/images/icons/arrow_icon.png']")
	private WebElement dropDownButton;
	
	@FindBy(xpath = "//a[contains(text(),'Log Off')]")
	private WebElement logoutButton;
	
	@FindBy(xpath = "//img[@src='/assets/analytics/assets/arrow_icon.png']")
	private WebElement logoutDropDown;
	
	@FindBy(xpath = "//span[text()='Logout']")
	private WebElement adherenceLogOutButton;
	
	@FindBy(xpath = "//div[@id='interactive-close-button']")
	private WebElement adCancelButton;
	
	public void sendkeyToUserNameTextField(String key) {
		try {
			userNameTextField.sendKeys(key);
			System.out.println("Entered value in userName TextField");
		} catch (Exception e) {
			System.out.println("Not able to enter value in userName TextField "+e);
		}
	}
	
	public void clickOnProceedButton() {
		try {
			proceedButton.click();
			System.out.println("Clicked on Proceed Button");
		} catch (Exception e) {
			System.out.println("Not able to click on Proceed Button "+e);
		}
	}
	
	public void sendkeyToPasswordTextField(String key) {
		try {
			passwordTextField.sendKeys(key);
			System.out.println("Sendkey to Password TextField");
		} catch (Exception e) {
			System.out.println("Not able to sendkey to Password TextField "+e);
		}
	}
	
	public void clickOnLoginButton() {
		try {
			loginButton.click();
			System.out.println("Clicked on Login Button");
		} catch (Exception e) {
			System.out.println("Not able to click on Login Button "+e);
		}
	}
	
	public void clickOnDropDownButton() {
		try {
			dropDownButton.click();
			System.out.println("Clicked on DropDown Button");
		} catch (Exception e) {
			System.out.println("Not able to click on DropDown Button "+e);
		}
	}
	
	public void clickOnLogoutButton() {
		try {
			logoutButton.click();
			System.out.println("Clicked on logoutButton");
		} catch (Exception e) {
			System.out.println("Not able to click on logoutButton "+e);
		}
	}
	
	public void clickOnLogoutDropDown() {
		try {
			logoutDropDown.click();
			System.out.println("Clicked on logout DropDown");
		} catch (Exception e) {
			System.out.println("Not able to click on logout DropDown "+e);
		}
	}
	
	public void clickOnAdherenceLogOutButton() {
		try {
			adherenceLogOutButton.click();
			System.out.println("Clicked on LogOut Button ");
		} catch (Exception e) {
			System.out.println("Not able to click on LogOut Button "+e);
		}
	}
	
	public void clickOnAdCancelButton() {
		if(adCancelButton.isDisplayed()) {
			adCancelButton.click();
			System.out.println("clicked on ad cross button");
		}else {
			System.out.println("Ad is not appeared");
		}
	}
	
	public void cancelPopup() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		boolean popupClosed = false;

		List<WebElement> iframes = wait.until(
		        ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe"))
		);

		for (WebElement frame : iframes) {

		    driver.switchTo().frame(frame);

		    List<WebElement> closeButtons =
		            driver.findElements(By.id("interactive-close-button"));

		    if (!closeButtons.isEmpty() && closeButtons.get(0).isDisplayed()) {

		        WebElement closeBtn = closeButtons.get(0);

		        ((JavascriptExecutor) driver)
		                .executeScript("arguments[0].click();", closeBtn);

		        System.out.println("Popup closed using JS click");
		        popupClosed = true;

		        driver.switchTo().defaultContent();
		        break;

		    } else {
		        driver.switchTo().defaultContent();
		    }
		}

		if (!popupClosed) {
		    System.out.println("Popup not present, continuing execution");
		}

	}
}
