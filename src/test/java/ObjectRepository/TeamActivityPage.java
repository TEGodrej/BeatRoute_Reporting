package ObjectRepository;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TeamActivityPage {
	
	WebDriver driver;
	public TeamActivityPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//li[@class='highlight']//span[@class='item-label'][normalize-space()='Team Activity']")
	private WebElement teamActivityTab;
	
	@FindBy(xpath = "//body[1]/div[5]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/ul[1]/li[2]/div[1]/div[1]/i[1]")
	private WebElement activityLogTab;
	
	@FindBy(xpath = "//a[contains(@href,'/download/CSV/logRequest/type/activity')]")
	private WebElement generateFileButton;
	
	@FindBy(xpath = "(//i[@class='fa fa-arrow-down'])[2]")
	private WebElement downloadButton;
	
	@FindBy(xpath = "//strong[contains(text(), 'Your account is about to expire ')]")
	private WebElement errorMessage;
	
	@FindBy(xpath = "//a[@class='close']")
	private WebElement closeButton;
	
	@FindBy(xpath = "//span[text()='Users']")
	private WebElement userTab;
	
	@FindBy(xpath = "//a[@href='/team/user/downloadUserList']")
	private WebElement userDownload;
	
	@FindBy(xpath = "//a[@href='/download/CSV/getLast/type/attendanceSummary']")
	private WebElement attendanceDownloadTab;
	
	@FindBy(xpath = "(//i[@class='action-icon link-text fa fa-arrow-down'])[2]")
	private WebElement download_act;
//	
	@FindBy(xpath = "(//i[@class='fa fa-arrow-down'])[2]")
	private WebElement downloadLastFile;
//	====================================================================================================
	@FindBy(xpath = "//div[normalize-space()='Download Daily Attendance']")
	private WebElement dailyAttendanceButton;
	
	@FindBy(xpath = "//a[normalize-space()='User Day Start - Day End log']")
	private WebElement userDay;
	
	@FindBy(xpath = "//div[@id='main-content-tens']//li//li[2]//ul[1]//li[1]//a[1]")
	private WebElement generate_FileTab;
	
	@FindBy(xpath = "//a[@href='/download/CSV/getLast?type=startEndDayActivities']")
	private WebElement download_Button;
	
	public WebElement getcloseButton() {
		return closeButton;
	}
	
	public WebElement getuserDay() {
		return userDay;
	}
	
	public void clickOnTeamActivityTab() {
		try {
			teamActivityTab.click();
			System.out.println("Clicked on TeamActivity Tab");
		} catch (Exception e) {
			System.out.println("Not able to click on TeamActivity Tab "+e);
		}
	}
	
	public void clickOnactivityLogTab() {
		try {
			activityLogTab.click();
			System.out.println("clicked on ActivityLog Tab");
		} catch (Exception e) {
			System.out.println("Not able to click on ActivityLog Tab "+e);
		}
	}
	
	public void clickOngenerateFileButton() {
		try {
			generateFileButton.click();
			System.out.println("Clicked on GenerateFile Button");
		} catch (Exception e) {
			System.out.println("Not able to click on GenerateFile Button "+e);
		}
	}
	
	// Handle date
    Date date = new Date();
    SimpleDateFormat sim = new SimpleDateFormat("dd MMM, yyyy");
    String day1 = sim.format(date);
	public void clickOnChangedate() {
		try {

        WebElement dateChange = driver.findElement(By.xpath(
            "//div[@id='activityDateFilterTrigger1']//span[@class='printDate'][normalize-space()='" + day1 + "']"));
        dateChange.click();
        String Date=dateChange.getText();
        System.out.println("Clicked on "+Date);
		}
		catch (Exception e) {
			System.out.println("Not able to click on chage "+e);
		}
		
	}
	
	 LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
		
	    LocalDate yesterday = today.minusDays(1);
	    
	    LocalDate firstDayOfMonth = today.withDayOfMonth(1);
	    int day=yesterday.getDayOfMonth();
	
	public void clickOnFromDate() {
		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("d");
//	        String day1 = sdf.format(date);
//	        System.out.println(day1);
	        WebElement reportDate = driver.findElement(By.xpath("//a[@data-date='"+day+"']"));
	        reportDate.click();
	        
	        System.out.println("Selected From-date");
		} catch (Exception e) {
			System.out.println("Not able to select From-date "+e);
		}
	}
	
	public void clickOnToDate() {
		try {
//			SimpleDateFormat sdf1 = new SimpleDateFormat("d");
//	        String day2 = sdf1.format(date);
	        WebElement reportDate1 = driver.findElement(By.xpath("//a[@data-date='"+ day +"']"));
	        reportDate1.click();
	        
	        System.out.println("Selected To-date");
		} catch (Exception e) {
			System.out.println("Not able to select To-date "+e);
		}
	}
	
	public void downloadFile() {
		try {
			// Wait until 50 minutes later
	        LocalDateTime now = LocalDateTime.now();
	        LocalDateTime targetTime = now.plusMinutes(10);
	        
	        System.out.println("Please wit for 10 minutes ; download is under process");

	        System.out.println("Current Time: " + now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
	        System.out.println("Will click at: " + targetTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));

	        while (LocalDateTime.now().isBefore(targetTime)) {
	            Thread.sleep(1000);
	        }
	        
//	        activityLogTab.click();

	        // Click to download (file goes to /downloads inside project)
	        downloadButton.click();
	        System.out.println("clicked on download button");
		} catch (Exception e) {
			System.out.println("File has not been downloaded "+e);
		}
	}
	
	public void errorMessageDisplay() {
		
		try {
		    if (closeButton.isDisplayed()) {
		        closeButton.click();
		        System.out.println("Pop Up closed");
		    }
		} catch (Exception e) {
		    System.out.println("Pop-up not displayed");
		}
	}
	
	public void clickOnUserTab() {
		try {
			userTab.click();
			System.out.println("clicked on UserTab ");
		} catch (Exception e) {
			System.out.println("Not able to click on UserTab "+e);
		}
	}
	
	public void clickOnUserDownload() {
		try {
			userDownload.click();
			System.out.println("clicked on UserDownload");
		} catch (Exception e) {
			System.out.println("Not abke to click on UserDownload "+e);
		}
	}
	
	public void clickOnAttendanceDateChange() {
		try {
			WebElement date=driver.findElement(By.xpath("//div[@class='fromdate date']//span[text()='"+day1+"']"));
			date.click();
			System.out.println("Clicked on date change");
		} catch (Exception e) {
			System.out.println("Not able to click on date change "+e);
		}
	}
	public void select_FromDate() {
		try {
			WebElement FromDate=driver.findElement(By.xpath("//a[text()='"+day+"']"));
			FromDate.click();
			System.out.println("selcted "+day+" as From-date");
		} catch (Exception e) {
			System.out.println("Not able to select From-date "+e);
		}
	}
	
	public void select_ToDate() {
		try {
			WebElement ToDate=driver.findElement(By.xpath("//a[text()='"+day+"']"));
			ToDate.click();
			System.out.println("selcted "+day+" as To-date");
		} catch (Exception e) {
			System.out.println("Not able to select To date "+e);
		}
	}
	
	public void attendanceFileDownload() {
		try {
			// Wait until 50 minutes later
//	        LocalDateTime now = LocalDateTime.now();
//	        LocalDateTime targetTime = now.plusMinutes(10);
//	        
//	        System.out.println("Please wit for 10 minutes ; download is under process");
//
//	        System.out.println("Current Time: " + now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
//	        System.out.println("Will click at: " + targetTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
//
//	        while (LocalDateTime.now().isBefore(targetTime)) {
//	            Thread.sleep(1000);
//	        }
	        
//	        activityLogTab.click();

	        // Click to download (file goes to /downloads inside project)
	        attendanceDownloadTab.click();
	        System.out.println("clicked on Download button");
		} catch (Exception e) {
			System.out.println("File has not been Downloaded "+e);
		}
	}
	
	public void clickOnDownload_act() {
		
			try {
				download_act.click();
				System.out.println("Clicked on Download Activity");
			}catch (Exception e){
				
			System.out.println("not able to Click on Download Activity "+e);
			
		}
		 
	}
	
	public void clickOnDownloadFile() {
		try {
			WebElement act=driver.findElement(By.xpath("//i[@class='action-icon link-text fa fa-arrow-down']"));
			act.click();
//			downloadLastFile.click();
			System.out.println("Clicked on CPB_DownloadActivity");
		} catch (Exception e) {
			System.out.println("Not able to click on CPB_DownloadActivity  "+e);
		}
	}
	
	public void clickOnDailyAttendanceButton() {
		try {
			dailyAttendanceButton.click();
			System.out.println("Clicked on Daily_Attendance Button");
		} catch (Exception e) {
			System.out.println("Not able to click on Daily_Attendance Button "+e);
		}
	}
	
	public void clickOnGenerate_FileTab() {
		try {
			generate_FileTab.click();
			System.out.println("Clicked on Generate_FileTab");
		} catch (Exception e) {
			System.out.println("Not able to click on Generate_FileTab "+e);
		}
	}
	
	public void clickOnDownload_Button() {
		try {
			// Wait until 50 minutes later
	        LocalDateTime now = LocalDateTime.now();
	        LocalDateTime targetTime = now.plusMinutes(1);
	        
	        System.out.println("Please wit for 10 minutes ; download is under process");

	        System.out.println("Current Time: " + now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
	        System.out.println("Will click at: " + targetTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));

	        while (LocalDateTime.now().isBefore(targetTime)) {
	            Thread.sleep(1000);
	        }
	        
	        clickOnDailyAttendanceButton();
	        System.out.println("Clicked on Daily_Attendance Button");
	        
	        Actions action= new Actions(driver);
			action.moveToElement(getuserDay()).perform();
	        
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(65));

	        By downloadBtn = By.xpath("//a[@href='/download/CSV/getLast?type=startEndDayActivities']");

	        WebElement element = wait.until(
	                ExpectedConditions.presenceOfElementLocated(downloadBtn)
	        );

	        // Click to download (file goes to /downloads inside project)
	        wait.until(ExpectedConditions.elementToBeClickable(element));

	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].click();", element);
	        System.out.println("clicked on Download button");
		} catch (Exception e) {
			System.out.println("File has not been Downloaded "+e);
		}
	}
		
	
}
