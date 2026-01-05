package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginGracia {

	WebDriver driver;
	public LoginGracia(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@name='username']")
	private WebElement userNameTextField;
	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordTextField;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitButton;
	
	public void sendkeyToUserNameTextField(String key) {
		try {
			userNameTextField.click();
			userNameTextField.sendKeys(key);
			System.out.println("Entered UserName in textfield");
		} catch (Exception e) {
			System.out.println("Not able to enter UserName in textfield "+e);
		}
	}
	
	
	public void sendkeyToPasswordTextField(String key) {
		try {
			passwordTextField.click();
			passwordTextField.sendKeys(key);
			System.out.println("Entered password in textfield");
		} catch (Exception e) {
			System.out.println("Not able to enter password in textfield "+e);
		}
	}
	
	public void clickOnSubmitButton() {
		try {
			submitButton.click();
			System.out.println("Click on submitButton");
		} catch (Exception e) {
			System.out.println("Not able to click on submitButton "+e);
		}
	}
}
