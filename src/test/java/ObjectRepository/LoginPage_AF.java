package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	
}
