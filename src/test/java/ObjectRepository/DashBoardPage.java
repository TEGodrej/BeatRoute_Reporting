package ObjectRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
	
	@FindBy(xpath = "//a[@class='dropdown-toggle']//span[text()='Reports']")
	private WebElement reportTab;
	
	@FindBy(xpath = "//span[text()='Team Productivity & Adherence']")
	private WebElement adherenceTab;
	//div[@id="dateRangeFilterTrigger1"]
	@FindBy(xpath = "//div[contains(@class,'layout-date-filter')]//select[contains(@class,'daterange-dropdown')]")
	private WebElement dateChangeDropDown;
	
	@FindBy(xpath = "//option[text()='Custom Date Range']")
	private WebElement customDateOption;
	
	@FindBy(xpath = "//mat-icon[@fonticon='file_download']")
	private WebElement fileDownloadButton;
	
	@FindBy(xpath = "//span[text()='Attendance']")
	private WebElement attendanceTab;
	
	@FindBy(xpath = "//span[text()='Check-In/Out and Attendance ']")
	private WebElement checkInTab;
	
	@FindBy(xpath = "//li[3]//div[1]//div[1]")
	private WebElement attendanceSummaryDownloadTab;
	
	@FindBy(xpath = "//div[@class='btn-group layout-actionbtn open']//a[@title='Click To Generate File'][normalize-space()='Generate File']")
	private WebElement gen_File;
	
	@FindBy(xpath  = "//li[3]//div[1]//div[1]//i[contains(@class,'fa-arrow-down')]")
	private WebElement attendanceSum_Download;

	@FindAll({@FindBy(xpath = "//input[@type='password']"),@FindBy(id = "i0118")})
	private WebElement passwordTextfield;
	
	@FindAll({@FindBy(xpath = "//input[@type='submit']"), @FindBy(id = "idSIButton9")})
	private WebElement signInButton;
	
	public WebElement getdateChangeDropDown() {
		return dateChangeDropDown;
	}
	
	public WebElement getattendanceSummaryDownloadTab() {
		return attendanceSummaryDownloadTab;
	}
	
	
    LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
    LocalDate yesterday = today.minusDays(1);
    
    ZoneId indiaZone = ZoneId.of("Asia/Kolkata");

    LocalDate today1 = ZonedDateTime.now(indiaZone).toLocalDate();
    LocalDate targetDate = today.minusMonths(1).plusDays(1);
    LocalDate targetDate1 = today.minusDays(1);

    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
    
    DateTimeFormatter formatter1 =
            DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);

    String formattedDate = targetDate.format(formatter);
    String formattedDate1 = targetDate1.format(formatter1);
    
    
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
    
    public void AdherenceFromDate() {
//    	SimpleDateFormat sim= new SimpleDateFormat("MMM dd ,yyyy");
//    	String date = sim.format(new Date()); 
    	System.out.println("Looking for: " + formattedDate);  //span[text()='"+formattedDate+"']
    	WebElement F_date=driver.findElement(By.xpath("//div[@id='dateRangeFilterTrigger1']"));
    	F_date.click();
    }
    
    public void adherence_FromDate() {
    	WebElement fdate=driver.findElement(By.xpath("//span[text()=' "+day+" ']"));
    	fdate.click();
    	System.out.println("selected From-Date : " +day);
    }
    
    public void adherence_ToDate() {
    	WebElement Tdate=driver.findElement(By.xpath("//button[@aria-label='"+formattedDate1+"']"));
    	Tdate.click();
    	System.out.println("selected To-Date : " +formattedDate1);
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
    
    public void clickOnReportTab() {
    	try {
    		reportTab.click();
    		System.out.println("clicked on reportTab");
		} catch (Exception e) {
			System.out.println("Not able to click on reportTab "+e);
		}
    }
    
    public void clickOnAdherenceTab() {
    	try {
    		adherenceTab.click();
    		System.out.println("Clicked on adherenceTab ");
		} catch (Exception e) {
			System.out.println("Not able to click on adherenceTab "+e);
		}
    }
    
    public void clickOnDateChangeDropDown() {
    	try {
    		dateChangeDropDown.click();
    		System.out.println("Clicked on dateChangeDropDown");
		} catch (Exception e) {
			System.out.println("Not able to click on dateChangeDropDown "+e);
		}
    }
    
    public void clickOnCustomDateOption() {
    	try {
    		customDateOption.click();
    		System.out.println("clicked on customDateOption");
		} catch (Exception e) {
			System.out.println("Not able to click on customDateOption "+e);
		}
    }
    
    public void clickOnFileDownloadButton() {
    	try {
    		fileDownloadButton.click();
    		System.out.println("Clicked on fileDownloadButton ");
		} catch (Exception e) {
			System.out.println("Not able to click on fileDownloadButton "+e);
		}
    }
    
    public void clickOnAttendanceTab() {
    	try {
    		attendanceTab.click();
    		System.out.println("Clicked on Attendance Tab");
		} catch (Exception e) {
			System.out.println("Not able to click on Attendance Tab "+e);
		}
    }
    
    public void clickOncheckInTab() {
    	try {
    		checkInTab.click();
    		System.out.println("Clicked on checkIn Tab");
		} catch (Exception e) {
			System.out.println("Not able to click on checkIn Tab");
		}
    }
    
    public void clickOnAttendanceSummaryDownloadTab() {
    	try {
    		attendanceSummaryDownloadTab.click();
    		System.out.println("Clicked on Attendance Summary Download Tab ");
		} catch (Exception e) {
			System.out.println("Not able to click on Attendance Summary Download Tab "+e);
		}
    }
    
    
    public void clickOnGenerate_File() {
    	try {
    		gen_File.click();
    		System.out.println("Clicked on generate file Tab");
		} catch (Exception e) {
			System.out.println("Not able to click on generate file Tab "+e);
		}
    }
    
    public void clickOnAttendanceSum_Download() {
    	try {
//    		WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    		attendanceSum_Download = wait.until(ExpectedConditions.elementToBeClickable(attendanceSum_Download));
//    		element.click();
    		attendanceSum_Download.click();
    		System.out.println("Clicked on attendanceSum_Download");
		} catch (Exception e) {
			System.out.println("Not able to click on attendanceSum_Download "+e);
		}
    }
	public void sendkeyToPasswordTextfield(String key) {
    	try {
    		passwordTextfield.sendKeys(key);
    		System.out.println("Entered value in passwordTextfield");
		} catch (Exception e) {
			System.out.println("Not able to enter value in passwordTextfield "+e);
		}
    }
    
    public void clickOnsignInButton() {
    	try {
    		signInButton.click();
    		System.out.println("Clicked on signInButton");
		} catch (Exception e) {
			System.out.println("Not able to click on signInButton "+e);
		}
    }
}

