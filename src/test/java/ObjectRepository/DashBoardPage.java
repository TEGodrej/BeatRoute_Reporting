package ObjectRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	private WebElement scanTab;
	
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
	
    LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
	
    LocalDate yesterday = today.minusDays(1);
    
    LocalDate firstDayOfMonth = today.withDayOfMonth(1);
    int day=yesterday.getDayOfMonth();
    
    public void clickOnFromDate() throws InterruptedException {
    	 if (today.isEqual(firstDayOfMonth)) {
             // Click the "previous month" button
             WebElement previousMonthBtn = driver.findElement(By.xpath(
                 "//button[@aria-label='Previous month']//span[@class='mat-mdc-focus-indicator']"
             ));
             previousMonthBtn.click();

             // Small wait to allow calendar to load previous month
//             Thread.sleep(1000);

             // Re-locate yesterday’s day in the *previous month’s calendar*
             
             WebElement yesterdays = driver.findElement(By.xpath("//div[text()='" + day + "']"));
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
             wait.until(ExpectedConditions.elementToBeClickable(yesterdays));
             yesterdays.click();
             System.out.println(yesterdays.getText());

         } else {
             // Same month → just click yesterday’s date
            
             WebElement yesterdays = driver.findElement(By.xpath("//div[text()=' " + day + " ']"));
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
             wait.until(ExpectedConditions.elementToBeClickable(yesterdays));
             yesterdays.click();
             System.out.println(yesterdays.getText());
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
//             Thread.sleep(1000);

             // Re-locate yesterday’s day in the *previous month’s calendar*
             
             WebElement yesterdays1 = driver.findElement(By.xpath("//div[text()=' " + day + " ']"));
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
             wait.until(ExpectedConditions.elementToBeClickable(yesterdays1));
             yesterdays1.click();
             System.out.println(yesterdays1.getText());

         } else {
             // Same month → just click yesterday’s date
            
             WebElement yesterdays1 = driver.findElement(By.xpath("//div[text()=' " + day + " ']"));
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
             wait.until(ExpectedConditions.elementToBeClickable(yesterdays1));
             yesterdays1.click();
             System.out.println(yesterdays1.getText());
         }
    }
    
    public WebElement getToDateButton() {
		return toDateButton;
	}

	public void setToDateButton(WebElement toDateButton) {
		this.toDateButton = toDateButton;
	}

	public WebElement getScanTab() {
		return scanTab;
	}

	public void setSacnTab(WebElement sacnTab) {
		this.scanTab = sacnTab;
	}

	public WebElement getLiquidationTab() {
		return liquidationTab;
	}

	

	public WebElement getFilterIcon() {
		return filterIcon;
	}

	public void setFilterIcon(WebElement filterIcon) {
		this.filterIcon = filterIcon;
	}

	public WebElement getCustomDateRange() {
		return CustomDateRange;
	}

	public void setCustomDateRange(WebElement customDateRange) {
		CustomDateRange = customDateRange;
	}

	public WebElement getApplyButton() {
		return applyButton;
	}

	public void setApplyButton(WebElement applyButton) {
		this.applyButton = applyButton;
	}

	public WebElement getDownloadTab() {
		return downloadTab;
	}

	public void setDownloadTab(WebElement downloadTab) {
		this.downloadTab = downloadTab;
	}

	public WebElement getLiquidationLog() {
		return liquidationLog;
	}

	public void setLiquidationLog(WebElement liquidationLog) {
		this.liquidationLog = liquidationLog;
	}

	public WebElement getDownloadButton() {
		return downloadButton;
	}

	public void setDownloadButton(WebElement downloadButton) {
		this.downloadButton = downloadButton;
	}

	public void clickOnScanTab() {
    	try {
    		scanTab.click();
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
