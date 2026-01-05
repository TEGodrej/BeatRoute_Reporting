package ObjectRepository;

import java.time.LocalDate;
import java.time.ZoneId;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageGracia {

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
	
    LocalDate yesterday = today.minusDays(1);
    
    LocalDate firstDayOfMonth = today.withDayOfMonth(1);
    int day=yesterday.getDayOfMonth();
	
	public void fromDate() {
		try {
			WebElement fromDate=driver.findElement(By.xpath("//span[text()='"+day+"']"));
			fromDate.click();
			System.out.println("selected fromDate: "+day );
		}catch (Exception e) {
			System.out.println("Not able to select From-Date"+day + e);
		}
	}
	
	public void select_To_Date(){
		try {
			WebElement To_Date=driver.findElement(By.xpath("//span[text()='"+day+"']"));
			To_Date.click();
			System.out.println("selected To_Date: "+day );
		} catch (Exception e) {
			System.out.println("Not able to select To-Date"+day + e);
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
