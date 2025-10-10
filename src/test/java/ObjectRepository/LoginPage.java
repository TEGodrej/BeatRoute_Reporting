package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtility.BaseClass;

public class LoginPage extends BaseClass{
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath  = "//input[@placeholder='User ID']")
	private WebElement username;
	
	@FindBy(xpath = "//input[@value='PROCEED']")
	private WebElement proceedButton;
	
	@FindBy(id = "Password")
	private WebElement passwordTextField;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement logInButton;
	
	@FindBy(xpath = "//body[1]/div[5]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/ul[1]/li[2]/div[1]/div[1]/i[1]")
	private WebElement activityLog;
	
	@FindBy(xpath = "//a[contains(@href,'/download/CSV/logRequest/type/activity')]")
	private WebElement generateFile;
	
	@FindBy(xpath = "//a[@href='/download/CSV/getLast/type/activity']")
	private WebElement downloadButton;
	
	@FindBy(xpath = "//mat-icon[normalize-space(text())='logout']")
	private WebElement logoutButton;

	
	public void SendkeyToUserName(String key) {
		try {
			username.sendKeys(key);
			System.out.println("Entered value in username");
		}catch (Exception e) {
			System.out.println("Not able to enter value in username " + e);
		}
	}
	
	public void clickOnProceedButton() {
		try {
			proceedButton.click();
			System.out.println("Clicked on Proceed Button");
		}catch (Exception e) {
			System.out.println("Not able to click on Proceed Button "+ e);
		}
	}
	
	public void sendkeyToPasswordTextField(String key) {
		try {
			passwordTextField.sendKeys(key);
			System.out.println("Entered value in Password TextField");
		}catch (Exception e) {
			System.out.println("Not able to enter value in Password TextField " + e);
		}
	}
	
	public void clickOnLogInButton() {
		try {
			logInButton.click();
			System.out.println("Clicked on logIn Button");
		}catch (Exception e) {
			System.out.println("Not able to click on logIn Button " + e);
		}
	}
	
	public void clickOnLogoutButton() {
		try {
			logoutButton.click();
			System.out.println("Clicked on logoutButton");
		}catch (Exception e) {
			System.out.println("Not able to click on logoutButton "+ e);
		}
	}

}
