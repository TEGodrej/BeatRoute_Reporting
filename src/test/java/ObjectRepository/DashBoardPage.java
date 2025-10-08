package ObjectRepository;

import java.time.Duration;
import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtility.BaseClass;



public class DashBoardPage extends BaseClass{
	
	WebDriver driver;
	public DashBoardPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//input[@value='toDate']")
	private WebElement toDateButton;
	
	@FindBy(xpath = "//mat-label[text()='Scan']")
	private WebElement sacnTab;
	
	@FindBy(xpath = "//mat-label[text()='Liquidation']")
	private WebElement liquidationTab;
	
	@FindBy(xpath = "(//span[@title='Advanced filters'])[1]")
	private WebElement filterIcon;
	
	@FindBy(xpath = "//li[text()=' Custom date range ']")
	private WebElement CustomDateRange;
	
	@FindBy(xpath = "//button[text()='Apply ']")
	private WebElement applyButton;
	
	@FindBy(xpath = "//i[@class='fa fa-download scASFIconStyle scCMCenterXY']")
	private WebElement downloadTab;
	
	@FindBy(xpath = "//div[@class='scASFDropdownListItems']/child::span[text()='Liquidation log']")
	private WebElement liquidationLog;
	
	@FindBy(xpath = "//button[text()='Download ']")
	private WebElement downloadButton;
	
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
             Thread.sleep(Duration.ofSeconds(4).toMillis());

             // Re-locate yesterday’s day in the *previous month’s calendar*
             
             WebElement yesterdays = driver.findElement(By.xpath("//div[text()=' " + day + " ']"));
             yesterdays.click();

         } else {
             // Same month → just click yesterday’s date
            
             WebElement yesterdays = driver.findElement(By.xpath("//div[text()=' " + day + " ']"));
             yesterdays.click();
         }
    }
    
    public void clickOnToDate() throws InterruptedException {
    	 if (today.isEqual(firstDayOfMonth)) {
             // Click the "previous month" button
             WebElement previousMonthBtn = driver.findElement(By.xpath(
                 "//button[@class='mat-calendar-previous-button mdc-icon-button mat-mdc-icon-button mat-unthemed mat-mdc-button-base']"
             ));
             previousMonthBtn.click();

             // Small wait to allow calendar to load previous month
             Thread.sleep(Duration.ofSeconds(4).toMillis());

             // Re-locate yesterday’s day in the *previous month’s calendar*
             
             WebElement yesterdays1 = driver.findElement(By.xpath("//div[text()=' " + day + " ']"));
             yesterdays1.click();

         } else {
             // Same month → just click yesterday’s date
            
             WebElement yesterdays1 = driver.findElement(By.xpath("//div[text()=' " + day + " ']"));
             yesterdays1.click();
         }
    }
    
    public void clickOnSacnTab() {
    	try {
    		sacnTab.click();
    		System.out.println("Clicked on sacnTab");
    	}catch (Exception e) {
			System.out.println("Not able to click on SacnTab "+ e);
		}
    }
    
    public void clickOnLiquidationTab() {
    	try {
    		liquidationTab.click();
    		System.out.println("Clicked on Liquidation Tab");
    	}catch (Exception e) {
			System.out.println("Not able to click on Liquidation Tab " + e);
		}
    }
    
    public void clickOnFilterIcon() {
    	try {
    		filterIcon.click();
    		System.out.println("Clicked on filterIcon");
    	}catch (Exception e) {
			System.out.println("Not able to click on filterIcon " + e);
		}
    }
    
    public void clickOnCustomDateRange() {
    	try {
    		CustomDateRange.click();
    		System.out.println("Clicked on CustomDateRange");
    	}catch (Exception e) {
			System.out.println("Not able to click on CustomDateRange " + e);
		}
    }
    
    public void clickOnToDateButton() {
    	try {
    		toDateButton.click();
    		System.out.println("Clicked on toDateButton");
    	}catch (Exception e) {
			System.out.println("Not able to click on toDateButton " + e);
		}
    }
    
    public void clickOnApplyButton() {
    	try {
    		applyButton.click();
    		System.out.println("Clicked on Apply Button");
    	}catch (Exception e) {
			System.out.println("Not able to click on Apply Button "+ e);
		}
    }
    
    public void clickOnDownloadTab() {
    	try {
    		downloadTab.click();
    		System.out.println("Clicked on downloadButton");
    	}catch (Exception e) {
			System.out.println("Not able to click on Download Button " + e);
		}
    }
    
    public void clickOnLiquidationLog() {
    	try {
    		liquidationLog.click();
    		System.out.println("Clicked on liquidationLog");
    	}catch (Exception e) {
			System.out.println("Not able to click On liquidationLog "+ e);
		}
    }
    
    public void clickOnDownloadButton() {
    	try {
    		downloadButton.click();
    		System.out.println("Clicked on download Button");
    	}catch (Exception e) {
			System.out.println("Not able to click on downloadButton "+ e);
		}
    }
}
