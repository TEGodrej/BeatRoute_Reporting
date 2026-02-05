package ObjectRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtility.BaseClass_Gracia;

public class HomePageGracia extends BaseClass_Gracia{

	WebDriver driver;
	public HomePageGracia(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()='Transactions']")
	private WebElement transactionButton;
	
	@FindBy(xpath = "//button[@aria-label='Calendar']")
	private WebElement calenderButton;
	
	@FindBy(xpath = "//button[text()='Apply Filters']")
	private WebElement applyFilter;
	
	@FindBy(xpath = "//button[@title='Download filtered data']")
	private WebElement downloadButton;
	
	
	LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
	DateTimeFormatter formatter =
	        DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
    String yesterday = today.minusDays(1).format(formatter);
    
//    LocalDate firstDayOfMonth = today.withDayOfMonth(1);
//    int day=yesterday.getDayOfMonth();
	
	public void fromDate() {
		try {
			WebElement fromDate=driver.findElement(By.xpath("//span[contains(@aria-label,'"+yesterday+"')]"));
			fromDate.click();
//			driverutility. forceClik(fromDate);
			System.out.println("selected From-Date: "+yesterday );
		}catch (Exception e) {
			System.out.println("Not able to select From-Date "+yesterday + e);
		}
	}
	
	public void select_To_Date(){
		try {
			WebElement To_Date=driver.findElement(By.xpath("//span[contains(@aria-label,'"+yesterday+"')]"));
			To_Date.click();
//			driverutility.forceClik(To_Date);
			System.out.println("selected To_Date: "+yesterday );
		} catch (Exception e) {
			System.out.println("Not able to select To-Date "+yesterday + e);
		}
	}
	
	public void clickOnTransactionButton() {
		try {
			transactionButton.click();
			System.out.println("Clicked on Transaction Button");
		} catch (Exception e) {
			System.out.println("Not able to click on transactionButton "+e);
		}
	}
	
	public void clickOnCalenderButton() {
		try {
			calenderButton.click();
			System.out.println("clicked on calender Button ");
		} catch (Exception e) {
			System.out.println("Not able to click on calender Button "+e);
		}
	}
	
	public void clickOnApplyFilter() {
		try {
			applyFilter.click();
			System.out.println("Clicked on Apply_Filter");
		} catch (Exception e) {
			System.out.println("Not able to click on Apply_Filter "+e);
		}
	}
	
	public void clickOnDownloadButton() {
		try {
			downloadButton.click();
			System.out.println("Click on Download Button");
		} catch (Exception e) {
			System.out.println("Not able to click on Download Button");
		}
	}
}
