package ObjectRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.openqa.selenium.By;
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
	
	@FindBy(id = "Username")
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
	
	@FindBy(xpath = "//mat-icon[text()='logout']")
	private WebElement logoutButton;
	
	
	
	LocalDate today = LocalDate.now();
	
    LocalDate yesterday = today.minusDays(1);
    
    LocalDate firstDayOfMonth = today.withDayOfMonth(1);
    int day=yesterday.getDayOfMonth();
    
    public void clickOnFromDate() throws InterruptedException {
    	 if (today.isEqual(firstDayOfMonth)) {
             // Click the "previous month" button
             WebElement previousMonthBtn = driver.findElement(By.xpath(
                 "//button[@class='mat-calendar-previous-button mdc-icon-button mat-mdc-icon-button mat-unthemed mat-mdc-button-base']"
             ));
             previousMonthBtn.click();

             // Small wait to allow calendar to load previous month
             Thread.sleep(1000);

             // Re-locate yesterday’s day in the *previous month’s calendar*
             
             WebElement yesterdays = driver.findElement(By.xpath("//div[text()=' " + day + " ']"));
             yesterdays.click();

         } else {
             // Same month → just click yesterday’s date
            
             WebElement yesterdays = driver.findElement(By.xpath("//div[text()=' " + day + " ']"));
             yesterdays.click();
         }
    }
	
	
	
	Date date = new Date();
	public void clikOnDateChange() {
		try {
	        SimpleDateFormat sim = new SimpleDateFormat("dd MMM, yyyy");
	        String day = sim.format(date);

	        WebElement dateChange = driver.findElement(By.xpath(
	            "//div[@id='activityDateFilterTrigger1']//span[@class='printDate'][normalize-space()='" + day + "']"));
	        dateChange.click();
	        
	        System.out.println("Clicked on date");
		}catch (Exception e) {
			System.out.println("Not able to click on date " + e);
		}
	}
	
	public void clickOnReportDate() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd");
	        String day1 = sdf.format(date);
	        WebElement reportDate = driver.findElement(By.xpath("//a[@data-date='" + day1 + "']"));
	        reportDate.click();
			
		}catch (Exception e) {
			System.out.println("Not able to click on date " + e);
		}
	}
	
	public void clickOnsecondReportDate() {
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
	        String day2 = sdf1.format(date);
	        WebElement reportDate1 = driver.findElement(By.xpath("//a[@data-date='" + day2 + "']"));
	        reportDate1.click();
		}catch (Exception e) {
			System.out.println("Not able to click on reportDate " + e);
		}
	}
	
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
